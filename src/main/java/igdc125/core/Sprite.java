package igdc125.core;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Sprite extends Container {
	private BufferedImage _image;

	private int _frameWidth = 0;
	private int _frameHeight = 0;
	private boolean _animated = false;
	private int _fps = 12;
	private boolean _paused = false;
	private int _currentFrame = 0;
	private BufferedImage _currentFrameImage;
	private ArrayList<BufferedImage> frames;
	private float _lastFrameTime = 0f;
	private int _direction = 1;

	public Sprite(BufferedImage image, int frameWidth, int frameHeight, int fps) {
		_image = image;
		_animated = true;
		_frameWidth = frameWidth;
		_frameHeight = frameHeight;
		_fps = fps;

		int framesW = image.getWidth() / frameWidth;
		int framesH = image.getHeight() / frameHeight;

		frames = new ArrayList<>();

		for (int i = 0; i < framesW; i++) {
			for (int j = 0; j < framesH; j++) {
				frames.add(image.getSubimage(i * frameWidth, j * frameHeight, frameWidth, frameHeight));
			}
		}

		_currentFrameImage = frames.get(0);
	}

	public Sprite invert() {
		_direction = _direction == -1 ? 1 : -1;
		return this;
	}

	public Sprite(BufferedImage image) {
		_image = _currentFrameImage = image;
		_frameWidth = _image.getWidth();
		_frameHeight = _image.getHeight();
		_animated = false;
	}

	@Override
	public void update(float delta) {
		if (_animated && !_paused) {
			if (_lastFrameTime >= (1f / _fps)) {
				_currentFrame += _direction;

				if (_direction == 1) {
					if (_currentFrame > frames.size() - 1) {
						_currentFrame = 0;
					}
				} else {
					if (_currentFrame < 0) {
						_currentFrame = frames.size() - 1;
					}
				}
				_lastFrameTime = 0f;
				_currentFrameImage = frames.get(_currentFrame);
			} else {
				_lastFrameTime += delta;
			}
		}
		super.update(delta);
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(_currentFrameImage, -_frameWidth / 2, -_frameHeight / 2, null);
	}
}
