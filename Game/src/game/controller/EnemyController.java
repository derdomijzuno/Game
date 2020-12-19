package game.controller;

import java.awt.event.KeyEvent;

import game.core.Position;
import game.main.Game;
import game.main.Handler;

public class EnemyController implements Controller {

	private boolean up;
	private boolean down;
	private boolean left;
	private boolean right;

	Handler handler;

	public EnemyController(Handler handler) {
		this.handler = handler;
	}

	@Override
	public boolean isRequestingUp() {
		return up;
	}

	@Override
	public boolean isRequestingDown() {
		return down;
	}

	@Override
	public boolean isRequestingLeft() {
		return left;
	}

	@Override
	public boolean isRequestingRight() {
		return right;
	}

	public void moveToTarget(Position target, Position current) {
		double deltaX = target.getX() - current.getX();
		double deltaY = target.getY() - current.getY();

		up = deltaY < 0 && Math.abs(deltaY - 1) > Position.PROXIMITY_RANGE;
		right = deltaX > 0 && Math.abs(deltaX + 1) > Position.PROXIMITY_RANGE;
		down = deltaY > 0 && Math.abs(deltaY + 1) > Position.PROXIMITY_RANGE;
		left = deltaX < 0 && Math.abs(deltaX - 1) > Position.PROXIMITY_RANGE;
	}

	public void stop() {
		up = false;
		down = false;
		left = false;
		right = false;
	}

}
