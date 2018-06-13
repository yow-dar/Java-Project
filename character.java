import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.concurrent.*;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Character extends JPanel implements ActionListener, Runnable, KeyListener {

	protected BufferedImage to_be_painted;
	private int mx, my;
	private int hp, mp;
	private boolean is_hit, is_right;
	protected final int normalDamage = 10;
	protected final int skillDamage = 20;
	private Timer timer;
	private int freezingTime = 400;
	public boolean up, down, right, left;
	int index = 0;
	Test pictures;
	BufferedImage[] walk_img;
	BufferedImage[] walk_img_inverse;
	BufferedImage[] stand_img;
	BufferedImage[] stand_img_inverse;
	BufferedImage[] jump_img;
	BufferedImage[] jump_img_inverse;
	BufferedImage[] skill1_img;
	BufferedImage[] skill1_img_inverse;
	BufferedImage[] skill2_img;
	BufferedImage[] skill2_img_inverse;
	BufferedImage[] attack_img;
	BufferedImage[] attack_img_inverse;
	BufferedImage[] hit_img;
	BufferedImage[] hit_img_inverse;

	public Character(Game game) {
		setBackground(new Color(0, 0, 0, 0));
		setOpaque(false);
		//setBackground(null);
		mx = 0;
		my = 0;
		hp = 100;
		mp = 100;
		is_hit = false;
		is_right = true;
		timer = new Timer(5, this);
		timer.start();

		addKeyListener(game);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
//		File file = new File("C:\\Users\\蔡朋璁\\Downloads\\test.png");
//		
//		try {
//			to_be_painted = ImageIO.read(file);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		setVisible(true);
	}

	@Override
	public void paintComponent(Graphics g) {
		//System.out.println("p");
		super.paintComponent(g);
		g.drawImage(to_be_painted, 0, 0, 100, 100, null);
	}
	
//	public void update(Graphics g) {
//		Image imageBuffer = createImage(getWidth(), getHeight());
//		Graphics graphicsBuffer = imageBuffer.getGraphics();
//		paint(graphicsBuffer);
//		graphicsBuffer.dispose();
//		g.drawImage(imageBuffer, 0, 0, this);
//	}

	public void setX(int x) {
		setLocation(x, getY());
	}

	public void setY(int y) {
		setLocation(getX(), y);
	}

	public int getmx() {
		return mx;
	}

	public void setmx(int mx) {
		this.mx = mx;
	}

	public int getmy() {
		return my;
	}

	public void setmy(int my) {
		this.my = my;
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
//		if (is_hit) {
//			TimeUnit.SECONDS.sleep(1);
//			this.is_hit = false;
//		}
	}

	public boolean getRight() {
		return is_right;
	}

	public void setRight(boolean is_right) {
		this.is_right = is_right;
	}

	public void changeImage(BufferedImage[] images) throws InterruptedException {
		Graphics g = getGraphics();
		for (int i = 0; i < images.length; ++i) {
			to_be_painted = images[i];
			//update(g);
			repaint();
			TimeUnit.MICROSECONDS.sleep(500);
		}
	}

	public void walk(int i) throws InterruptedException {// 1�W2�k3�U4��
		//System.out.println("u");
		if (!is_hit) {
			if (i < 1 || i > 4) {
				System.out.println("Character_???.walk called with illegal i: " + i);
				return;
			}
			setLocation(getX() + (((i+1) % 2) * (-i + 3) * getmx()), getY() + ((i % 2) * (i-2) * getmy()));
			Graphics g = getGraphics();
			if (getRight()) {
				to_be_painted = walk_img[index % 4];
				//update(g);
			}
			else {
				to_be_painted = walk_img_inverse[index % 4];
				//update(g);
			}
			++index;
		}
	}

	public void stand() throws InterruptedException {
		if (!is_hit) {
			if (!getHit()) {
				if (getRight())
					changeImage(stand_img);
				else
					changeImage(stand_img_inverse);
			}
		}
	}

	public void hit() throws InterruptedException {
		if (!is_hit) {
			if (getRight())
				changeImage(hit_img);
			else
				changeImage(hit_img_inverse);
		}
	}

	public void attacked(Character player1, Character player2) throws InterruptedException {
		if (!is_hit) {
			double dx, dy;

			if (getRight()) {
				changeImage(attack_img);
				dx = player2.getX()+player2.getWidth()/2 - player1.getX()-player1.getWidth()/2;
			} else {
				changeImage(attack_img_inverse);
				dx = player1.getX()+player1.getWidth()/2 - player2.getX()-player2.getWidth()/2;
			}

			dy = player1.getY()+player1.getHeight()/2 - player2.getY()-player2.getHeight()/2;

			if (dx * dx + dy * dy < 2500) {
				player2.setHit(true);
				player2.setHP(player2.getHP() - this.normalDamage);
				player2.hit();
				if(getRight())
					player2.setX(player2.getX()+100);
				else
					player2.setX(player2.getX()-100);
				// new Thread(player2).start(); //set a time to freeze enemy(TO_DO)
			}
		}
	}

	public void jump() throws InterruptedException {
		if (!is_hit) {
			if (getRight())
				changeImage(jump_img);
			else
				changeImage(jump_img_inverse);
		}
	}

	public void skill1() throws InterruptedException {
		if (!is_hit) {
			if (getRight())
				changeImage(skill1_img);
			else
				changeImage(skill1_img_inverse);
		}
	}

	public void skill2(Character player1, Character player2) throws InterruptedException {
		double dx, dy;
		if (!is_hit) {
			if (getRight()) {
				dx = player2.getX()+player2.getWidth()/2 - player1.getX()-player1.getWidth()/2;
				changeImage(skill2_img);
			}
			else {
				changeImage(skill2_img_inverse);
				dx = player1.getX()+player1.getWidth()/2 - player2.getX()-player2.getWidth()/2;
			}

			dy = player1.getY()+player2.getHeight()/2 - player2.getY()-player2.getHeight()/2;
			if (getRight())
				setX(getX() + 2);
			else
				setX(getX() - 2);
			if (dx * dx + dy * dy < 2500) {
				player2.setHit(true);
				player2.setHP(player2.getHP() - this.skillDamage);
				player2.hit();
				if(getRight())
					player2.setX(player2.getX()+100);
				else
					player2.setX(player2.getX()-100);
				// new Thread(player2).start(); //set a time to freeze enemy(TO_DO)
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void run() {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//System.out.println("f");
		if (is_hit) {
			//System.out.println("u");
			freezingTime--;
			if (freezingTime < 0) {
				is_hit = false;
			}
		} else {
			//System.out.println("c");
			try {
				if (up)
					walk(1);
				if (right)
					walk(2);
				if (down)
					walk(3);
				if (left)
					walk(4);
				if(to_be_painted ==  null && stand_img != null)
					to_be_painted = stand_img[0];
				if(getGraphics()!=null)
					repaint();//update(getGraphics());
			} catch (InterruptedException e1) {
				e1.printStackTrace();
				//System.out.println("k");
			}
			freezingTime = 400;
		}
	}
}