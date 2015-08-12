package igdc125.interpolation;

public class Linear extends Interpolate {

	@Override
	protected float calculate(float t) {
		return start + (end - start) * t;
	}

}
