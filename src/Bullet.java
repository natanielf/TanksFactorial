import java.awt.Graphics;
import java.awt.Color;

public class Bullet {
	protected int x, y, vX, vY, size, speed;
	private Color color;
	private Mouse mouse;

	public Bullet(int x, int y, int vX, int vY, Mouse mouse) {
		this.x = x;
		this.y = y;
		this.vX = vX;
		this.vY = vY;
		size = 10;
		speed = 3;
		color = new Color(25, 90, 150);
		this.mouse = mouse;
	}

	public void paint(Graphics g) {
		g.setColor(color);
		x += vX;
		y += vY;
		g.fillOval(x, y, 10, 10);
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
	
	public void setVelocityX() {
		// TODO
		vX *= Math.cos(mouse.getAngleInRadians());
	}
	
	public void setVelocityY() {
		// TODO
		vY *= Math.sin(mouse.getAngleInRadians());
	}
}