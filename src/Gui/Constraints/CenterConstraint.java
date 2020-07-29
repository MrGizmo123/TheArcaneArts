package Gui.Constraints;

import org.lwjgl.opengl.Display;

public class CenterConstraint extends PositionConstraint{

	public static int X = 0;
	public static int Y = 1;
	
	private int axis;
	
	public CenterConstraint(int axis) 
	{
		this.axis = axis;
	}
	
	@Override
	public int getPos() 
	{
		int displayDimension;
		
		if(axis == X)
		{
			displayDimension = Display.getWidth();
		}
		else
		{
			displayDimension = Display.getHeight();
		}
		
		return displayDimension / 2;
	}
	
}
