package Game.Render.Shaders;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.util.vector.Matrix4f;

public class EntityShader extends ShaderProgram {

	private static final String VERTEX = "src/Game/Render/Shaders/entityVertex.glsl";
	private static final String FRAGMENT = "src/Game/Render/Shaders/entityFragment.glsl";
	
	private int location_transMatrix;
	private int location_viewMatrix;
	private int location_projMatrix;
	private int location_texture;
	
	public EntityShader() {
		super(VERTEX,FRAGMENT);
	}
	
	@Override
	protected void UniformLocations() {
		location_transMatrix = super.uniformLocation("transMatrix");
		location_viewMatrix = super.uniformLocation("viewMatrix");
		location_projMatrix = super.uniformLocation("projMatrix");
		location_texture = super.uniformLocation("sampler");
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "pos");
		super.bindAttribute(1, "texCoords");
		super.bindAttribute(2, "normal");
	}
	
	public void loadTexture(int texture)
	{
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture);
		
		super.loadInt(location_texture, 0);
	}
	
	public void loadTransMatrix(Matrix4f matrix)
	{
		super.loadMatrix(location_transMatrix, matrix);
	}
	
	public void loadViewMatrix(Matrix4f matrix)
	{
		super.loadMatrix(location_viewMatrix, matrix);
	}
	
	public void loadProjMatrix(Matrix4f matrix)
	{
		super.loadMatrix(location_projMatrix, matrix);
	}

}
