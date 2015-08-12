package igdc125.core;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public abstract class Container implements IGameObject, KeyListener {
	public float x = 0;
	public float y = 0;
	public float rotation = 0;
	public Vector scale = new Vector(1f, 1f);
	public Container parent = null;

	private ArrayList<Container> children = new ArrayList<>();

	public void addChild(Container child) {
		children.add(child);
		child.parent = this;
	}

	public void removeChild(Container child) {
		children.remove(child);
		child.parent = null;
	}

	@Override
	public void update(float delta) {
		for (int i = children.size() - 1; i >= 0; i--) {
			Container child = children.get(i);
			child.update(delta);
		}
	}

	public void draw(Graphics2D g) {
	};

	@Override
	public void render(Graphics2D g) {
		g.translate(x, y);
		g.rotate(rotation);
		g.scale(scale.x, scale.y);

		draw(g);
		for (Container child : children) {
			child.render(g);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		for (int i = children.size() - 1; i >= 0; i--) {
			children.get(i).keyPressed(e);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		for (int i = children.size() - 1; i >= 0; i--) {
			children.get(i).keyReleased(e);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		for (int i = children.size() - 1; i >= 0; i--) {
			children.get(i).keyTyped(e);
		}

	}

}
