package igdc125.game.tiles;

import java.util.Formattable;
import java.util.Formatter;

import igdc125.core.Container;
import igdc125.game.MapObject;
import igdc125.game.maps.Map;

public class Tile extends Container implements Formattable {
	public static final int SIZE = 9;
	public boolean passable = false;

	public static Tile factory(int color, int x, int y, Map map) {
		switch (color) {
		case 0xffff0000:
			return new FloorTile();
		case 0xffffff00:
			map.player.setTile(x, y);
			return null;
		case 0xff00ff00:
			return new TravalatorTile();
		case 0xffff00ff:
			return new Bonus();
		case 0xff00ffff:
			return new SawThrow();
		case 0xff800080:
			return new Spring();
		case 0xff008080:
			return new Press();
		default:
			return null;
		}
	}

	public int cellX;
	public int cellY;

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

	public void touch(MapObject mapObject, int dx, int dy, float delta) {

	}

	protected void selfRemove() {
		((Map) parent).removeTile(cellX, cellY);
	}

	public Map getMap() {
		return (Map) parent;
	}
}
