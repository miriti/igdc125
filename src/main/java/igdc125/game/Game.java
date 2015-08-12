package igdc125.game;

import igdc125.core.Container;

public class Game extends Container {

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

}
