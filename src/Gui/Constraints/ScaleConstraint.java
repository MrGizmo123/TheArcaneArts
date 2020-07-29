package Gui.Constraints;

public class ScaleConstraint {

	public static final int WIDTH = 0;
	public static final int HEIGHT = 1;
	
	private float scale;
	private int axis;
	
	// takes in scale (1.0 is the width of the window)
	public ScaleConstraint(float scale, int axis)
	{
		this.scale = scale;
		this.axis = axis;
	}
	
	// returns the scale
	public float getScale() {
		return scale;
	}
	
	// returns the axis i.e. width , height
	public int getAxis()
	{
		return axis;
	}
	
}
