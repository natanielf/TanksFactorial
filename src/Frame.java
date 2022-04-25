import java.awt.Graphics;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.net.URL;

public class Frame extends JPanel implements ActionListener, MouseListener, KeyListener {

	private Image i;
	private Img img;

	private JFrame f;
	private Timer t;
	private Mouse m;
	private Arena arena;
	private int width, height;
	private boolean ctrlKeyPressed;
	private PlayerTank tank;
	private int mult;

	public Frame(int m) {
		mult = m;

		this.f = new JFrame("Tanks!");
		this.f.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.f.setMinimumSize(new Dimension(1024, 576));
		this.f.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		this.f.add(this);
		this.f.setResizable(true);
		this.f.addMouseListener(this);
		this.f.addKeyListener(this);
		this.f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.t = new Timer(8, this);
		init();
		this.t.start();
		this.f.setVisible(true);
	}

	public void paint(Graphics g) {
		paintBackground(g);
		arena.paint(g);
		g.drawImage(i, 10, 10, null);
		tank.paint(g);
		m.paintAimLine(g, tank.getX(), tank.getY());
		// small x max: 1366
		// small y max: 705
		// large x max: 1920
		// large y max: 1017
	}

	public static void main(String[] args) {
		new Frame(2);
	}

	public void init() {
		this.width = f.getWidth();
		this.height = f.getHeight();
		this.m = new Mouse();
		this.ctrlKeyPressed = false;
		this.arena = new Arena();
		this.tank = new PlayerTank(10, 10, 0, 10, arena.getWidth(), arena.getHeight());
	}

	public void paintBackground(Graphics g) {
		g.setColor(Color.lightGray);
		g.fillRect(0, 0, 950 * mult, 500 * mult);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (Character.toUpperCase(e.getKeyChar())) {
		case 'W':
			tank.moveNorth();
			break;
		case 'A':
			tank.moveWest();
			break;
		case 'S':
			tank.moveSouth();
			break;
		case 'D':
			tank.moveEast();
			break;
		}
		if (e.getKeyCode() == 17)
			this.ctrlKeyPressed = true;
		if (e.getKeyCode() == 81 && this.ctrlKeyPressed)
			System.exit(0);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (Character.toUpperCase(e.getKeyChar())) {
		case 'W':
			tank.stopY();
			break;
		case 'A':
			tank.stopX();
			break;
		case 'S':
			tank.stopY();
			break;
		case 'D':
			tank.stopX();
			break;
		}
		if (e.getKeyCode() == 17)
			this.ctrlKeyPressed = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		tank.shoot();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		m.setOnWindow(true);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		m.setOnWindow(false);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		m.setPressed(true);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		m.setPressed(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.width = f.getWidth();
		this.height = f.getHeight();
		m.update();
		repaint();
	}
}
