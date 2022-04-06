import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Cursor{
	
	private double x, y; // position of the background
	private Image img; 	
	private AffineTransform tx;

	public Cursor(int initX, int initY) {
		img = getImage("/imgs/crosshair.png"); //load the image for crosshair
		x = initX;
		y = initY;

		tx = AffineTransform.getTranslateInstance(0, 0);
		tx = AffineTransform.getTranslateInstance(x, y);
		init(x, y); 				//initialize the location of the image
									//use your variables
	}
	
	public void changePicture(String newFileName) {
		img = getImage(newFileName);
		init(x, y);
	}
	
	public void paint(Graphics g) {
		
		// These are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(img, tx, null);
		
		// Call update method to update the actual picture location
		update();
		g2.drawImage(img, tx, null);
	}
	
	private void update() {
		// User input for mouse movement
		x = MouseInfo.getPointerInfo().getLocation().getX() - 25;
		y = MouseInfo.getPointerInfo().getLocation().getY() - 47;
		
		tx.setToTranslation (x, y);
		tx.scale(0.075, 0.075);
	}
	
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(0.5, 0.5);
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Cursor.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

}
