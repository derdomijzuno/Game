package game.map;

import java.awt.image.BufferedImage;

public class GameMap {

	private Tile[][] map;

	public GameMap(BufferedImage image) {
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

				if (red == 0 && green == 0 && blue == 255) {
					map[x][y] = new Tile(x, y, true);
				}
				if (red == 255 && green == 0 && blue == 0) {
					map[x][y] = new Tile(x, y, true);
				}
				if (red == 0 && green == 0 && blue == 0) {
					map[x][y] = new Tile(x, y, false);
				}else {
					map[x][y] = new Tile(x, y, true);
				}
			}
		}

	}
	
	public boolean gridWithinBounds(int gridX, int gridY) {
        return gridX >= 0 && gridX < map.length
                && gridY >= 0 && gridY < map[0].length;
    }

	public Tile[][] getTiles() {
		return map;
	}

}
