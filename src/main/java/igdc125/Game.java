package igdc125;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Game extends Container {

	int phase = 0;
	private BufferedImage image;

	public Game() {
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/test.png"));
			System.out.println(image);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void update(float delta) {
		super.update(delta);
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Palette.PALETTE[0]);
		g.fillRect(0, 0, 64, 64);

		for (int i = 0; i < 64; i++) {
			for (int j = 0; j < 64; j++) {
				g.setColor(Palette.PALETTE[(i * j) % 8]);
				g.drawRect(i, j, 1, 1);
			}
		}

		g.rotate(1);

		g.drawImage(image, 1, 1, null);
	}

}
