import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import javax.swing.*;

public class character extends JPanel implements ActionListener, KeyListener {

	Timer t = new Timer(10, this);
	double x = 0, y = 0, mx = 0, my = 0;

	public character() {
		t.start();
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.fill(new Ellipse2D.Double(x, y, 60, 100));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
		x += mx;
		y += my;
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			mx = 0;
			my = -5;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			mx = 0;
			my = 5;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			mx = 5;
			my = 0;
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			mx = -5;
			my = 0;
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE){
		}
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
		mx = 0;
		my = 0;
	}

}