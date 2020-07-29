package Game.Render.SubRenderers;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GL31;
import org.lwjgl.util.vector.Matrix4f;

import Game.Entities.Camera;
import Game.Entities.Sun;
import Game.Models.Model;
import Game.Models.ModelData;
import Game.Models.OBJLoader;
import Game.Render.Shaders.BlockShader;
import Game.tools.GameResourcesAndSettings;
import Game.tools.Loader;
import Game.tools.Maths;

public class BlockRenderer {
	
	private BlockShader shader;
	
	public BlockRenderer(Matrix4f projMatrix) {
		shader = new BlockShader();
		shader.start();
		shader.loadProjMatrix(projMatrix);
		shader.stop();
	}
	
	public void prepare() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glClearColor(1, 0, 0, 1);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
	}
	
	private void bindBlockModel(Model m) {
		GL30.glBindVertexArray(m.vaoId);
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		GL20.glEnableVertexAttribArray(2);
		GL20.glEnableVertexAttribArray(3);
		GL20.glEnableVertexAttribArray(4);
	}
	
	public void render(Model blockModel, int noOfBlocks, Sun s, Camera cam) {
		shader.start();
		
		bindBlockModel(blockModel);
		
		shader.loadTexture(GameResourcesAndSettings.BLOCK_TEXTURE_ATLAS);
		shader.loadSun(s);
		shader.loadViewMatrix(Maths.createViewMatrix(cam));
		shader.loadAtlasSize(GameResourcesAndSettings.BLOCK_ATLAS_SIZE);
		
		GL31.glDrawElementsInstanced(GL11.GL_TRIANGLES, blockModel.vertexCount, GL11.GL_UNSIGNED_INT, 0, noOfBlocks);
		
		shader.stop();
	}
	
	public void cleanUp(){
		shader.cleanUp();
	}
	
}
