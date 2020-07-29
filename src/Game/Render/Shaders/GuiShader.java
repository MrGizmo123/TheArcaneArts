package Game.Render.Shaders;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.util.vector.Matrix4f;

public class GuiShader extends ShaderProgram{

	private static final String VERTEX = "src/Game/Render/Shaders/guiVertex.glsl";
	private static final String FRAGMENT = "src/Game/Render/Shaders/guiFragment.glsl";
	
	private int location_texture;
	private int location_projMatrix;
	private int location_transMatrix;
	private int location_aspectRatio;
	private int location_isSelected;
	
	public GuiShader() {
		super(VERTEX, FRAGMENT);
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "pos");
		super.bindAttribute(1, "texCoords");
	}
	
	@Override
	protected void UniformLocations() {
		location_texture = super.uniformLocation("sampler");
		location_projMatrix = super.uniformLocation("projMatrix");
		location_transMatrix = super.uniformLocation("transMatrix");
		location_aspectRatio = super.uniformLocation("aspectRatio");
		location_isSelected = super.uniformLocation("isSelected");
	}
	
	public void isSelected(boolean isSelected)
	{
		super.loadBoolean(location_isSelected, isSelected);
	}
	
	public void loadTexture(int texture)
	{
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture);
		
		super.loadInt(location_texture, 0);
	}
	
	public void loadProjMatrix(Matrix4f matrix)
	{
		super.loadMatrix(location_projMatrix, matrix);
	}
	
	public void loadTransMatrix(Matrix4f matrix)
	{
		super.loadMatrix(location_transMatrix, matrix);
	}
	
	public void loadAspectRatio(float aspect)
	{
		super.loadFloat(location_aspectRatio, aspect);
	}
	
}
