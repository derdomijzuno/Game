package game.map;

import java.awt.image.BufferedImage;

import game.gfx.SpriteLibrary;

public class Tile {

	private int x, y;
	private boolean isWalkable;
	private String tileName;
	private BufferedImage sprite;
	

	public Tile(int x, int y, boolean isWalkable, String tileName, SpriteLibrary spriteLibrary) {
		this.x = x;
		this.y = y;
		this.isWalkable = isWalkable;
		this.tileName = tileName;
		this.sprite = spriteLibrary.getTile(tileName);
	}
	
	public BufferedImage getSprite() {
		return sprite;
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

	public String getTileName() {
		return tileName;
	}

	public void setTileName(String tileName) {
		this.tileName = tileName;
	}

	public int getMoveCost() {
		int moveCost = 0;
		switch (tileName) {
		case "dirt":
			moveCost = 25;
			break;
		default:
			moveCost = 1000;
		}
		;
		return moveCost;
	}

}
