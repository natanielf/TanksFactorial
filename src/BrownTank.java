import java.awt.Color;

public class BrownTank extends Tank {
	private Color color;
	private int speedX, speedY;

	public BrownTank(int x, int y, Color color) {
		super(x, y);
		color = new Color(150, 75, 0);
		speedX = 0;
		speedY = 0;
	}
}
