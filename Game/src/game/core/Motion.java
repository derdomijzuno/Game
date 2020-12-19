package game.core;

import game.controller.Controller;

public class Motion {

	private Vector2D vector;
	private double speed;

	public Motion(double speed) {
		this.speed = speed;
		this.vector = new Vector2D(0, 0);
	}

	public void update(Controller controller, double speed) {
		int velX = 0;
		int velY = 0;

		if (controller.isRequestingUp())
			velY--;
		if (controller.isRequestingDown())
			velY++;
		if (controller.isRequestingLeft())
			velX--;
		if (controller.isRequestingRight())
			velX++;

		vector = new Vector2D(velX, velY);
		vector.normalize();
		vector.multiply(speed);
	}

	public Vector2D getVector() {
		return vector;
	}

	public boolean isMoving() {
		return vector.length() > 0;
	}

}
