package game.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BufferedImageLoader {


	public static BufferedImage loadImage(String path) {
		try {
			return ImageIO.read(BufferedImageLoader.class.getResource(path));
		} catch (IOException e) {
			System.out.println("Could not load image from path: " + path);
		}
		return null;
	}

}
