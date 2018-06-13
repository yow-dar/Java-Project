import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Ball extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1324447176911644068L;
	private Timer t;
	private int x, y, mx;
	private BufferedImage img;
	private Test pic;
	private Character player1, player2;
	private boolean is_right;

	public Ball(int a, int b, Character player1, Character player2) {
		//setVisible(false);
		setBackground(new Color(0, 0, 0, 0));
		setOpaque(false);
		setLocation(x, y);
		//setBackground(Color.blue);
		setSize(80,80);
		x = a;
		y = b;
		
		setVisible(true);

		t = new Timer(5, this);
		pic = new Test();
		this.player1 = player1; 
		this.player2 = player2; 
		try {
			pic.initial();
			if (player1 instanceof Character_Davis) {
				if (player1.getRight()) {
					img = pic.davis_ball[0];
					is_right = true;
				}
				else {
					img = pic.davis_ball_inverse[0];
					is_right = false;
				}
			} else {
				if (player1.getRight()) {
					img = pic.deep_ball[0];
					is_right = true;
				}
				else {
					img = pic.deep_ball_inverse[0];
					is_right = false;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		t.start();
		mx = 2;
	}

	public void paint(Graphics g) {
		
		// g.drawImage(img, x, y, 80, 80, null);
		g.drawImage(img, 0, 0, 80, 80, null);super.paint(g);
	}

	public void actionPerformed(ActionEvent e) {
		x += is_right? mx : -mx;
		setLocation(x, y);
		if (x > 800 || x < 0) {
			Game.remove(this);
			t.stop();
			t = null;
		}
		int dx = this.getX()+getWidth()/2-player2.getX()-player2.getWidth()/2;
		int dy = this.getY()+getHeight()/2-player2.getY()-player2.getHeight()/2;
		if(dx*dx + dy*dy<2500) {
			Game.remove(this);
			t.stop();
			t = null;
			try {
				player2.hit();
				player2.setHit(true);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			player2.setHP(player2.getHP()-player1.normalDamage);
		}
		//update(getGraphics());
		//repaint();
	}
}
