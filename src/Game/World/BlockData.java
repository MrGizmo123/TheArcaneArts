package Game.World;

import java.util.List;

import Game.Entities.Blocks.Block;
import Game.Entities.Blocks.CustomModelBlock;

public class BlockData {

	private List<Block> standardBlocks;
	private List<CustomModelBlock> customModelBlocks;
	
	public BlockData(List<Block> standardBlocks, List<CustomModelBlock> customModelBlocks) {
		super();
		this.standardBlocks = standardBlocks;
		this.customModelBlocks = customModelBlocks;
	}

	public List<Block> getStandardBlocks() {
		return standardBlocks;
	}

	public List<CustomModelBlock> getCustomModelBlocks() {
		return customModelBlocks;
	}
	
}
