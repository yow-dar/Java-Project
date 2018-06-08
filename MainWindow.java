

import javax.swing.JWindow;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

public class MainWindow {

	private JFrame window;
	private JPanel menuPanel;
	private JPanel gamePanel;
	
	private StatusDisplayer statDisplay;
	private JPanel playableArea;
	
	public JPanel getGamePanel()
	{
		return gamePanel;
	}
	
	MainWindow() {
		window = new JFrame();
		window.setLayout(null);
		window.getContentPane().setBackground(Color.black);
		window.setTitle("Main");
		window.setSize(800,600);
		window.setResizable(false);
		window.addWindowListener(new MainWindowListeners(window));
		window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		menuPanel = new JPanel();
		menuPanel.setVisible(false);
		menuPanel.setBackground(Color.blue);
		window.add(menuPanel);
		
		gamePanel = new JPanel();
		gamePanel.setVisible(false);
		gamePanel.setBackground(null);
		gamePanel.setLayout(null);
		window.add(gamePanel);
		
		statDisplay = new StatusDisplayer();
		statDisplay.setParent(gamePanel);
		statDisplay.setVisible(true);
		playableArea = new JPanel();
		playableArea.setVisible(true);
		playableArea.setLayout(null);
		gamePanel.add(playableArea);
		
		syncFrameSize();
		
		window.setVisible(true);
		menuPanel.setVisible(true);
		
		startGame();
	}
	
	public void startGame()
	{
		gamePanel.setVisible(true);
		menuPanel.setVisible(false);
	}
	public void backtoMenu()
	{
		menuPanel.setVisible(true);
		gamePanel.setVisible(false);
	}
	
	public void setSize(int width, int height)
	{
		window.setSize(width, height);
		syncFrameSize();
	}
	public void setSize(Dimension d)
	{
		window.setSize(d);
		syncFrameSize();
	}
	
	public StatusDisplayer getStatusDisplayer()
	{
		return statDisplay;
	}
	public JPanel getPlayableArea()
	{
		return playableArea;
	}
	
	protected void syncFrameSize() {
		menuPanel.setSize(window.getSize());
		gamePanel.setSize(window.getSize());
		statDisplay.setSize(gamePanel.getWidth(), gamePanel.getHeight()/3);
		playableArea.setLocation(0, gamePanel.getHeight()/3);
		playableArea.setSize(gamePanel.getWidth(),gamePanel.getHeight() - gamePanel.getHeight()/3);
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
	
}
