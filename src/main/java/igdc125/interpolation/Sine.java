package igdc125.interpolation;

public class Sine extends Interpolate {

	@Override
	protected float calculate(float t) {
		return start + (end - start) * (float) Math.sin((Math.PI / 2) * t);
	}

}
