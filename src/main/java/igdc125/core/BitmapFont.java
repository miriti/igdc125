package igdc125.core;

import igdc125.game.Palette;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

class BitmapChar {
	public char c;
	private HashMap<Color, BufferedImage> imgs = new HashMap<>();
	private BufferedImage _original_img;
	public int width;
	public int height;

	public BitmapChar(char c, int x, int y, int w, int h, BufferedImage srcImg) {
		this.c = c;
		this._original_img = srcImg.getSubimage(x, y + 1, w, h + 3);
		width = w;
		height = h;

		for (Color col : Palette.PALETTE) {
			BufferedImage climg = new BufferedImage(_original_img.getWidth(), _original_img.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);

			for (int i = 0; i < _original_img.getWidth(); i++) {
				for (int j = 0; j < _original_img.getHeight(); j++) {
					int cl = _original_img.getRGB(i, j);
					climg.setRGB(i, j, cl == 0xffffffff ? col.getRGB() : cl);
				}
			}

			imgs.put(col, climg);
		}
	}

	public void draw(Graphics2D g, int x, int y, Color color) {
		g.drawImage(imgs.get(color), x, y, null);
	}
}

public class BitmapFont {
	private BufferedImage _fontImage;
	private HashMap<Character, BitmapChar> _chars = new HashMap<>();
	private Color currentColor = Palette.PALETTE[0];

	public BitmapFont() {
		super();

		_fontImage = Resources.getImage("font.png");

		StringBuilder out = new StringBuilder();

		InputStream is = getClass().getClassLoader().getResourceAsStream("font.txt");
		InputStreamReader reader = new InputStreamReader(is);

		char[] buffer = new char[1024];

		while (true) {
			int rsz;
			try {
				rsz = reader.read(buffer);

				if (rsz < 0)
					break;
				out.append(buffer);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		String[] list = out.toString().split("\n");

		for (String item : list) {
			if (item.trim() != "") {
				String[] params = item.split("\\t");
				if (params.length == 5) {
					char c;

					if (params[0] != " ") {
						c = params[0].charAt(0);
					} else {
						c = ' ';
					}
					_chars.put(c, new BitmapChar(c, Integer.valueOf(params[1]), Integer.valueOf(params[2]), Integer.valueOf(params[3]), Integer.valueOf(params[4]), _fontImage));
				}
			}
		}
	}

	public void setCurrentColor(Color color) {
		currentColor = color;
	}

	public void drawString(Graphics2D g, int x, int y, String str) {
		int cx = 0;

		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (_chars.containsKey(c)) {
				BitmapChar bc = _chars.get(c);
				bc.draw(g, x + cx, y, currentColor);
				cx += bc.width;
			}
		}
	}
}
