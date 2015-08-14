package igdc125.game;

import igdc125.core.Vector;
import igdc125.game.tiles.Tile;

public class Mob extends MapObject {
	protected Vector moveSpeed = new Vector();
	private boolean _inJump;

	public void jump() {
		_inJump = true;
		moveSpeed.y = -60;
	}

	public boolean inJump() {
		return _inJump;
	}
	
	@Override
	public void tileCollision(Tile tile, float dx, float dy) {
		if (dy > 0) {
			moveSpeed.y = 0;
			_inJump = false;
		}
	}
}
