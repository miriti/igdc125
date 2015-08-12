package igdc125.game;

import java.awt.event.KeyEvent;

import igdc125.core.Container;
import igdc125.core.Resources;
import igdc125.core.Sprite;
import igdc125.interpolation.Chain;
import igdc125.interpolation.Interpolate;
import igdc125.interpolation.Linear;
import igdc125.interpolation.Sine;

public class Intro extends Container {
	private Sprite sprite;
	private Chain int_size;
	private Chain int_rotation;

	public Intro() {
		super();
		sprite = new Sprite(Resources.getImage("igdc.png"));
		sprite.x = 32;
		sprite.y = 32;
		sprite.scale.set(2, 2);
		addChild(sprite);

		int_size = new Chain();
		int_size.add(new Sine().init(0.0f, 0.8f, 2.5f));
		int_size.add(new Sine().init(0.8f, 1.0f, 2.0f));
		int_size.add(new Linear().init(1.0f, 1.0f, 0.5f));
		int_size.add(new Linear().init(1.0f, 0.0f, 1.0f));

		int_rotation = new Chain();
		int_rotation.add(new Sine().init((float) (Math.PI * 20f), 0, 2.5f));
		int_rotation.add(new Linear().init(0.0f, 0.0f, 2.5f));
		int_rotation.add(new Sine().init(0.0f, (float) (Math.PI * 20f), 1f));
	}

	@Override
	public void keyPressed(KeyEvent e) {
		skip();
	}

	@Override
	public void update(float delta) {
		super.update(delta);
		int_size.next(delta);
		sprite.scale.set(int_size.getCurrent(), int_size.getCurrent());
		sprite.rotation = int_rotation.next(delta);

		if (int_rotation.isFinished()) {
			skip();
		}
	}

	public void skip() {
		((Game) parent).setState(new Menu());
	}
}
