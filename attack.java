import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class attack extends JPanel implements ActionListener{
	
	private Timer t;
	private static double x=60, y=60, mx;
	
	public attack() {
		t = new Timer(5, this);
		t.start();
		mx = 2;
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		g.fillOval((int) x, (int) y, 50, 50);
	}
	
	public void actionPerformed(ActionEvent e) {
		x += mx;
		repaint();
	}
	
	public static void atk(double a, double b) {
		x = a;
		y = b;
	}
}
