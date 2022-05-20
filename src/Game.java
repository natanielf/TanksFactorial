import java.io.File;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;
import processing.core.PSurface;

public class Game extends PApplet {

	private Arena arena;
	private HUD hud;
	private PlayerTank player;
	private Tank opponent;
	int aimX, aimY;
	PImage background;
	static final int FRAMEWIDTH = 400, FRAMEHEIGHT = 234, FRAMERATE = 60;

	public static void main(String[] args) {
		String[] processingArgs = { "Tanks!" };
		Game g = new Game();
		PApplet.runSketch(processingArgs, g);
	}

	public Game() {
		this.arena = new Arena(this, FRAMEWIDTH, FRAMEHEIGHT, new File("./maps/test.txt"));
		this.hud = new HUD(this, 1600, 200, FRAMEWIDTH, FRAMEHEIGHT);
		this.player = new PlayerTank(this, 100, 100, 40);
		createSurface();
	}

	@Override
	public void settings() {
		size(FRAMEWIDTH, FRAMEHEIGHT);
	}

	@Override
	public void setup() {
		surface.setTitle("Tanks!");
		surface.setResizable(true);
		cursor(CROSS);
		rectMode(CENTER);
		frameRate(FRAMERATE);
		PFont font = createFont("DejaVu Sans", 48);
		textFont(font);
		textAlign(CENTER, CENTER);
		background = loadImage("saulGooman.jpg");
	}
	
	//Used to update the frames of the game
	public void draw() {
		background(background);
		arena.paint();
		paintAimLine();
		player.paint();
		hud.paint(player.getAmmo());
		if (frameCount % (FRAMERATE * 2) == 0)
			player.replenishAmmo();
		//Allows aimline to follow cursor
		updateAim(10);
		
	}

	//prints the aim line
	public void paintAimLine() {
		stroke(0, 0, 0);
		strokeWeight(2);
		strokeCap(ROUND);
		line(player.getX(), player.getY(), aimX, aimY);
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
	public void mousePressed() {
		if (mouseButton == LEFT) {
			player.shoot(mouseX, mouseY);
		}
	}
	
	//Updates the aim-line to go towards the cursor
	public void updateAim(int n) {
		if (mouseX-10 >= aimX)
			aimX += n;
		else if (mouseX+10 <= aimX)
			aimX -= n;
		
		if (mouseY-10 >= aimY)
			aimY += n;
		else if (mouseY+10 <= aimY)
			aimY -= n;
		
		if (Math.abs(mouseX - aimX) < 10)
			aimX = mouseX;
		if (Math.abs(mouseY - aimY) < 10)
			aimY = mouseY;
		
	}
	

	@Override
	public void keyReleased() {
		if (key != CODED) {
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
