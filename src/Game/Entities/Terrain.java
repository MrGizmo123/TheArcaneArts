package Game.Entities;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector3f;

import Game.Entities.Blocks.Block;
import Game.Entities.Blocks.BlockTypes.BoneFire;
import Game.Entities.Blocks.BlockTypes.SandBlock;
import Game.Entities.Blocks.BlockTypes.StoneBlock;
import Game.World.WorldGen.HeightsGenerator;

public class Terrain {
	
	public static final int LENGTH = 500;

	private List<Block> blocks;
	private float[][] heights;
	private HeightsGenerator generator;
	
	public Terrain() {
		blocks = new ArrayList<Block>();
		generator = new HeightsGenerator();
		heights = new float[LENGTH][LENGTH];
		generateTerrain();
	}
	
	private void generateTerrain() {
		for(int x=0;x < LENGTH;x++) {
			for(int z=0;z < LENGTH;z++) {
				float height = generator.generateHeight(x, z);
				heights[x][z] = height;
				
				if(height < -2)
				{
					Block b = new StoneBlock(new Vector3f(x, height, z));
					blocks.add(b);
				}
				else
				{
					Block b = new SandBlock(new Vector3f(x, height, z));
					blocks.add(b);
				}
				
				//Block fire = new BoneFire(new Vector3f(x, height+1, z));
				//blocks.add(fire);
				
			}
		}
		
		Block fire = new BoneFire(new Vector3f(0, 1, 0));
		blocks.add(fire);
		
	}
	
	public int getBlockCount() {
		return blocks.size();
	}
	
	public List<Block> getBlocks() {
		return blocks;
	}
	
	public float getHeight(int x, int z) {
		return heights[x][z];
	}
	
}

