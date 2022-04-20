import java.awt.Color;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;

public class Mouse {

	private int numButtons;
	private Point location;
	private double x, y;
	private boolean pressed, onWindow;
	private PlayerTank tank;

	public Mouse() {
		this.numButtons = MouseInfo.getNumberOfButtons();
		this.location = MouseInfo.getPointerInfo().getLocation();
		this.x = location.getX();
		this.y = location.getY();
		this.pressed = false;
		this.onWindow = true;
	}

	public void update() {
		this.location = MouseInfo.getPointerInfo().getLocation();
		this.x = location.getX();
		this.y = location.getY();
	}

	public void paintAimLine(Graphics g, int tankX, int tankY) {
		g.setColor(Color.black);
		g.drawLine(tankX + 18, tankY + 18, (int) this.x, (int) this.y - 24);
	}

	@Override
	public String toString() {
		return this.x + ", " + this.y;
	}

	public double getX() {
		return this.x;
	}

	public double getY() {
		return this.y;
	}
	
	public double getXDistance() {
		return this.x - tank.getX();
	}
	
	public double getYDistance() {
		return this.y - tank.getY();
	}
	
	public double getAngleInRadians() {
		return Math.atan(getXDistance() / getYDistance());
	}
	
	public boolean isPressed() {
		return this.pressed;
	}

	public void setPressed(boolean pressed) {
		this.pressed = pressed;
	}

	public boolean isOnWindow() {
		return onWindow;
	}

	public void setOnWindow(boolean onWindow) {
		this.onWindow = onWindow;
	}

}