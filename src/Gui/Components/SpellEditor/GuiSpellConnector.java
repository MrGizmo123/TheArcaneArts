package Gui.Components.SpellEditor;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;

import Game.Render.DisplayManager;
import Game.Spell.SpellDataType;
import Gui.Gui;
import Gui.GuiLayout;
import Gui.Constraints.AbsolutePositionConstraint;
import Gui.Constraints.AspectConstraint;
import Gui.Constraints.ScaleConstraint;
import Gui.Layouts.SpellProgrammingMenu;

public class GuiSpellConnector {

	private Vector2f p1;
	private Vector2f p2;
	
	private Gui s1;
	private Gui s2;
	private Gui s3;
	
	private int width;
	
	private int inputNode; // pointer to the input node (index in list)
	private int outputNode; // pointer to the output node (index in list)
	
	private SpellDataType type;
	private GuiLayout parent;
	
	public GuiSpellConnector(Vector2f p1, Vector2f p2, SpellDataType t, int width, GuiLayout parentLayout, int inputNodePtr)
	{
		this.p1 = p1;
		this.p2 = p2;
		this.width = width;
		this.parent = parentLayout;
		this.type = t;
		
		s1 = new Gui(t.getTexture(), parentLayout);
		s2 = new Gui(t.getTexture(), parentLayout);
		s3 = new Gui(t.getTexture(), parentLayout);
		generateGuis();
		
		s1.show();
		s2.show();
		s3.show();
		
		this.inputNode = inputNodePtr;
	}
	
	public void setOutputNode(int outputNode, int port)
	{
		this.outputNode = outputNode;
		if(this.parent instanceof SpellProgrammingMenu)
		{
			SpellProgrammingMenu menu = (SpellProgrammingMenu) parent;
			
			if((menu.getNodeFromPtr(outputNode).getInputDataType(port)).equals(this.type))
			{
				
				this.outputNode = outputNode;
			}
			else
			{
				this.hide();
			}
		}
	}
	
	public SpellDataType getType()
	{
		return type;
	}
	
	public int getTexture()
	{
		return this.type.getTexture();
	}
	
	public int getInputNodePointer()
	{
		return inputNode;
	}
	
	public int getOutputNodePointer()
	{
		return outputNode;
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public void setP2(Vector2f p2)
	{
		this.p2 = p2;
		generateGuis();
	}
	
	private void generateGuis()
	{
		float midX = (p1.x + p2.x) / 2f;
		float midY = (p1.y + p2.y) / 2f; 
		
		// Segment 1 -------------------------------------------------------
		float s1Width = (p1.x - midX) / (float)Display.getWidth();
		float s1Aspect = ((float)width / (float)Display.getHeight()) / s1Width;
		
		s1.addXPosConstraint(new AbsolutePositionConstraint((int)((p1.x + midX) / 2)));
		s1.addYPosConstraint(new AbsolutePositionConstraint((int)(p1.y)));
		s1.addScaleConstraint(new ScaleConstraint(s1Width, ScaleConstraint.WIDTH));
		s1.addAspectConstraint(new AspectConstraint(DisplayManager.aspectRatio/s1Aspect));
		
		// Segment 2 -------------------------------------------------------
		float s2Height = (p1.y - p2.y) / (float)Display.getHeight();
		float s2Aspect = s2Height / ((float)width / (float)Display.getWidth());
		
		s2.addXPosConstraint(new AbsolutePositionConstraint((int)midX));
		s2.addYPosConstraint(new AbsolutePositionConstraint((int)midY));
		s2.addScaleConstraint(new ScaleConstraint(s2Height, ScaleConstraint.HEIGHT));
		s2.addAspectConstraint(new AspectConstraint(DisplayManager.aspectRatio/s2Aspect));
		
		// Segment 3 -------------------------------------------------------
		float s3Width = (midX - p2.x) / (float)Display.getWidth();
		float s3Aspect = ((float)width / (float)Display.getHeight()) / s3Width;
		
		s3.addXPosConstraint(new AbsolutePositionConstraint((int)((p2.x + midX) / 2)));
		s3.addYPosConstraint(new AbsolutePositionConstraint((int)(p2.y)));
		s3.addScaleConstraint(new ScaleConstraint(s3Width, ScaleConstraint.WIDTH));
		s3.addAspectConstraint(new AspectConstraint(DisplayManager.aspectRatio/s3Aspect));
		
	}
	
	public void hide()
	{
		s1.hide();
		s2.hide();
		s3.hide();
	}
	
}
