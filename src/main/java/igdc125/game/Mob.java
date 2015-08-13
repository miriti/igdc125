package igdc125.game;

import igdc125.core.Resources;
import igdc125.core.Sprite;

public class Mob extends MapObject {
	
	private Sprite sprite;

	public Mob() {
		sprite = new Sprite(Resources.getSprite("spritesheet.png", 0, 0, 9, 9));
		addChild(sprite);
	}
}
