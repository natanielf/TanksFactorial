import java.awt.Color;
import java.awt.Graphics;

public class Tile {

	/*
	 * tile0 = Ground tile tile1 = Hole tile tile2 = Wall tile tile3 = Fragile wall
	 * tile tile4 = Broken tile tile5 = Slow ground tile tile6 = Fast ground tile
	 * tile7 = Powerup tile tile8 = Ice tile(???)
	 */

	int type;
	protected boolean floor, clear, frail;
	protected double speed;
	private String txtr;

	public Tile() {
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
		g.setColor(new Color(100, 200, 50));
		g.fillRect(x, y, size, size);
	}
}
