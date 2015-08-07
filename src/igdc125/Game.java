package igdc125;

import java.awt.Color;
import java.awt.Graphics;

public class Game implements IGameObject {

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(Graphics g) {
		for (int i = 0; i < 64; i++) {
			for (int j = 0; j < 64; j++) {
				g.setColor(new Color((int) (Math.random() * 0xffffff)));
				g.fillRect(i, j, 1, 1);
			}
		}

	}

}
