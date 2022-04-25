import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

public class Img {

	
	public static Image img(String path) {
		Image tempImage;
		try {
			URL imageURL = Frame.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		}
		catch (Exception e) {
			e.printStackTrace();
			tempImage = null;
		}
		return tempImage;
	}

}
