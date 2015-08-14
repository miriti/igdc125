package igdc125.game;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import igdc125.core.Container;
import igdc125.core.Resources;

public class Map extends Container {
	public Tile[][] map;
	private Mob mob;

	public Map() {
		super();
		initFromBitmap(Resources.getImage("map.png"));

		mob = new Mob();
		addChild(mob);
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
					tile.setCell(i, j);
					addChild(tile);
				}
			}
		}
	}

	@Override
	public void update(float delta) {
		x = 32 - mob.x;
		y = 32 - mob.y;
		super.update(delta);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			mob.x--;
			break;
		case KeyEvent.VK_RIGHT:
			mob.x++;
			break;
		case KeyEvent.VK_UP:
			mob.y--;
			break;
		case KeyEvent.VK_DOWN:
			mob.y++;
			break;
		}
	}

	public Tile getTile(int x, int y) {
		if ((x >= 0) && (x < map.length) && (y >= 0) && (y < map[0].length)) {
			return map[x][y];
		}

		return null;

	}
}
