package igdc125;

import java.awt.Graphics2D;
import java.util.ArrayList;

public abstract class Container implements IGameObject {
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
		for (int i = children.size()-1; i >= 0; i--) {
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

}
