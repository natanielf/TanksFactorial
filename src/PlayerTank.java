import java.awt.Color;
import java.awt.Graphics;

import processing.core.PApplet;

public class PlayerTank extends Tank {

	public PlayerTank(PApplet app, int x, int y, int size) {
		super(app, x, y, size);
		color = Color.BLUE;
	}

	public void placeMine() {
		System.out.println("BEEEEM!");
	}

	public void shoot() {
		System.out.println("Pew!");
	}

	public void shoot(int mX, int mY) {
		System.out.println("Bullet fired! Mouse location: " + mX + " " + mY);
	}

	public void paint() {
		super.app.strokeWeight(0);
		super.app.rect(x, y, size, size);
	}
	
	public void update() {
		x += vX;
		y += vY;
	}

}