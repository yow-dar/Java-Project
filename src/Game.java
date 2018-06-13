import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

import java.awt.Color;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

public class Game implements ActionListener, KeyListener, Runnable {

	public static JPanel p;
	private Character deep;
	private Character davis;
	private Timer t;
	private MainWindow mainWindow;
	private Thread thread;
	private boolean banBalls;
	public Game(MainWindow mainWindow) {
		try {
			deep = new Character_Deep(this);
			davis = new Character_Davis(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.mainWindow = mainWindow;
		p = mainWindow.getPlayableArea();
		deep.setLocation(25, 25);
		deep.setSize(100, 100);
		davis.setLocation(p.getWidth() - 100 - 25, 25);
		davis.setSize(100, 100);
		p.add(deep,0);
		p.add(davis,0);
		
		mainWindow.getStatusDisplayer().registerCharacter(1, deep);
		mainWindow.getStatusDisplayer().registerCharacter(3, davis);
		
//		JPanel fpanel = new JPanel();
//		fpanel.setBackground(Color.orange);
//		fpanel.setLocation(10, 10);
//		fpanel.setSize(100,100);
//		p.add(fpanel);
//		fpanel.setVisible(true);
		//p.setBackground(Color.orange);
		p.setVisible(true);
		banBalls = true;
		t = new Timer(10, this);
		t.start();
		
		
		try {
			URL in = this.getClass().getClassLoader().getResource("main.wav");
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
	}

	public void actionPerformed(ActionEvent e) {
		//p.repaint();
//		p.paintComponents(p.getGraphics());
		//p.update(p.getGraphics());
		//p.repaint();
//		davis.update(p.getGraphics());
//		deep.update(p.getGraphics());
		mainWindow.getStatusDisplayer().update();
		if(davis.getMP() <= 3000) {
			davis.setMP(davis.getMP()+10);
		}
		if(deep.getMP() <= 3000) {
			deep.setMP(deep.getMP()+10);
		}
	}
	
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
				davis.up = true;
				davis.setmy(5);
			break;
		case KeyEvent.VK_DOWN:
			//System.out.println("YEE");
			davis.down = true;
			davis.setmy(5);
			break;
		case KeyEvent.VK_RIGHT:
			davis.setRight(true);
			davis.right = true;
			davis.setmx(10);
			break;
		case KeyEvent.VK_LEFT:
			davis.setRight(false);
			davis.left = true;
			davis.setmx(10);
			break;
		case KeyEvent.VK_COMMA:
			thread = new Thread(this);
			if(!davis.getHit() && banBalls && davis.getMP()>=600){
				try {
					davis.skill1();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				
				Ball davis_b = new Ball(davis.getX(), davis.getY(), davis, deep);
				p.add(davis_b,0);
				davis_b.setSize(80, 80);
				p.setVisible(true);
				banBalls = false;
				thread.start();
			}
			break;
		case KeyEvent.VK_PERIOD:
			try {
				davis.skill2(davis, deep);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			break;
		case KeyEvent.VK_W:
			deep.up = true;
			deep.setmy(5);
			break;
		case KeyEvent.VK_S:
			deep.down = true;
			deep.setmy(5);
			break;
		case KeyEvent.VK_D:
			deep.setRight(true);
			deep.right = true;
			deep.setmx(10);
			break;
		case KeyEvent.VK_A:
			deep.setRight(false);
			deep.left = true;
			deep.setmx(10);
			break;
		case KeyEvent.VK_F:
			thread = new Thread(this);
			if(!deep.getHit() && banBalls && deep.getMP() >= 600){
				try {
					deep.skill1();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				Ball deep_b = new Ball(deep.getX(), deep.getY(), deep , davis);
				p.add(deep_b,0);
				deep_b.setSize(80, 80);
				p.setVisible(true);
				banBalls = false;
				thread.start();
			}
		break;
		case KeyEvent.VK_G:
			try {
				deep.skill2(deep, davis);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			break;
		default:
			break;
		}
	}

	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			davis.up = false;
			break;
		case KeyEvent.VK_DOWN:
			davis.down = false;
			break;
		case KeyEvent.VK_RIGHT:
			davis.right = false;
			break;
		case KeyEvent.VK_LEFT:
			davis.left = false;
			break;
		case KeyEvent.VK_W:
			deep.up = false;
			break;
		case KeyEvent.VK_S:
			deep.down = false;
			break;
		case KeyEvent.VK_D:
			deep.right = false;
			break;
		case KeyEvent.VK_A:
			deep.left = false;
			break;
		default:
			break;
		}
	}

	public void keyTyped(KeyEvent e) {

	}

	public static void remove(Ball ball) {
		p.remove(ball);
		ball = null;
	}
	
	public void run() {
		try {
			Thread.currentThread().sleep(1500);
			banBalls = true;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		MainWindow mainWindow = new MainWindow();
		Game game = new Game(mainWindow);
		mainWindow.setKeyListener(game);
	}
}