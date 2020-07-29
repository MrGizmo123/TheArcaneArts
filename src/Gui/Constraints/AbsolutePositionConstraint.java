package Gui.Constraints;

public class AbsolutePositionConstraint extends PositionConstraint{
	
	private int offset;
	
	//takes in the absolute value of offset
	public AbsolutePositionConstraint(int value)
	{
		this.offset = value;
	}

	//gives the offset in pixels
	public int getPos() {
		return offset;
	}

}
