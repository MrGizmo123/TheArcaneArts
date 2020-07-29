package Game.Entities;

import org.lwjgl.util.vector.Vector3f;

public class Sun {

	private Vector3f direction;
	private Vector3f color;
	
	public Sun(Vector3f dir, Vector3f color) {
		this.direction = dir;
		this.color = color;
	}
	
	public Vector3f getDirection() {
		return direction;
	}
	
	public Vector3f getColor() {
		return color;
	}
	
	public void setDir(Vector3f dir) {
		this.direction = dir;
	}
	
}
