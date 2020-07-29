package Gui.Layouts;

import org.lwjgl.input.Keyboard;

import Game.tools.Input;
import Gui.GuiLayout;
import Gui.GuiManager;

public class PlayingMenu extends GuiLayout{

	public PlayingMenu()
	{
		super();
	}
	
	@Override
	protected void updateLayout()
	{
		if(Input.isKeyPressed('e'))
		{
			GuiManager.loadSpellEditMenu();
		}
		
		if(Input.isKeyPressed('q'))
		{
			GuiManager.loadMainMenu();
		}
	}
	
}
