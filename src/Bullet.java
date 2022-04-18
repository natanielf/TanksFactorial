import java.awt.Graphics;
import java.awt.Color;

public class Bullet {

	protected int x, y, vX, vY, size;
	private Color color;

	public Bullet(int x, int y, int vX, int vY) {
		this.x = x;
		this.y = y;
		this.vX = vX;
		this.vY = vY;
		size = 10;
		color = new Color(25, 90, 150);
	}

	public void paint(Graphics g) {
		Color oldColor = g.getColor();
		g.setColor(color);
		x += vX;
		y += vY;
		g.fillOval(x, y, 10, 10);

		// TODO:

	}

	public void bounce(int d) {
		switch (d) {

		case 0:
			vY = 0 - vY;
		case 1:
			vX = 0 - vX;
		case 2:
			vY = Math.abs(vY);
		case 3:
			vX = Math.abs(vX);
		}
	}

}
