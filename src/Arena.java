import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Arena {
	// mult(iplier) can be used to increase the size of the game screen
	private int mult;
	protected Tile[][] grid;
	protected int width, height, rows, cols, tileSize, tileSpacer;

	public Arena() {
		this.rows = 18;
		this.cols = 32;
		createArena();
		this.tileSize = 30;
		this.tileSpacer = 1;
		mult = 1;
	}

	public Arena(File f) {
		this.rows = 18;
		this.cols = 32;
		this.tileSize = 30;
		this.tileSpacer = 1;
		this.mult = 1;
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
		mult = 1;
	}

	public Arena(int fW, int fH, File f) {
		this(fW, fH);
		textFileInput(f);
	}

	public Arena(int m) {
		this.rows = 18;
		this.cols = 32;
		createArena();
		this.tileSpacer = 3;
		this.tileSize = (int) 3 * Math.min(width / (cols + (cols * tileSpacer)), height / (rows + (rows * tileSpacer)));
		mult = m;
	}

	public void paint(Graphics g) {
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				grid[r][c].paint(g, c * tileSize + (tileSpacer * c), r * tileSize + (tileSpacer * r), tileSize);
			}
		}
	}

	public void createArena() {
		this.grid = new Tile[rows][cols];
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				grid[r][c] = new Tile();
			}
		}
	}

	public void updateSize(int fW, int fH) {
		this.width = fW;
		this.height = fH;
		this.tileSize = (int) 3 * Math.min(width / (cols + (cols * tileSpacer)), height / (rows + (rows * tileSpacer)));
	}

	public void textFileInput(File f) {
		try {
			Scanner s = new Scanner(f);
			int r = 0;
			while (s.hasNextLine() && r < grid.length) {
				String line = s.nextLine();
				char[] lineArray = line.toCharArray();
				for (int i = 0; i < lineArray.length; i++) {
					char c = lineArray[i];
					int type = 0;
					switch (c) {
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
					grid[r][i].setType(type);
				}
				r++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
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
