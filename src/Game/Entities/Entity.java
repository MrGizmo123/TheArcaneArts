package Game.Entities;

import org.lwjgl.util.vector.Vector3f;

import Game.Models.TexturedModel;

public class Entity {

	protected TexturedModel model;
	protected Vector3f pos;
	protected Vector3f rot;
	protected float scale;
	
	public Entity(TexturedModel model, Vector3f pos, float rx, float ry, float rz, float scale) {
		this.model = model;
		this.pos = pos;
		this.rot = new Vector3f(rx, ry, rz);
		this.scale = scale;
	}
	
	public TexturedModel getModel() {
		return model;
	}
	
	public Vector3f getPos() {
		return pos;
	}
	
	public Vector3f getRot() {
		return rot;
	}
	
	public float getScale() {
		return scale;
	}
	
}
