package igdc125.game;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import igdc125.core.Container;

abstract class MenuItem {
	public String title;

	public abstract void exec();
}

public class Menu extends Container {

	private ArrayList<MenuItem> items = new ArrayList<>();
	private int selectedItem = 0;

	public Menu() {
		super();

		items.add(new MenuItem() {
			{
				title = "Start";
			}

			@Override
			public void exec() {
				((Game) parent).setState(new Intro());
			}
		});

		items.add(new MenuItem() {
			{
				title = "Quit";
			}

			@Override
			public void exec() {
				System.exit(0);
			}
		});
	}

	@Override
	public void draw(Graphics2D g) {
		for (int i = 0; i < items.size(); i++) {
			if (i == selectedItem) {
				g.setColor(Palette.PALETTE[3]);
			} else {
				g.setColor(Palette.PALETTE[1]);
			}
			g.drawString(items.get(i).title, 10, 15 + i * 10);
		}
	}
	

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case 10:
			items.get(selectedItem).exec();
			break;
		case 40:
			selectedItem++;
			if (selectedItem > items.size() - 1)
				selectedItem = 0;
			break;
		case 38:
			selectedItem--;
			if (selectedItem < 0)
				selectedItem = items.size() - 1;
			break;
		}
	}
}
