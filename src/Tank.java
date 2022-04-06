import java.awt.Color;

//Waiting on the World to Change!

public class Tank {
	private int x, y, ammo, max;
	private double health, speed, charge;

	public Tank(int pX, int pY) {
		x = pX;
		y = pY;
	}

	public int getX() {
		return x;
	}

	public void setX(int pX) {
		x = pX;
	}
 
	public int getY() {
		return y;
	}

	public void setY(int pY) {
		y = pY;
	}
}
