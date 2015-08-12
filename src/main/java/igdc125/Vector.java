package igdc125;

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
}