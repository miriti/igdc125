package igdc125;

import java.awt.Color;

public class Palette {
	public static final Color PRIMARY = new Color((int) (Math.random() * 0xffffff));
	public static final Color SECONDARY = new Color((int) (Math.random() * 0xffffff));

	public static final Color[] PALETTE = { PRIMARY, SECONDARY, new Color((int) (Math.random() * 0xffffff)), new Color((int) (Math.random() * 0xffffff)), new Color((int) (Math.random() * 0xffffff)),
			new Color((int) (Math.random() * 0xffffff)), new Color((int) (Math.random() * 0xffffff)), new Color((int) (Math.random() * 0xffffff)) };

	public static Color random() {
		return PALETTE[(int) (Math.random() * PALETTE.length)];

	}
}
