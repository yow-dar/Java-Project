import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class character extends JPanel implements ActionListener, KeyListener {
	
	private Graphics g;	// new added
	private BufferedImage to_be_painted; // new added
	private Timer t;
	private static double x, y, mx, my;
	private int hp, mp;
	private boolean is_hit, is_right;

	public character() throws IOException {
		t = new Timer(5, this);
		x=0;
		y=0;
		mx=0;
		my=0;
		hp = 100;
		mp = 100;
		is_hit = false;
		is_right = true;
		t.start();
		File file = new File("C:\\Users\\User\\Desktop\\test.jpg"); // new addeed
		to_be_painted = ImageIO.read(file); // new added
		g = to_be_painted.getGraphics(); // new added
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
	}

	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(to_be_painted, (int)x, (int)y, (int)100, (int)100, null); // new added
	}

	public void actionPerformed(ActionEvent e) {
		if (x < 0 || x > 720)
			mx = -mx;
		if (y < 0 || y > 500)
			my = -my;
		x += mx;
		y += my;
		repaint();
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_X);
		else
		{
			mx = Game.changeX(e.getKeyCode());
			my = Game.changeY(e.getKeyCode());
		}
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
		mx = 0;
		my = 0;
	}

	public static double getx() {
		return x;
	}

	public static double gety() {
		return y;
	}

	public int getHP() {
		return hp;
	}

	public void HP(int hp) {
		this.hp = hp;
	}

	public int getMP() {
		return mp;
	}

	public void setMP(int mp) {
		this.mp = mp;
	}

	public boolean getHit() {
		return is_hit;
	}
	
	public void setHit(boolean is_hit) {
		this.is_hit = is_hit;
	}
	
	public boolean getRight() {
		return is_right;
	}
	
	public void setRight(boolean is_right) {
		this.is_right = is_right;
	}
	

	public static void main(String[] args) throws IOException {
		JFrame f = new JFrame("Java Project");
		character i = new character();

		//f.add();
		f.add(i);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(800, 600);
		f.setResizable(false);
	}
}