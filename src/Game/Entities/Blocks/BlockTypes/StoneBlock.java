package Game.Entities.Blocks.BlockTypes;

import org.lwjgl.util.vector.Vector3f;

import Game.Entities.Blocks.Block;

public class StoneBlock extends Block{

	public StoneBlock(Vector3f pos) {
		super(pos);
	}

	@Override
	public Vector3f getPos() {
		return super.pos;
	}

	@Override
	public int getType() {
		return 0;
	}

}
