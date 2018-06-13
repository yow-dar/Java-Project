import java.awt.Color;
import java.awt.Container;

import javax.swing.JPanel;

public class StatusDisplayer {

	private Container parent;
	
	private JPanel displayPanel;
	
	private CharacterStatusDisplay[] CharacterDisplays;
	
	public StatusDisplayer()
	{
		displayPanel = new JPanel();
		displayPanel.setBackground(Color.BLUE);
		displayPanel.setLayout(null);
		
		CharacterDisplays = new CharacterStatusDisplay[8];
		for(int i=0;i<8;++i)
		{
			CharacterDisplays[i] = new CharacterStatusDisplay();
			displayPanel.add(CharacterDisplays[i].getComponent());
		}
	}
	
	public void setParent(Container parent)
	{
		this.parent = parent;
		parent.add(displayPanel);
	}
	public Container getParent()
	{
		return parent;
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
	
	public void update()
	{
		for(int i=0;i<CharacterDisplays.length;++i)
		{
			CharacterDisplays[i].update();
		}
	}
	public void update(int index)
	{
		if(index<CharacterDisplays.length && index >= 0)
			CharacterDisplays[index].update();
		else
			System.out.println("StatusDisplayer.update(int index) called with illegal index: "+index);
	}
	public void update(Character chara)
	{
		if(chara == null)
			return;
		for(int i=0;i<CharacterDisplays.length;++i)
		{
			if(CharacterDisplays[i].getCharacter() == chara)
				CharacterDisplays[i].update();
		}
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
				CharacterDisplays[4*y+x].setLocation(x*swidth, y*sheight);
				CharacterDisplays[4*y+x].setSize(swidth,sheight);
			}
	}
	
	public void registerCharacter(int id,Character chara)
	{
		if(id<CharacterDisplays.length && id>0)
			CharacterDisplays[id].setCharacter(chara);
		else
			System.out.println("StatusDisplayer.registerCharacter called with illegal id: "+id);
	}
	public void clearCharacters()
	{
		for(int i=0;i<8;++i)
			CharacterDisplays[i].setCharacter(null);
	}
	public void unregisterCharacter(int id)
	{
		if(id<CharacterDisplays.length && id>0)
			CharacterDisplays[id].setCharacter(null);
		else
			System.out.println("StatusDisplayer.unregisterCharacter called with illegal id: "+id);
	}
	public void unregisterCharacter(Character chara)
	{
		for(int i=0;i<CharacterDisplays.length;++i)
			if(CharacterDisplays[i].getCharacter() == chara)
				CharacterDisplays[i].setCharacter(null);
	}
	
	
	protected class CharacterStatusDisplay
	{
		private JPanel display;
		private Character chara;
		
		private JPanel HPBar;
		private JPanel HPBarFill;
		private JPanel MPBar;
		private JPanel MPBarFill;
		
		CharacterStatusDisplay()
		{
			chara = null;
			
			display = new JPanel();
			display.setLayout(null);
			display.setVisible(true);
			
			HPBar = new JPanel();
			HPBar.setBackground(new Color(65, 0, 0));
			HPBar.setLayout(null);
			display.add(HPBar);
			HPBarFill = new JPanel();
			HPBarFill.setBackground(new Color(204,0,0));
			HPBar.add(HPBarFill);
			HPBar.setVisible(true);
			HPBarFill.setVisible(true);
			
			MPBar = new JPanel();
			MPBar.setBackground(new Color(0,0,40));
			MPBar.setLayout(null);
			display.add(MPBar);
			MPBarFill = new JPanel();
			MPBarFill.setBackground(new Color(0,0,204));
			MPBar.add(MPBarFill);
			MPBar.setVisible(true);
			MPBar.setVisible(true);
		}
		
		public void setLocation(int x,int y)
		{
			display.setLocation(x, y);
		}
		
		public void setSize(int width,int height)
		{
			display.setSize(width,height);
			HPBar.setSize((int)(width*0.5), (int)(height*0.2));
			MPBar.setSize((int)(width*0.5), (int)(height*0.2));
			
			HPBar.setLocation((int)(width*0.25), (int)(height*0.25));
			MPBar.setLocation((int)(width*0.25), (int)(height*0.55));
			
			update();
		}
		
		public JPanel getComponent()
		{
			return display;
		}
		
		public void setCharacter(Character chara)
		{
			this.chara = chara;
			update();
		}
		public Character getCharacter()
		{
			return chara;
		}
		public void update()
		{
			// TODO: EVERY FUCKING THING
			if(chara==null)
			{
				HPBarFill.setSize(0,(int)HPBar.getSize().getHeight());
				MPBarFill.setSize(0,(int)MPBar.getSize().getHeight());
			}
			else if(chara.getHP() <= 0)
			{
				HPBarFill.setSize(0,(int)HPBar.getSize().getHeight());
				MPBarFill.setSize(0,(int)MPBar.getSize().getHeight());
			}
			else
			{
				HPBarFill.setSize(HPBar.getWidth()*chara.getHP()/100,HPBar.getHeight());
				MPBarFill.setSize(MPBar.getWidth()*chara.getMP()/3000,MPBar.getHeight());
			}
		}
	}
}
