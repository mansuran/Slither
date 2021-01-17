import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;
import java.applet.AudioClip;

public class Slither extends Applet implements Runnable, KeyListener {
	
	final int LENGTH = 400;
	final int WIDTH = 400;
	Thread thread;
	Snake p;
	Snake p2;
	Apple a;
	boolean gameStarted = false;
	boolean gameOver = false;
	Graphics gfx;
	Image img;
	private AudioClip clip;
	
	public void init() {
		this.resize(LENGTH, WIDTH);
		thread = new Thread(this);
		thread.start();
		p = new Snake();
		p2 = new Snake();
		a = new Apple();
		img = createImage(LENGTH, WIDTH);
		gfx = img.getGraphics();
		this.addKeyListener(this);
		clip = getAudioClip(getDocumentBase(), "snake.mp3.au");
	}
	
	public void paint (Graphics g) {
		gfx.setColor(Color.black);
		gfx.fillRect(0, 0, LENGTH, WIDTH);
		
		int score1 = a.getScore();
		String score = Integer.toString(score1);
		
		if(p.getX() < 5 || p.getX() > 395 || p.getY() < 5 || p.getY() > 395 || p.suicide() || p2.death(p.getX(), p.getY())) {
			gfx.setColor(Color.red);
			gfx.setFont(new Font("TimesRoman", Font.BOLD, 24));
			gfx.drawString("PLAYER 2 WINS", 95, 170);
			gameOver = true;
			clip.stop();
		}
		
		if(p2.getX() < 5 || p2.getX() > 395 || p2.getY() < 5 || p2.getY() > 395 || p2.suicide() || p.death(p2.getX(), p2.getY())) {
			gfx.setColor(Color.red);
			gfx.setFont(new Font("TimesRoman", Font.BOLD, 24));
			gfx.drawString("PLAYER 1 WINS!", 95, 170);
			gameOver = true;
			clip.stop();
		}
		
		else {
			gfx.setColor(Color.red);
			gfx.fillRect(0, 0, 400, 5);
			gfx.fillRect(0, 395, 400, 5);
			gfx.fillRect(0, 0, 5, 400);
			gfx.fillRect(395, 0, 5, 400);
			
			gfx.setColor(Color.white);
			gfx.setFont(new Font("TimesRoman",Font.PLAIN, 12));
			gfx.drawString("Score: " + score, 180, 25);
			
			p.draw(gfx);
			p2.draw(gfx);
			a.draw(gfx);
			
			if (!gameStarted) {
				clip.play();
				gfx.setColor(Color.white);
				gfx.setFont(new Font("TimesRoman", Font.PLAIN, 14));
				gfx.drawString("Press ENTER to Begin..", 100, 130);
				gfx.setColor(Color.white);
				gfx.setFont(new Font("TimesRoman", Font.ITALIC, 48));
				gfx.drawString("SLITHER", 100, 100); 
			}
		}
		g.drawImage(img, 0, 0, this);
	}
	
	public void update (Graphics g) {
		paint(g);
	}
	
	public void repaint (Graphics g) {
		paint(g);
	}
	
	public void run() {
		while(true) {
			if (gameStarted && !gameOver) {
				p.move();
				p2.move();
				if (a.checkCollision(p)) {
					a.move();
					p.sizeUp();
				}
				else if (a.checkCollision(p2)) {
					a.move();
					p2.sizeUp();
				}
			}
			
			repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
		
		
	}

	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			if (p.getYDir() != 1) {
				p.setYDir(-1);
				p.setXDir(0);
			}
		}
		else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			if (p.getYDir() != -1) {
				p.setYDir(1);
				p.setXDir(0);
			}
		}
		else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			if (p.getXDir() != 1) {
				p.setXDir(-1);
				p.setYDir(0);
			}
		}
		else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (p.getXDir() != -1) {
				p.setXDir(1);
				p.setYDir(0);
			}
		}
		else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			gameStarted = true;
		}
		
		else if(e.getKeyCode() == KeyEvent.VK_W) {
			if (p2.getYDir() != 1) {
				p2.setYDir(-1);
				p2.setXDir(0);
			}
		}
		else if (e.getKeyCode() == KeyEvent.VK_S) {
			if (p2.getYDir() != -1) {
				p2.setYDir(1);
				p2.setXDir(0);
			}
		}
		else if (e.getKeyCode() == KeyEvent.VK_A) {
			if (p2.getXDir() != 1) {
				p2.setXDir(-1);
				p2.setYDir(0);
			}
		}
		else if (e.getKeyCode() == KeyEvent.VK_D) {
			if (p2.getXDir() != -1) {
				p2.setXDir(1);
				p2.setYDir(0);
			}
		}
		else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			gameStarted = true;
		}
		
	}

	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP) {

		}
		else if (e.getKeyCode() == KeyEvent.VK_DOWN) {

		}
		else if (e.getKeyCode() == KeyEvent.VK_LEFT) {

		}
		else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {

		}
		
		else if(e.getKeyCode() == KeyEvent.VK_W) {

		}
		else if (e.getKeyCode() == KeyEvent.VK_S) {

		}
		else if (e.getKeyCode() == KeyEvent.VK_A) {

		}
		else if (e.getKeyCode() == KeyEvent.VK_D) {

		}
		
		
	}

	public void keyTyped(KeyEvent arg0) {
		
	}
	
	
	
}
