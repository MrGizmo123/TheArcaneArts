package Game.Entities.Blocks;

import org.lwjgl.util.vector.Vector3f;

public abstract class Block {
	
	protected Vector3f pos;
	
	public Block(Vector3f pos) {
		this.pos = pos;
	}
	
	public Vector3f getPos()
	{
		return pos;
	}
	
	public abstract int getType();
	
}
