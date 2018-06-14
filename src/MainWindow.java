import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MainWindow {

	private JFrame window;
	private JPanel mainPanel;
	
	private JPanel menuPanel;
	private JPanel gamePanel;

	private StatusDisplayer statDisplay;
	private JPanel playableArea;
	private ImageIcon bg;
	private JLabel bgl;
	
	private KeyListener keyListener;

	public JPanel getGamePanel() {
		return gamePanel;
	}

	MainWindow() {
		window = new JFrame();
		window.setLayout(null);
		window.getContentPane().setBackground(Color.black);
		window.setTitle("Main");
		window.setSize(800, 600);
		window.setResizable(false);
		window.addWindowListener(new MainWindowListeners(window));
		window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		keyListener = null;
		mainPanel = new JPanel() { 
			@Override
			public boolean isOptimizedDrawingEnabled() {
				return false;
			}
		};
		mainPanel.setBackground(null);
		mainPanel.setSize(window.getSize());
		mainPanel.setLayout(null);
		mainPanel.setVisible(true);
		window.add(mainPanel);

		menuPanel = new JPanel();
		menuPanel.setVisible(false);
		menuPanel.setBackground(Color.blue);
		mainPanel.add(menuPanel);

		gamePanel = new JPanel();
		gamePanel.setVisible(false);
		//gamePanel.setBackground(new Color(0,0,0,0));
		gamePanel.setLayout(null);
		mainPanel.add(gamePanel);
		
//		window.setComponentZOrder(menuPanel, 1);
//		window.setComponentZOrder(gamePanel, 1);

		statDisplay = new StatusDisplayer();
		statDisplay.setParent(gamePanel);
		statDisplay.setVisible(true);
		playableArea = new JPanel();
		playableArea.setVisible(true);
		playableArea.setLayout(null);
		//playableArea.setBackground(new Color(0,0,0,0));
		gamePanel.add(playableArea);
		
		BufferedImage bg = null;
		try {
			bg = ImageIO.read(new File("src/Background.png"));
		} catch (IOException e) {
			System.out.println("failed fetching background image");
			e.printStackTrace();
		}
		if(bg!=null)
		{
			this.bg = new ImageIcon(bg);
			bgl = new JLabel(this.bg);
			bgl.setVisible(true);
			playableArea.add(bgl);
			playableArea.setComponentZOrder(bgl, playableArea.getComponentCount()-1);
		}

		syncFrameSize();

		window.setVisible(true);
		menuPanel.setVisible(true);

		startGame();
	}
	
	public void dispose()
	{
		window.dispose();
	}

	public void startGame() {
		gamePanel.setVisible(true);
		menuPanel.setVisible(false);
	}

	public void backtoMenu() {
		menuPanel.setVisible(true);
		gamePanel.setVisible(false);
	}

	public void setSize(int width, int height) {
		window.setSize(width, height);
		syncFrameSize();
	}

	public void setSize(Dimension d) {
		window.setSize(d);
		syncFrameSize();
	}

	public StatusDisplayer getStatusDisplayer() {
		return statDisplay;
	}

	public JPanel getPlayableArea() {
		return playableArea;
	}
	
	public void setKeyListener(KeyListener listener)
	{
		if(keyListener == null)
			window.removeKeyListener(keyListener);
		keyListener = listener;
		window.addKeyListener(listener);
	}

	protected void syncFrameSize() {
		menuPanel.setSize(window.getSize());
		gamePanel.setSize(window.getSize());
		statDisplay.setSize(gamePanel.getWidth(), gamePanel.getHeight() / 3);
		playableArea.setLocation(0, gamePanel.getHeight() / 3);
		playableArea.setSize(gamePanel.getWidth(), gamePanel.getHeight() - gamePanel.getHeight() / 3);
		bgl.setSize(playableArea.getSize());
	}
	
	public Dialog createDialog()
	{
		return new Dialog();
	}
	public Dialog createDialog(String text)
	{
		return new Dialog().setText(text);
	}

	private class MainWindowListeners implements WindowListener {

		JFrame target;

		MainWindowListeners(JFrame tar) {
			target = tar;
		}

		@Override
		public void windowActivated(WindowEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowClosed(WindowEvent arg0) {
			// TODO Auto-generated method stub
			System.exit(0);
		}

		@Override
		public void windowClosing(WindowEvent arg0) {
			// TODO Auto-generated method stub
			target.dispose();
		}

		@Override
		public void windowDeactivated(WindowEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowDeiconified(WindowEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowIconified(WindowEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowOpened(WindowEvent arg0) {
			// TODO Auto-generated method stub

		}

	}
	
	public class Dialog extends JPanel
	{
		private JPanel dialogTextPanel;
		private JLabel dialogText;
		private JPanel dialogButtonPanel;
		private JButton closeButton;
		private Runnable action;
		private Dialog() 
		{
			this.setVisible(false);
			this.setLayout(new BorderLayout());
			
			dialogTextPanel = new JPanel();
			dialogTextPanel.setLayout(new FlowLayout());
			dialogText = new JLabel();
			dialogTextPanel.add(dialogText);
			this.add(dialogTextPanel, BorderLayout.CENTER);
			dialogTextPanel.setVisible(true);
			dialogText.setVisible(true);
			
			dialogButtonPanel = new JPanel();
			dialogButtonPanel.setLayout(new FlowLayout());
			closeButton = new JButton("OK");
			dialogButtonPanel.add(closeButton);
			this.add(dialogButtonPanel, BorderLayout.PAGE_END);
			closeButton.setVisible(true);
			dialogButtonPanel.setVisible(true);
			DialogButtonListener tmp = new DialogButtonListener(this);
			closeButton.addActionListener(tmp);

			action = null;
			
			this.setSize(mainPanel.getWidth()/4,mainPanel.getHeight()/4);
			mainPanel.add(this, 0);
			this.setLocation(mainPanel.getWidth()/2 - this.getWidth()/2, mainPanel.getHeight()/2 - this.getHeight()/2);
			
		}
		
		public Dialog setText(String text)
		{
			dialogText.setText(text);
			return this;
		}
		public Dialog setButtonAction(Runnable r)
		{
			action = r;
			return this;
		}
		
		private class DialogButtonListener implements ActionListener
		{
			private Dialog d;
			
			public DialogButtonListener(Dialog dd)
			{
				d = dd;
			}

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				d.setVisible(false);
				mainPanel.remove(d);
				if(d.action != null)
					d.action.run();
			}
			
		}
	}

}