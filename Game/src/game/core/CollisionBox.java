package game.core;

import java.awt.Rectangle;

public class CollisionBox {

	private Rectangle bounds;
	private int x, y, width, height;

	public CollisionBox(Rectangle bounds) {
		this.bounds = bounds;
		x = (int) bounds.getX();
		y = (int) bounds.getY();
		width = (int) bounds.getWidth();
		height = (int) bounds.getHeight();
	}

	public boolean collidesWith(CollisionBox other) {
		return bounds.intersects(other.bounds);
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public Rectangle getBoundsTop() {
		return new Rectangle(x + width / 10, y, width - width / 5, height / 15);
	}

	public Rectangle getBoundsLeft() {
		return new Rectangle(x, y + height / 10, width / 15, height - height / 5);
	}

	public Rectangle getBoundsRight() {
		return new Rectangle(x + width - (width / 15), y + height / 10, width / 15, height - height / 5);
	}

	public Rectangle getBoundsBottom() {
		return new Rectangle(x + width / 10, y + height - (height / 15), width - width / 5, height / 15);
	}

}
