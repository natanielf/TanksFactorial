import java.io.File;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;

public class Bullet {
	
	private PApplet app;
	private Arena arena;
	private PVector location, velocity;
	private int size, startFrame, rCount, rLimit, moe;
	private ArrayList<Tile> walls;
	private Tile targetBlackTile;

	public Bullet(PApplet app, int tankX, int tankY, int mouseX, int mouseY) {
		this.app = app;
		this.arena = new Arena(app, 1280, 720, new File("./maps/test.txt"));
		this.location = new PVector(tankX, tankY);
		this.velocity = new PVector((mouseX - tankX) / 50, (mouseY - tankY) / 50);
		this.size = 4;
		this.startFrame = app.frameCount;
		this.rCount = 0;
		this.rLimit = 2;
		this.moe = 15;
		walls = arena.getWalls();
		createTargetBlackTile();
		this.velocity = new PVector(mouseX - tankX, mouseY - tankY).normalize().mult(8);
	}

	public void paint() {
		location.x += velocity.x;
		location.y += velocity.y;
		createTargetBlackTile();
		if (this.targetBlackTile != null && collide() == true) {
			if (collideLeft() == true || collideRight() == true) {
				bounceX();
				deadBullet();
			} else if (collideUp() == true || collideDown() == true) {
				bounceY();
				deadBullet();
			}
		}
		app.fill(0);
		app.ellipse(location.x, location.y, size, size);
	}

	public boolean collide() {
		if (collideLeft() == true
				|| collideRight() == true
				|| collideUp() == true
				|| collideDown() == true)
			return true;
		else
			return false;
	}
	
	public void createTargetBlackTile() {
		this.targetBlackTile = null;
		float x = location.x;
		float y = location.y;
		Tile pTargetBlackTile = null;
		for (int i = 0; i < walls.size(); i++) {
			float xBlackTilePos = walls.get(i).getX();
			float yBlackTilePos = walls.get(i).getY();
			int size = walls.get(i).getSize();
			if ((x >= xBlackTilePos && x <= xBlackTilePos + size)
					&& (y >= yBlackTilePos && y <= yBlackTilePos + size))
				pTargetBlackTile = walls.get(i);
		}
		this.targetBlackTile = pTargetBlackTile;
	}
	
	public boolean collideLeft() {
		float x = location.x;
		float y = location.y;
		float xBlackTilePos = targetBlackTile.getX();
		float yBlackTilePos = targetBlackTile.getY();
		int size = targetBlackTile.getSize();
		if ((x + this.size >= xBlackTilePos - this.moe && x + this.size <= xBlackTilePos + this.moe)
				&& (y >= yBlackTilePos && y <= yBlackTilePos + size))
			return true;
		else
			return false;
	}

	public boolean collideRight() {
		float x = location.x;
		float y = location.y;
		float xBlackTilePos = targetBlackTile.getX();
		float yBlackTilePos = targetBlackTile.getY();
		int size = targetBlackTile.getSize();
		if ((x - this.size >= xBlackTilePos + size - this.moe && x - this.size <= xBlackTilePos + size + this.moe)
				&& (y >= yBlackTilePos && y <= yBlackTilePos + size))
			return true;
		else
			return false;
	}

	public boolean collideUp() {
		float x = location.x;
		float y = location.y;
		float xBlackTilePos = targetBlackTile.getX();
		float yBlackTilePos = targetBlackTile.getY();
		int size = targetBlackTile.getSize();
		if ((x >= xBlackTilePos && x <= xBlackTilePos + size)
				&& (y + this.size >= yBlackTilePos - this.moe && y + this.size <= yBlackTilePos + this.moe))
			return true;
		else
			return false;
	}

	public boolean collideDown() {
		float x = location.x;
		float y = location.y;
		float xBlackTilePos = targetBlackTile.getX();
		float yBlackTilePos = targetBlackTile.getY();
		int size = targetBlackTile.getSize();
		if ((x >= xBlackTilePos && x <= xBlackTilePos + size)
				&& (y - this.size >= yBlackTilePos + size - this.moe && y - this.size <= yBlackTilePos + size + this.moe))
			return true;
		else
			return false;
	}
	
//	public boolean ifKilled() {
//		return true;
//	}
	
//	public boolean kill() {
//		
//	}

	public void bounceX() {
		velocity.x *= -1;
		rCount++;
	}

	public void bounceY() {
		velocity.y *= -1;
		rCount++;
	}

	public void deadBullet() {
		if (rCount >= rLimit) {
			location.x = 6000f; 
			velocity.x = 0;
			velocity.y = 0;
		}
	}

	public int getStartFrame() {
		return this.startFrame;
	}
	
	public float getX() {
		return location.x;
	}
	
	public float getY() {
		return location.y;
	}
	
	public int getSize() {
		return this.size;
	}
	
}