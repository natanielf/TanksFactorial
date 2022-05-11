import java.io.File;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PSurface;

public class Game extends PApplet {

	private Arena arena;
	private PlayerTank player;
	// private PSurface surface;

	public static void main(String[] args) {
		String[] processingArgs = { "Tanks!" };
		Game g = new Game();
		PApplet.runSketch(processingArgs, g);
	}

	public Game() {
		this.arena = new Arena(this, displayWidth, displayHeight, new File("./maps/test.txt"));
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
		rectMode(CENTER);
		noStroke();
		fill(10, 10, 20, 20);
	}

	public void draw() {
		cursor(CROSS);
		background(200);
		rect(50, 50, 40, 40);
		stroke(0, 0, 0);
		line(60, 60, mouseX, mouseY);
		arena.paint();
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
