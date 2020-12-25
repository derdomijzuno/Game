package game.map;

import java.awt.image.BufferedImage;

import game.core.Position;
import game.gfx.SpriteLibrary;
import game.main.Game;

public class GameMap {

	private Tile[][] map;
	private SpriteLibrary spriteLibrary;

	public GameMap(BufferedImage image, SpriteLibrary spriteLibrary) {
		this.spriteLibrary = spriteLibrary;
		map = new Tile[image.getWidth()][image.getHeight()];

		for (int x = 0; x < map.length; x++) {
			for (int y = 0; y < map[0].length; y++) {
				map[x][y] = new Tile(x, y, true, "", spriteLibrary);
			}
		}
	}

	public void initializeTiles() {

		for (int x = 0; x < map.length; x++) {
			for (int y = 0; y < map[0].length; y++) {
				if (getTile(x, y).getVNoise() < 0.5) {
					map[x][y] = new Tile(x, y, false, "water", spriteLibrary);
				}
				if (getTile(x, y).getVNoise() > 0.5 && getTile(x, y).getVNoise() < 0.8) {
					map[x][y] = new Tile(x, y, true, "grass", spriteLibrary);
				}
				if (getTile(x, y).getVNoise() > 0.8 && getTile(x, y).getVNoise() < 1) {
					map[x][y] = new Tile(x, y, true, "dirt", spriteLibrary);
				}

			}
		}

	}

	public boolean gridWithinBounds(int gridX, int gridY) {
		return gridX >= 0 && gridX < map.length && gridY >= 0 && gridY < map[0].length;
	}

	public Tile[][] getTiles() {
		return map;
	}

	public Position getRandomPosition() {
		double x = Math.random() * map.length * Game.tileSize;
		double y = Math.random() * map[0].length * Game.tileSize;

		return new Position(x, y);

	}

	public Tile getTile(int x, int y) {
		return map[x][y];
	}

}
