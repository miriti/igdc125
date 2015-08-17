package igdc125;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Formatter;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.sun.javafx.binding.StringFormatter;

import igdc125.core.Resources;
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

	public static void generateFont() {
		BufferedImage fontImage = new BufferedImage(128, 128, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics g = fontImage.getGraphics();

		String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!@#$%^&*()_+-=., ";

		Font font = new Font("Ubuntu Mono", Font.PLAIN, 10);
		g.setFont(font);
		g.setColor(Color.WHITE);

		FontMetrics metrix = g.getFontMetrics(font);
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(new File("src/main/resources/font.txt")));
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		int fx = 0;
		int fy = 0;

		for (int i = 0; i < chars.length(); i++) {
			char c = chars.charAt(i);
			int charWidth = (int) metrix.getStringBounds(String.valueOf(c), g).getWidth();
			int charHeight = (int) metrix.getStringBounds(String.valueOf(c), g).getHeight();

			g.drawString(String.valueOf(c), fx, fy + metrix.getHeight());

			String str = StringFormatter.format("%s\t%d\t%d\t%d\t%d\n", c, fx, fy, charWidth, charHeight).getValue();
			try {
				writer.write(str);
			} catch (IOException e) {
				e.printStackTrace();
			}

			fx += charWidth;
			if (fx > fontImage.getWidth() - charWidth) {
				fx = 0;
				fy += charHeight;
			}
		}

		try {
			writer.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		File output = new File("src/main/resources/font.png");
		try {
			ImageIO.write(fontImage, "png", output);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("DONE!");
		System.exit(0);
	}

	public static void main(String[] args) {
		if((args.length==1)&&(args[0].equals("--generate-font"))){
			generateFont();
		}

		Dimension displaySize = Toolkit.getDefaultToolkit().getScreenSize();

		Resources.preload();

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
		frame.addKeyListener(_game);
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
