package igdc125;

import java.awt.Graphics2D;

public interface IGameObject {
	void update(float delta);

	void render(Graphics2D g);
}
