package Game.Render.Shaders;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import Game.Entities.Sun;

public class BlockShader extends ShaderProgram{

	private static final String VERTEX = "src/Game/Render/Shaders/blockVertex.glsl";
	private static final String FRAGMENT = "src/Game/Render/Shaders/blockFragment.glsl";
	
	private int location_projMatrix;
	private int location_viewMatrix;
	private int location_texture;
	private int location_sunDir;
	private int location_sunColor;
	private int location_atlasSize;
	
	public BlockShader() {
		super(VERTEX,FRAGMENT);
	}

	protected void UniformLocations() {
		location_projMatrix = super.uniformLocation("projMatrix");
		location_viewMatrix = super.uniformLocation("viewMatrix");
		location_texture = super.uniformLocation("tex");
		location_sunDir = super.uniformLocation("sunDir");
		location_sunColor = super.uniformLocation("sunColor");
		location_atlasSize = super.uniformLocation("atlasSize");
	}

	protected void bindAttributes() {
		super.bindAttribute(0, "pos");
		super.bindAttribute(1, "textureCoords");
		super.bindAttribute(2, "normal");
		
		super.bindAttribute(3, "blockPos");
		super.bindAttribute(4, "blockType");
	}
	public void loadTexture(int texture)
	{
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture);
		
		super.loadInt(location_texture, 0);
	}
	
	public void loadProjMatrix(Matrix4f matrix){
		super.loadMatrix(location_projMatrix, matrix);
	}
	
	public void loadViewMatrix(Matrix4f matrix){
		super.loadMatrix(location_viewMatrix, matrix);
	}
	
	public void loadSun(Sun sun)
	{
		super.loadVector(location_sunDir, sun.getDirection());
		super.loadVector(location_sunColor, sun.getColor());
	}
	
	public void loadAtlasSize(float size)
	{
		super.loadFloat(location_atlasSize, size);
	}
	
}
