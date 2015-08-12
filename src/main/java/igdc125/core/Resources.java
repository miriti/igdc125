package igdc125.core;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class Resources {
	private static Resources _instance = null;
	private HashMap<String, BufferedImage> _stored = new HashMap<>();
	private Font _font;

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

	public Font _getFont() {
		if (_font == null) {
			try {
				_font = Font
						.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("visitor2.ttf"))
						.deriveFont(14f);
			} catch (FontFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(1);
			}
			return _font;
		} else {
			return _font;
		}
	}

	public static Font getFont() {
		if (_instance == null) {
			_instance = new Resources();
		}

		return _instance._getFont();
	}
}
