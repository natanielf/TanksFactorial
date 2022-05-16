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
		this.velocity = new PVector((mouseX - tankX) / 40, (mouseY - tankY) / 40);
		this.minVelocity = new PVector(velocity.x / 10, velocity.y / 10);
		this.startFrame = app.frameCount;
	}

	public void paint() {
		if (app.frameCount % 4 == 0) {
			if (velocity.x > minVelocity.x)
				velocity.x -= velocity.x / 50;
			if (velocity.y > minVelocity.y)
				velocity.y -= velocity.y / 50;
		}
		location.x += velocity.x;
		location.y += velocity.y;
		app.fill(0, 0, 0);
		app.ellipse(location.x, location.y, size, size);
	}

	public int getStartFrame() {
		return this.startFrame;
	}

}
