package igdc125.game.tiles;

import java.awt.Graphics2D;

import igdc125.core.Container;
import igdc125.core.Resources;
import igdc125.core.Vector;

class CheckpintText extends Container {
	private float _time = 0f;

	@Override
	public void draw(Graphics2D g) {
		Resources.font.drawString(g, -20, -8, "Checkpoint");
		super.draw(g);
	}

	@Override
	public void update(float delta) {
		if (_time >= 2f) {
			parent.removeChild(this);
		} else {
			y -= 5 * delta;
			_time += delta;
		}
	}
}

public class Checkpoint extends Tile {
	private boolean _checked = false;
	private double _phase = 0;

	public Checkpoint() {
		passable = true;
	}

	@Override
	public void update(float delta) {
		if (!_checked) {
			if (!getMap().player.dead) {
				if (Vector.len(x, y, getMap().player.x, getMap().player.y) < 15) {
					CheckpintText t = new CheckpintText();
					t.x = x;
					t.y = y;
					getMap().addChild(t);
					_checked = true;
					getMap().playerSpawn.x = cellX;
					getMap().playerSpawn.y = cellY;
				}
			}
		} else {
			rotation = (float) ((-Math.PI / 32) + (Math.sin(_phase) * Math.PI / 16));
			_phase += (Math.PI / 4) * delta;
		}
		super.update(delta);
	}
}
