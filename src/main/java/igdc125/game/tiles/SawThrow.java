package igdc125.game.tiles;

import igdc125.core.Resources;
import igdc125.core.Sprite;
import igdc125.core.Vector;
import igdc125.game.MapObject;

class Saw extends MapObject {
	private Sprite sprite;
	private SawThrow emmiter;
	private int _direction;

	public Saw(SawThrow emmiter, int dir) {
		_direction = dir;
		this.emmiter = emmiter;
		sprite = new Sprite(Resources.getSprite("spritesheet.png", 18, 0, 9, 9));
		addChild(sprite);
	}

	@Override
	public void update(float delta) {
		if (Vector.len(x, y, getMap().player.x, getMap().player.y) < 5) {
			getMap().player.kill();
		}
		x += (55f * _direction) * delta;
		rotation += (Math.PI * 6) * delta;
		super.update(delta);
	}

	@Override
	public void tileCollision(Tile tile, float dx, float dy) {
		if (tile != emmiter) {
			// TODO Some particle effect
			getMap().removeChild(this);
		}
	}
}

public class SawThrow extends Tile {
	private float _interval = 0.75f;
	private float _time = 0.0f;
	private int _direction;

	public SawThrow(int dir) {
		super();

		_direction = dir;

		addChild(new Sprite(Resources.getSprite("tileset.png", 18, 0, 9, 9)));
	}

	private void emit() {
		Saw saw = new Saw(this, _direction);
		saw.x = x;
		saw.y = y;
		getMap().addChild(saw);
	}

	@Override
	public void update(float delta) {
		if (_time >= _interval) {
			emit();
			_time = 0;
		} else {
			_time += delta;
		}
		super.update(delta);
	}
}
