package igdc125.game.tiles;

import igdc125.core.Resources;
import igdc125.core.Sprite;
import igdc125.core.Vector;
import igdc125.game.MapObject;

class Saw extends MapObject {
	private Sprite sprite;
	private SawThrow emmiter;

	public Saw(SawThrow emmiter) {
		this.emmiter = emmiter;
		sprite = new Sprite(Resources.getSprite("spritesheet.png", 18, 0, 9, 9));
		addChild(sprite);
	}

	@Override
	public void update(float delta) {
		if(Vector.len(x, y, getMap().player.x, getMap().player.y) < 5) {
			System.out.println("HIT");
		}
		x += 60 * delta;
		rotation += (Math.PI * 6) * delta;
		super.update(delta);
	}

	@Override
	public void tileCollision(Tile tile, float dx, float dy) {
		if (tile != emmiter) {
			getMap().removeChild(this);
		}
	}
}

public class SawThrow extends Tile {
	private float _interval = 0.5f;
	private float _time = 0.0f;

	public SawThrow() {
		super();

		addChild(new Sprite(Resources.getSprite("tileset.png", 18, 0, 9, 9)));
	}

	private void emit() {
		Saw saw = new Saw(this);
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
