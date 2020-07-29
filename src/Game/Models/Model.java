package Game.Models;

public class Model {

	public int vaoId;
	public int vertexCount;
	public int vbo;
	
	public Model(int vaoId,int vertexCount){
		this.vaoId = vaoId;
		this.vertexCount = vertexCount;
	}
	
	public Model(int vao,int vertexCount,int vbo){
		this.vaoId = vao;
		this.vertexCount = vertexCount;
		this.vbo = vbo;
	}
	
}
