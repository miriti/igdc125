package igdc125.game;

import igdc125.core.Vector;
import igdc125.game.tiles.Tile;

public class Mob extends MapObject {
	protected Vector moveSpeed = new Vector();
	public Vector externalSpeed = new Vector();
	private boolean _inJump;

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
		moveSpeed.y += Game.GRAVITY * delta;

		x += moveSpeed.x * delta;
		y += moveSpeed.y * delta;

		super.update(delta);
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
}
