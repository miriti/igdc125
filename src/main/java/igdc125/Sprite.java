package igdc125;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Sprite extends Container {
	private BufferedImage _image;

	public Sprite(BufferedImage image) {
		_image = image;
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(_image, -_image.getWidth() / 2, -_image.getHeight() / 2, null);
	}
}
