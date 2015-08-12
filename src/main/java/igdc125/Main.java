package igdc125;

import java.applet.Applet;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;

import igdc125.game.Game;
import igdc125.game.Palette;

@SuppressWarnings("serial")
public class Main extends Applet implements Runnable {
	public static JFrame frame;
	private static JPanel jpanel;
	public static final int SCALE = 5;
	public static final int WND_SIZE = 64 * SCALE;

	private Image _render;
	private boolean _running = true;
	private Game _game;

	public static void main(String[] args) {

		Dimension displaySize = Toolkit.getDefaultToolkit().getScreenSize();

		frame = new JFrame("IGDC #125");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation((displaySize.width - WND_SIZE) / 2, (displaySize.height - WND_SIZE) / 2);

		jpanel = new JPanel();
		jpanel.setPreferredSize(new Dimension(WND_SIZE, WND_SIZE));

		Main main = new Main(); // ;)

		frame.add(jpanel);
		frame.pack();
		frame.setVisible(true);

		main.start();
	}

	@Override
	public void start() {
		_render = new BufferedImage(64, 64, BufferedImage.TYPE_3BYTE_BGR);
		_render.setAccelerationPriority(1);
		_game = new Game();
		new Thread(this).start();
	}

	@Override
	public void run() {
		long lastTime = new Date().getTime();

		while (_running) {
			long currentTime = new Date().getTime();

			Graphics renderGraphics = _render.getGraphics();
			renderGraphics.setColor(Palette.PALETTE[0]);
			renderGraphics.fillRect(0, 0, 64, 64);
			
			_game.update((currentTime - lastTime) / 1000f);
			
			lastTime = currentTime;
			_game.render((Graphics2D) renderGraphics);

			jpanel.getGraphics().drawImage(_render, 0, 0, 64 * SCALE, 64 * SCALE, null);

			try {
				Thread.sleep(1000 / 60);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
