package igdc125.game;

import java.awt.image.BufferedImage;

import igdc125.core.Container;
import igdc125.core.Resources;
import igdc125.game.tiles.Tile;

public class Map extends Container {
	public Tile[][] map;
	public Mob player;

	public Map() {
		super();
		player = new Player();

		initFromBitmap(Resources.getImage("map.png"));

		addChild(player);
	}

	public void initFromBitmap(BufferedImage bitmap) {
		int w = bitmap.getWidth();
		int h = bitmap.getHeight();
		map = new Tile[w][h];

		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				Tile tile = Tile.factory(bitmap.getRGB(i, j), i, j, this);
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
		super.update(delta);
		x = 32 - player.x;
		y = 32 - player.y;
	}

	public Tile getTile(int x, int y) {
		if ((x >= 0) && (x < map.length) && (y >= 0) && (y < map[0].length)) {
			return map[x][y];
		}

		return null;

	}
}
