import javax.swing.*;
import java.awt.event.*;

public class Move extends JFrame {

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
}
