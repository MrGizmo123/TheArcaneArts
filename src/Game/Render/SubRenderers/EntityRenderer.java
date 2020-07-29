package Game.Render.SubRenderers;

import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;

import Game.Entities.Camera;
import Game.Entities.Entity;
import Game.Render.Shaders.EntityShader;
import Game.tools.Maths;

public class EntityRenderer {

	private EntityShader shader;
	
	public EntityRenderer(Matrix4f projMatrix)
	{
		shader = new EntityShader();
		
		shader.start();
		shader.loadProjMatrix(projMatrix);
		shader.start();
	}
	
	private void bindEntity(Entity e)
	{
		GL30.glBindVertexArray(e.getModel().model.vaoId);
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		GL20.glEnableVertexAttribArray(2);
		
		shader.loadTransMatrix(Maths.createTransMatrix(e.getPos(), e.getRot().x, e.getRot().y, e.getRot().z, e.getScale()));
		shader.loadTexture(e.getModel().texture);
	}
	
	public void render(List<Entity> entities, Camera cam)
	{
		shader.start();
		
		shader.loadViewMatrix(Maths.createViewMatrix(cam));
		
		for(Entity e : entities)
		{
			bindEntity(e);
			
			GL11.glDrawElements(GL11.GL_TRIANGLES, e.getModel().model.vertexCount, GL11.GL_UNSIGNED_INT, 0);
		}
		
		shader.stop();
	}
	
}
