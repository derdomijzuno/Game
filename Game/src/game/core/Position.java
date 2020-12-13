package game.core;

public class Position {
	
	private double x,y;
	
	public Position(double x, double y) {
		this.x=x;
		this.y=y;
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
		return (int) (x / 50);
	}

	public int gridY() {
		return (int) (y / 50);
	}
	
	public static Position ofGridPosition(int gridX, int gridY) {
		return new Position(gridX * 50 + 50 / 2, gridY * 50 + 50 / 2);
	}
	
	public void apply(Motion movement) {
		Vector2D vector = movement.getVector();
		x += vector.getX();
		y += vector.getY();
	}
	
}
