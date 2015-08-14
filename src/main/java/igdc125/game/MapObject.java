package igdc125.game;

import igdc125.core.Container;

public class MapObject extends Container {

	public int boundWidth = Tile.SIZE;
	public int boundHeight = Tile.SIZE;

	public int tileX() {
		return (int) Math.floor(x / Tile.SIZE);
	}

	public int tileY() {
		return (int) Math.floor(y / Tile.SIZE);
	}

	public void tilesCollision() {
		for (int i = tileX() - 1; i <= tileX() + 1; i++) {
			for (int j = tileY() - 1; j <= tileY() + 1; j++) {
				if ((i == tileX()) && (j == tileY())) {
					continue;
				}

				Tile tile = ((Map) parent).getTile(i, j);

				if (tile != null) {
					float dx = x - tile.x;
					float dy = y - tile.y;

					float mindx = (boundWidth / 2f) + (Tile.SIZE / 2f);
					float mindy = (boundHeight / 2f) + (Tile.SIZE / 2f);

					if ((Math.abs(dx) < mindx) && (Math.abs(dy) < mindy)) {
						float offsetx = mindx - Math.abs(dx);
						float offsety = mindy - Math.abs(dy);

						if (Math.abs(offsetx) > Math.abs(offsety)) {
							if (dy < 0) {
								y -= offsety;
							} else {
								y += offsety;
							}
						} else {
							if (dx < 0) {
								x -= offsetx;
							} else {
								x += offsetx;
							}
						}
					} else {
						System.out.printf("%s %f %f %f %f\n", tile, dx, dy, mindx, mindy);
					}
				}
			}
		}
	}

	@Override
	public void update(float delta) {
		tilesCollision();
		super.update(delta);
	}
}
