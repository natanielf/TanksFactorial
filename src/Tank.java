import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;

public class Tank {

	protected PApplet app;
	protected PVector location, velocity;
	protected int ammo, maxAmmo, speed, size;
	protected ArrayList<Bullet> bullets;

	public Tank(PApplet app, int x, int y, int s) {
		this.app = app;
		this.location = new PVector(x, y);
		this.velocity = new PVector(0, 0);
		this.maxAmmo = 5;
		this.ammo = maxAmmo;
		speed = 6;
		size = s;
		bullets = new ArrayList<>();
	}

	public void moveEast() {
		velocity.x = speed;

	}

	public void moveWest() {
		velocity.x = -speed;
	}

	public void moveNorth() {
		velocity.y = -speed;

	}

	public void moveSouth() {
		velocity.y = speed;
	}

	public void stopX() {
		velocity.x = 0;
	}

	public void stopY() {
		velocity.y = 0;
	}

	public int getX() {
		return (int) location.x;
	}

	public int getY() {
		return (int) location.y;
	}

	public int getAmmo() {
		return ammo;
	}

	public void update() {
		location.x += velocity.x;
		location.y += velocity.y;
	}

	public void shoot(int mX, int mY) {
		if (ammo > 0) {
			ammo--;
			bullets.add(new Bullet(app, (int) location.x, (int) location.y, mX, mY));
		}
	}

	public void paint() {
		update();
		app.strokeWeight(0);
		app.fill(20, 115, 250);
		app.ellipse(location.x, location.y, size, size);
		for (int i = 0; i < bullets.size(); i++) {
			Bullet b = bullets.get(i);
			if (b.getStartFrame() < app.frameCount - (app.frameRate * 8))
				bullets.remove(i);
		}
		for (Bullet b : bullets) {
			b.paint();
		}
	}

	public void replenishAmmo() {
		if (ammo < maxAmmo)
			ammo++;
	}

}
