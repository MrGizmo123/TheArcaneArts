package Game.Render.Shaders;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.util.vector.Matrix4f;

import Game.Entities.Camera;
import Game.tools.Maths;

public class CustomModelBlockShader extends ShaderProgram{

	private static final String VERTEX = "src/Game/Render/Shaders/customBlockVertex.glsl";
	private static final String FRAGMENT = "src/Game/Render/Shaders/customBlockFragment.glsl";
	
	private int location_projMatrix;
	private int location_viewMatrix;
	private int location_transMatrix;
	
	private int location_texture;
	
	public CustomModelBlockShader() {
		super(VERTEX, FRAGMENT);
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "pos");
		super.bindAttribute(1, "texCoords");
		super.bindAttribute(2, "normal");
	}
	
	@Override
	protected void UniformLocations() {
		location_projMatrix = super.uniformLocation("projMatrix");
		location_viewMatrix = super.uniformLocation("viewMatrix");
		location_texture = super.uniformLocation("sampler");
		location_transMatrix = super.uniformLocation("transMatrix");
	}
	
	public void loadProjMatrix(Matrix4f projMatrix)
	{
		super.loadMatrix(location_projMatrix, projMatrix);
	}
	
	public void loadTransMatrix(Matrix4f transMatrix)
	{
		super.loadMatrix(location_transMatrix, transMatrix);
	}
	
	public void loadViewMatrix(Camera cam)
	{
		super.loadMatrix(location_viewMatrix, Maths.createViewMatrix(cam));
	}
	
	public void loadTexture(int texture)
	{
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture);
		
		super.loadInt(location_texture, 0);
	}
	
}
