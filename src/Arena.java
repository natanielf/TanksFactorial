public class Arena {
	
	protected Tile[][] arena;
	
	//Blank Arena *TESTING*
	public Arena() {
		arena = new Tile[16][24];
		
		for(int r = 0; r < 16; r++) {
			for(int c = 0; c < 24; c++) {
				arena[r][c] = new Tile();
			}
		}
	}
	//TODO: Text file input
	
}
 