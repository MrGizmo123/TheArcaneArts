package Gui;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;

import Game.tools.Input;
import Game.tools.Maths;
import Game.tools.utils.AABB;
import Gui.Constraints.AspectConstraint;
import Gui.Constraints.CenterConstraint;
import Gui.Constraints.PositionConstraint;
import Gui.Constraints.ScaleConstraint;

public class Gui {

	private PositionConstraint xConstraint;
	private PositionConstraint yConstraint;
	
	private ScaleConstraint scaleConstraint;
	private AspectConstraint aspectConstraint;
	
	protected int texture;
	protected GuiLayout parentLayout;
	
	private AABB boundingBox;
	private boolean hasFocus;
	private boolean isFocusable;
	
	// default settings
	public Gui(int texture, GuiLayout layout)
	{
		this.texture = texture;
		this.xConstraint = new CenterConstraint(CenterConstraint.X);
		this.yConstraint = new CenterConstraint(CenterConstraint.Y);
		
		this.scaleConstraint = new ScaleConstraint(0.5f,ScaleConstraint.WIDTH);
		this.aspectConstraint = new AspectConstraint(1);
		
		this.parentLayout = layout;
		
		recalculateAABB();
		hasFocus = false;
		isFocusable = true;
	}
	
	public void setFocusable(boolean f)
	{
		this.isFocusable = f;
	}
	
	//update function (some subclases might need this)
	public void update()
	{
		
	}
	
	public boolean hasFocus()
	{
		return hasFocus;
	}
	
	public void loseFocus()
	{
		hasFocus = false;
		focusLost();
	}
	
	public void setFocus()
	{
		hasFocus = true;
		focusGained();
	}
	
	public void checkFocus()
	{
		Vector2f mousePos = new Vector2f(Mouse.getX(),Mouse.getY());
		if(Input.isMouseButtonPressed(0))
		{
			if (boundingBox.isIntersecting(mousePos) && !hasFocus) {
				setFocus();
			}
			else
			{
				loseFocus();
			}
		}
	}
	
	public void focusGained() 
	{
		
	}
	
	public void focusLost()
	{
		
	}
	
	public boolean isKeyPressed(char key)
	{
		return (Input.isKeyPressed(key) && hasFocus);
	}
	
	public boolean isKeyReleased(char key)
	{
		return (Input.isKeyReleased(key) && hasFocus);
	}
	
	private void recalculateAABB()
	{
		boundingBox = calculateAABB();
	}
	
	private AABB calculateAABB() 
	{
		Vector2f scale = this.getScale();
		Vector2f pos = this.getPositionInViewPort();
		
		int widthInPixels = (int) (scale.x * Display.getWidth());
		int heightInPixels = (int) (scale.y * Display.getHeight());
		
		int x1 = (int) (pos.x - ((float)widthInPixels / (float)2));
		int y1 = (int) (pos.y - ((float)heightInPixels / (float)2));
		
		int x2 = (int) (pos.x + ((float)widthInPixels / (float)2));
		int y2 = (int) (pos.y + ((float)heightInPixels / (float)2));
		
		return new AABB(x1, y1, x2, y2);
		
	}
	
	//callback for when position constraints are updated
	protected void constraintsUpdated()
	{
		
	}
	
	public void show()
	{
		this.parentLayout.addGui(this);
	}
	
	public void hide()
	{
		this.parentLayout.removeGui(this);
	}
	
	// self explanatory
	
	public void addXPosConstraint(PositionConstraint c)
	{
		this.xConstraint = c;
		constraintsUpdated();
		recalculateAABB();
	}
	
	public void addYPosConstraint(PositionConstraint c)
	{
		this.yConstraint = c;
		constraintsUpdated();
		recalculateAABB();
	}
	
	public void addScaleConstraint(ScaleConstraint c)
	{
		this.scaleConstraint = c;
		constraintsUpdated();
		recalculateAABB();
	}
	
	public void addAspectConstraint(AspectConstraint c)
	{
		this.aspectConstraint = c;
		constraintsUpdated();
		recalculateAABB();
	}
	
	// returns opengl texture id
	public int getTexture()
	{
		return texture;
	}
	
	public float getAspect()
	{
		return aspectConstraint.getAspectRatio();
	}
	
	// calculates and returns the position in Normalised Device Coordinates
	public Vector2f getPositionInNDC()
	{
		int xPos = xConstraint.getPos();
		int yPos = yConstraint.getPos();
		
		Vector2f ndc = Maths.viewportToNDC(new Vector2f(xPos, yPos));
		return ndc;
	}
	
	// calculates and returns the position in Viewport coords
	public Vector2f getPositionInViewPort()
	{
		int xPos = xConstraint.getPos();
		int yPos = yConstraint.getPos();
		
		Vector2f result = new Vector2f(xPos, yPos);
		return result;
	}
	
	// calculates the scale according to axes and aspect ratio and returns it
	public Vector2f getScale()
	{
		float xScale;
		float yScale;
		
		float scale = scaleConstraint.getScale();
		
		if(scaleConstraint.getAxis() == ScaleConstraint.WIDTH)
		{
			xScale = scale;
			yScale = aspectConstraint.getHeightFromWidth(xScale);
		}
		else
		{
			yScale = scale;
			xScale = aspectConstraint.getWidthFromHeight(yScale);
		}
		
		return new Vector2f(xScale, yScale);
		
	}
	
	public int getWidth()
	{
		int width;
		
		float scale = scaleConstraint.getScale();
		
		if(scaleConstraint.getAxis() == ScaleConstraint.WIDTH)
		{
			width = (int) (scale * Display.getWidth());
		}
		else
		{
			width = (int) (aspectConstraint.getWidthFromHeight(scale) * Display.getWidth());
		}
		
		return width;
	}
	
	public int getHeight()
	{
		int height;
		
		float scale = scaleConstraint.getScale();
		
		if(scaleConstraint.getAxis() == ScaleConstraint.WIDTH)
		{
			height = (int) (aspectConstraint.getHeightFromWidth(scale) * Display.getHeight());
		}
		else
		{
			height = (int) (scale * Display.getHeight());
		}
		
		return height;
	}
	
}
