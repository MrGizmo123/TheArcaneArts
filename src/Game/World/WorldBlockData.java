package Game.World;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import Game.Game;
import Game.Entities.Blocks.AnimatedBlock;
import Game.Entities.Blocks.Block;
import Game.Entities.Blocks.CustomModelBlock;
import Game.Models.Model;
import Game.Models.ModelData;
import Game.tools.GameResourcesAndSettings;
import Game.tools.Maths;
import Game.tools.utils.vec2;

public class WorldBlockData {

	private static final int frustum_dist = 50;
	
	private Map<vec2, Chunk> chunks;
	
	private int standardBlockModelVBO;
	private int standardBlockTypeVBO;
	
	private Model standardBlockModel;
	private int noOfBlocks;
	
	public WorldBlockData()
	{
		chunks = new HashMap<vec2, Chunk>();
		
		noOfBlocks = 0;
		
		chunks.put(new vec2(0,1), new Chunk(new Vector2f(0,1)));
		
		ModelData data = GameResourcesAndSettings.block_model_data;
		standardBlockModel = Game.loader.loadToVAO(data.getIndices(), data.getVertices(), data.getTextureCoords(), data.getNormals());
		
		standardBlockModelVBO = Game.loader.createEmptyVbo(1);
		standardBlockTypeVBO = Game.loader.createEmptyVbo(1);
		
		Game.loader.addInstancedAttribf(standardBlockModel.vaoId, standardBlockModelVBO, 3, 3, 0, 0);
		Game.loader.addInstancedAttribf(standardBlockModel.vaoId, standardBlockTypeVBO, 4, 1, 0, 0);
	}
	
	public void update()
	{
		List<Block> allStandardBlocks = new ArrayList<Block>();
		
		for(Chunk c : chunks.values())
		{
			if(checkWhetherVisible(c))
			{
				List<Block> allBlocks = c.getRenderableBlocks();
				
				allStandardBlocks.addAll(allBlocks);
				
				c.update();
			}
		}
		
		noOfBlocks = allStandardBlocks.size();
		
		updateBlockVBO(allStandardBlocks);
	}
	
	public List<CustomModelBlock> getCustomBlocks()
	{
		List<CustomModelBlock> result = new ArrayList<CustomModelBlock>();
		
		for(Chunk c : chunks.values())
		{
			if(checkWhetherVisible(c))
			{
				result.addAll(c.getCustomBlocks());
			}
			
		}
		
		return result;
	}
	
	private boolean checkWhetherVisible(Chunk c)
	{
		Vector2f chunkRealPos = new Vector2f(c.getChunkGridPos().x * Chunk.CHUNK_SIZE * 2, c.getChunkGridPos().y * Chunk.CHUNK_SIZE * 2);
		Vector2f playerPos2f = Game.world.getPlayer().getPos2f();
		
		Vector2f chunkPos1 = new Vector2f(chunkRealPos.x, chunkRealPos.y);
		Vector2f chunkPos2 = new Vector2f(chunkRealPos.x + Chunk.CHUNK_SIZE, chunkRealPos.y);
		Vector2f chunkPos3 = new Vector2f(chunkRealPos.x, chunkRealPos.y + Chunk.CHUNK_SIZE);
		Vector2f chunkPos4 = new Vector2f(chunkRealPos.x + Chunk.CHUNK_SIZE, chunkRealPos.y + Chunk.CHUNK_SIZE);
		
		boolean chunk1 = Maths.distance(chunkPos1, playerPos2f) < frustum_dist;
		boolean chunk2 = Maths.distance(chunkPos2, playerPos2f) < frustum_dist;
		boolean chunk3 = Maths.distance(chunkPos3, playerPos2f) < frustum_dist;
		boolean chunk4 = Maths.distance(chunkPos4, playerPos2f) < frustum_dist;
		
		return chunk1 || chunk2 || chunk3 || chunk4;
	}
	
	public int getNoOfBlocks()
	{
		return noOfBlocks;
	}
	
	public int getVboId()
	{
		return standardBlockModelVBO;
	}
	
	public Model getStandardBlockModel()
	{
		return standardBlockModel;
	}
	
	public void addBlocks(List<Block> blocks)
	{
		for(Block b : blocks)
		{
			addBlock(b);
		}
	}
	
	public void addBlock(Block b)
	{
		
		Vector2f block_chunk = new Vector2f((int)b.getPos().x / Chunk.CHUNK_SIZE, (int)b.getPos().z / Chunk.CHUNK_SIZE);
		
		if(doesChunkExist(block_chunk))
		{
			chunks.get(new vec2(block_chunk)).addBlock(b);
		}
		else
		{
			Chunk c = new Chunk(block_chunk);
			c.addBlock(b);
			chunks.put(new vec2(c.getChunkGridPos()), c);
		}
		
	}
	
	private void updateBlockVBO(List<Block> inputBlocks)
	{
		float[] positions = createPositionArray(inputBlocks);
		float[] typesArray = createTypesArray(inputBlocks);
		
		Game.loader.updateVbof(standardBlockModelVBO, positions);
		Game.loader.updateVbof(standardBlockTypeVBO, typesArray);
	}
	
	private float[] createPositionArray(List<Block> inputBlocks)
	{
		float[] positions = new float[inputBlocks.size() * 3];
		
		int ptr = 0;
		for(Block b : inputBlocks) {
			Vector3f pos = b.getPos();
			positions[ptr] = pos.x * 2;
			positions[ptr + 1] = pos.y;
			positions[ptr + 2] = pos.z * 2;
			ptr += 3;
		}
		
		return positions;
	}
	
	private float[] createTypesArray(List<Block> inputBlocks)
	{
		float[] result = new float[inputBlocks.size()];
		
		int ptr = 0;
		for(Block b : inputBlocks)
		{
			result[ptr] = (float)b.getType();
			ptr++;
		}
		
		return result;
	}
	
	private boolean doesChunkExist(Vector2f chunkPos)
	{
		return (chunks.get(new vec2(chunkPos)) != null);
	}
	
}
