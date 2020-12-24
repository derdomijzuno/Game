package game.map;

import java.util.Random;

import game.gfx.SpriteLibrary;

public class MapGenerator {

	public void generateMap(GameMap map, SpriteLibrary spriteLibrary) {

		int w = map.getTiles().length;
		int h = map.getTiles()[0].length;

		Random generator = new Random();
		Object[] values = spriteLibrary.tiles.keySet().toArray();

		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				String randomValue = values[generator.nextInt(values.length)].toString();
				boolean isWater = false;
				if ((randomValue == values[2])) {
					isWater = true;
				}
				map.getTiles()[x][y] = new Tile(x, y, !isWater, randomValue, spriteLibrary);
			}
		}

	}

	public void generateMap2(GameMap map) {

		float[][] noise1 = getVNoise(map, 5, 40);
//		float[][] noise2 = getVNoise(map, 5, -20, 20);
//		float[][] noise3 = getVNoise(map, 2, -10, 10);
//		float[][] noise4 = getVNoise(map, 1, -5, 5);
//		float[][] noise5 = getVNoise(map, 3, -3, 3);
//		float[][] noise6 = getVNoise(map, 1, -1, 1);

		for (int x = 0; x < map.getTiles().length; x++) {
			for (int y = 0; y < map.getTiles()[0].length; y++) {
				map.getTile(x, y).setVNoise(noise1[x][y]);// + noise2[x][y] +
															// noise3[x][y] +
															// noise4[x][y]
				// + noise5[x][y]
				// + noise6[x][y]);
			}
		}

	}

	private float[][] getVNoise(GameMap map, int interval, int range) {

		int w = map.getTiles().length;
		int h = map.getTiles()[0].length;

		int minNoise = -range;
		int maxNoise = range;

		Random r = new Random();

		float[][] noise = new float[w][h];

		for (int x = 0; x < noise.length / interval + 1; x++) {
			for (int y = 0; y < noise[0].length / interval + 1; y++) {
				noise[x * interval][y * interval] = r.nextInt(maxNoise - minNoise) + minNoise;
			}
		}
		// + interval < noise.length
		for (int x = 0; x < noise.length; x++) {
			for (int y = 0; y < noise[0].length; y++) {
				if (x % interval < interval / 2) {
					for (int i = 0; i < interval / 2; i++) {
						noise[x + 1][y] = (float) (noise[x][y] * 0.75 + noise[x + interval - i][y] * 0.25);
						System.out.println((x + 1) + " " + (x + interval - i));
					}
				}
				if (x % interval > interval / 2) {
					for (int i = 0; i < interval / 2; i++) {
//						noise[x + 1][y] = (float) (noise[x][y] * 0.25 + noise[x + interval - i][y] * 0.75);
//						System.out.println((x + 1) + " " + (x + interval - i));
					}
				}
			}
//			break;
		}

		return noise;
	}

}
