import java.awt.Graphics;

public class Arena {

	protected Tile[][] arena;
	private int tileSize;

	// Blank Arena *TESTING*
	public Arena() {
		this.arena = new Tile[16][24];
		for (int r = 0; r < 16; r++) {
			for (int c = 0; c < 24; c++) {
				arena[r][c] = new Tile();
			}
		}
		this.tileSize = 24;
	}

	public void paint(Graphics g) {
		for (int r = 0; r < 16; r++) {
			for (int c = 0; c < 24; c++) {
				arena[r][c].paint(g, r * tileSize, c * tileSize);
			}
		}
	}

	// TODO: Text file input

}
