package igdc125;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Game extends Container {

	public Game() {
		super();
		addChild(new Intro());
	}

}
