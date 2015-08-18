package igdc125.game;

import igdc125.core.Container;
import igdc125.game.maps.Map;
import igdc125.game.tiles.Tile;

public abstract class MapObject extends Container {

	public int boundWidth = Tile.SIZE;
	public int boundHeight = Tile.SIZE;

	public int tileX() {
		return (int) Math.floor((x + Tile.SIZE / 2) / Tile.SIZE);
	}

	public int tileY() {
		return (int) Math.floor((y + Tile.SIZE / 2) / Tile.SIZE);
	}

	public void setTile(int tileX, int tileY) {
		x = tileX * Tile.SIZE;
		y = tileY * Tile.SIZE;
	}

	public Map getMap() {
		return (Map) parent;
	}

	public abstract void tileCollision(Tile tile, float dx, float dy);

	public void tilesCollision() {
		for (int i = tileX() - 1; i <= tileX() + 1; i++) {
			for (int j = tileY() - 1; j <= tileY() + 1; j++) {
				if (parent == null)
					return;
				Tile tile = ((Map) parent).getTile(i, j);

				if ((tile != null) && (!tile.passable)) {
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
								tileCollision(tile, 0, -offsety);
							} else {
								y += offsety;
								tileCollision(tile, 0, offsety);
							}
						} else {
							if (dx < 0) {
								x -= offsetx;
								tileCollision(tile, -offsetx, 0);
							} else {
								x += offsetx;
								tileCollision(tile, offsetx, 0);
							}
						}
					}
				}
			}
		}
	}

	@Override
	public void update(float delta) {
		tilesCollision();
		
		if (getMap() == null)
			return;

		Tile[] touched = new Tile[] { getMap().getTile(tileX() - 1, tileY()), getMap().getTile(tileX() + 1, tileY()),
				getMap().getTile(tileX(), tileY() - 1), getMap().getTile(tileX(), tileY() + 1) };

		for (Tile tile : touched) {
			if (tile != null) {
				tile.touch(this, tile.cellX - tileX(), tile.cellY - tileY(), delta);
			}
		}

		super.update(delta);
	}

}
