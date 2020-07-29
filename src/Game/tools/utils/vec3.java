package Game.tools.utils;

import org.lwjgl.util.vector.Vector3f;

public class vec3 {

	public float x;
	public float y;
	public float z;
	
	public vec3()
	{
		x = 0;
		y = 0;
		z = 0;
	}
	
	public vec3(float X, float Y, float Z)
	{
		this.x = X;
		this.y = Y;
		this.z = Z;
	}
	
	public vec3(Vector3f v)
	{
		this.x = v.x;
		this.y = v.y;
		this.z = v.z;
	}
	
	@Override
	public boolean equals(Object o)
	{
		vec3 object = (vec3)o;
		
		return (object.x == this.x && object.y == this.y && object.z == this.z);
	}
	
	@Override 
	public int hashCode()
	{
		return (int)x + (int)y + (int)z;
	}
	
}
