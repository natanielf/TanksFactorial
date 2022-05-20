import processing.core.PApplet;
import processing.core.PVector;

public class Bullet {

	private PApplet app;
	private PVector location, velocity, minVelocity;
	private int size, startFrame, rCount, rLimit;
	private Arena arena;
	
	public Bullet(PApplet app, int tankX, int tankY, int mouseX, int mouseY) {
		this.app = app;
		this.location = new PVector(tankX, tankY);
		this.velocity = new PVector((mouseX - tankX) / 50, (mouseY - tankY) / 50);
		this.minVelocity = new PVector(velocity.x / 10, velocity.y / 10);
		this.size = 8;
		this.startFrame = app.frameCount;
		this.rCount = 0;
		this.rLimit = 2;
	}

	public Bullet(PApplet app, int tankX, int tankY, int mouseX, int mouseY, boolean variableSpeed) {
		this(app, tankX, tankY, mouseX, mouseY);
		if (!variableSpeed) {
			this.velocity = new PVector(mouseX - tankX, mouseY - tankY).normalize().mult(8);
			this.minVelocity = velocity;
		}
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
		
		// TODO: Fix the if statement logic
		// pass in the arena, traverse the arena, check for black tiles, if black, then bounce!
		
		if (location.x == 0 || location.x == 1280) {
			bounceX();
			deadBullet();
		}
		if (location.y == 0 || location.y == 720) {
			bounceY();
			deadBullet();
		}
		
		app.fill(0, 0, 0);
		app.ellipse(location.x, location.y, size, size);
	}
	
	public void bounceX() {
		velocity.x *= -1;
		rCount++;
	}
	
	public void bounceY() {
		velocity.y *= -1;
		rCount++;
	}
	
	public void deadBullet() {
		if (rCount >= rLimit)
			location.x = 6000;
	}

	public int getStartFrame() {
		return this.startFrame;
	}

}
