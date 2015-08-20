package igdc125.game;

import igdc125.core.Container;
import igdc125.core.Resources;
import igdc125.core.Sprite;

class WinDie extends Container {
	private Sprite sprite;
	private float _speed;

	public WinDie() {
		sprite = new Sprite(Resources.getImage("win.png"));
		scale.set((float) (0.5f + Math.random() * 2));

		_speed = (float) (10f + Math.random() * 30);
		addChild(sprite);
	}

	@Override
	public void update(float delta) {
		y += _speed * delta;

		if (y >= 64 + 30f) {
			parent.removeChild(this);
		}
		super.update(delta);
	}
}

public class Win extends Container {
	private float _time = 0f;

	@Override
	public void update(float delta) {

		if (_time <= 0f) {
			WinDie wd = new WinDie();
			wd.x = (float) (Math.random() * 64f);
			wd.y = -15f;
			addChild(wd);
			_time = 0.2f;
		} else {
			_time -= delta;
		}

		super.update(delta);
	}
}
