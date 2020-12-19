package game.gfx;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class SpriteSet {

	private Map<String, BufferedImage> animationSheets;

	public SpriteSet() {
		this.animationSheets = new HashMap<>();
	}

	public void addSheet(String name, BufferedImage animationSheet) {
		animationSheets.put(name, animationSheet);
	}

	public BufferedImage get(String name) {
		return animationSheets.get(name);
	}

}
