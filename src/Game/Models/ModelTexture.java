package Game.Models;

public class ModelTexture {

	public int texture;
	public float reflectivity;
	public float shineDamper;
	
	public ModelTexture(int texture,float reflectivity,float shineDamper){
		this.texture = texture;
		this.reflectivity = reflectivity;
		this.shineDamper = shineDamper;
	}
	
}
