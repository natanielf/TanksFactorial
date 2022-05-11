import java.io.File;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PSurface;

public class Game extends PApplet {

	private Arena arena;
	private PlayerTank player;
	private Tank opponent;

	public static void main(String[] args) {
		String[] processingArgs = { "Tanks!" };
		Game g = new Game();
		PApplet.runSketch(processingArgs, g);
	}

	public Game() {
		this.arena = new Arena(this, displayWidth, displayHeight, new File("./maps/test.txt"));
		this.player = new PlayerTank(this, 25, 25, 40);
		createSurface();
	}

	@Override
	public void settings() {
		size(1280, 720);
	}

	@Override
	public void setup() {
		surface.setTitle("Tanks!");
		surface.setResizable(true);
		cursor(CROSS);
		rectMode(CENTER);
	}

	public void draw() {
		background(200);
		arena.paint();
		paintAimLine();
		player.update();
		player.paint();
	}

	public void paintAimLine() {
		stroke(0);
		strokeWeight(2);
		strokeCap(ROUND);
		line(player.getX(), player.getY(), mouseX, mouseY);
	}

	@Override
	public void keyPressed() {
		if (key != CODED) {
			switch (Character.toUpperCase(key)) {
			case 'W':
				player.moveNorth();
				break;
			case 'A':
				player.moveWest();
				break;
			case 'S':
				player.moveSouth();
				break;
			case 'D':
				player.moveEast();
				break;
			}
		}
	}

	@Override
	public void keyReleased() {
		switch (Character.toUpperCase(key)) {
		case 'W':
			player.stopY();
			break;
		case 'A':
			player.stopX();
			break;
		case 'S':
			player.stopY();
			break;
		case 'D':
			player.stopX();
			break;
		}
	}

	private void createSurface() {
		this.surface = new PSurface() {

			@Override
			public Object getNative() {
				return null;
			}

			@Override
			public void hideCursor() {

			}

			@Override
			public void initFrame(PApplet arg0) {

			}

			@Override
			public void initOffscreen(PApplet arg0) {

			}

			@Override
			public boolean isStopped() {
				return false;
			}

			@Override
			public PImage loadImage(String arg0, Object... arg1) {
				return null;
			}

			@Override
			public boolean openLink(String arg0) {
				return false;
			}

			@Override
			public void pauseThread() {

			}

			@Override
			public void placePresent(int arg0) {

			}

			@Override
			public void placeWindow(int[] arg0, int[] arg1) {

			}

			@Override
			public void resumeThread() {

			}

			@Override
			public void selectFolder(String arg0, String arg1, File arg2, Object arg3) {

			}

			@Override
			public void selectInput(String arg0, String arg1, File arg2, Object arg3) {

			}

			@Override
			public void selectOutput(String arg0, String arg1, File arg2, Object arg3) {

			}

			@Override
			public void setAlwaysOnTop(boolean arg0) {

			}

			@Override
			public void setCursor(int arg0) {

			}

			@Override
			public void setCursor(PImage arg0, int arg1, int arg2) {

			}

			@Override
			public void setFrameRate(float arg0) {

			}

			@Override
			public void setIcon(PImage arg0) {

			}

			@Override
			public void setLocation(int arg0, int arg1) {

			}

			@Override
			public void setResizable(boolean arg0) {

			}

			@Override
			public void setSize(int arg0, int arg1) {

			}

			@Override
			public void setTitle(String arg0) {

			}

			@Override
			public void setVisible(boolean arg0) {

			}

			@Override
			public void showCursor() {

			}

			@Override
			public void startThread() {

			}

			@Override
			public boolean stopThread() {
				return false;
			}

		};
	}

}
