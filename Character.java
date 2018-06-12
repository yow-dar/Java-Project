package game;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.*;

public abstract class Character extends JPanel implements Runnable {

	private Graphics g;
	private Timer t;
	private static double x;
	private static double y;
	private double mx;
	private double my;
	private int hp, mp;
	protected final int normalDamage = 10;
	protected final int skillDamage = 10;
	private boolean is_hit;
	private boolean is_right;
	protected BufferedImage to_be_painted;

	public Character() {
		g = null;
		x = 0;
		y = 0;
		mx = 0;
		my = 0;
		hp = 100;
		mp = 100;
		is_hit = false;
		is_right = true;
		g = getGraphics();
		t.start();
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		g = to_be_painted.getGraphics(); // new added
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

	/*
	 * public void keyPressed(KeyEvent e) { mx = Move.changeX(e.getKeyCode()); my =
	 * Move.changeY(e.getKeyCode()); }
	 * 
	 * public void keyTyped(KeyEvent e) { }
	 * 
	 * public void keyReleased(KeyEvent e) { mx = 0; my = 0; }
	 */

	public void setx(double x) {
		Character.x = x;
	}

	public static double getx() {
		return x;
	}

	public void sety(double y) {
		Character.y = y;
	}

	public static double gety() {
		return y;
	}

	public int getHP() {
		return hp;
	}

	public void setHP(int hp) {
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

	public void setHit(boolean is_hit) throws InterruptedException {
		this.is_hit = is_hit;
		if (is_hit) {
			TimeUnit.SECONDS.sleep(1);
			this.is_hit = false;
		}
	}

	public boolean getRight() {
		return is_right;
	}

	public void setRight(boolean is_right) {
		this.is_right = is_right;
	}

	public void changeImage(BufferedImage[] images, Character character) throws InterruptedException {
		Graphics g = getGraphics();
		for (int i = 0; i < images.length; ++i) {
			to_be_painted = images[i];
			if (character instanceof Character_Davis) {// ball
				if (images.toString().equals("davis_ball")) {
					paint(to_be_painted.getGraphics());
				} else if (images.toString().equals("davis_ball_inverse")) {
					paint(to_be_painted.getGraphics());
				}
				// davis stand
				else if (images.toString().equals("davis_stand")) {
					paint(to_be_painted.getGraphics());
				} else if (images.toString().equals("davis_stand_inverse")) {
					paint(to_be_painted.getGraphics());
				}
				// David hit
				else if (images.toString().equals("davis_hit")) {
					paint(to_be_painted.getGraphics());
				} else if (images.toString().equals("davis_hit_inverse")) {
					paint(to_be_painted.getGraphics());
				}
				// davis attack
				else if (images.toString().equals("davis_attacked")) {
					paint(to_be_painted.getGraphics());
				} else if (images.toString().equals("davis_attacked_inverse")) {
					paint(to_be_painted.getGraphics());
				}
				// davis jump
				else if (images.toString().equals("davis_jump")) {
					paint(to_be_painted.getGraphics());
				} else if (images.toString().equals("davis_jump_inverse")) {
					paint(to_be_painted.getGraphics());
				}
				// davis skill1
				else if (images.toString().equals("davis_skill1")) {
					paint(to_be_painted.getGraphics());
				} else if (images.toString().equals("davis_skill1_inverse")) {
					paint(to_be_painted.getGraphics());
				}
				// davis skill2
				else if (images.toString().equals("davis_skill2")) {
					paint(to_be_painted.getGraphics());
				} else if (images.toString().equals("davis_skill2_inverse")) {
					paint(to_be_painted.getGraphics());
				}
			} 
			else if (character instanceof Character_Deep) {// ball
				if (images.toString().equals("deep_ball")) {
					paint(to_be_painted.getGraphics());
				} else if (images.toString().equals("deep_ball_inverse")) {
					paint(to_be_painted.getGraphics());
				}
				// deep stand
				else if (images.toString().equals("deep_stand")) {
					paint(to_be_painted.getGraphics());
				} else if (images.toString().equals("deep_stand_inverse")) {
					paint(to_be_painted.getGraphics());
				}
				// deep hit
				else if (images.toString().equals("deep_hit")) {
					paint(to_be_painted.getGraphics());
				} else if (images.toString().equals("deep_hit_inverse")) {
					paint(to_be_painted.getGraphics());
				}
				// deep attack
				else if (images.toString().equals("deep_attacked")) {
					paint(to_be_painted.getGraphics());
				} else if (images.toString().equals("deep_attacked_inverse")) {
					paint(to_be_painted.getGraphics());
				}
				// deep jump
				else if (images.toString().equals("deep_jump")) {
					paint(to_be_painted.getGraphics());
				} else if (images.toString().equals("deep_jump_inverse")) {
					paint(to_be_painted.getGraphics());
				}
				// deep skill1
				else if (images.toString().equals("deep_skill1")) {
					paint(to_be_painted.getGraphics());
				} else if (images.toString().equals("deep_skill1_inverse")) {
					paint(to_be_painted.getGraphics());
				}
				// deep skill2
				else if (images.toString().equals("deep_skill2")) {
					paint(to_be_painted.getGraphics());
				} else if (images.toString().equals("deep_skill2_inverse")) {
					paint(to_be_painted.getGraphics());
				}
				update(g);
				TimeUnit.MICROSECONDS.sleep(100);
			}
		}
	}

	public void paint(Graphics g) {
		g.drawImage(to_be_painted, (int) x, (int) y, null);
	}

	public abstract void run();

	public abstract void start();
}