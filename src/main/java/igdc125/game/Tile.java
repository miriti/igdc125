package igdc125.game;

import igdc125.core.Container;
import igdc125.core.Resources;
import igdc125.core.Sprite;

public class Tile extends Container {
	public static final int SIZE = 9;

	public Tile() {
		super();

		addChild(new Sprite(Resources.getSprite("tileset.png", 0, 0, 9, 9)));
	}

}
