
import processing.core.PApplet;

public class PlayerTank extends Tank {

	public PlayerTank(PApplet app, int x, int y, int size) {
		super(app, x, y, size);
	}
	
	@Override
	public void setColor() {
		app.fill(20, 115, 250);
	}

}
