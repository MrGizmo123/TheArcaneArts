package Gui;

import java.util.List;

import Game.Game;

import Gui.Layouts.MainMenu;
import Gui.Layouts.PlayingMenu;
import Gui.Layouts.SpellProgrammingMenu;
import Gui.TextRendering.Text;

public class GuiManager {

	private static GuiLayout activeLayout;
	
	private static MainMenu mainMenu;
	private static PlayingMenu playingMenu;
	private static SpellProgrammingMenu spellMenu;
	
	public static void init()
	{
		mainMenu = new MainMenu();
		playingMenu = new PlayingMenu();
		spellMenu = new SpellProgrammingMenu();
		
		activeLayout = mainMenu;
		Game.freezeGameInput();
	}
	
	public static void update()
	{
		activeLayout.update();
	}
	
	public static List<Gui> getActiveGuis()
	{
		return activeLayout.getGuis();
	}
	
	public static List<Text> getActiveTexts()
	{
		return activeLayout.getTexts();
	}
	
	//change functions
	
	public static void loadPlayingLayout()
	{
		activeLayout = playingMenu;
		Game.unFreezeGameInput();
	}
	
	public static void loadSpellEditMenu()
	{
		activeLayout = spellMenu;
		Game.freezeGameInput();
	}
	
	public static void loadMainMenu()
	{
		activeLayout = mainMenu;
		Game.freezeGameInput();
	}
	
}
