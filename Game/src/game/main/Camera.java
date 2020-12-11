package game.main;

public class Camera {

	private float x, y;

	public Camera(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public void tick(GameObject temp) {
		x += (temp.getX() - x) - 1280 / 2 * 0.05f;
		y += (temp.getY() - y) - 720 / 2 * 0.05f;

		if (x <= 0)
			x = 0;
		if (x >= 1280)
			x = 1280;
		if (y <= 0)
			y = 0;
		if (y >= 720)
			y = 720;
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
