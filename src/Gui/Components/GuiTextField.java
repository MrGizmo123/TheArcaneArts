package Gui.Components;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;

import Game.tools.GameResourcesAndSettings;
import Game.tools.Input;
import Gui.Gui;
import Gui.GuiLayout;
import Gui.TextRendering.Text;

public class GuiTextField extends Gui{

	private Text text;
	
	public GuiTextField(float textSize, boolean centered, GuiLayout parentLayout) {
		super(GameResourcesAndSettings.GUI_DARK_GREY, parentLayout);
		
		Vector2f viewport = super.getPositionInViewPort();
		
		text = new Text("", textSize, GameResourcesAndSettings.GAME_FONT, viewport, centered);
		
		parentLayout.addText(text);
	}
	
	@Override
	protected void constraintsUpdated()
	{
		Vector2f viewport = super.getPositionInViewPort();
		
		text.changePos(viewport);
		positionText();
	}
	
	@Override
	public void update()
	{
		if(super.hasFocus())
		{
			for(int key : Input.getKeysPressed())
			{
				try {
				    	
			    	if(key == 8)
			    	{
			    		text.removeChar(text.getLength() - 1);
				   		continue;
				   	}
				    	
				   	char c = Keyboard.getEventCharacter();
				    	
				   	if(((int)c) == 0)
				    {
				    	continue;
				    }
						
					text.append(c);
				}
				catch (Exception e)
				{
					continue;
				}
			}
		}
	}
	
	private void positionText()
	{
		Vector2f viewport = super.getPositionInViewPort();
		
		text.changePos(new Vector2f(viewport.x, viewport.y + ((float)GameResourcesAndSettings.GAME_FONT.getMaxHeight() * text.getSize() * Display.getHeight() / Display.getWidth())));
	}
	
}
