package igdc125.game;

import java.awt.event.KeyEvent;

import igdc125.core.Resources;
import igdc125.core.Sprite;
import igdc125.game.tiles.Tile;

public class Player extends Mob {
	private Sprite sprite;

	private float _speed = 0;
	private float _respTime = 0f;

	public Player(boolean fromRespawn) {
		if (fromRespawn) {
			_respTime = 1f;
		}
		sprite = new Sprite(Resources.getSprite("spritesheet.png", 0, 0, 9, 18), 9, 9, 2).pause().setCurrentFrame(1);
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

		if ((dead) && (dead_time > 0.5f)) {
			respawn();
		}
		super.keyPressed(e);
	}

	@Override
	public void update(float delta) {

		if (_respTime > 0) {

			visible = ((int) _respTime * 100) % 25 == 0;

			_respTime -= delta;

			if (_respTime <= 0) {
				visible = true;
			}
		} else {
			if (Input.getVector().x != 0) {
				moveSpeed.x = _speed;
				if (Input.getVector().x == 1) {
					if (_speed < 60) {
						_speed += 180 * delta;
					}
				} else {
					if (_speed > -60) {
						_speed -= 180 * delta;
					}
				}

			} else {
				_speed = 0;
				if (moveSpeed.x != 0) {
					moveSpeed.x *= 0.9f;
				}
			}

			if (Input.getUp() == 1 && !inJump()) {
				jump();
			}

			if (moveSpeed.x != 0) {
				scale.x = Input.getVector().x < 0 ? -1 : 1;
			}
		}

		super.update(delta);
	}

	@Override
	public void tileCollision(Tile tile, float dx, float dy) {
		if (dx != 0) {
			_speed = 0;
		}
		super.tileCollision(tile, dx, dy);
	}

	@Override
	public void respawn() {
		getMap().respawnPlayer();
	}
}
