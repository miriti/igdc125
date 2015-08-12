package igdc125;

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
