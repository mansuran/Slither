import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Snake{

	List<Point> snakePoints;
	int xDir, yDir;
	final int STARTSIZE = 20;
	int startx;
	int starty;
	
	public Snake() {
		snakePoints = new ArrayList<Point>();
		startx = (int)Math.floor(Math.random() *300 + 20);
		starty = (int)Math.floor(Math.random() *300 + 20);
		xDir = 0;
		yDir = 0;
		snakePoints.add(new Point(startx, starty));
		for (int i = 1; i < STARTSIZE; i++) {
			snakePoints.add(new Point(startx - i * 5, starty));
		}
	}
	
	public void sizeUp() {
		int size = snakePoints.size() / 5;
		for (int x = 0; x < 50; x++) {
			snakePoints.add(new Point(snakePoints.get(size).getX() - xDir, snakePoints.get(size).getY() - yDir));
		}
	}
	
	public void draw (Graphics g) {
		g.setColor(Color.green);
		for(Point p : snakePoints) {
			g.fillRect(p.getX(), p.getY(), 5, 5);
		}
	}
	
	public void move() {
		if(xDir == 1) {
			Point newp = new Point(snakePoints.get(0).getX() + 1, snakePoints.get(0).getY());
			for(int i = snakePoints.size() - 1; i >= 1; i--) {
				snakePoints.set(i, snakePoints.get(i-1));
			}
			snakePoints.set(0, newp);
		}
		
		if(yDir == 1) {
			Point newp = new Point(snakePoints.get(0).getX(), snakePoints.get(0).getY() + 1);
			for(int i = snakePoints.size() - 1; i >= 1; i--) {
				snakePoints.set(i, snakePoints.get(i-1));
			}
			snakePoints.set(0, newp);
		}
		
		if(xDir == -1) {
			Point newp = new Point(snakePoints.get(0).getX() - 1, snakePoints.get(0).getY());
			for(int i = snakePoints.size() - 1; i >= 1; i--) {
				snakePoints.set(i, snakePoints.get(i-1));
			}
			snakePoints.set(0, newp);
		}
		
		if(yDir == -1) {
			Point newp = new Point(snakePoints.get(0).getX(), snakePoints.get(0).getY() - 1);
			for(int i = snakePoints.size() - 1; i >= 1; i--) {
				snakePoints.set(i, snakePoints.get(i-1));
			}
			snakePoints.set(0, newp);
		}
	}
	
	public boolean suicide() {
		int size = snakePoints.size();
		for (int i = 1; i < size; i++) {
			int curx = snakePoints.get(i).getX();
			int cury = snakePoints.get(i).getY();
			if ((curx == this.getX()) && (cury == this.getY())) {
				return true;
			}
		}
		return false;
	}
	
	public boolean death(int x, int y) {
		int size = snakePoints.size();
		for (int i = 1; i < size; i++) {
			int curx = snakePoints.get(i).getX();
			int cury = snakePoints.get(i).getY();
			if ((curx == x) && (cury == y)) {
				return true;
			}
		}
		return false;
	}
	
	public int getXDir() {
		return xDir;
	}
	
	public int getYDir() {
		return yDir;
	}
	
	public void setYDir(int y) {
		yDir = y;
	}
	
	public void setXDir (int x) {
		xDir = x;
	}
	
	public int getX() {
		return snakePoints.get(0).getX();
	}
	
	public int getY() {
		return snakePoints.get(0).getY();
	}
}
