package Game.Render;

import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;

import Game.Entities.Camera;
import Game.Entities.Entity;
import Game.Entities.Player;
import Game.Entities.Sun;
import Game.Entities.Blocks.CustomModelBlock;
import Game.Models.Model;
import Game.Render.SubRenderers.BlockRenderer;
import Game.Render.SubRenderers.CustomModelBlockRenderer;
import Game.Render.SubRenderers.EntityRenderer;
import Game.tools.Maths;

public class Renderer {

	private BlockRenderer blockRenderer;
	private CustomModelBlockRenderer customBlockRenderer;
	private EntityRenderer entityRenderer;
	
	public Renderer() {
		Matrix4f projMatrix = Maths.createProjectionMatrix(1000.0f, 0.01f);
		
		blockRenderer = new BlockRenderer(projMatrix);
		customBlockRenderer = new CustomModelBlockRenderer(projMatrix);
		entityRenderer = new EntityRenderer(projMatrix);
	}
	
	public void prepare() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glClearColor(1, 0, 0, 1);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glCullFace(GL11.GL_BACK);
	}
	
	public void renderStandardBlocks(Model blockModel, int noOfBlocks, Sun sun, Player player) {
		blockRenderer.render(blockModel, noOfBlocks, sun, player.getCam());
	}
	
	public void renderCustomBlocks(List<CustomModelBlock> blocks, Sun sun, Player player)
	{
		customBlockRenderer.render(blocks, player.getCam(), sun);
	}
	
	public void renderEntities(List<Entity> entities, Camera cam)
	{
		entityRenderer.render(entities, cam);
	}
	public void cleanUp() {
		blockRenderer.cleanUp();
		customBlockRenderer.cleanUp();
	}
	
}
