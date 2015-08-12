package igdc125;

public class Intro extends Container {
	private Sprite sprite;

	public Intro() {
		super();
		sprite = new Sprite(Resources.getImage("igdc.png"));
		sprite.x = 32;
		sprite.y = 32;
		addChild(sprite);
	}
}
