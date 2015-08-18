package igdc125.game.tiles;

import igdc125.core.Resources;
import igdc125.core.Sprite;
import igdc125.game.MapObject;
import igdc125.game.Mob;

public class Spring extends Tile {
	private Sprite anim;
	private float _time = 0;

	public Spring() {
		super();

		anim = new Sprite(Resources.getSprite("tileset.png", 18, 9, 18, 18), 9, 18, 10).pause();
		anim.anchor.y = 0.75f;
		addChild(anim);
	}

	@Override
	public void update(float delta) {
		if (_time > 0) {
			_time -= delta;
			
			if (_time <= 0) {
				anim.setCurrentFrame(0);
			}
		}
		super.update(delta);
	}

	@Override
	public void touch(MapObject mapObject, int dx, int dy, float delta) {
		if (mapObject instanceof Mob) {
			if (dy > 0) {
				((Mob) mapObject).jump(110);
				anim.setCurrentFrame(1);
				_time = 0.3f;
			}
		}
	}
}
