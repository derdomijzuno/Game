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
		initializeTiles(image);
	}

	private void initializeTiles(BufferedImage image) {

		for (int x = 0; x < map.length; x++) {
			for (int y = 0; y < map[0].length; y++) {
				int pixel = image.getRGB(x, y);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;

				if (red == 0 && green == 0 && blue == 0) {
					map[x][y] = new Tile(x, y, false, "wall", spriteLibrary);
				} else if (red == 255 && green == 127 && blue == 39) {
					map[x][y] = new Tile(x, y, true, "dirt", spriteLibrary);
				} else {
					map[x][y] = new Tile(x, y, true, "normal", spriteLibrary);
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
//		double x = Game.tileSize * 1;
//		double y = Game.tileSize * 4;

		return new Position(x, y);

	}

	public Tile getTile(int x, int y) {
		return map[x][y];
	}

}
