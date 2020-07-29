package Gui;

import java.util.ArrayList;
import java.util.List;

import Gui.TextRendering.Text;

public abstract class GuiLayout {

	private List<Gui> guis;
	private List<Text> texts;
	
	private List<Gui> guisToBeAdded;
	private List<Gui> guisToBeRemoved;
	
	private Gui focusedGui;
	
	public GuiLayout()
	{
		guis = new ArrayList<Gui>();
		texts = new ArrayList<Text>();
		
		guisToBeAdded = new ArrayList<Gui>();
		guisToBeRemoved = new ArrayList<Gui>();
		
		focusedGui = null;
	}
	
	public void addGui(Gui g)
	{
		guisToBeAdded.add(g);
		focusedGui = g;
	}
	
	public void removeGui(Gui g)
	{
		guisToBeRemoved.add(g);
	}
	
	public void addText(Text t)
	{
		texts.add(t);
	}
	
	public void removeText(Text t)
	{
		texts.remove(t);
	}
	
	public List<Gui> getGuis()
	{
		return guis;
	}
	
	public List<Text> getTexts()
	{
		return texts;
	}
	
	public void update()
	{
		updateLayout();
		
		for(Gui g : guis)
		{
			g.update();
			g.checkFocus();
			
			if(g.hasFocus())
			{
				setFocusedGui(g);
			}
		}
		
		for(Gui g : guisToBeAdded)
		{
			guis.add(g);
		}
		
		for(Gui g : guisToBeRemoved)
		{
			guis.remove(g);
		}
		
		guisToBeAdded.clear();
		guisToBeRemoved.clear();
	}
	
	protected void updateLayout()
	{
		
	}
	
	public void setFocusedGui(Gui g)
	{
		for(Gui gui : guis)
		{
			if(gui.hasFocus())
				gui.loseFocus();
		}
		
		focusedGui = g;
		focusedGui.setFocus();
	}
	
}
