package igdc125;

import java.awt.Color;
import java.awt.Graphics2D;

public class Circle extends Container {

	private float a = 0;
	private Color color;

	public Circle(boolean nest) {
		this.color = Palette.random();
		if (nest) {
			this.addChild(new Circle(false));
		}
	}

	@Override
	public void update(float delta) {
		x = (float) Math.cos(a) * 10;
		y = (float) Math.sin(a) * 10;
		a += (Math.PI / 10) * delta;
		super.update(delta);
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(color);
		g.fillOval(-5, -5, 10, 10);
	}

}
