package igdc125.game.tiles;

import igdc125.core.Resources;
import igdc125.core.Sprite;
import igdc125.game.MapObject;
import igdc125.game.Mob;

public class Press extends Tile {
	private Sprite anim;

	private float _pressTime = 0f;
	private boolean _deadly = false;

	public Press() {
		super();
		anim = new Sprite(Resources.getSprite("tileset.png", 36, 0, 27, 18), 9, 18, 24).yoyo(true);
		anim.anchor.y = 0.25f;
		anim.repeats = 1;
		addChild(anim);
	}

	@Override
	public void update(float delta) {
		if (_pressTime <= 0) {
			anim.play();
			_pressTime = 1.5f;
			_deadly = true;
		} else {
			_pressTime -= delta;
			_deadly = !anim.isPaused();
		}
		super.update(delta);
	}

	@Override
	public void touch(MapObject mapObject, int dx, int dy, float delta) {
		if (_deadly) {
			if (mapObject instanceof Mob) {
				((Mob) mapObject).kill();
			}
		}
		super.touch(mapObject, dx, dy, delta);
	}
}
