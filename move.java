import javax.swing.JFrame;

public class move {

	public static void main(String[] args) {
		JFrame f = new JFrame();
		character i = new character();
		
		f.add(i);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(800, 600);
	}

}
