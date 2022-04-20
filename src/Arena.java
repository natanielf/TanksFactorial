import java.awt.Graphics;

public class Arena {
	//mult(iplier) can be used to increase the size of the game screen
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

	public Arena(int fW, int fH) {
		this.rows = 18;
		this.cols = 32;
		createArena();
		this.width = fW;
		this.height = fH;
		this.tileSpacer = 3;
		this.tileSize = (int) 3 * Math.min(width / (cols + (cols * tileSpacer)), height / (rows + (rows * tileSpacer)));
		mult = 1;
	}
	
	public Arena(int fW, int fH, int m) {
		this.rows = 18;
		this.cols = 32;
		createArena();
		this.width = fW;
		this.height = fH;
		this.tileSpacer = 3;
		this.tileSize = (int) 3 * Math.min(width / (cols + (cols * tileSpacer)), height / (rows + (rows * tileSpacer)));
		mult = m;
	}
	
	
	public void paint(Graphics g) {
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				grid[r][c].paint(g, c * tileSize + (tileSpacer * c) * mult, r * tileSize + (tileSpacer * r) * mult, tileSize * mult);
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

	// TODO: Text file input
}
