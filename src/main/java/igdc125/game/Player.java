package igdc125.game;

import igdc125.core.Resources;
import igdc125.core.Sprite;

public class Player extends Mob {
	private Sprite sprite;

	public Player() {
		sprite = new Sprite(Resources.getSprite("spritesheet.png", 0, 0, 9, 18), 9, 9, 2);
		addChild(sprite);
	}

	@Override
	public void update(float delta) {
		moveSpeed.x = Input.getVector().x * 60;

		if (Input.getUp() == 1 && !inJump()) {
			jump();
		}

		if (moveSpeed.x != 0) {
			this.scale.x = moveSpeed.x < 0 ? -1 : 1;
		}
		
		super.update(delta);
	}
}
