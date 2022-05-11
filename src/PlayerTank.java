import java.awt.Color;
import java.awt.Graphics;

public class PlayerTank extends Tank {
	public PlayerTank(int x, int y) {
		super(x, y);
		super.color = Color.BLUE;
	}

	public PlayerTank(int pX, int pY, int minXVal, int minYVal, int maxXVal, int maxYVal) {
		super(pX, pY, minXVal, minYVal, maxXVal, maxYVal);
		super.color = Color.BLUE;
	}

	public void placeMine() {
		System.out.println("BEEEEM!");
	}

	public void shoot() {
		
		// TODO: call the paint method
		
		System.out.println("Pew!");
		// Graphics g = new Graphics();
		// paint(g);
	}
	
	public void paint(Graphics g) {
		
		// paint it to the frame
	}
}