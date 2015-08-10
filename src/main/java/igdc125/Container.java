package igdc125;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public abstract class Container implements IGameObject {
	public float x = 0;
	public float y = 0;

	private ArrayList<Container> children = new ArrayList<>();

	public void addChild(Container child) {
		children.add(child);
	}

	public void removeChild(Container child) {
		children.remove(child);
	}

	@Override
	public void update(float delta) {
		for (Container child : children) {
			child.update(delta);
		}

	}

	public abstract void draw(Graphics2D g);

	@Override
	public void render(Graphics2D g) {
		AffineTransform matrix = g.getTransform();

		g.translate(x, y);
		draw(g);
		for (Container child : children) {
			child.render(g);
		}

		//g.setTransform(matrix);
	}

}
