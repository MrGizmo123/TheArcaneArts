package Game.Render.SubRenderers;

import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import Game.Entities.Camera;
import Game.Entities.Sun;
import Game.Entities.Blocks.Block;
import Game.Entities.Blocks.CustomModelBlock;
import Game.Render.Shaders.CustomModelBlockShader;
import Game.tools.Maths;

public class CustomModelBlockRenderer {

	CustomModelBlockShader shader;
	
	public CustomModelBlockRenderer(Matrix4f projMatrix)
	{
		shader = new CustomModelBlockShader();
		
		shader.start();
		shader.loadProjMatrix(projMatrix);
		shader.stop();
	}
	
	private void prepare()
	{
		GL11.glDisable(GL11.GL_CULL_FACE);
	}
	
	private void bindBlock(CustomModelBlock b)
	{
		GL30 .glBindVertexArray(b.getModel().model.vaoId);
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		GL20.glEnableVertexAttribArray(2);
		
		shader.loadTexture(b.getModel().texture);
		
		shader.loadTransMatrix(Maths.createTransMatrix(new Vector3f(((Block) b).getPos().x * 2,((Block) b).getPos().y,((Block) b).getPos().z * 2), 0, 0, 0, 1f));
	}
	
	public void render(List<CustomModelBlock> blocks, Camera cam, Sun sun)
	{
		prepare();
		
		shader.start();
		
		shader.loadViewMatrix(cam);
		
		for(CustomModelBlock b : blocks)
		{
			
			bindBlock(b);
			
			GL11.glDrawElements(GL11.GL_TRIANGLES, b.getModel().model.vertexCount, GL11.GL_UNSIGNED_INT, 0);
			
		}
		
		shader.stop();
	}
	
	public void cleanUp()
	{
		shader.cleanUp();
	}
	
}
