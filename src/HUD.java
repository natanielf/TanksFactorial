import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class HUD {

	private int x, y, w, h;
	private int ammo;
	private double health;
	private Color bkgdColor, fontColor;
	private Font small, medium, large;

	public HUD(int x, int y, int frameW, int frameH) {
		this.x = x;
		this.y = y;
		this.w = frameW / 3;
		this.h = frameH / 5;
		this.ammo = 0;
		this.health = 0;
		this.bkgdColor = new Color(210, 180, 140);
		this.fontColor = Color.white;
		this.small = new Font(Font.SANS_SERIF, Font.PLAIN, 20);
		this.medium = new Font(Font.SANS_SERIF, Font.BOLD, 35);
		this.large = new Font(Font.SANS_SERIF, Font.BOLD, 50);
	}

	public void paint(Graphics g) {
		g.setColor(bkgdColor);
		g.fillRect(x, y, w, h);
	}

}
