package Game.tools.utils;

import org.lwjgl.util.vector.Vector2f;

public class vec2 {

	public float x;
	public float y;
	
	public vec2()
	{
		x = 0;
		y = 0;
	}
	
	public vec2(float X, float Y)
	{
		this.x = X;
		this.y = Y;
	}
	
	public vec2(Vector2f v)
	{
		this.x = v.x;
		this.y = v.y;
	}
	
	@Override
	public boolean equals(Object o)
	{
		vec2 object = (vec2)o;
		
		return (object.x == this.x && object.y == this.y);
	}
	
	@Override 
	public int hashCode()
	{
		return (int)x + (int)y;
	}
	
}
