package igdc125.game;

import igdc125.core.Vector;
import igdc125.game.tiles.Tile;
import igdc125.interpolation.Interpolate;
import igdc125.interpolation.Linear;

public abstract class Mob extends MapObject {
	protected Vector moveSpeed = new Vector();
	public Vector externalSpeed = new Vector();
	private boolean _inJump;
	public boolean dead;
	protected float dead_time = 0f;
	private Interpolate _rotation;
	private float _dead_y = 0;

	public void jump() {
		jump(65);
	}

	public void jump(float value) {
		_inJump = true;
		moveSpeed.y = -value;
	}

	public boolean inJump() {
		return _inJump;
	}

	protected void stopJump() {
		moveSpeed.y = 0;
		_inJump = false;
	}

	@Override
	public void update(float delta) {
		if (!dead) {
			moveSpeed.y += Game.GRAVITY * delta;

			x += (moveSpeed.x + externalSpeed.x) * delta;
			y += (moveSpeed.y + externalSpeed.y) * delta;

			externalSpeed.mult(0.9f);

			super.update(delta);
		} else {
			rotation = _rotation.next(delta);
			y += _dead_y * delta;
			_dead_y += 2f;
			dead_time += delta;
			if (_rotation.isFinished())
				respawn();
		}
	}

	@Override
	public void tileCollision(Tile tile, float dx, float dy) {
		if (dx == 0) {
			if (dy > 0) {
				moveSpeed.y = 0;
			}

			if (dy < 0) {
				stopJump();
			}
		}
	}

	public void kill() {
		if (!dead) {
			dead = true;
			_rotation = new Linear().init(0f, (float) Math.PI * 200f, 2f);
			_dead_y = -40f;
		}
	}

	public abstract void respawn();
}
