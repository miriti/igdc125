package igdc125;

import java.awt.Color;

public class Palette {

	public static final Color[] PALETTE = { new Color(0x1c2804), new Color(0x465114), new Color(0x9aa264), new Color(0xdae2a9) };

	public static Color random() {
		return PALETTE[(int) (Math.random() * PALETTE.length)];

	}
}
