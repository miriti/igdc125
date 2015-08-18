package igdc125.interpolation;

public abstract class Interpolate {
	protected float start;
	protected float end;
	protected float time;
	protected float totalTime = 0f;
	protected boolean finished = false;
	protected float current;

	public Interpolate init(float start, float end, float time) {
		this.start = start;
		this.end = end;
		this.time = time;
		this.finished = false;
		this.totalTime = 0f;
		this.current = start;
		return this;
	}

	public Interpolate changeValue(float value) {
		totalTime = (value / (end - start)) * time;
		current = value;
		finished = false;
		return this;
	}

	public Interpolate change(float start, float end) {
		this.start = start;
		this.end = end;
		finished = false;
		return this;
	}

	public Interpolate reset() {
		totalTime = 0;
		finished = false;
		return this;
	}

	public boolean isFinished() {
		return finished;
	}

	public float getCurrent() {
		return current;
	}

	protected abstract float calculate(float t);

	public float next(float delta) {
		if ((totalTime > time) || (finished)) {
			finished = true;
			current = end;
			return end;
		}
		float result = calculate(totalTime / time);
		current = result;
		totalTime += delta;
		return result;
	};
}
