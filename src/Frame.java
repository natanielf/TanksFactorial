import java.awt.Graphics;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Frame extends JPanel implements ActionListener, MouseListener, KeyListener {

	private Img img;

	private JFrame f;
	private Timer t;
	private Mouse m;
	private Arena arena;
	private int width, height;
	private boolean ctrlKeyPressed;
	private PlayerTank tank;
	private HUD hud;

	private JPanel startScreen;
	private JButton b1, b2;
	private ActionListener startBtnAction;
	private boolean singlePlayer, inProgress;
	private int level;

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
		createStartScreen();
		this.t.start();
		this.f.setVisible(true);
	}

	public void paint(Graphics g) {
		paintBackground(g);
		if (arena != null)
			arena.paint(g);
		if (tank != null) {
			tank.paint(g);
			m.paintAimLine(g, tank.getX(), tank.getY());
		}
		if (hud != null)
			hud.paint(g);
	}

	public static void main(String[] args) {
		new Frame();
	}

	public void init() {
		this.width = f.getWidth();
		this.height = f.getHeight();
		this.m = new Mouse();
	}

	public void paintBackground(Graphics g) {
		g.setColor(Color.lightGray);
		g.fillRect(0, 0, width, height);
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
		if (tank != null)
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
		f.repaint();
		f.revalidate();
	}

	public void setGameType(ActionEvent e) {
		this.singlePlayer = e.getActionCommand().equals("Single-Player");
		f.remove(startScreen);
		f.revalidate();
		f.repaint();
		startGame();
	}

	public void startGame() {
		this.level = 1;
		this.inProgress = true;
		this.ctrlKeyPressed = false;
		this.arena = new Arena(width, height, new File("./maps/test.txt"));
		this.tank = new PlayerTank(50, 50, 5, 5, arena.getWidth(), arena.getHeight());
		this.hud = new HUD(500, 500, width, height);
		f.revalidate();
		f.repaint();
	}

	public void createStartScreen() {
		this.startScreen = new JPanel();
		startScreen.setBounds(10, 10, 200, 200);
		startScreen.setSize(new Dimension(500, 800));

		startScreen.setPreferredSize(new Dimension(500, 800));
		startScreen.setBackground(Color.darkGray);

		this.b1 = new JButton("Single-Player");
		b1.setPreferredSize(new Dimension(200, 40));
		startScreen.add(b1);
		this.b2 = new JButton("Multiplayer");
		b2.setPreferredSize(new Dimension(200, 40));
		startScreen.add(b2);

		this.startBtnAction = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setGameType(e);
			}
		};

		b1.addActionListener(startBtnAction);
		b2.addActionListener(startBtnAction);

		f.add(startScreen, BorderLayout.CENTER);
		f.revalidate();
		f.repaint();
	}

}
