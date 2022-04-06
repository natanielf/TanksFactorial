import java.awt.Color;

public class GrayTank extends Tank {
	private Color color;
	private int speedX, speedY;
	
	public GrayTank(int x, int y, Color color) {
		super(x, y);
		color = Color.GRAY;
	}
}
