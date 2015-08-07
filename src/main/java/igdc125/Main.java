package igdc125;

import java.applet.Applet;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Main extends Applet implements Runnable {
	public static JFrame frame;
	private static JPanel jpanel;
	public static final int SCALE = 4;
	public static final int WND_SIZE = 64 * SCALE;

	private Image _volatileImage;
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
		_volatileImage = jpanel.createVolatileImage(64, 64);
		_game = new Game();
		new Thread(this).start();
	}

	@Override
	public void run() {
		while (_running) {
			_volatileImage.getGraphics().fillRect(0, 0, 64, 64);

			_game.render(_volatileImage.getGraphics());

			jpanel.getGraphics().drawImage(_volatileImage, 0, 0, WND_SIZE, WND_SIZE, null);

			try {
				Thread.sleep(1000 / 60);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
