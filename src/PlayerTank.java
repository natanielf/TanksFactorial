
import processing.core.PApplet;

public class PlayerTank extends Tank {

	public PlayerTank(PApplet app, int x, int y, int size, Arena map) {
		super(app, x, y, size, map);
	}
	
	@Override
	public void setColor() {
		app.fill(20, 115, 250);
	}
	
}
