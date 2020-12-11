package game.main;

import java.awt.Canvas;

public class Game extends Canvas implements Runnable{

	
	public Game() {
		new Window(this, 1280, 720);
	}
	
	private Thread thread;
	private boolean isRunning = false;  
	
	private void start() {
		isRunning = true;
		thread = new Thread(this);
		thread.start();
	}
	
	private void stop() {
		try {
			thread.stop();
		} catch (Exception e) {
			System.out.println(e);
		}
		isRunning = false;
	}
	
	@Override
	public void run() {
		
		
		
	}
	
	public static void main(String[] args) {
		new Game();
	}

}
