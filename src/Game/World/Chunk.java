package Game.World;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import Game.Entities.Blocks.AnimatedBlock;
import Game.Entities.Blocks.Block;
import Game.Entities.Blocks.CustomModelBlock;
import Game.tools.utils.vec3;

public class Chunk {

	public static final int CHUNK_SIZE = 16;
	
	private Vector2f chunkGridPos;
	
	private Map<vec3, Block> standardBlocks;
	private Map<vec3, CustomModelBlock> customBlocks;
	
	public Chunk(Vector2f chunkGridPos)
	{
		this.chunkGridPos = chunkGridPos;
		
		this.standardBlocks = new HashMap<vec3, Block>();
		this.customBlocks = new HashMap<vec3, CustomModelBlock>();
	}
	
	public Chunk(Vector2f chunkGridPos, List<Block> blockList)
	{
		this.chunkGridPos = chunkGridPos;
		
		this.standardBlocks = arrangeBlocks(blockList);
	}
	
	public void update()
	{
		for(CustomModelBlock c : customBlocks.values())
		{
			if(c instanceof AnimatedBlock)
			{
				((AnimatedBlock) c).update();
			}
		}
	}
	
	public Vector2f getChunkGridPos()
	{
		return this.chunkGridPos;
	}
	
	public List<Block> getRenderableBlocks()
	{
		return getVisibleBlocks(standardBlocks);
	}
	
	public Collection<CustomModelBlock> getCustomBlocks()
	{
		return customBlocks.values();
	}
	
	public void addBlock(Block b)
	{
		
		if(b instanceof CustomModelBlock)
		{
			customBlocks.put(new vec3(b.getPos()), (CustomModelBlock) b);
			return;
		}
		
		standardBlocks.put(new vec3(b.getPos()), b);
	}
	
	private List<Block> getVisibleBlocks(Map<vec3, Block> blockMap)
	{
		List<Block> result = new ArrayList<Block>();
		
		for(Block b : blockMap.values())
		{
			if(isVisible(b))
			{
				result.add(b);
			}
		}
		
		return result;
		
	}
	
	private boolean isVisible(Block block)
	{
		Vector3f blockPos = block.getPos();
		
		Vector3f up = new Vector3f(blockPos.x, blockPos.y+1, blockPos.z);
		Vector3f down = new Vector3f(blockPos.x, blockPos.y-1, blockPos.z);
		
		Vector3f front = new Vector3f(blockPos.x+1, blockPos.y, blockPos.z);
		Vector3f back = new Vector3f(blockPos.x-1, blockPos.y, blockPos.z);
		
		Vector3f left = new Vector3f(blockPos.x, blockPos.y, blockPos.z+1);
		Vector3f right = new Vector3f(blockPos.x, blockPos.y, blockPos.z-1);
		
		Block u = standardBlocks.get(new vec3(up));
		Block d = standardBlocks.get(new vec3(down));
		
		Block f = standardBlocks.get(new vec3(front));
		Block b = standardBlocks.get(new vec3(back));
		
		Block l = standardBlocks.get(new vec3(left));
		Block r = standardBlocks.get(new vec3(right));
		
		return  (u == null || d == null ||
				 f == null || b == null ||
				 l == null || r == null);
	}
	
	private Map<vec3, Block> arrangeBlocks(List<Block> inputBlocks)
	{
		Map<vec3, Block> result = new HashMap<vec3, Block>();
		
		for(Block b : inputBlocks)
		{
			result.put(new vec3(b.getPos()), b);
		}
		
		return result;
	}
	
}
