package Game.World;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector3f;

import Game.Entities.Entity;
import Game.Entities.Player;
import Game.Entities.Sun;
import Game.Entities.Terrain;

public class World {

	private Player player;
	private Sun sun;
	
	private WorldBlockData blockData;
	private List<Entity> entities;
	
	public World()
	{
		entities = new ArrayList<Entity>();
		
		blockData = new WorldBlockData();
		Terrain t = new Terrain();
		blockData.addBlocks(t.getBlocks());
		
		player = new Player(new Vector3f(Terrain.LENGTH,0,Terrain.LENGTH));
		entities.add(player);
		
		sun = new Sun(new Vector3f(0,-1,0), new Vector3f(1,1,1));
	}
	
	public void addEntity(Entity e)
	{
		entities.add(e);
	}
	
	public void update()
	{
		player.update();
		
		blockData.update();
	}

	public Player getPlayer() {
		return player;
	}

	public Sun getSun() {
		return sun;
	}
	
	public WorldBlockData getBlockData()
	{
		return blockData;
	}
	
	public List<Entity> getEntities()
	{
		return entities;
	}
	
}
