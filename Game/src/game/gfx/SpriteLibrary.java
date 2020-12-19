package game.gfx;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class SpriteLibrary {

	public Map<String, SpriteSet> units;
	public Map<String, BufferedImage> tiles;

	public SpriteLibrary() {
		units = new HashMap<>();
		tiles = new HashMap<>();
		loadSpritesFromDisk();
	}

	private void loadSpritesFromDisk() {
		loadUnits("/sprites/units");
		loadTiles("/sprites/tiles");
	}

	private void loadTiles(String path) {
		String[] imagesInFolder = getImagesInFolder(path);

		for (String fileName : imagesInFolder) {
			tiles.put(fileName.substring(0, fileName.length() - 4),
					BufferedImageLoader.loadImage(path + "/" + fileName));
		}
	}

	private void loadUnits(String path) {
		String[] folderNames = getFolderNames(path);

		for (String folderName : folderNames) {
			SpriteSet spriteSet = new SpriteSet();
			String pathToFolder = path + "/" + folderName;
			String[] sheetsInFolder = getImagesInFolder(path + "/" + folderName);

			for (String sheetName : sheetsInFolder) {
				spriteSet.addSheet(sheetName.substring(0, sheetName.length() - 4),
						BufferedImageLoader.loadImage(pathToFolder + "/" + sheetName));
			}

			units.put(folderName, spriteSet);
		}
	}

	private String[] getImagesInFolder(String basePath) {
		URL resource = SpriteLibrary.class.getResource(basePath);
		File file = new File(resource.getFile());
		return file.list((current, name) -> new File(current, name).isFile());
	}

	private String[] getFolderNames(String basePath) {
		URL resource = SpriteLibrary.class.getResource(basePath);
		File file = new File(resource.getFile());
		return file.list((current, name) -> new File(current, name).isDirectory());
	}

	public SpriteSet getUnit(String name) {
		return units.get(name);
	}

	public BufferedImage getTile(String name) {
		return tiles.get(name);
	}
}
