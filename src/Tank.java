import java.awt.Color;

public class Tank {
	private int x;
	private int y;
	private Color color;
	
	public Tank(int pX, int pY, Color pColor) {
		x = pX;
		y = pY;
		color = pColor;
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
