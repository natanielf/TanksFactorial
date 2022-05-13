import processing.core.PApplet;

public class Bullet {

	private PApplet app;
	private int x, y, size, vX, vY, minVX, minVY, startFrame;

	public Bullet(PApplet app, int tankX, int tankY, int mouseX, int mouseY) {
		this.app = app;
		this.x = tankX;
		this.y = tankY;
		this.size = 8;
		this.vX = (mouseX - tankX) / 40;
		this.vY = (mouseY - tankY) / 40;
		this.minVX = vX / 10;
		this.minVY = vY / 10;
		this.startFrame = app.frameCount;
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
		if (app.frameCount % 4 == 0) {
			if (vX > minVX)
				vX -= vX / 50;
			if (vY > minVY)
				vY -= vY / 50;
		}
		x += vX;
		y += vY;
		app.fill(0, 0, 0);
		app.ellipse(x, y, size, size);
	}
	
	public int getStartFrame() {
		return this.startFrame;
	}

}
