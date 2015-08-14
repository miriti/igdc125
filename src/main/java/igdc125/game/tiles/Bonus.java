package igdc125.game.tiles;

import igdc125.core.Resources;
import igdc125.core.Sprite;
import igdc125.core.Vector;
import igdc125.game.MapObject;
import igdc125.game.Player;
import igdc125.game.maps.Map;

public class Bonus extends Tile {
	private Sprite anim;

	public Bonus() {
		super();
		passable = true;
		anim = new Sprite(Resources.getSprite("spritesheet.png", 9, 0, 9, 18), 9, 9, 3);
		addChild(anim);
	}

	@Override
	public void touch(MapObject mapObject, int dx, int dy, float delta) {
		if (mapObject instanceof Player) {

			if (Vector.len(x, y, mapObject.x, mapObject.y) < 5) {
				Resources.sndBonus.play();
				selfRemove();
			}
		}
		super.touch(mapObject, dx, dy, delta);
	}
}
