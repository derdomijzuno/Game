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

		float[][] noiseMap = new float[map.getTiles().length][map.getTiles()[0].length];

		float[][] noiseMap1 = getVNoise(map, 10, 40);
		float[][] noiseMap2 = getVNoise(map, 5, 20);
		float[][] noiseMap3 = getVNoise(map, 1, 10);

		for (int x = 0; x < map.getTiles().length; x++) {
			for (int y = 0; y < map.getTiles()[0].length; y++) {
				noiseMap[x][y] = noiseMap1[x][y] + noiseMap2[x][y] + noiseMap3[x][y];
			}
		}

		float lowestNoise = getLowestNoise(noiseMap);

		for (int x = 0; x < map.getTiles().length; x++) {
			for (int y = 0; y < map.getTiles()[0].length; y++) {
				noiseMap[x][y] -= lowestNoise;
			}
		}

		float highestNoise = getHighestNoise(noiseMap);

		for (int x = 0; x < map.getTiles().length; x++) {
			for (int y = 0; y < map.getTiles()[0].length; y++) {
				noiseMap[x][y] = noiseMap[x][y] / highestNoise;
			}
		}

		for (int x = 0; x < map.getTiles().length; x++) {
			for (int y = 0; y < map.getTiles()[0].length; y++) {
				map.getTile(x, y).setVNoise(noiseMap[x][y]);
			}
		}

	}

	private float getLowestNoise(float[][] noiseMap) {

		float lowest = noiseMap[0][0];

		for (int x = 0; x < noiseMap.length; x++) {
			for (int y = 0; y < noiseMap[0].length; y++) {
				if (lowest > noiseMap[x][y]) {
					lowest = noiseMap[x][y];
				}
			}
		}

		return lowest;
	}

	private float getHighestNoise(float[][] noiseMap) {

		float highest = noiseMap[0][0];

		for (int x = 0; x < noiseMap.length; x++) {
			for (int y = 0; y < noiseMap[0].length; y++) {
				if (highest < noiseMap[x][y]) {
					highest = noiseMap[x][y];
				}
			}
		}

		return highest;
	}

	private float[][] getVNoise(GameMap map, int interval, int range) {

		int w = map.getTiles().length;
		int h = map.getTiles()[0].length;

		int minNoise = -range;
		int maxNoise = range;

		Random r = new Random();

		float[][] noise = new float[w][h];

		for (int x = 0; x < noise.length / interval; x++) {
			for (int y = 0; y < noise[0].length / interval; y++) {
				noise[x * interval][y * interval] = r.nextInt(maxNoise - minNoise) + minNoise;
			}
		}

		for (int x = 0; x < noise.length; x++) {
			for (int y = 0; y < noise[0].length; y++) {
				if (x % interval < 1 && x < noise.length - interval) {
					for (int i = 0; i < interval / 2; i++) {
						noise[x + i + 1][y] = (noise[x + i][y] * 0.75f) + (noise[x + interval - i][y] * 0.25f);
						noise[x + interval - i - 1][y] = (noise[x + i][y] * 0.25f)
								+ (noise[x + interval - i][y] * 0.75f);
					}
				}
			}
		}

		for (int x = 0; x < noise.length; x++) {
			for (int y = 0; y < noise[0].length; y++) {
				if (y % interval < 1 && y < noise[0].length - interval) {
					for (int i = 0; i < interval / 2; i++) {
						noise[x][y + i + 1] = (noise[x][y + i] * 0.75f) + (noise[x][y + interval - i] * 0.25f);
						noise[x][y + interval - i - 1] = (noise[x][y + i] * 0.25f)
								+ (noise[x][y + interval - i] * 0.75f);
					}
				}
			}
		}

		return noise;
	}

}
