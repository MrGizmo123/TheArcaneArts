package Gui.Components.SpellEditor;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;

import Game.Render.DisplayManager;
import Game.Spell.SpellDataType;
import Game.Spell.SpellNodeType;
import Game.tools.GameResourcesAndSettings;
import Gui.GuiLayout;
import Gui.Components.ClickListener;
import Gui.Components.GuiButton;
import Gui.Constraints.AbsolutePositionConstraint;
import Gui.Constraints.AspectConstraint;
import Gui.Constraints.ScaleConstraint;
import Gui.Layouts.SpellProgrammingMenu;

public class GuiSpellNode extends GuiButton{

	private static final float width = 0.075f;
	
	private SpellNodeType type;
	private int pointer;
	
	// the pointer var will contain the pointer to this node
	public GuiSpellNode(SpellNodeType type, int pointer, GuiLayout parentLayout)
	{
		super(type.name(), 0.3f, GameResourcesAndSettings.GUI_GREY, new Vector2f(0, 50), parentLayout);
		this.type = type;
		this.pointer = pointer;
		
		createGuis(parentLayout);
	}
	
	private void createGuis(GuiLayout parentLayout)
	{
		this.addScaleConstraint(new ScaleConstraint(width, ScaleConstraint.WIDTH));
		this.addAspectConstraint(new AspectConstraint(1));
			
		this.addXPosConstraint(new AbsolutePositionConstraint(Mouse.getX()));
		this.addYPosConstraint(new AbsolutePositionConstraint(Mouse.getY()));
			
		show();
		
		Vector2f nodePos = new Vector2f(Mouse.getX(),Mouse.getY());
		addInputs(type.getNoOfInputs(), nodePos, width, parentLayout);
		addOutputs(type.getNoOfOutputs(), nodePos, width, parentLayout);
	}
	
	private void addInputs(int noOfInputs, Vector2f nodePos, float nodeWidth, GuiLayout parentLayout)
	{
		float increment = 2f / (float)(noOfInputs + 1);
		
		Vector2f cursor = new Vector2f(nodePos.x - (Display.getWidth() * nodeWidth / 2f),
									   nodePos.y + (Display.getHeight() * nodeWidth * increment * (noOfInputs - 1)) / 2);
		
		for(int i=0;i<noOfInputs;i++)
		{
			addInput(cursor, this.getInputDataType(i), i);
			
			cursor.y-=(increment * nodeWidth * Display.getHeight());
		}
		
	}
	
	private void addOutputs(int noOfOutputs, Vector2f nodePos, float nodeWidth, GuiLayout parentLayout)
	{
		float increment = 2f / (float)(noOfOutputs + 1);
		
		Vector2f cursor = new Vector2f(nodePos.x + (Display.getWidth() * nodeWidth / 2f),
									   nodePos.y + (Display.getHeight() * nodeWidth * increment * (noOfOutputs - 1)) / 2);
		
		for(int i=0;i<noOfOutputs;i++)
		{
			addOutput(cursor, this.getOutputDataType(i));
			
			cursor.y-=(increment * nodeWidth *  Display.getHeight());
		}
		
	}
	
	private void addInput(Vector2f cursor, SpellDataType typeOfPort, int index)
	{
		GuiButton g = new GuiButton(typeOfPort.getTexture(), parentLayout);
		g.addXPosConstraint(new AbsolutePositionConstraint((int) cursor.x));
		g.addYPosConstraint(new AbsolutePositionConstraint((int) cursor.y));
		g.addScaleConstraint(new ScaleConstraint(0.01f, ScaleConstraint.WIDTH));
		g.addAspectConstraint(new AspectConstraint(1.77f));
		
		g.show();
		
		g.setClickListener(new ClickListener() {

			@Override
			public void clicked() {
				
				if(parentLayout instanceof SpellProgrammingMenu)
				{
					SpellProgrammingMenu menu = (SpellProgrammingMenu) parentLayout;
					
					if(menu.isConnectorSelected())
					{
						menu.setSelectedConnectorP2(g.getPositionInViewPort());
						menu.setSelectedConnectorOutput(pointer, index);
						menu.setSelectedConnector(null);
					}
				}
				
			}
			
		});
	}
	
	private void addOutput(Vector2f cursor, SpellDataType typeOfPort)
	{
		GuiButton g = new GuiButton(typeOfPort.getTexture(), parentLayout);
		g.addXPosConstraint(new AbsolutePositionConstraint((int) cursor.x));
		g.addYPosConstraint(new AbsolutePositionConstraint((int) cursor.y));
		g.addScaleConstraint(new ScaleConstraint(0.01f, ScaleConstraint.WIDTH));
		g.addAspectConstraint(new AspectConstraint(1.77f));
		
		g.setClickListener(new ClickListener() {

			@Override
			public void clicked() {
				
				if(parentLayout instanceof SpellProgrammingMenu)
				{
					SpellProgrammingMenu menu = (SpellProgrammingMenu) parentLayout;
					
					if(menu.isConnectorSelected())
					{
						menu.removeCurrentConnector();
						menu.setSelectedConnector(null);
						return;
					}
					
					menu.setSelectedConnector(new GuiSpellConnector(g.getPositionInViewPort(),
											  new Vector2f(Mouse.getX(), Mouse.getY()),
											  typeOfPort,
											  10,
											  parentLayout,
											  pointer));
				}
				
			}
			
		});
		
		g.show();
	}
	
	public SpellDataType getInputDataType(int port)
	{
		return this.type.getInputDataType(port);
	}
	
	public SpellDataType getOutputDataType(int port)
	{
		return this.type.getOutputDataType(port);
	}
	
	public SpellNodeType getType()
	{
		return type;
	}
	
}
