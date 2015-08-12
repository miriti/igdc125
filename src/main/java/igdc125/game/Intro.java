package igdc125.game;

import igdc125.core.Container;
import igdc125.core.Resources;
import igdc125.core.Sprite;
import igdc125.interpolation.Chain;
import igdc125.interpolation.Interpolate;
import igdc125.interpolation.Sine;

public class Intro extends Container {
	private Sprite sprite;
	private Interpolate int_size;
	private Interpolate int_rotation;

	public Intro() {
		super();
		sprite = new Sprite(Resources.getImage("igdc.png"));
		sprite.x = 32;
		sprite.y = 32;
		sprite.scale.set(2, 2);
		addChild(sprite);

		int_size = new Chain().add(new Sine().init(0, 1, 2.5f)).add(new Sine().init(1, 1.2f, 2f));

		int_rotation = new Sine().init((float) (Math.PI * 20f), 0, 2.5f);
	}

	@Override
	public void update(float delta) {
		super.update(delta);
		int_size.next(delta);
		sprite.scale.set(int_size.getCurrent(), int_size.getCurrent());
		sprite.rotation = int_rotation.next(delta);

		if (int_size.isFinished()) {
			((Game) parent).setState(new Menu());
		}
	}
}
