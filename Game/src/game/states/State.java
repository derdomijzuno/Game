package game.states;

import java.awt.Graphics;

import game.main.Handler;

public abstract class State {

	protected Handler handler;
	public State(Handler handler) {
		this.handler = handler;
	}
	
	protected abstract void tick();
	protected abstract void render(Graphics g);
	
}
