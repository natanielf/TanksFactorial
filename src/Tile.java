import processing.core.PApplet;

public class Tile {

	private PApplet app;

	private int x, y, size, type;

	public Tile(PApplet app, int x, int y, int size, int type) {
		this.app = app;
		this.x = x;
		this.y = y;
		this.size = size;
		this.type = type;
	}

	public void paint() {
		switch (type) {
			case -1:
				app.stroke(200, 0, 0);
			case 0:
				app.stroke(200, 200, 200);
			case 1:
				app.stroke(100, 100, 100);
		}
		app.rect(x, y, size, size);
	}

	public void setType(int type) {
		this.type = type;
	}

}
