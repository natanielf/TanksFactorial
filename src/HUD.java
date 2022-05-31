import processing.core.PApplet;

public class HUD {

	private PApplet app;
	private int x, y, w, h;

	public HUD(PApplet app, int x, int y, int frameWidth, int frameHeight) {
		this.app = app;
		this.x = x;
		this.y = y;
		this.w = frameWidth / 3;
		this.h = frameHeight / 5;
	}

	public void paint(String text, int x, int y) {
		app.fill(0, 0, 0);
		app.rect(x - (w / 2), y - (h / 2), w, h);
		app.fill(255, 255, 255);
		app.text(text, x, y);
	}

}
