import java.awt.Color;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;
import processing.data.JSONObject;

public class Tank {

	protected PApplet app;
	protected PVector location, velocity;
	protected int ammo, maxAmmo, speed, size;
	protected ArrayList<Bullet> bullets;
	private Color color;

	private Arena map;
	private ArrayList<Tile> walls;
	private Tile targetBlackTile;

	public Tank(PApplet app, int x, int y, int s, String color, Arena map) {
		this.app = app;
		this.location = new PVector(x, y);
		this.velocity = new PVector(0, 0);
		this.maxAmmo = 5;
		this.ammo = maxAmmo;
		this.speed = 6;
		this.size = s;
		setColor(color);
		this.map = map;
		this.bullets = new ArrayList<>();
		this.walls = map.getWalls();
		createTargetBlackTile();
	}

	public void createTargetBlackTile() {
		this.targetBlackTile = null;
		float x = location.x;
		float y = location.y;
		Tile pTargetBlackTile = null;
		for (int i = 0; i < walls.size(); i++) {
			float xBlackTilePos = walls.get(i).getX();
			float yBlackTilePos = walls.get(i).getY();
			int wSize = walls.get(i).getSize();
			if ((x + size >= xBlackTilePos && x - size <= xBlackTilePos + wSize)
					&& (y + size >= yBlackTilePos && y - size <= yBlackTilePos + wSize))
				pTargetBlackTile = walls.get(i);
		}
		this.targetBlackTile = pTargetBlackTile;
	}

	public void setColor(String color) {
		switch (color.toUpperCase()) {
			case "BLUE":
				this.color = Color.BLUE;
				break;
			case "RED":
				this.color = Color.RED;
				break;
		}
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

	public void update() {
		// TODO: Make the tank not get stuck when it touches a wall
		location.x = PApplet.constrain(location.x, Arena.MARGIN + 2, map.getWidth() - Arena.MARGIN);
		location.y = PApplet.constrain(location.y, Arena.MARGIN + 2, map.getHeight() - Arena.MARGIN);
		if (!map.collide(location, size)) {
			if (velocity.x != 0 && velocity.y != 0) {
				location.x += velocity.x / Math.sqrt(2);
				location.y += velocity.y / Math.sqrt(2);
			} else {
				location.x += velocity.x;
				location.y += velocity.y;
			}
		} else
			createTargetBlackTile();
		if (this.targetBlackTile != null) {
			if (map.collideLeft(location, size) == true || map.collideRight(location, size) == true)
				location.x = PApplet.constrain(location.x, targetBlackTile.getX(),
						targetBlackTile.getX() + targetBlackTile.getSize());
			else if (map.collideUp(location, size) == true || map.collideDown(location, size) == true)
				location.y = PApplet.constrain(location.y, targetBlackTile.getY(),
						targetBlackTile.getY() + targetBlackTile.getSize());
		}
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
		app.fill(color.getRed(), color.getGreen(), color.getBlue());
		app.ellipse(location.x, location.y, size, size);
		for (int i = 0; i < bullets.size(); i++) {
			Bullet b = bullets.get(i);
			if (b.getStartFrame() < app.frameCount - (Game.FRAMERATE * 8))
				bullets.remove(i);
		}
		for (Bullet b : bullets) {
			b.paint();
		}
		replenishAmmo();
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
			} else {
				bullets.remove("bullet" + i);
			}
		}
		tank.put("bullets", bullets);
		return tank.toString();
	}

	public void fromJSON(String source) {
		if (source == null || source.length() == 0)
			return;
		JSONObject tank = JSONObject.parse(source);
		if (tank.hasKey("location")) {
			this.location.x = tank.getJSONObject("location").getInt("x");
			this.location.y = tank.getJSONObject("location").getInt("y");
		}
		// if (tank.hasKey("bullets")) {
		// JSONObject bullets = tank.getJSONObject("bullets");
		// for (int i = 0; i < maxAmmo; i++) {
		// if (bullets.hasKey("bullet" + i)) {
		// int x = bullets.getJSONObject("bullet" + i).getInt("x");
		// int y = bullets.getJSONObject("bullet" + i).getInt("y");
		// Bullet b = this.bullets.get(i);
		// b.setX(x);
		// b.setY(y);
		// }
		// }
		// }
	}

	public int getX() {
		return (int) location.x;
	}

	public int getY() {
		return (int) location.y;
	}

	public int getAmmo() {
		return this.ammo;
	}

	public int getSize() {
		return this.size;
	}

	public ArrayList<Bullet> getBullets() {
		return bullets;
	}

	public void setX(int x) {
		location.x = x;
	}

	public void setY(int y) {
		location.y = y;
	}

}
