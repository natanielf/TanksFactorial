import java.awt.Color;

public class PlayerTank extends Tank {
	public PlayerTank(int x, int y) {
		super(x, y);
		super.color = Color.BLUE;
	}
	
	public void placeMine() { 
		System.out.println("BEEEEM!");
	}
	
	public void shoot() {
		System.out.println("Pew!");
	}
}