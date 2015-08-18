package igdc125.game.tiles;

import igdc125.core.Resources;
import igdc125.core.Sprite;
import igdc125.core.Vector;

public class Bonus extends Tile {
	private Sprite anim;

	public Bonus() {
		super();
		passable = true;
		anim = new Sprite(Resources.getSprite("spritesheet.png", 9, 0, 9, 18), 9, 9, 3);
		addChild(anim);
	}

	@Override
	public void update(float delta) {
		if (Vector.len(x, y, getMap().player.x, getMap().player.y) < 7) {
			Resources.sndBonus.play();
			selfRemove();
		}
		super.update(delta);
	}
}
