package igdc125.game.tiles;

import igdc125.core.Resources;
import igdc125.core.Sprite;
import igdc125.game.Game;
import igdc125.game.MapObject;
import igdc125.game.Player;
import igdc125.game.Win;

public class Exit extends Tile {
	public Exit() {
		passable = true;

		addChild(new Sprite(Resources.getSprite("tileset.png", 0, 9, 9, 9)));
	}

	@Override
	public void touch(MapObject mapObject, int dx, int dy, float delta) {
		if (mapObject instanceof Player) {
			((Game) getMap().parent).setState(new Win());
		}
		super.touch(mapObject, dx, dy, delta);
	}
}
