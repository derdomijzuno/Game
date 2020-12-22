package game.main;

import game.entity.GameObject;
import game.states.GameState;

public class Camera {

	private float x, y;
	private Handler handler;

	public Camera(float x, float y, Handler handler) {
		this.x = x;
		this.y = y;
		this.handler = handler;
	}

	public void tick() {

//		x += ((temp.getPos().getX() - x) - Game.WindowWidth / 2) * 0.05f;
//		y += ((temp.getPos().getY() - y) - Game.WindowHeight / 2) * 0.05f;

		if (handler.getCamx() < Game.tileSize * 2) {
			x -= 10;
		}
		if (handler.getCamx() > Game.WindowWidth - Game.tileSize * 2) {
//			x += ((handler.getMx() - x) - Game.WindowWidth / 2) * 0.05f;
			x += 10;
		}

		if (handler.getCamy() < Game.tileSize * 2) {
			y -= 10;
		}
		if (handler.getCamy() > Game.WindowHeight - Game.tileSize * 2) {
			y += 10;
//			y += ((handler.getMy() - y) - Game.WindowHeight / 2) * 0.05f;
		}

		if (x <= 0)
			x = 0;
		if (x >= GameState.map.getTiles().length * Game.tileSize - Game.WindowWidth)
			x = GameState.map.getTiles().length * Game.tileSize - Game.WindowWidth;
		if (y <= 0)
			y = 0;
		if (y >= GameState.map.getTiles()[0].length * Game.tileSize - Game.WindowHeight)
			y = GameState.map.getTiles()[0].length * Game.tileSize - Game.WindowHeight;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

}
