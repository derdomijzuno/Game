package games.core;

import games.main.Game;

public class Position {

	private double x, y;

	public static int PROXIMITY_RANGE = 5;

	public Position(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public int intX() {
		return (int) Math.round(x);
	}

	public int intY() {
		return (int) Math.round(y);
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public int gridX() {
		return (int) (x / Game.tileSize);
	}

	public int gridY() {
		return (int) (y / Game.tileSize);
	}

	public static Position ofGridPosition(int gridX, int gridY) {
		return new Position(gridX * Game.tileSize, gridY * Game.tileSize);
	}

	public void apply(Motion movement) {
		Vector2D vector = movement.getVector();

		x += vector.getX();
		y += vector.getY();
	}

	public boolean isInRangeOf(Position position) {
		return Math.abs(x - position.getX()) < Position.PROXIMITY_RANGE
				&& Math.abs(y - position.getY()) < Position.PROXIMITY_RANGE;
	}

}
