import java.awt.Graphics;
import java.awt.Color;
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
	private int width, height;

	public Frame() {
		this.f = new JFrame("Tanks!");
		this.f.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.f.setMinimumSize(new Dimension(1280, 720));
		this.f.add(this);
		this.f.setResizable(true);
		this.f.setLayout(new GridLayout(1, 2));
		this.f.addMouseListener(this);
		this.f.addKeyListener(this);
		this.f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.t = new Timer(16, this);
		this.t.start();
		this.f.setVisible(true);

		init();
	}

	public void paint(Graphics g) {
		System.out.println("test");
	}

	public static void main(String[] args) {
		new Frame();
	}

	public void init() {
		this.width = f.getWidth();
		this.height = f.getHeight();
		this.m = new Mouse();
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
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

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
		repaint();
	}
}
