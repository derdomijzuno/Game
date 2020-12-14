package game.main;

public class Camera {

	private float x, y;

	public Camera(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public void tick(GameObject temp) {
		x += ((temp.getPos().getX() - x) - Game.WindowWidth / 2) * 0.05f;
		y += ((temp.getPos().getY() - y) - Game.WindowHeight / 2) * 0.05f;

		if (x <= 0)
			x = 0;
		if (x >= Game.WindowWidth)
			x = Game.WindowWidth;
		if (y <= 0)
			y = 0;
		if (y >= Game.WindowHeight)
			y = Game.WindowHeight;
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
