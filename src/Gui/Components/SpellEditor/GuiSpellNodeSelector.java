package Gui.Components.SpellEditor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;

import Game.Render.DisplayManager;
import Game.Spell.SpellNodeType;
import Game.tools.GameResourcesAndSettings;
import Gui.Gui;
import Gui.GuiLayout;
import Gui.Components.ClickListener;
import Gui.Components.GuiButton;
import Gui.Constraints.AbsolutePositionConstraint;
import Gui.Constraints.AspectConstraint;
import Gui.Constraints.ScaleConstraint;
import Gui.Layouts.SpellProgrammingMenu;

public class GuiSpellNodeSelector extends Gui{

	private static final float TEXT_SIZE = 0.3f;
	private static final int padding = 10;
	private static final float buttonSpacing = 1.1f;
	
	private List<GuiButton> nodeTypes;
	private GuiSpellGrid grid;
	
	public GuiSpellNodeSelector(GuiLayout layout, GuiSpellGrid g)
	{
		super(GameResourcesAndSettings.GUI_GREY, layout);
		nodeTypes = new ArrayList<GuiButton>();
		grid = g;
		
		createGuis();
	}
	
	@Override
	public void focusLost()
	{
		hide();
	}
	
	public void show(Vector2f pos)
	{
		super.show();
		
		parentLayout.setFocusedGui(this);
		
		positionGuis(pos);
	}
	
	@Override
	public void hide()
	{
		
		
		for(GuiButton g : nodeTypes)
		{
			g.hide();
		}
		
		super.hide();
		
		if(super.parentLayout instanceof SpellProgrammingMenu)
		{
			SpellProgrammingMenu menu = (SpellProgrammingMenu) super.parentLayout;
			menu.setBackgroundAsFocus();
		}
	}
	
	private void positionGuis(Vector2f pos)
	{
		float maxHeight = GameResourcesAndSettings.GAME_FONT.getMaxHeight();
		
		float topY = pos.y;
		float bottomY = pos.y - (TEXT_SIZE * (nodeTypes.size()-1) * maxHeight * buttonSpacing);
		
		float center = (topY + bottomY) / 2;
		
		this.addXPosConstraint(new AbsolutePositionConstraint((int) pos.x));
		this.addYPosConstraint(new AbsolutePositionConstraint((int) center));
		
		this.show();
		
		int i = 0;
		for(GuiButton g : nodeTypes)
		{
			g.addXPosConstraint(new AbsolutePositionConstraint((int) pos.x));
			g.addYPosConstraint(new AbsolutePositionConstraint((int) (pos.y - (TEXT_SIZE * i * maxHeight * buttonSpacing))));
			
			g.show();
			i++;
		}
	}
	
	private void createGuis()
	{
		List<SpellNodeType> types = new ArrayList<SpellNodeType>(Arrays.asList(SpellNodeType.values()));
		
		// constants required to get scale of guis
		int maxStringLength = getMaxNodeNameLength(types);
		
		float maxHeight = GameResourcesAndSettings.GAME_FONT.getMaxHeight();
		float maxWidth = GameResourcesAndSettings.GAME_FONT.getMaxWidth();
		
		float buttonWidth = TEXT_SIZE * maxStringLength * maxWidth / (float)Display.getWidth();
		float buttonAspect = maxStringLength * maxWidth / (maxHeight);
		
		float bgHeight = (TEXT_SIZE * maxHeight * (float)types.size() * buttonSpacing + (float)padding) / (float)Display.getHeight();
		float bgWidth = (TEXT_SIZE * maxStringLength * maxWidth + (float)padding) / (float)Display.getWidth();
		
		// scale constraints for selector bg gui
		this.addScaleConstraint(new ScaleConstraint(bgWidth, ScaleConstraint.WIDTH));
		this.addAspectConstraint(new AspectConstraint(bgWidth/bgHeight * DisplayManager.aspectRatio));
		
		for(SpellNodeType type : types) 
		{
			GuiButton b = new GuiButton(type.name(), TEXT_SIZE, GameResourcesAndSettings.GUI_DARK_GREY, super.parentLayout);
			b.addScaleConstraint(new ScaleConstraint(buttonWidth, ScaleConstraint.WIDTH));
			b.addAspectConstraint(new AspectConstraint(buttonAspect));
			
			b.hide();
			
			b.setClickListener(new ClickListener() {

				@Override
				public void clicked() {
					grid.addNode(type);
					//hide();
				}
				
			});
			
			nodeTypes.add(b);
		}
	}
	
	private int getMaxNodeNameLength(List<SpellNodeType> types)
	{
		int maxStringLength = 0;
		for(SpellNodeType type : types) 
		{
			if(type.name().length() > maxStringLength)
			{
				maxStringLength = type.name().length();
			}
		}
		return maxStringLength;
	}
	
}
