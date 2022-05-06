import java.awt.Color;
import java.awt.Graphics;

public class Tile {

	/*
	 * tile0 = Ground tile tile1 = Hole tile tile2 = Wall tile tile3 = Fragile wall
	 * tile tile4 = Broken tile tile5 = Slow ground tile tile6 = Fast ground tile
	 * tile7 = Powerup tile tile8 = Ice tile(???)
	 */

	private int type;
	protected boolean floor, clear, frail;
	protected double speed;
	private String txtr;

	public Tile() {
		type = 0;
		clear = true;
		speed = 1;
		txtr = "tile0";
	}

	public Tile(double s) {
		clear = true;
		speed = s;
		txtr = "tile1";
	}

	public void frag() {
		if (frail) {
			floor = true;
			clear = true;
			frail = false;
			speed = 0.9;
			txtr = "tile5";
		}
	}

	public void paint(Graphics g, int x, int y, int size) {
		switch (this.type) {
		case 0:
			g.setColor(new Color(120, 120, 120));
			break;
		case 1:
			g.setColor(new Color(50, 50, 50));
			break;
		default:
			g.setColor(Color.RED);
			break;
		}
		// g.setColor(new Color(100, 200, 50));
		g.fillRect(x, y, size, size);
	}

	public void setType(int type) {
		this.type = type;
	}

}
