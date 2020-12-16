package game.main;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import game.input.KeyInput;
import game.input.MouseInput;
import game.states.GameState;
import game.states.Menu;
import game.states.StateID;

public class Game extends Canvas implements Runnable {

	// Initialization

	Handler handler;
	GameState game;
	Menu menu;
	public static StateID gs;

	public static final int tileSize = 64;
	public static final int WindowWidth = 1280;
	public static final int WindowHeight = 720;

	private void init() {

		handler = new Handler();

		menu = new Menu(handler);
		game = new GameState(handler);
		game.init();

		gs = StateID.Menu;

		new Window(this, WindowWidth, WindowHeight);

		MouseInput mi = new MouseInput(handler);
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(mi);
		this.addMouseMotionListener(mi);

	}

	// RENDERING
	private void render() {

		BufferStrategy bs = this.getBufferStrategy();

		if (bs == null) {
			this.createBufferStrategy(2);
			return;
		}

		Graphics g = bs.getDrawGraphics();

		// START RENDERING

		if (gs == StateID.Game)
			game.render(g);
		if (gs == StateID.Menu)
			menu.render(g);

		// STOP RENDERING

		g.dispose();
		bs.show();

	}

	// UPDATE

	private void tick() {

		if (gs == StateID.Game)
			game.tick();
		if (gs == StateID.Menu)
			menu.tick();
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
