package igdc125.game;

import java.awt.event.KeyEvent;

import igdc125.core.Container;

public class Game extends Container {
	public static final float GRAVITY = 100;
	
	Container _currentState = null;

	public Game() {
		super();
		setState(new Intro());
	}

	public void setState(Container newState) {
		if (_currentState != null) {
			removeChild(_currentState);
		}
		addChild(newState);
		_currentState = newState;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		Input.keyPressed(e);
		super.keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		Input.keyReleased(e);
		super.keyReleased(e);
	}
}
