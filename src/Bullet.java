import java.io.File;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;

public class Bullet {

	private PApplet app;
	private Arena arena;
	private PVector location, velocity;
	private int size, startFrame, rCount, rLimit, type;
	private ArrayList<Tile> blackTiles;
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
		this.type = 0;
		createNumBlackTiles();
		this.targetBlackTile = blackTiles.get(0);
		this.velocity = new PVector(mouseX - tankX, mouseY - tankY).normalize().mult(8);
	}

	public void paint() {
		location.x += velocity.x;
		location.y += velocity.y;
		getTargetBlackTile(location);
		if (collide(location) == true) {
			
			if (collideLeft(location) == true || collideRight(location) == true) {
				setType(0);
			}
			if (collideUp(location) == true || collideDown(location) == true) {
				setType(1);
			}
			switch (type) {
			case 0:
				deadBullet();
				bounceX();
			case 1:
				deadBullet();
				bounceY();
			}
		}
//		getTargetBlackTile(location);
//		if (collideLeft(location) == true || collideRight(location) == true) {
//			bounceX();
//			deadBullet();
//		}
//		if (collideUp(location) == true || collideDown(location) == true) {
//			bounceY();
//			deadBullet();
//		}
		app.fill(0);
		app.ellipse(location.x, location.y, size, size);
	}

	public void createNumBlackTiles() {
		blackTiles = new ArrayList<Tile>();
		Tile[][] grid = arena.getGrid();
		for (int r = 0; r < grid.length; r++) {
			for (int c = 0; c < grid[0].length; c++) {
				if (grid[r][c].getType() == 1) {
					blackTiles.add(grid[r][c]);
				}
			}
		}
	}

	public boolean collide(PVector location) {
		if (collideLeft(location) == true
				|| collideRight(location) == true
				|| collideUp(location) == true
				|| collideDown(location) == true) {
			return true;
		} else {
			return false;
		}
	}
	
	// TODO: Make it so that getTargetBlackTile method works
	public void getTargetBlackTile(PVector location) {
		int x = (int) location.x;
		int y = (int) location.y;
		Tile pTargetBlackTile = blackTiles.get(0);
		for (int i = 0; i < blackTiles.size(); i++) {
			int xBlackTilePos = (int) blackTiles.get(i).getLocation().x;
			int yBlackTilePos = (int) blackTiles.get(i).getLocation().y;
			int size = blackTiles.get(i).getSize();
			if ((x >= xBlackTilePos && x <= xBlackTilePos + size)
					&& (y >= yBlackTilePos && y <= yBlackTilePos + size)) {
				pTargetBlackTile = blackTiles.get(i);
			}
		}
		this.targetBlackTile = pTargetBlackTile;
	}

	public boolean collideLeft(PVector location) {
		int x = (int) location.x;
		int y = (int) location.y;
		int xBlackTilePos = (int) targetBlackTile.getLocation().x;
		int yBlackTilePos = (int) targetBlackTile.getLocation().y;
		int size = targetBlackTile.getSize();
		if ((x >= xBlackTilePos) && (y >= yBlackTilePos && y <= yBlackTilePos + size)) {
			return true;
		}
		return false;
	}

	public boolean collideRight(PVector location) {
		int x = (int) location.x;
		int y = (int) location.y;
		int xBlackTilePos = (int) targetBlackTile.getLocation().x;
		int yBlackTilePos = (int) targetBlackTile.getLocation().y;
		int size = targetBlackTile.getSize();
		if ((x <= xBlackTilePos) && (y >= yBlackTilePos && y <= yBlackTilePos + size)) {
			return true;
		}
		return false;
	}

	public boolean collideUp(PVector location) {
		int x = (int) location.x;
		int y = (int) location.y;
		int xBlackTilePos = (int) targetBlackTile.getLocation().x;
		int yBlackTilePos = (int) targetBlackTile.getLocation().y;
		int size = targetBlackTile.getSize();
		if ((x >= xBlackTilePos && x <= xBlackTilePos + size) && (y >= yBlackTilePos)) {
			return true;
		}
		return false;
	}

	public boolean collideDown(PVector location) {
		int x = (int) location.x;
		int y = (int) location.y;
		int xBlackTilePos = (int) targetBlackTile.getLocation().x;
		int yBlackTilePos = (int) targetBlackTile.getLocation().y;
		int size = targetBlackTile.getSize();
		if ((x >= xBlackTilePos && x <= xBlackTilePos + size) && (y <= yBlackTilePos)) {
			return true;
		}
		return false;
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

	public int getX() {
		return (int) location.x;
	}

	public void setX(int x) {
		location.x = x;
	}

	public int getY() {
		return (int) location.y;
	}

	public void setY(int y) {
		location.y = y;
	}

	public void setType(int type) {
		this.type = type;
	}

}
