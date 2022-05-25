import java.io.File;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;

public class Bullet {
	
	private PApplet app;
	private Arena arena;
	private PVector location, velocity, minVelocity;
	private int size, startFrame, rCount, rLimit;
	private ArrayList<Tile> blackTiles;

	public Bullet(PApplet app, int tankX, int tankY, int mouseX, int mouseY) {
		this.app = app;
		this.arena = new Arena(app, 1280, 720, new File("./maps/test.txt"));
		this.location = new PVector(tankX, tankY);
		this.velocity = new PVector((mouseX - tankX) / 50, (mouseY - tankY) / 50);
		this.minVelocity = new PVector(velocity.x / 10, velocity.y / 10);
		this.size = 8;
		this.startFrame = app.frameCount;
		this.rCount = 0;
		this.rLimit = 2;
		createNumBlackTiles();
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
		// pass in the arena, traverse the arena, check for black tiles, if black, then
		// bounce!
		if (ifHitBlack(location)) {
			if (location.x == 0 || location.x == 1280) {
				bounceX();
				deadBullet();
			}
			if (location.y == 0 || location.y == 720) {
				bounceY();
				deadBullet();
			}
		}
		app.fill(0, 0, 0);
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
	
	public boolean ifHitBlack(PVector location) {
		for (int i = 0; i < blackTiles.size(); i++) {
			if (((int) location.x >= (int) blackTiles.get(i).getLocation().x
					&& (int) location.x <= (int) blackTiles.get(i).getLocation().x + (int) blackTiles.get(i).getSize())
				&& ((int) location.y >= (int) blackTiles.get(i).getLocation().y
					&& (int) location.y <= (int) blackTiles.get(i).getLocation().y + (int) blackTiles.get(i).getSize())) {
				return true;
			}
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

}
