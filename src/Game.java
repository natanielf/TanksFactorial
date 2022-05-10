import processing.core.PApplet;

public class Game extends PApplet {

	public static void main(String[] args) {
		String[] processingArgs = { "Tanks!" };
		Game test = new Game();
		PApplet.runSketch(processingArgs, test);
	}

	@Override
	public void settings() {
		// size(displayWidth, displayHeight);
		fullScreen();
	}

	@Override
	public void setup() {
		rectMode(CENTER);
		noStroke();
		fill(0, 102, 153, 204);
	}

	public void draw() {
		cursor(CROSS);
		background(200);
		rect(50, 50, 40, 40);
		stroke(0);
		line(60, 60, mouseX, mouseY);
	}

}
