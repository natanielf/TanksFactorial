import processing.core.PApplet;
import processing.core.PVector;

public class Bullet {

	private PApplet app;
	private int size, startFrame;
	private PVector location, velocity, minVelocity;

	public Bullet(PApplet app, int tankX, int tankY, int mouseX, int mouseY) {
		this.app = app;
		this.location = new PVector(tankX, tankY);
		this.size = 8;
		this.velocity = new PVector((mouseX - tankX) / 50, (mouseY - tankY) / 50);
		this.minVelocity = new PVector(velocity.x / 10, velocity.y / 10);
		this.startFrame = app.frameCount;
	}

	public void paint() {
		if (app.frameCount % 10 == 0) {
			if (velocity.x > minVelocity.x)
				velocity.x -= velocity.x / 100;
			if (velocity.y > minVelocity.y)
				velocity.y -= velocity.y / 100;
		}
		location.x += velocity.x;
		location.y += velocity.y;
		if (location.x == 0 || location.x == 1280)
			velocity.x *= -1;
		if (location.y == 0 || location.y == 720)
			velocity.y *= -1;
		app.fill(0, 0, 0);
		app.ellipse(location.x, location.y, size, size);
	}

	public int getStartFrame() {
		return this.startFrame;
	}

}
