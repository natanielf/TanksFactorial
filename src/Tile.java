import processing.core.PApplet;
import processing.core.PVector;

public class Tile {

	private PApplet app;
	private PVector location;
	private int size, type, moe;

	public Tile(PApplet app, int x, int y, int size, int type) {
		this.app = app;
		this.location = new PVector(x, y);
		this.size = size;
		this.type = type;
		this.moe = 7;
	}

	public void paint() {
		switch (type) {
		case 0:
			app.fill(200);
			break;
		case 1:
			app.fill(50);
			break;
		default:
			app.fill(200, 0, 0);
			break;
		}
		app.noStroke();
		app.rect(location.x, location.y, size, size);
	}

	public boolean collide(PVector tank, int tSize) {
		return (tank.x + tSize >= location.x && tank.x - tSize <= location.x + size)
			&& (tank.y + tSize >= location.y && tank.y - tSize <= location.y + size);
	}
	
	public boolean collideLeft(PVector tank, int tSize) {
		float tX = tank.x;
		float tY = tank.y;
		float xBlackTilePos = location.x;
		float yBlackTilePos = location.y;
		if ((tX + tSize >= xBlackTilePos - this.moe && tX + tSize <= xBlackTilePos + this.moe)
				&& (tY >= yBlackTilePos && tY <= yBlackTilePos + size))
			return true;
		else
			return false;
	}
	
	public boolean collideRight(PVector tank, int tSize) {
		float tX = tank.x;
		float tY = tank.y;
		float xBlackTilePos = location.x;
		float yBlackTilePos = location.y;
		if ((tX - tSize >= xBlackTilePos + size - this.moe && tX - tSize <= xBlackTilePos + size + this.moe)
				&& (tY >= yBlackTilePos && tY <= yBlackTilePos + size))
			return true;
		else
			return false;
	}
	
	public boolean collideUp(PVector tank, int tSize) {
		float tX = tank.x;
		float tY = tank.y;
		float xBlackTilePos = location.x;
		float yBlackTilePos = location.y;
		if ((tX >= xBlackTilePos && tX <= xBlackTilePos + size)
				&& (tY + tSize >= yBlackTilePos - this.moe && tY + tSize <= yBlackTilePos + this.moe))
			return true;
		else
			return false;
	}

	public boolean collideDown(PVector tank, int tSize) {
		float tX = tank.x;
		float tY = tank.y;
		float xBlackTilePos = location.x;
		float yBlackTilePos = location.y;
		if ((tX >= xBlackTilePos && tX <= xBlackTilePos + size)
				&& (tY - tSize >= yBlackTilePos + size - this.moe && tY - tSize <= yBlackTilePos + size + this.moe))
			return true;
		else
			return false;
	}

	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	public int getSize() {
		return size;
	}
	
	public float getX() {
		return location.x;
	}
	
	public float getY() {
		return location.y;
	}

}
