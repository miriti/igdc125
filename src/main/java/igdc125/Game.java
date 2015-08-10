package igdc125;

import java.awt.Color;
import java.awt.Graphics2D;

public class Game extends Container {

	int phase = 0;
	

	@Override
	public void update(float delta) {
		//phase++;
		super.update(delta);
	}

	@Override
	public void draw(Graphics2D g) {
		for (int i = 0; i < 64; i++) {
			for (int j = 0; j < 64; j++) {
				g.setColor(Palette.random());
				g.drawLine(i, j, i, j);
			}
		}
	}

}
