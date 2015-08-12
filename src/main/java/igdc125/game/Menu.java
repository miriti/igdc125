package igdc125.game;

import java.awt.Graphics2D;

import igdc125.core.Container;
import igdc125.core.Resources;

public class Menu extends Container {
	public Menu() {
		super();
	}

	@Override
	public void draw(Graphics2D g) {

		for (int i = 0; i < 64; i++) {
			for (int j = 0; j < 64; j++) {
				g.setColor((i % 2 == 0) ^ (j % 2 == 0) ? Palette.PALETTE[0] : Palette.PALETTE[1]);
				g.fillRect(i, j, 1, 1);
			}
		}

		g.setFont(Resources.getFont());
		g.setColor(Palette.PALETTE[3]);
		g.rotate(Math.PI/4);
		g.drawString("1234567890", 0, g.getFontMetrics(Resources.getFont()).getHeight()/2);
	}
}
