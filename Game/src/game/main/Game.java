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
import game.objects.ID;
import game.objects.Player;

public class Game extends Canvas implements Runnable {

	// Initialization

	Handler handler;
	Camera camera;
	A_Visualization a_;

	private BufferedImage level = null;

	private void init() {
		
		new Window(this, 1280, 720);
		handler = new Handler();
		camera = new Camera(0, 0);
		
		
		
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(new MouseInput(handler));

		BufferedImageLoader loader = new BufferedImageLoader();
		level = loader.loadImage("/level.png/");
		
		
//		a_ = new A_Visualization(level);
		
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

		g2d.translate(-camera.getX(), -camera.getY());

		handler.render(g);
//		a_.render(g);

		g2d.translate(camera.getX(), camera.getY());

		// STOP RENDERING

		g.dispose();
		bs.show();

	}

	// UPDATE

	private void tick() {

		for (int i = 0; i < handler.objects.size(); i++) {
			if (handler.objects.get(i).getId() == ID.Player) {
				camera.tick(handler.objects.get(i));
			}
		}

		handler.tick();
//		a_.tick();
	}

	private void loadLevel(BufferedImage image) {
		int w = image.getWidth();
		int h = image.getHeight();

		for (int xx = 0; xx < w; xx++) {
			for (int yy = 0; yy < h; yy++) {
				int pixel = image.getRGB(xx, yy);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;

				if (red == 0 && green == 0 && blue == 0)
					handler.addObject(new Player(xx * 50, yy * 50, ID.Player));
			}
		}
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
		double amountOfTicks = 60.0;
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
