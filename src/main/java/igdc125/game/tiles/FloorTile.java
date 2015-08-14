package igdc125.game.tiles;

import igdc125.core.Resources;
import igdc125.core.Sprite;

public class FloorTile extends Tile {
	public FloorTile() {
		addChild(new Sprite(Resources.getSprite("tileset.png", 0, 0, 9, 9)));
	}
}
