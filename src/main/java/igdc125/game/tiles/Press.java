package igdc125.game.tiles;

import igdc125.core.Resources;
import igdc125.core.Sprite;

public class Press extends Tile {
	private Sprite anim;

	public Press() {
		super();
		anim = new Sprite(Resources.getSprite("tileset.png", 36, 0, 27, 18), 9, 18, 15).yoyo(true);
		anim.anchor.y = 0.25f;
		addChild(anim);
	}
}
