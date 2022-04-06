import java.awt.Color;

public class MainTank extends Tank {
	private Color color;
	private int speedX, speedY;
	
	public MainTank(int x, int y, Color color) {
		super(x, y);
		color = Color.BLUE;
	}
}