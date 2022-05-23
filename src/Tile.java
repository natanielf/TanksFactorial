import processing.core.PApplet;
import processing.core.PVector;

public class Tile {

	private PApplet app;
	private PVector location;
	private int size, type;

	public Tile(PApplet app, int x, int y, int size, int type) {
		this.app = app;
		this.location = new PVector(x, y);
		this.size = size;
		this.type = type;
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

	public boolean collide(int x, int y) {
		return (x > location.x || x < location.x + size) && (y > location.y || y < location.y + size);
	}

	public void setType(int type) {
		this.type = type;
	}

}
