import java.awt.Color;
import java.awt.Graphics;

public class Apple {
	double x, y;
	int score = 0;

	public Apple() {
		x = Math.random() * 390;
		y = Math.random() * 390;
	}
	
	public boolean checkCollision(Snake p) {
		if ((Math.abs((x - p.getX())) < 5) && (Math.abs((y - p.getY())) < 5)) {
			score ++;
			return true;
		}
		return false;
	}
	
	public void move() {
		x = Math.random() * 390;
		y = Math.random() * 390;
		
	}
	
	public void draw (Graphics g) {
		g.setColor(Color.white);
		g.fillRect((int)x, (int)y, 5, 5);
	}
	
	public int getScore() {
		return score;
	}
}
