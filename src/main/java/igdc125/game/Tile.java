package igdc125.game;

import java.util.Formattable;
import java.util.Formatter;

import igdc125.core.Container;
import igdc125.core.Resources;
import igdc125.core.Sprite;

public class Tile extends Container implements Formattable {
	public static final int SIZE = 9;

	public static Tile factory(int color) {
		switch (color) {
		case 0xffff0000:
			return new Tile();
		default:
			return null;
		}
	}

	public int cellX;
	public int cellY;

	public Tile() {
		super();

		addChild(new Sprite(Resources.getSprite("tileset.png", 0, 0, 9, 9)));
	}

	public void setCell(int cellX, int cellY) {
		this.cellX = cellX;
		this.cellY = cellY;

		x = cellX * SIZE;
		y = cellY * SIZE;
	}

	@Override
	public void formatTo(Formatter formatter, int flags, int width, int precision) {
		formatter.format("[%d %d]", cellX, cellY);

	}
}
