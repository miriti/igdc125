package igdc125.core;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class Resources {
	private static Resources _instance = null;
	public static Sound sndBonus;
	private HashMap<String, BufferedImage> _stored = new HashMap<>();
	public static BitmapFont font;

	private BufferedImage _getImage(String path) {
		if (_stored.containsKey(path)) {
			return _stored.get(path);
		}
		try {
			BufferedImage image = ImageIO.read(getClass().getClassLoader().getResourceAsStream(path));
			_stored.put(path, image);
			return image;
		} catch (IOException e) {
			return null;
		}
	}

	public static BufferedImage getImage(String path) {
		if (_instance == null) {
			_instance = new Resources();
		}

		return _instance._getImage(path);
	}

	public static BufferedImage getSprite(String path, int x, int y, int w, int h) {
		BufferedImage _img = getImage(path);

		return _img.getSubimage(x, y, w, h);
	}

	public static void preload() {
		font = new BitmapFont();
		sndBonus = new Sound("snd/bonus.wav");
	}
}
