package game.core;

import game.main.Game;

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
		return (int) (x / Game.tileSize);
	}

	public int gridY() {
		return (int) (y / Game.tileSize);
	}
	
	public static Position ofGridPosition(int gridX, int gridY) {
		return new Position(gridX * Game.tileSize + Game.tileSize / 2, gridY * Game.tileSize + Game.tileSize / 2);
	}
	
	public void apply(Motion movement) {
		Vector2D vector = movement.getVector();
		
		x += vector.getX();
		y += vector.getY();
	}
	
}
