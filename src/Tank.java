import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;


public class Tank {
	
	private Img img;
	private Image image;
	
	private int mult;
	protected int x, y, ammo, max, vX, vY, speed;
	protected double health, speedM, charge;
	protected Color color;

	public Tank(int pX, int pY) {
		mult = 1;
		x = pX;		y = pY;
		vX = 0;		vY = 0;
		speed = 3;
		color = new Color(0, 0, 0);
	}
	
	public Tank(int pX, int pY, int m) {
		
		image = img.img("yellow.png");
		
		mult = m;
		//TODO: Instead of inputting the x and y coords, input the tile row and col
		x = pX;		y = pY;
		vX = 0;		vY = 0;
		speed = 3 * mult;
		color = new Color(0, 0, 0);
	}

	public void paint(Graphics g) {
		g.setColor(color);
		g.fillRect(x , y , 36 * mult, 36 * mult);
		g.drawImage(image, x*mult, y*mult, null);
		x += vX;
		y += vY;
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
