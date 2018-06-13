import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.concurrent.*;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.xml.stream.events.StartDocument;

public class Character extends JPanel implements ActionListener, Runnable, KeyListener {

	protected BufferedImage to_be_painted;
	protected BufferedImage[] changeImgArray ;
	public boolean changeNow;
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
	BufferedImage[] lose_img;
	BufferedImage[] lose_img_inverse;

	public Character(Game game) {
		setBackground(new Color(0, 0, 0, 0));
		setOpaque(false);
		//setBackground(null);
		mx = 0;
		my = 0;
		hp = 100;
		mp = 3000;
		is_hit = false;
		is_right = true;
		timer = new Timer(75, this);
		timer.start();

		addKeyListener(game);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		
		changeImgArray = null;
		changeNow = false;
//		File file = new File("C:\\Users\\����\Downloads\\test.png");
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
			//repaint();
			paintImmediately(0,0,getWidth(),getHeight());
			Thread.currentThread().sleep(100);
		}
	}

	public void walk(int i) throws InterruptedException {// 1嚙磕2嚙糊3嚙磊4嚙踝蕭
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
		if(getX() < -95)
			setX(775);
		if(getX() > 795)
			setX(-75);
		if(getY() > 270)
			setY(270);
		if(getY() < 75)
			setY(75);
			
	}

	public void stand() throws InterruptedException {
		if (!is_hit && !changeNow) {
			if (!getHit()) {
				if (getRight()) {
					changeImgArray = stand_img;
					changeNow = true;
				}
				else {
					changeImgArray = stand_img_inverse;
					changeNow = true;
				}
			}
		}
	}

	public void hit() throws InterruptedException { //跟attack反了
		if (!changeNow) {
			try {
				URL in = this.getClass().getClassLoader().getResource("001.wav");
				AudioInputStream audio = AudioSystem.getAudioInputStream(in);
				Clip clip = AudioSystem.getClip();
		         // Open audio clip and load samples from the audio input stream.
		         clip.open(audio);
		         clip.start();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedAudioFileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (LineUnavailableException e) {
		         e.printStackTrace();
		    }
			if (getRight()) {
				changeImgArray = attack_img;
				changeNow = true;
			}
			else{
				changeImgArray = attack_img_inverse;
				changeNow = true;
			}
		}
	}

	public void attacked(Character player1, Character player2) throws InterruptedException {	//normal attack
		if (!is_hit && !changeNow) {
			double dx, dy;
			if (getRight())
				dx = player2.getX()+player2.getWidth()/2 - player1.getX()-player1.getWidth()/2;
			else
				dx = player1.getX()+player1.getWidth()/2 - player2.getX()-player2.getWidth()/2;
			dy = player1.getY()+player1.getHeight()/2 - player2.getY()-player2.getHeight()/2;
			if (dx * dx + dy * dy < 4500) {
				player2.hit();
				player2.setHit(true);
				player2.setHP(player2.getHP() - this.normalDamage);
				if(getRight())
					player2.setX(player2.getX()+100);
				else
					player2.setX(player2.getX()-100);
				// new Thread(player2).start(); //set a time to freeze enemy(TO_DO)
			}
			if (getRight()) {
				changeImgArray = attack_img;
				changeNow = true;
			} else {
				changeImgArray = attack_img_inverse;
				changeNow = true;
			}
		}
	}

	public void jump() throws InterruptedException {
		if (!is_hit && !changeNow) {
			if (getRight()){
				changeImgArray = jump_img;
				changeNow = true;
			}
			else{
				changeImgArray = jump_img_inverse;
				changeNow = true;
			}
		}
	}

	public void skill1() throws InterruptedException {
		if (!is_hit && !changeNow && getMP()>=600) {
			if (getRight()){
				changeImgArray = skill1_img;
				changeNow = true;
			}
			else{
				changeImgArray = skill1_img_inverse;
				changeNow = true;
			}
			try {
				URL in = this.getClass().getClassLoader().getResource("100.wav");
				AudioInputStream audio = AudioSystem.getAudioInputStream(in);
				Clip clip = AudioSystem.getClip();
		         // Open audio clip and load samples from the audio input stream.
		         clip.open(audio);
		         clip.start();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedAudioFileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (LineUnavailableException e) {
		         e.printStackTrace();
		    }
			setMP(getMP() - 600);
		}
	}

	public void skill2(Character player1, Character player2) throws InterruptedException {
		double dx, dy;
		if (!is_hit && !changeNow && getMP()>=1000) {
			try {
				URL in = this.getClass().getClassLoader().getResource("095.wav");
				AudioInputStream audio = AudioSystem.getAudioInputStream(in);
				Clip clip = AudioSystem.getClip();
		         // Open audio clip and load samples from the audio input stream.
		         clip.open(audio);
		         clip.start();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedAudioFileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (LineUnavailableException e) {
		         e.printStackTrace();
		    }
			
			if (getRight()) {
				dx = player2.getX()+player2.getWidth()/2 - player1.getX()-player1.getWidth()/2;
				changeImgArray = skill2_img;
				changeNow = true;

			}
			else {
				changeImgArray = skill2_img_inverse;
				changeNow = true;
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

			setMP(getMP() - 1000);
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
		try {
			changeImage(changeImgArray);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(getHP() > 0) {
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
					if (up) {
						walk(1);
					}
					if (right) {
						walk(2);
				}
					if (down) {
						walk(3);
				}
					if (left) {
						walk(4);
				}
					if(to_be_painted ==  null && stand_img != null)
						to_be_painted = stand_img[0];
					if(getGraphics()!=null)
						repaint();//update(getGraphics());
				} catch (InterruptedException e1) {
					e1.printStackTrace();
					//System.out.println("k");
				}
				freezingTime = 13;
			}
			if(changeNow) {
				try {
					changeImage(changeImgArray);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				changeNow = false;
			}
		}
		else {
			try {
				if(getRight()) {
					changeImage(lose_img);
				}
				else {
					changeImage(lose_img_inverse);
				}
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			timer.stop();
		}
	}
}