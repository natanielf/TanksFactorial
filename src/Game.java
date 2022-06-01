import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;
import processing.core.PSurface;
import processing.data.JSONObject;

public class Game extends PApplet {

	private Arena arena;
	private HUD hud;
	private Tank player1, player2;
	private JSONObject config;
	private Server server;
	private Client client;
	private int moe;
	static final int FRAMEWIDTH = 1280, FRAMEHEIGHT = 720, FRAMERATE = 60;
	private int sum;

	public static void main(String[] args) {
		String[] processingArgs = { "Tanks!" };
		Game g = new Game(args);
		PApplet.runSketch(processingArgs, g);
	}

	public Game(String[] args) {
		this.arena = new Arena(this, FRAMEWIDTH, FRAMEHEIGHT, new File("./maps/test.txt"));
		this.hud = new HUD(this, 1400, 150, FRAMEWIDTH, FRAMEHEIGHT);
		this.player1 = new Tank(this, 100, 100, 18, "blue", arena);
		this.moe = 7;
		this.sum = 0;
		createSurface();
		parseArgs(args);
		parseConfig();
	}

	@Override
	public void settings() {
		size(FRAMEWIDTH, FRAMEHEIGHT);
	}

	@Override
	public void setup() {
		surface.setTitle("Tanks!");
		surface.setResizable(true);
		surface.setIcon(loadImage("./img/icon.png"));
		cursor(CROSS);
		rectMode(CORNER);
		ellipseMode(RADIUS);
		frameRate(FRAMERATE);
		textFont(createFont("Arial", 48));
		textAlign(CENTER, CENTER);
	}

	// Used to update the frames of the game
	public void draw() {
		if (sum < 600) {
			background(150);
			arena.paint();
			player1.paint();
			paintOpponent();
			hud.paint("AMMO: " + player1.getAmmo(), 1400, 300);
			hud.paint("TIME: " + (600 - sum) / 60, 1400, 500);
			handleConnection();
			playerKillOpponent();
			playerKillItself();
			sum++;
		}
	}

	public void playerKillOpponent() {
		ArrayList<Bullet> pBullets = player1.getBullets();
		if (pBullets != null) {
			for (int i = 0; i < pBullets.size(); i++) {
				float bX = pBullets.get(i).getX();
				float bY = pBullets.get(i).getY();
				float oX = player2.getX();
				float oY = player2.getY();
				int bRadius = pBullets.get(i).getSize() / 2;
				int oTankRadius = player2.getSize() / 2;
				float distance = (float) Math.sqrt(Math.pow(bX - oX, 2) + Math.pow((bY - oY), 2));
				if ((distance >= bRadius + oTankRadius - this.moe) && (distance <= bRadius + oTankRadius + this.moe)) {
					sum = 0;
					player2.setX(1050);
					player2.setY(525);
				}
			}
		}
	}

	public void playerKillItself() {
		ArrayList<Bullet> pBullets = player1.getBullets();
		int j = 1;
		if (pBullets != null) {
			for (int i = 0; i < pBullets.size(); i++) {
				float bX = pBullets.get(i).getX();
				float bY = pBullets.get(i).getY();
				float pX = player1.getX();
				float pY = player1.getY();
				int bRadius = pBullets.get(i).getSize() / 2;
				int pTankRadius = player1.getSize() / 2;
				float distance = (float) Math.sqrt(Math.pow(bX - pX, 2) + Math.pow((bY - pY), 2));
				if ((distance >= bRadius + pTankRadius - this.moe) && (distance <= bRadius + pTankRadius + this.moe)) {
					j++;
					// player1.setX(6000);
					// player1.setY(6000);
				}
			}

		}
		if (j >= 3) {
			player1.setX(100);
			player1.setY(100);
		}
	}

	public void opponentKillPlayer() {
		ArrayList<Bullet> pBullets = player1.getBullets();
		if (pBullets != null) {
			for (int i = 0; i < pBullets.size(); i++) {
				float bX = pBullets.get(i).getX();
				float bY = pBullets.get(i).getY();
				float pX = player1.getX();
				float pY = player1.getY();
				int bRadius = pBullets.get(i).getSize() / 2;
				int pTankRadius = player1.getSize() / 2;
				float distance = (float) Math.sqrt(Math.pow(bX - pX, 2) + Math.pow((bY - pY), 2));
				if ((distance >= bRadius + pTankRadius - this.moe) && (distance <= bRadius + pTankRadius + this.moe)) {
					player2.setX(1050);
					player2.setY(525);
				}
			}
		}
	}

