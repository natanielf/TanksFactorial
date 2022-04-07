public class Bullet {
	
	protected int x, y, vX, vY;

	public Bullet(int x, int y, int vX, int vY) {
		this.x = x;
		this.y = y;
		this.vX = vX;
		this.vY = vY;
	}
	
	public void bounce(int d) {
		switch(d) {
		
		case 0:
			vY = 0 - vY;
		case 1:
			vX = 0 - vX;
		case 2:
			vY = Math.abs(vY);
		case 3:
			vX = Math.abs(vX);
		}
	}

}
