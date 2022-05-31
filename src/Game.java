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
	private Tank player;
	private Tank opponent;
	private JSONObject config;
	private Server server;
	private Client client;
	private int moe;
	static final int FRAMEWIDTH = 1280, FRAMEHEIGHT = 720, FRAMERATE = 60;

	public static void main(String[] args) {
		String[] processingArgs = { "Tanks!" };
		Game g = new Game(args);
		PApplet.runSketch(processingArgs, g);
	}

	public Game(String[] args) {
		this.arena = new Arena(this, FRAMEWIDTH, FRAMEHEIGHT, new File("./maps/test.txt"));
		this.hud = new HUD(this, 1400, 150, FRAMEWIDTH, FRAMEHEIGHT);
		this.player = new Tank(this, 100, 100, 18, arena);
		this.moe = 7;
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
		PFont font = createFont("Arial", 48);
		textFont(font);
		textAlign(CENTER, CENTER);
	}

	// Used to update the frames of the game
	@Override
	public void draw() {
		background(150);
		arena.paint();
		player.paint();
		paintOpponent();
		hud.paint(player.getAmmo());
		handleConnection();
		playerKillOpponent();
	}
	
	// TODO: Fix kill mechanics
	public void playerKillOpponent() {
		ArrayList<Bullet> pBullets = player.getBullets();
		if (pBullets != null)  {
			for (int i = 0; i < pBullets.size(); i++) {
				float bX = pBullets.get(i).getX();
				float bY = pBullets.get(i).getY();
				float oX = opponent.getX();
				float oY = opponent.getY();
				int bRadius = pBullets.get(i).getSize() / 2;
				int oTankRadius = opponent.getSize() / 2;
				float distance = (float) Math.sqrt(Math.pow(bX - oX, 2) + Math.pow((bY - oY), 2));
				if ((distance >= bRadius + oTankRadius - this.moe) && (distance <= bRadius + oTankRadius + this.moe)) {
					opponent.setX(0);
					opponent.setY(0);
				}
			}
		}
	}
	
	// TODO:
	public void playerKillItself() {
		
	}
	
	// TODO:
	public void opponentKillPlayer() {
		
	}
	
	// TODO:
	public void opponentKillItself() {
		
	}

	public void handleConnection() {
		if (!config.getBoolean("singlePlayer")) {
			if (config.getBoolean("server")) {
				// opponent.fromJSON(server.getData());
				server.sendData(player.toJSON());
			} else {
				opponent.fromJSON(client.getData());
				client.sendData(player.toJSON());
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
			this.opponent = new Tank(this, 1050, 525, 18, arena);
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
			this.opponent = new Tank(this, 1050, 525, 18, arena);
		}
	}

	public void paintOpponent() {
		if (opponent != null) {
			opponent.paint();
		}
	}

	// Paints the aim line from the tank to the cursor
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
	public void mousePressed() {
		if (mouseButton == LEFT) {
			player.shoot(mouseX, mouseY);
		}
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
