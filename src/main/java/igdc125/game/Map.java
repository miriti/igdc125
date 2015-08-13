package igdc125.game;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import igdc125.core.Container;
import igdc125.core.Resources;

public class Map extends Container {
	public Tile[][] map;

	public Map() {
		super();
		initFromBitmap(Resources.getImage("map.png"));
		addChild(new Mob());
	}

	public void initFromBitmap(BufferedImage bitmap) {
		int w = bitmap.getWidth();
		int h = bitmap.getHeight();
		map = new Tile[w][h];
		
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				Tile tile = Tile.factory(bitmap.getRGB(i, j));
				map[i][j] = tile;

				if (tile != null) {
					tile.x = i * Tile.SIZE;
					tile.y = j * Tile.SIZE;
					addChild(tile);
				}
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			x--;
			break;
		case KeyEvent.VK_RIGHT:
			x++;
			break;
		case KeyEvent.VK_UP:
			y--;
			break;
		case KeyEvent.VK_DOWN:
			y++;
			break;
		}
	}
}
