import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import processing.core.PApplet;

public class Arena {

	private PApplet app;
	private Tile[][] grid;
	private int width, height, rows, cols, tileSize, tileSpacer;
	private static final int MARGIN = 30;

	public Arena(PApplet app, int fW, int fH) {
		this.app = app;
		this.rows = 18;
		this.cols = 32;
		this.width = fW;
		this.height = fH;
		this.tileSpacer = 0;
		this.tileSize = Math.min(width / cols, height / rows);
		createArena();
	}

	public Arena(PApplet app, int fW, int fH, File f) {
		this(app, fW, fH);
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

	public void textFileInput(File f) {
		try {
			try (Scanner s = new Scanner(f)) {
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
