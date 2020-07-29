package Gui.Constraints;

import org.lwjgl.opengl.Display;

public class RelativePositionConstraint extends PositionConstraint{

	public static final int WIDTH = 0;
	public static final int HEIGHT = 1;
	
	private float pos;
	private int type;
	
	public RelativePositionConstraint(float pos, int type) {
		this.pos = pos;
		this.type = type;
	}
	
	@Override
	public int getPos() {
		return (int) ((type == WIDTH) ? pos * Display.getWidth() : pos * Display.getHeight());
	}

}
