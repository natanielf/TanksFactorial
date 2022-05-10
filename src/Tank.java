import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class Tank {

	private Img img;
	private Image image;

	protected int x, y, ammo, maxAmmo, vX, vY, speed;
	protected int minX, minY, maxX, maxY;
	protected double health, speedMult, charge;
	protected Color color;

	public Tank(int pX, int pY) {
		x = pX;
		y = pY;
		vX = 0;
		vY = 0;
		speed = 6;
		color = new Color(0, 0, 0);
	}

	public Tank(int pX, int pY, int minXVal, int minYVal, int maxXVal, int maxYVal) {
		x = pX;
		y = pY;
		vX = 0;
		vY = 0;
		speed = 6;
		minX = minXVal;
		minY = minYVal;
		maxX = maxXVal;
		maxY = maxYVal;
		color = new Color(0, 0, 0);
	}

	public Tank(int pX, int pY, int m) {
		image = img.img("yellow.png");
		// TODO: Instead of inputting the x and y coords, input the tile row and col
		x = pX;
		y = pY;
		vX = 0;
		vY = 0;
		color = new Color(0, 0, 0);
	}

	public void paint(Graphics g) {
		if (x < maxX && vX > 0)
			x += vX;

		if (x > minX && vX < 0)
			x += vX;

		if (y < maxY && vY > 0)
			y += vY;

		if (y > minY && vY < 0)
			y += vY;

		g.setColor(color);
	}

	public void moveEast() {
		vX = speed;

	}

	public void moveWest() {
		vX = -speed;
	}

	public void moveNorth() {
		vY = -speed;

	}

	public void moveSouth() {
		vY = speed;
	}

	public void stopX() {
		vX = 0;
	}

	public void stopY() {
		vY = 0;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}
