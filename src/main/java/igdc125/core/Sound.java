package igdc125.core;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {
	private AudioInputStream stream;
	private String _path;

	public Sound(String path) {
		_path = path;
	}

	public void play() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					stream = AudioSystem.getAudioInputStream(getClass().getClassLoader().getResourceAsStream(_path));
					Clip clip = AudioSystem.getClip();
					clip.open(stream);
					clip.start();
				} catch (UnsupportedAudioFileException | IOException e) {
					e.printStackTrace();
				} catch (LineUnavailableException e) {
					e.printStackTrace();
				}
			}
		}).start();

	}
}
