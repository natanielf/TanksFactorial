import java.awt.MouseInfo;
import java.awt.Point;

public class Mouse {

	private int numButtons;
	private Point location;
	private double x, y;
	private boolean pressed;

	public Mouse() {
		this.numButtons = MouseInfo.getNumberOfButtons();
		this.location = MouseInfo.getPointerInfo().getLocation();
		this.x = location.getX();
		this.y = location.getY();
		this.pressed = false;
	}

	public double getX() {
		return this.x;
	}

	public double getY() {
		return this.y;
	}

	public boolean isPressed() {
		return this.pressed;
	}

	public void setPressed(boolean pressed) {
		this.pressed = pressed;
	}

}
