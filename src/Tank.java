import java.util.ArrayList;

import processing.core.PApplet;

public class Tank {

	protected PApplet app;
	protected int x, y, ammo, maxAmmo, vX, vY, speed, size;
	protected ArrayList<Bullet> bullets;

	public Tank(PApplet a, int pX, int pY, int s) {
		app = a;
		x = pX;
		y = pY;
		ammo = 5;
		maxAmmo = 5;
		vX = 0;
		vY = 0;
		speed = 6;
		size = s;
		bullets = new ArrayList<>();
	}

	public void moveEast() {
		vX = speed;

	}

	public void moveWest() {
		vX = -speed;
	}

	public void moveNorth() {
		vY = -speed;

	}

	public void moveSouth() {
		vY = speed;
	}

	public void stopX() {
		vX = 0;
	}

	public void stopY() {
		vY = 0;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getAmmo() {
		return ammo;
	}

	public void update() {
		x += vX;
		y += vY;
	}

	public void shoot(int mX, int mY) {
		if (ammo > 0) {
			ammo--;
			bullets.add(new Bullet(app, x, y, mX, mY));
		}
	}

	public void paint() {
		app.strokeWeight(0);
		app.fill(20, 115, 250);
		app.ellipse(x, y, size, size);
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
