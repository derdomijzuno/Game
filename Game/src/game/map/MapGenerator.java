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

		float[][] noise1 = getVNoise(map, 5, -40, 40);
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

	private float[][] getVNoise(GameMap map, int interval, int minNoise, int maxNoise) {

		int w = map.getTiles().length;
		int h = map.getTiles()[0].length;

		Random r = new Random();

		float[][] noise = new float[w][h];

		for (int x = 0; x < noise.length / interval; x++) {
			for (int y = 0; y < noise[x].length / interval; y++) {
				noise[x * interval][y * interval] = r.nextInt(maxNoise - minNoise) + minNoise;
			}
		}

//		for (int i = 1; i <= interval; i++) {
//			float x1 = noise[(i - 1) * interval][0];
//			float x2 = noise[i * interval][0];
//			float dif = x2 - x1;
//
//			float rise = dif / (interval);
//
//			System.out.println("x1: " + x1 + " x2: " + x2 + " dif: " + dif + " rise: " + rise);
//
//			for (int i2 = 1; i2 < interval; i2++) {
////				noise[(i - 1) * interval + i2][0] = noise[(i - 1) * interval][0] + rise * i2;
////				System.out.println(noise[(i-1) * interval][0] + rise * i2);
//			}
//		}

//		for (int i = 0; i < interval; i++) {
//			float[] f1 = new float[interval + 1];
//			for (int j = 0; j < interval + 1; j++) {
//				f1[j] = noise[i * interval + j][0];
//			}
//
//		}

		return noise;
	}

}
