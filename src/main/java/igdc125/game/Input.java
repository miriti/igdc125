package igdc125.game;

import java.awt.event.KeyEvent;
import java.util.HashMap;

import igdc125.core.Vector;

public class Input {
	public static boolean[] pressedKeys = new boolean[256];

	private static Vector _vector = new Vector();

	public static float getLeft() {
		return pressedKeys[KeyEvent.VK_LEFT] || pressedKeys[KeyEvent.VK_A] ? 1 : 0;
	}

	public static float getRigth() {
		return pressedKeys[KeyEvent.VK_RIGHT] || pressedKeys[KeyEvent.VK_D] ? 1 : 0;
	}

	public static float getUp() {
		return pressedKeys[KeyEvent.VK_UP] || pressedKeys[KeyEvent.VK_W] ? 1 : 0;
	}

	public static float getDown() {
		return pressedKeys[KeyEvent.VK_DOWN] || pressedKeys[KeyEvent.VK_S] ? 1 : 0;
	}

	public static Vector getVector() {
		_vector.x = -getLeft() + getRigth();
		_vector.y = -getUp() + getDown();
		return _vector;
	}

	public static void keyPressed(KeyEvent e) {
		pressedKeys[e.getKeyCode()] = true;
	}

	public static void keyReleased(KeyEvent e) {
		pressedKeys[e.getKeyCode()] = false;
	}
}
