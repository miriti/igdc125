package igdc125.game.tiles;

import igdc125.core.Resources;
import igdc125.core.Sprite;
import igdc125.game.MapObject;
import igdc125.game.Mob;

public class TravalatorTile extends Tile {
	private Sprite anim;
	private int _direction;

	public TravalatorTile(int dir) {
		_direction = dir;
		anim = new Sprite(Resources.getSprite("tileset.png", 9, 0, 9, 27), 9, 9, 5);
		if (dir == 1) {
			anim.invert();
		}
		addChild(anim);
	}

	@Override
	public void touch(MapObject mapObject, int dx, int dy, float delta) {
		if (dy == 1) {
			if (mapObject instanceof Mob)
				((Mob) mapObject).externalSpeed.x = 55f * _direction;
		}
	}
}
