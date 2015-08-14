package igdc125.game.tiles;

import igdc125.core.Resources;
import igdc125.core.Sprite;
import igdc125.game.MapObject;

public class TravalatorTile extends Tile {
	private Sprite anim;

	public TravalatorTile() {
		anim = new Sprite(Resources.getSprite("tileset.png", 9, 0, 9, 27), 9, 9, 5);
		anim.invert();
		addChild(anim);
	}

	@Override
	public void touch(MapObject mapObject, int dx, int dy, float delta) {
		if (dy == 1) {
			mapObject.x += 10 * delta;
		}
	}
}
