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
		return new Rectangle(x + width / 8, y, width - width / 4, height / 10);
	}

	public Rectangle getBoundsLeft() {
		return new Rectangle(x, y + height / 8, width / 10, height - height / 4);
	}

	public Rectangle getBoundsRight() {
		return new Rectangle(x + width - (width / 15), y + height / 8, width / 10, height - height / 4);
	}

	public Rectangle getBoundsBottom() {
		return new Rectangle(x + width / 8, y + height - (height / 15), width - width / 4, height / 10);
	}

}
