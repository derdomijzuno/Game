package game.controller;

import java.awt.event.KeyEvent;

import game.main.Handler;

public class PlayerController implements Controller{

	Handler handler;
	
	public PlayerController(Handler handler) {
		this.handler=handler;
	}
	
	@Override
	public boolean isRequestingUp() {
		return handler.isKeyPressed(KeyEvent.VK_W);
	}

	@Override
	public boolean isRequestingDown() {
		return handler.isKeyPressed(KeyEvent.VK_S);
	}

	@Override
	public boolean isRequestingLeft() {
		return handler.isKeyPressed(KeyEvent.VK_A);
	}

	@Override
	public boolean isRequestingRight() {
		return handler.isKeyPressed(KeyEvent.VK_D);
	}

}
