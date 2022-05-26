import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import processing.core.PApplet;
import processing.core.PVector;

public class Arena {

	private Tile[][] grid;
	protected int width, height, rows, cols, tileSize, tileSpacer;
	protected ArrayList<Tile> walls; //used in textFileInput() and Collision()
	private PApplet app;
	protected static final int MARGIN = 30;

	public Arena(PApplet app, int fW, int fH) {
		this.app = app;
		this.rows = 18;
		this.cols = 32;
		this.width = fW;
		this.height = fH;
		this.tileSize = 36;
		this.tileSpacer = 0;
		
		walls = new ArrayList<Tile>();
		createArena();
	}

	public Arena(File f) {
		this.rows = 18;
		this.cols = 32;
		this.tileSize = 30;
		this.tileSpacer = 1;
		
		walls = new ArrayList<Tile>();
		createArena();
		textFileInput(f);
	}

	public Arena(int fW, int fH) {
		this.rows = 18;
		this.cols = 32;
		createArena();
		this.width = fW;
		this.height = fH;
		this.tileSpacer = 1;
		this.tileSize = (int) 3 * Math.min(width / (cols + (cols * tileSpacer)), height / (rows + (rows * tileSpacer)));
		this.tileSize = (int) Math.min(width / ((cols * tileSpacer)), height / ((rows * tileSpacer)));
		this.tileSpacer = 0;
		this.tileSize = Math.min(width / cols, height / rows);
		
		walls = new ArrayList<Tile>();
		createArena();
	}

	public Arena(PApplet app, int fW, int fH, File f) {
		this(app, fW, fH);
		
		walls = new ArrayList<Tile>();
		textFileInput(f);
	}

	public void paint() {
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				grid[r][c].paint();
			}
		}
	}

	public void createArena() {
		this.grid = new Tile[rows][cols];
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				grid[r][c] = new Tile(app, c * (tileSize + tileSpacer) + MARGIN, r * (tileSize + tileSpacer) + MARGIN,
						tileSize, 0);
			}
		}
	}

	public boolean collide(PVector tank) {
		for (int i = 0; i < rows; i++) {
			if (walls.get(i).collide(tank))
				return true;
		}
		return false;
	}
	
	public void textFileInput(File f) {
		try {
			try (Scanner s = new Scanner(f)) {
				int r = 0;
				while (s.hasNextLine() && r < grid.length) {
					String line = s.nextLine();
					char[] lineArray = line.toCharArray();
					for (int c = 0; c < lineArray.length; c++) {
						int type = 0;
						switch (lineArray[c]) {
						case '_':
							type = 0;
							break;
						case 'X':
							type = 1;
							break;
						default:
							type = -1;
							break;
						}
						grid[r][c].setType(type);
						if (type == 1)
							walls.add(grid[r][c]);
					}
					r++;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Looks like you Better Call Saul!");
		}
	}
	
	public Tile[][] getGrid() {
		return grid;
	}
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
