import java.awt.Graphics;

public class Arena {

	private Tile[][] arena;
	private int width, height;
	private int rows, cols;
	private int tileSize, tileSpacer;

	public Arena() {
		this.rows = 18;
		this.cols = 32;
		createArena();
		this.tileSize = 36;
		this.tileSpacer = 3;
	}

	public Arena(int fW, int fH) {
		this.rows = 18;
		this.cols = 32;
		createArena();
		this.width = fW;
		this.height = fH;
		this.tileSpacer = 3;
		this.tileSize = (int) 3 * Math.min(width / (cols + (cols * tileSpacer)), height / (rows + (rows * tileSpacer)));
	}

	public void paint(Graphics g) {
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				arena[r][c].paint(g, c * tileSize + (tileSpacer * c), r * tileSize + (tileSpacer * r), tileSize);
			}
		}
	}

	public void createArena() {
		this.arena = new Tile[rows][cols];
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				arena[r][c] = new Tile();
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
