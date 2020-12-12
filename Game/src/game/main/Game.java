package game.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import game.ai.A_Visualization;
import game.gfx.BufferedImageLoader;
import game.input.KeyInput;
import game.input.MouseInput;
import game.map.Tileset;
import game.objects.Enemy;
import game.objects.ID;
import game.objects.Obstacle;
import game.objects.Player;
import game.states.GameState;
import game.states.Menu;

public class Game extends Canvas implements Runnable {

	// Initialization

	Handler handler;
	Camera camera;
	A_Visualization a_;
	Menu menu;
	public static GameState gs;

	public static Tileset ts, aStarTs;

	private BufferedImage aStar = null;
	private BufferedImage level = null;

	private void init() {

		new Window(this, 1280, 720);
		handler = new Handler();
		camera = new Camera(0, 0);
		gs = GameState.Menu;

		menu = new Menu(handler);

		MouseInput mi = new MouseInput(handler);

		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(mi);
		this.addMouseMotionListener(mi);

		BufferedImageLoader loader = new BufferedImageLoader();
		aStar = loader.loadImage("/test.png/");
		aStarTs = new Tileset(aStar);

		a_ = new A_Visualization(aStarTs);

		level = loader.loadImage("/level.png/");

		ts = new Tileset(level);
		loadTileset(aStarTs);
	}

	private void loadTileset(Tileset ts) {
		for (int x = 0; x < ts.getTileset().length; x++) {
			for (int y = 0; y < ts.getTileset()[x].length; y++) {

				if (ts.getTile(x, y).getId() == ID.Player) {
					handler.addObject(new Player(x, y, ID.Player));
				}
				if (ts.getTile(x, y).getId() == ID.Enemy) {
					handler.addObject(new Enemy(x, y , ID.Enemy));
				}
				if (ts.getTile(x, y).getId() == ID.Obstacle) {
					handler.addObject(new Obstacle(x, y, ID.Obstacle));
				}
			}
		}
	}

	// RENDERING
	private void render() {

		BufferStrategy bs = this.getBufferStrategy();

		if (bs == null) {
			this.createBufferStrategy(2);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;

		// START RENDERING

		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, 1280, 720);

		if (gs == GameState.Game)
			render_game(g, g2d);
		if (gs == GameState.Menu)
			menu.render(g);
		if (gs == GameState.aStar)
			a_.render(g);

		if (handler.isDebug()) {
			g.setColor(Color.RED);
			String mousePos = "MX: " + handler.getMx() + " | MY: " + handler.getMy();
			g.drawString(mousePos, 20, 20);
		}

		// STOP RENDERING

		g.dispose();
		bs.show();

	}

	private void render_game(Graphics g, Graphics2D g2d) {
		g2d.translate(-camera.getX(), -camera.getY());

		handler.render(g);

		g2d.translate(camera.getX(), camera.getY());

		if (handler.isDebug()) {
			g.setColor(Color.RED);
			String mousePos = "MX: " + handler.getMx() + " | MY: " + handler.getMy();
			g.drawString(mousePos, 20, 20);
		}
	}

	// UPDATE

	private void tick() {

		if (gs == GameState.Game)
			tick_game();
		if (gs == GameState.Menu)
			menu.tick();
		if (gs == GameState.aStar)
			a_.tick();

	}

	private void tick_game() {
		for (int i = 0; i < handler.objects.size(); i++) {
			if (handler.objects.get(i).getId() == ID.Player) {
				camera.tick(handler.objects.get(i));
			}
		}

		handler.tick();
	}

	// START

	public Game() {
		start();
	}

	private Thread thread;
	private boolean isRunning = false;

	private void start() {
		isRunning = true;
		thread = new Thread(this);
		thread.start();
	}

	// STOP

	private void stop() {
		try {
			thread.stop();
		} catch (Exception e) {
			System.out.println(e);
		}
		isRunning = false;
	}

	// Gameloop
	@Override
	public void run() {

		init();
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 5.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		int updates = 0;
		while (isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println(updates + " TICKS, FPS " + frames);
				frames = 0;
				updates = 0;

			}
		}
		stop();
	}

	public static void main(String[] args) {
		new Game();
	}

}
