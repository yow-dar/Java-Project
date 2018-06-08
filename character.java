import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class character extends JPanel implements ActionListener, KeyListener {

	private Graphics g = null;
	private Timer t = new Timer(5, this);
	private double x = 0, y = 0, mx = 0, my = 0;
	private int hp = 100, mp = 100;
	private boolean is_hit = false;

	public character() {
		g = getGraphics();
		t.start();
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
	}

	public void paint(Graphics g) {
		super.paint(g);
		g.fillOval((int) x, (int) y, 60, 60);
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
		mx = Move.changeX(e.getKeyCode());
		my = Move.changeY(e.getKeyCode());
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
		mx = 0;
		my = 0;
	}

	public double getX() {
		return x;
	}

	public double getY() {
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

	public static void main(String[] args) {
		JFrame f = new JFrame("Java Project");
		character i = new character();

		f.add(i);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(800, 600);
		f.setResizable(false);
	}
}