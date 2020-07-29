package Game.Entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import Game.Game;
import Game.Models.TexturedModel;
import Game.tools.GameResourcesAndSettings;
import Game.tools.Input;

public class Player extends Entity{
	
	private Camera cam;
	
	public Player(Vector3f pos) {
		super(new TexturedModel(GameResourcesAndSettings.BONEFIRE_MODEL, GameResourcesAndSettings.BONEFIRE_BLOCK_TEXTURES[0]),pos, 0,0,0,1);
		this.cam = new Camera(pos);
	}
	
	public void update() {
		if(Game.gameInput)
		{
			if(Input.isKeyPressed('w')){
				pos.x-=1f;
			}if(Input.isKeyPressed('s')){
				pos.x+=1f;
			}if(Input.isKeyPressed('a')){
				pos.z-=1f;
			}if(Input.isKeyPressed('d')){
				pos.z+=1f;
			}
		}
		cam.update(pos);
	}
	
	public Vector2f getPos2f()
	{
		return new Vector2f(pos.x, pos.z);
	}
	
	public Camera getCam() {
		return cam;
	}
	
}
