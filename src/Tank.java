import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;
import processing.data.JSONObject;

public class Tank {

	protected PApplet app;
	protected PVector location, velocity;
	protected int ammo, maxAmmo, speed, size;
	protected ArrayList<Bullet> bullets;

	private Arena map;

	public Tank(PApplet app, int x, int y, int s, Arena map) {
		this.app = app;
		this.location = new PVector(x, y);
		this.velocity = new PVector(0, 0);
		this.maxAmmo = 5;
		this.ammo = maxAmmo;
		speed = 6;
		size = s;
		bullets = new ArrayList<>();
		this.map = map;
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
	
	PVector pv = new PVector(0,0);
	public void update() {
		
		if (!map.collide(location)) {
			if (velocity.x != 0 && velocity.y != 0) {
				location.x += velocity.x / Math.sqrt(2);
				location.y += velocity.y / Math.sqrt(2);
			} else {
				location.x += velocity.x;
				location.y += velocity.y;
			}
		} else
			System.out.println("I'd Better Call Saul!");
	}

	public void shoot(int mX, int mY) {
		if (ammo > 0) {
			ammo--;
			bullets.add(new Bullet(app, (int) location.x, (int) location.y, mX, mY, false));
		}
	}

	public void paint() {
		update();
		app.strokeWeight(0);
		setColor();
		app.ellipse(location.x, location.y, size, size);
		for (int i = 0; i < bullets.size(); i++) {
			Bullet b = bullets.get(i);
			if (b.getStartFrame() < app.frameCount - (app.frameRate * 8))
				bullets.remove(i);
		}
		for (Bullet b : bullets) {
			b.paint();
		}
		replenishAmmo();
	}

	public void setColor() {
		app.fill(200, 0, 0);
	}

	public void replenishAmmo() {
		if (app.frameCount % (Game.FRAMERATE * 2) == 0) {
			if (ammo < maxAmmo)
				ammo++;
		}
	}

	public String toJSON() {
		JSONObject tank = new JSONObject();
		JSONObject location = new JSONObject();
		location.put("x", (int) this.location.x);
		location.put("y", (int) this.location.y);
		tank.put("location", location);
		JSONObject bullets = new JSONObject();
		for (int i = 0; i < this.bullets.size(); i++) {
			if (this.bullets.get(i) != null) {
				JSONObject bullet = new JSONObject();
				bullet.put("x", (int) this.bullets.get(i).getX());
				bullet.put("y", (int) this.bullets.get(i).getY());
				bullets.put("bullet" + i, bullet);
			}
		}
		tank.put("bullets", bullets);
		return tank.toString();
	}

	public void fromJSON(String source) {
		JSONObject tank = JSONObject.parse(source);
		if (tank.size() == 0) {
			return;
		}
		if (tank.hasKey("location")) {
			this.location.x = tank.getJSONObject("location").getInt("x");
			this.location.y = tank.getJSONObject("location").getInt("y");
		}
	}
	
	public void setX(float x) {
		location.x = x;
	}

}
