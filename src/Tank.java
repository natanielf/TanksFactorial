import java.awt.Color;
import java.awt.Graphics;

public class Tank {
	protected int x, y, ammo, max, speedX, speedY;
	protected double health, speedM, charge;
	protected Color color;

	public Tank(int pX, int pY) {
		x = pX;
		y = pY;
		speedX = 0;
		speedY = 0;
		color = new Color(0, 0, 0);
	}

	public void paint(Graphics g) {
		g.setColor(color);
		g.fillRect(x, y, 36, 36);
		x += speedX;
		y += speedY;
	}

	public void moveEast() {
		speedX = 3;
	}

	public void moveWest() {
		speedX = -3;
	}

	public void moveNorth() {
		speedY = -3;
	}

	public void moveSouth() {
		speedY = 3;
	}

	public void stopX() {
		speedX = 0;
	}

	public void stopY() {
		speedY = 0;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
