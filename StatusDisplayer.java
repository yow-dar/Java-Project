

import java.awt.Color;

import javax.swing.JPanel;

public class StatusDisplayer {

	JPanel displayPanel;
	
	CharacterStatusDisplay[] characterDisplays;
	
	StatusDisplayer()
	{
		displayPanel = new JPanel();
		displayPanel.setBackground(Color.BLUE);
		displayPanel.setLayout(null);
		
		characterDisplays = new CharacterStatusDisplay[8];
		for(int i=0;i<8;++i)
		{
			characterDisplays[i] = new CharacterStatusDisplay();
			displayPanel.add(characterDisplays[i].getComponent());
		}
	}
	
	public void setVisible(boolean visible)
	{
		displayPanel.setVisible(visible);
	}
	public void show()
	{
		setVisible(true);
	}
	public void hide()
	{
		setVisible(false);
	}
	
	public void setSize(int width, int height)
	{
		displayPanel.setSize(width,height);
		readjustCharacterDisplays();
	}
	public void setSize(java.awt.Dimension d)
	{
		displayPanel.setSize(d);
		readjustCharacterDisplays();
	}
	
	protected void readjustCharacterDisplays()
	{
		int height = displayPanel.getHeight();
		int width = displayPanel.getWidth();
		
		int sheight = height/2;
		int swidth = width/4;
		for(int y=0;y<2;++y)
			for(int x=0;x<4;++x)
			{
				characterDisplays[4*y+x].setLocation(x*swidth, y*sheight);
				characterDisplays[4*y+x].setSize(swidth,sheight);
			}
	}
	
	public void registerCharacter(int id,Character character)
	{
		characterDisplays[id].setCharacter(character);
	}
	public void clearCharacters()
	{
		for(int i=0;i<8;++i)
			characterDisplays[i].setCharacter(null);
	}
	
	
	protected class CharacterStatusDisplay
	{
		private JPanel display;
		private Character character;
		
		CharacterStatusDisplay()
		{
			character = null;
			
			display = new JPanel();
			display.setLayout(null);
			display.setVisible(true);
		}
		
		public void setLocation(int x,int y)
		{
			display.setLocation(x, y);
		}
		
		public void setSize(int width,int height)
		{
			display.setSize(width,height);
		}
		
		public JPanel getComponent()
		{
			return display;
		}
		
		public void setCharacter(Character chara)
		{
			character = chara;
			update();
		}
		public Character getCharacter(Character chara)
		{
			return character;
		}
		public void update()
		{
			// TODO: EVERY FUCKING THING
		}
	}
}
