package Game.Entities.Blocks.BlockTypes;

import org.lwjgl.util.vector.Vector3f;

import Game.Entities.Blocks.AnimatedBlock;
import Game.Entities.Blocks.Block;
import Game.Entities.Blocks.CustomModelBlock;
import Game.Models.TexturedModel;
import Game.World.GameTickUpdater;
import Game.tools.GameResourcesAndSettings;

public class BoneFire extends Block implements CustomModelBlock, AnimatedBlock{
	
	private TexturedModel m = new TexturedModel(GameResourcesAndSettings.BONEFIRE_MODEL, GameResourcesAndSettings.BONEFIRE_BLOCK_TEXTURES[0]);
	private int currentFrame = 0;
	
	public BoneFire(Vector3f pos) {
		super(pos);
	}
	
	@Override
	public void update() {
		int noOfFramesToAdvance = (int) (GameTickUpdater.getNoOfTicks() / GameResourcesAndSettings.BONEFIRE_ANIMATION_SPEED);
		
		int increment = noOfFramesToAdvance - currentFrame;
		currentFrame+=increment;
		currentFrame%=GameResourcesAndSettings.BONEFIRE_ANIMATION_SIZE;
		
		m.texture = GameResourcesAndSettings.BONEFIRE_BLOCK_TEXTURES[currentFrame];
		
	}

	@Override
	public int getType() {
		return 2;
	}

	@Override
	public TexturedModel getModel() {
		return m;
	}
	
}
