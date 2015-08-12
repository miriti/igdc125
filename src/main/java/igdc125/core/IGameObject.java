package igdc125.core;

import java.awt.Graphics2D;

public interface IGameObject {
	void update(float delta);

	void render(Graphics2D g);
}
