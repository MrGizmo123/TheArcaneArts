package Game.tools;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.EXTTextureFilterAnisotropic;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GL33;
import org.lwjgl.opengl.GLContext;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import Game.Models.Model;
import Game.Models.ModelData;
import Game.Models.ModelTexture;
import Game.Models.OBJLoader;
import Game.Models.TexturedModel;

public class Loader {

	private List<Integer> vaos;
	private List<Integer> vbos;
	private List<Integer> textures;
	
	public Loader(){
		vaos = new ArrayList<Integer>();
		vbos = new ArrayList<Integer>();
		textures = new ArrayList<Integer>();
	}
	
	public Model loadToVAO(int[] indices, float[] positions, float[] normals){
		int vao = createVAO();
		store(0, 3, positions);
		store(1, 3, normals);
		storeIndeces(indices);
		GL30.glBindVertexArray(0);
		return new Model(vao,indices.length);
	}
	
	public Model loadToVAO(int[] indices, float[] positions, float[] textureCoords, float[] normals) {
		int vao = createVAO();
		store(0, 3, positions);
		store(1, 2, textureCoords);
		store(2, 3, normals);
		storeIndeces(indices);
		return new Model(vao, indices.length);
	}
	
	public int createEmptyVbo(int floatCount) {
		int vbo = GL15.glGenBuffers();
		vbos.add(vbo);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, floatCount * 4, GL15.GL_DYNAMIC_DRAW);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		return vbo;
	}
	
	public void addAttrib(int vao, int vbo, int attrib, int elementSize)
	{
		GL30.glBindVertexArray(vao);
		
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo);
		GL20.glVertexAttribPointer(attrib, elementSize, GL11.GL_FLOAT,false, 0, 0);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		
		GL30.glBindVertexArray(0);
	}
	
	public void addInstancedAttribf(int vao, int vbo, int attrib, int dataSize, int stride, int offset) {
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo);
		GL30.glBindVertexArray(vao);
		GL20.glVertexAttribPointer(attrib, dataSize, GL11.GL_FLOAT, false, stride * 4, offset * 4);
	    GL33.glVertexAttribDivisor(attrib, 1);
	    GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	    GL30.glBindVertexArray(0);
	}
	
	public void addInstancedAttribi(int vao, int vbo, int attrib, int dataSize, int stride, int offset) {
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo);
		GL30.glBindVertexArray(vao);
		GL20.glVertexAttribPointer(attrib, dataSize, GL11.GL_INT, false, stride * 4, offset * 4);
	    GL33.glVertexAttribDivisor(attrib, 1);
	    GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	    GL30.glBindVertexArray(0);
	}
	
	public void updateVbof(int vbo, float[] data) {
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo);
		FloatBuffer buffer = createFloatBuffer(data);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer.capacity() * 4, GL15.GL_DYNAMIC_DRAW);
		GL15.glBufferSubData(GL15.GL_ARRAY_BUFFER, 0, buffer);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	}
	
	public void updateVboi(int vbo, int[] data) {
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo);
		IntBuffer buffer = createIntBuffer(data);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer.capacity() * 4, GL15.GL_DYNAMIC_DRAW);
		GL15.glBufferSubData(GL15.GL_ARRAY_BUFFER, 0, buffer);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	}
	
	public Model loadToVAO(int[] indices, float[] positions) {
		int vao = createVAO();
		store(0, 3, positions);
		storeIndeces(indices);
		GL30.glBindVertexArray(0);
		return new Model(vao, indices.length);
	}
	
	public int loadToVAO3D(float[] positions, float[] textureCoords) {
		int vao = createVAO();
		store(0, 3, positions);
		store(1, 2, textureCoords);
		GL30.glBindVertexArray(0);
		return vao;
	}
	
	public int createVAO(){
		int vao = GL30.glGenVertexArrays();
		vaos.add(vao);
		GL30.glBindVertexArray(vao);
		return vao;
	}
	
	private void store(int attrib,int size,float[] data){
		int vbo = GL15.glGenBuffers();
		vbos.add(vbo);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo);
		FloatBuffer buffer = createFloatBuffer(data);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(attrib, size, GL11.GL_FLOAT,false, 0, 0);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	}
	
	public FloatBuffer createFloatBuffer(float[] data){
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}
	
	public void cleanUp(){
		for(int vao : vaos){
			GL30.glDeleteVertexArrays(vao);
		}
		for(int vbo : vbos){
			GL15.glDeleteBuffers(vbo);
		}
		for(int texture : textures){
			GL11.glDeleteTextures(texture);
		}
	}
	
	public int loadTexture(String fileName){
		Texture texture = null;
		try {
			texture = TextureLoader.getTexture("PNG", new FileInputStream("res/"+fileName+".png"));
			GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
			GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
			GL30.glGenerateMipmap(GL11.GL_TEXTURE_2D);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR_MIPMAP_LINEAR);
			
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL13.GL_CLAMP_TO_BORDER);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL13.GL_CLAMP_TO_BORDER);
			
			if(GLContext.getCapabilities().GL_EXT_texture_filter_anisotropic){
				float amount = Math.min(4f, EXTTextureFilterAnisotropic.GL_MAX_TEXTURE_MAX_ANISOTROPY_EXT);
				GL11.glTexParameterf(GL11.GL_TEXTURE_2D, EXTTextureFilterAnisotropic.GL_TEXTURE_MAX_ANISOTROPY_EXT, amount);
			}else{
				System.out.println("anisotropic filtering not supported");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		int textureID = texture.getTextureID();
		textures.add(textureID);
		return textureID;
	}
	
	private void storeIndeces(int[] indeces){
		int vboID = GL15.glGenBuffers();
		vbos.add(vboID);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboID);
		IntBuffer buffer = createIntBuffer(indeces);
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
	}
	
	public IntBuffer createIntBuffer(int[] data){
		IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}
	
	public TexturedModel loadTexturedModel(String modelName, String texture) {
		ModelData mData = OBJLoader.loadOBJ(modelName);
		Model model = loadToVAO(mData.getIndices(), mData.getVertices(), mData.getTextureCoords(), mData.getNormals());
		TexturedModel txm = new TexturedModel(model, loadTexture(texture));
		return txm;
	}
	
}
