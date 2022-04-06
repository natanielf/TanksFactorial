public class Tile {

	/*
	 * tile0 = Ground tile
	 * tile1 = Hole tile
	 * tile2 = Wall tile
	 * tile3 = Fragile wall tile
	 * tile4 = Broken tile
	 * tile5 = Slow ground tile
	 * tile6 = Fast ground tile
	 * tile7 = Powerup tile
	 * tile8 = Ice tile(???)
	 */
	
	int type;
	protected boolean floor, clear, frail;
	protected double speed;
	private String txtr;

	public Tile() {
		type = 0;
		floor = true;
		clear = true;
		frail = false;
		speed = 1;
		txtr = "tile0";
	}

	public void frag() {
		if (frail) {
			floor = true;
			clear = true;
			frail = false;
			speed = 0.9;
			txtr = "tile5";
		}
	}
}
