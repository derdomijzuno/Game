package game.map;

import game.objects.ID;

public class Tile {

	private int x, y;
	private ID id;

	public Tile(int x, int y, ID id) {
		this.x = x;
		this.y = y;
		this.id = id;
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

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}
	
	

}
