import processing.core.PApplet;

public class Bullet {

	private PApplet app;
	private int x, y, size, vX, vY;
	static final int MAXSPEED = 20, MINSPEED = 5;

	public Bullet(PApplet app, int tankX, int tankY, int mouseX, int mouseY) {
		this.app = app;
		this.x = tankX;
		this.y = tankY;
		this.vX = Math.min((mouseX - tankX) / 20, MAXSPEED);
		this.vY = Math.min((mouseY - tankY) / 20, MAXSPEED);
		this.size = 8;
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

	public void paint() {
		if (app.frameCount % 5 == 0) {
			if (vX > MINSPEED)
				vX--;
			if (vY > MINSPEED)
				vY--;
		}
		x += vX;
		y += vY;
		app.fill(0, 0, 0);
		app.ellipse(x, y, size, size);
	}

}
