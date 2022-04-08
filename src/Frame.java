import java.awt.Graphics;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.MouseInfo;
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

public class Frame extends JPanel implements ActionListener, MouseListener, KeyListener {

	private JFrame f;
	private Timer t;
	private Mouse m;
	private Arena arena;
	private int width, height;
	private boolean ctrlKeyPressed;

	public Frame() {
		this.f = new JFrame("Tanks!");
		this.f.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.f.setMinimumSize(new Dimension(1024, 576));
		this.f.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		this.f.add(this);
		this.f.setResizable(true);
		this.f.addMouseListener(this);
		this.f.addKeyListener(this);
		this.f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.t = new Timer(16, this);
		init();
		this.t.start();
		this.f.setVisible(true);
	}

	public void paint(Graphics g) {
		arena.updateSize(width, height);
		arena.paint(g);
	}

	public static void main(String[] args) {
		new Frame();
	}

	public void init() {
		this.width = f.getWidth();
		this.height = f.getHeight();
		this.m = new Mouse();
		this.ctrlKeyPressed = false;
		this.arena = new Arena(width, height);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (Character.toUpperCase(e.getKeyChar())) {
		case 'W':
			break;
		case 'A':
			break;
		case 'S':
			break;
		case 'D':
			break;
		}
		if (e.getKeyCode() == 17)
			this.ctrlKeyPressed = true;
		if (e.getKeyCode() == 81 && this.ctrlKeyPressed)
			System.exit(0);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == 17)
			this.ctrlKeyPressed = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {

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
