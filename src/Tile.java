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
			case 0:
				app.fill(100, 100, 100);
				break;
			case 1:
				app.fill(50, 50, 50);
				break;
			default:
				app.fill(200, 0, 0);
		}
		app.noStroke();
		app.rect(x, y, size, size);
	}

	public void setType(int type) {
		this.type = type;
	}

}
