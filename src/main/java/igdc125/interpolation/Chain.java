package igdc125.interpolation;

import java.util.ArrayList;

public class Chain extends Interpolate {

	private ArrayList<Interpolate> _chain = new ArrayList<>();

	public Chain(Interpolate... args) {
		for (Interpolate i : args) {
			this.add(i);
		}
	}

	@Override
	protected float calculate(float t) {
		return _chain.get(0).calculate(t);
	}

	@Override
	public float next(float delta) {
		if (_chain.size() > 0) {
			float val = _chain.get(0).next(delta);
			current = val;

			if (_chain.get(0).finished)
				_chain.remove(0);
			return val;
		} else {
			finished = true;
			return current;
		}
	}

	public Chain add(Interpolate item) {
		_chain.add(item);
		return this;
	}

}