	public void opponentKillItself() {
		ArrayList<Bullet> pBullets = player1.getBullets();
		if (pBullets != null) {
			for (int i = 0; i < pBullets.size(); i++) {
				float bX = pBullets.get(i).getX();
				float bY = pBullets.get(i).getY();
				float oX = player2.getX();
				float oY = player2.getY();
				int bRadius = pBullets.get(i).getSize() / 2;
				int oTankRadius = player2.getSize() / 2;
				float distance = (float) Math.sqrt(Math.pow(bX - oX, 2) + Math.pow((bY - oY), 2));
				if ((distance >= bRadius + oTankRadius - this.moe) && (distance <= bRadius + oTankRadius + this.moe)) {
					player2.setX(1050);
					player2.setY(525);
				}
			}
		}
	}

	public void handleConnection() {
		if (!config.getBoolean("singlePlayer")) {
			if (config.getBoolean("server")) {
				// opponent.fromJSON(server.getData());
				server.sendData(player1.toJSON());
			} else {
				player2.fromJSON(client.getData());
				client.sendData(player1.toJSON());
			}
		}

	}

	public void parseArgs(String[] args) {
		this.config = new JSONObject();
		for (String arg : args) {
			switch (arg) {
				case "--single":
					config.put("singlePlayer", true);
					break;
				case "--server":
					config.put("singlePlayer", false);
					config.put("server", true);
					break;
				case "--client":
					config.put("singlePlayer", false);
					config.put("server", false);
					break;
				default:
					if (arg.contains("."))
						config.put("address", arg);
					break;
			}
		}
		// Default to a single player game if no arguments are provided
		if (!config.hasKey("singlePlayer")) {
			config.put("singlePlayer", true);
		}
	}

	public void parseConfig() {
		if (config.getBoolean("singlePlayer")) {
			this.player2 = new Tank(this, 1050, 525, 18, "red", arena);
		} else {
			if (config.getBoolean("server")) {
				try {
					this.server = new Server(80);
				} catch (UnknownHostException e) {
					System.err.println(e);
				}
			} else {
				try {
					this.client = new Client(config.getString("address"), 80);
				} catch (IOException e) {
					System.err.println(e);
				}
			}
			this.player2 = new Tank(this, 1050, 525, 18, "red", arena);
		}
	}

	public void paintOpponent() {
		if (player2 != null) {
			player2.paint();
		}
	}

	// Paints the aim line from the tank to the cursor
	public void paintAimLine() {
		stroke(0);
		strokeWeight(2);
		strokeCap(ROUND);
		line(player1.getX(), player1.getY(), mouseX, mouseY);
	}

	@Override
	public void keyPressed() {
		if (key != CODED) {
			switch (Character.toUpperCase(key)) {
				case 'W':
					player1.moveNorth();
					break;
				case 'A':
					player1.moveWest();
					break;
				case 'S':
					player1.moveSouth();
					break;
				case 'D':
					player1.moveEast();
					break;
				case 'I':
					player2.moveNorth();
					break;
				case 'J':
					player2.moveWest();
					break;
				case 'K':
					player2.moveSouth();
					break;
				case 'L':
					player2.moveEast();
					break;
			}
		}
	}

	@Override
	public void mousePressed() {
		if (mouseButton == LEFT) {
			player1.shoot(mouseX, mouseY);
		}
	}

	@Override
	public void keyReleased() {
		if (key != CODED) {
			switch (Character.toUpperCase(key)) {
				case 'W':
					player1.stopY();
					break;
				case 'A':
					player1.stopX();
					break;
				case 'S':
					player1.stopY();
					break;
				case 'D':
					player1.stopX();
					break;
				case 'I':
					player2.stopY();
					break;
				case 'J':
					player2.stopX();
					break;
				case 'K':
					player2.stopY();
					break;
				case 'L':
					player2.stopX();
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
