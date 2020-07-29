package Game.tools.utils;

import org.lwjgl.util.vector.Vector2f;

public class AABB {

	private Vector2f p1;
	private Vector2f p2;
	
	public AABB(int x1, int y1, int x2, int y2)
	{
		p1 = new Vector2f(x1, y1);
		p2 = new Vector2f(x2, y2);
	}
	
	public boolean isIntersecting(Vector2f p)
	{
		boolean xBounds = p1.x < p.x && p2.x > p.x;
		boolean yBounds = p1.y < p.y && p2.y > p.y;
		return xBounds && yBounds;
	}
	
}
