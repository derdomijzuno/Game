package game.map;

import java.awt.image.BufferedImage;

import game.entity.ID;
import game.main.Handler;

public class Tileset {

	private Tile[][] ts;
	private int w,h;

	public Tileset(BufferedImage image) {
		initTileset(image);
		w=image.getWidth();
		h=image.getHeight();
	}

	private void initTileset(BufferedImage image) {
		ts = new Tile[image.getWidth()][image.getHeight()];

		int w = image.getWidth();
		int h = image.getHeight();

//		for (int xx = 0; xx < w; xx++) {
//			for (int yy = 0; yy < h; yy++) {
//				int pixel = image.getRGB(xx, yy);
//				int red = (pixel >> 16) & 0xff;
//				int green = (pixel >> 8) & 0xff;
//				int blue = (pixel) & 0xff;
//
//				ts[xx][yy] = new Tile(xx, yy, ID.Blank);
//
//				if (red == 0 && green == 0 && blue == 255)
//					ts[xx][yy].setId(ID.Player);
//				if (red == 255 && green == 0 && blue == 0)
//					ts[xx][yy].setId(ID.Enemy);
//				if (red == 0 && green == 0 && blue == 0)
//					ts[xx][yy].setId(ID.Obstacle);
//
//			}
//		}
	}

	public Tile getTile(int x, int y) {
		return ts[x][y];
	}

	public Tile[][] getTileset() {
		return ts;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

}
