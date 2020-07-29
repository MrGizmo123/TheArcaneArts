package Game.Render.Shaders;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.util.vector.Matrix4f;

public class FontShader extends ShaderProgram{

	private static final String VERTEX = "src/Game/Render/Shaders/fontVertex.glsl";
	private static final String FRAGMENT = "src/Game/Render/Shaders/fontFragment.glsl";
	
	private int location_texture;
	private int location_transMatrix;
	
	public FontShader()
	{
		super(VERTEX, FRAGMENT);
	}

	@Override
	protected void bindAttributes() 
	{
		super.bindAttribute(0, "pos");
		super.bindAttribute(1, "texCoords");
	}
	
	@Override
	protected void UniformLocations() 
	{
		location_texture = super.uniformLocation("sampler");
		location_transMatrix = super.uniformLocation("transMatrix");
	}
	
	public void loadTransMatrix(Matrix4f matrix)
	{
		super.loadMatrix(location_transMatrix, matrix);
	}
	
	public void loadTexture(int texture)
	{
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture);
		
		super.loadInt(location_texture, 0);
	}
	
}
