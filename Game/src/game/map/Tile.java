package game.map;

public class Tile {

	private int x, y;
	private boolean isWalkable;

	public Tile(int x, int y, boolean isWalkable) {
		this.x = x;
		this.y = y;
		this.isWalkable = isWalkable;
	}

	// Getters and Setters

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isWalkable() {
		return isWalkable;
	}

	public void setWalkable(boolean isWalkable) {
		this.isWalkable = isWalkable;
	}

}
