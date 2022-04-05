public class Tile {
	
	/*
	 * tile0 = Ground tile
	 * tile1 = Hole tile
	 * tile2 = Wall tile
	 * tile3 = Fragile wall tile
	 * tile4 = Broken tile
	 * tile5 = Slow ground tile
	 * tile6 = Fast ground tile
	 * tile7 = Ice tile(???)
	*/
	
	protected boolean floor, clear, frail;
	protected double speed;
	private String txtr;
	
	
	public Tile() {
		floor = true;
		clear = true;
		frail = false;
		speed = 1;
		txtr = "tile0";
	}
	
	public Tile(int type) {
		if(type == 0) {
			floor = true;
			clear = true;
			frail = false;
			speed = 0.6;
			txtr = "tile0";
		}
		if(type == 1) {
			floor = true;
			clear = true;
			frail = false;
			speed = 0.6;
			txtr = "tile1";
		}
		if(type == 2) {
			floor = true;
			clear = true;
			frail = false;
			speed = 0.6;
			txtr = "tile2";
		}
		if(type == 3) {
			floor = true;
			clear = true;
			frail = false;
			speed = 0.6;
			txtr = "tile3";
		}
		if(type == 4) {
			floor = true;
			clear = true;
			frail = false;
			speed = 0.6;
			txtr = "tile4";
		}
		if(type == 5) {
			floor = true;
			clear = true;
			frail = false;
			speed = 0.6;
			txtr = "tile5";
		}
		if(type == 6) {
			floor = true;
			clear = true;
			frail = false;
			speed = 0.6;
			txtr = "tile6";
		}
	}
	
	public void frag() {
		if(frail) {
			floor = true;
			clear = true;
			frail = false;
			speed = 0.9;
			txtr = "tile5";
		}
	}
}
