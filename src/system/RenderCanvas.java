package system;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class RenderCanvas extends Canvas {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9087391295773706536L;
	private BufferedImage image;

	public void redraw(Graphics g) {
		if (image != null)
			g.drawImage(image, 0, 0, null);
	}

	public void update(Graphics g) {
		this.redraw(g);
	}

	public void paint(Graphics g) {
		this.redraw(g);
	}

	public void drawImage(BufferedImage image) {
		this.image = image;
		repaint();
	}
}
