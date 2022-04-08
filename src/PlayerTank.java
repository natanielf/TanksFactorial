import java.awt.Color;

public class PlayerTank extends Tank {
	private Color color;
	private int speedX, speedY;
	
	public PlayerTank(int x, int y) {
		super(x, y);
		color = Color.BLUE;
		speedX = 10;
		speedY = 10;
	}
	
	public void moveNorth() {
		y += speedY;
	}
	
	public void moveSouth() {
		y -= speedY;
	}
	
	public void moveEast() {
		x += speedX;
	}
	
	public void moveWest() {
		x -= speedX;
	}
	
	public void placeMine() {
		
	}
	
	public void shoot() {
		
	}
}