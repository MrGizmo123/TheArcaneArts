package Gui.Constraints;

import Game.Render.DisplayManager;

public class AspectConstraint {

	// width : height = aspectRatio : 1
	private float aspectRatio;
	
	public AspectConstraint(float aspectRatio)
	{
		this.aspectRatio = aspectRatio / DisplayManager.aspectRatio;
	}
	
	public float getWidthFromHeight(float height)
	{
		return height * aspectRatio;
	}
	
	public float getHeightFromWidth(float width)
	{
		return width / aspectRatio;
	}
	
	public float getAspectRatio()
	{
		return aspectRatio;
	}
	
}
