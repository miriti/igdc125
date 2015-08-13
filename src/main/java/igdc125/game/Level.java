package igdc125.game;

import java.awt.event.KeyEvent;

import igdc125.core.Container;

public class Level extends Container {
	private Tile[][] map;

	public Level() {
		super();

		map = new Tile[10][10];

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				map[i][j] = new Tile();
				map[i][j].x = i * 9;
				map[i][j].y = j * 9;
				addChild(map[i][j]);
			}
		}
		
		addChild(new Mob());
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			x--;
			break;
		case KeyEvent.VK_RIGHT:
			x++;
			break;
		case KeyEvent.VK_UP:
			y--;
			break;
		case KeyEvent.VK_DOWN:
			y++;
			break;
		}
	}
}
