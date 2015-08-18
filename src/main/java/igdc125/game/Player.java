package igdc125.game;

import java.awt.event.KeyEvent;

import igdc125.core.Resources;
import igdc125.core.Sprite;
import igdc125.interpolation.Interpolate;
import igdc125.interpolation.Sine;
import sun.security.jgss.spnego.SpNegoCredElement;

public class Player extends Mob {
	private Sprite sprite;
	private Interpolate speedInt;
	private Interpolate speedDown;
	private Interpolate speed;

	public Player() {
		sprite = new Sprite(Resources.getSprite("spritesheet.png", 0, 0, 9, 18), 9, 9, 2).pause().setCurrentFrame(1);
		speedInt = new Sine().init(0f, 1f, 0.5f);
		speedDown = new Sine().init(1f, 0f, 0.8f);
		addChild(sprite);
	}

	@Override
	public void jump(float value) {
		sprite.setCurrentFrame(0);
		super.jump(value);
	}

	@Override
	protected void stopJump() {
		sprite.setCurrentFrame(1);
		super.stopJump();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			speedInt.change(0, -1);
			speedInt.changeValue(moveSpeed.x);
			speed = speedInt;
		}

		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			speedInt.change(0, 1);
			speedInt.changeValue(moveSpeed.x);
			speed = speedInt;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			speedDown.change(1, 0);
			speedDown.changeValue(moveSpeed.x);
			speed = speedDown;
		}

	}

	@Override
	public void update(float delta) {

		if (speed != null) {
			moveSpeed.x = speed.next(delta) * 60;
		}

		if (Input.getUp() == 1 && !inJump()) {
			jump();
		}

		if (moveSpeed.x != 0) {
			scale.x = moveSpeed.x < 0 ? -1 : 1;
		}

		super.update(delta);
	}
}
