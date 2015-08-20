package igdc125.core;

public class Vector {
	public float x;
	public float y;

	public Vector(float x, float y) {
		set(x, y);
	}

	public Vector() {
		set(0, 0);
	}

	public Vector set(float x, float y) {
		this.x = x;
		this.y = y;
		return this;
	}

	public static float len2(float x1, float y1, float x2, float y2) {
		return (float) (Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
	}

	public static float len(float x1, float y1, float x2, float y2) {
		return (float) Math.sqrt(len2(x1, y1, x2, y2));
	}

	@Override
	public String toString() {
		return "[" + x + ", " + y + "]";
	}

	public Vector mult(float a) {
		return mult(a, a);
	}

	public Vector mult(float mx, float my) {
		x *= mx;
		y *= my;
		return this;
	}

	public Vector set(float d) {
		x = d;
		y = d;
		return this;
	}
}
