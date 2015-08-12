package igdc125;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Resources {
	private static Resources _instance = null;

	private BufferedImage _getImage(String path) {
		try {
			return ImageIO.read(getClass().getClassLoader().getResourceAsStream(path));
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
}
