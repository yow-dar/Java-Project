import javax.swing.*;
import java.awt.event.*;

public class Game extends JFrame {
	
	public static JFrame f = new JFrame("Java Project");
	private static character i = new character();
	
	public static double changeX(int a) {
		switch (a) {
		case KeyEvent.VK_UP:
			return 0;
		case KeyEvent.VK_DOWN:
			return 0;
		case KeyEvent.VK_RIGHT:
			return 1.5;
		case KeyEvent.VK_LEFT:
			return -1.5;
		case KeyEvent.VK_X:
			attack.atk(character.getx(),character.gety());
			attack b = new attack();
			f.add(b);
			f.setVisible(true);
			return 0;
		default:
			return 0;
		}
	}

	public static double changeY(int a) {
		switch (a) {
		case KeyEvent.VK_UP:
			return -1.5;
		case KeyEvent.VK_DOWN:
			return 1.5;
		case KeyEvent.VK_RIGHT:
			return 0;
		case KeyEvent.VK_LEFT:
			return 0;
		default:
			return 0;
		}
	}
	
	public static void main(String[] args){
		f.add(i);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(800, 600);
		f.setResizable(false);
	}
}