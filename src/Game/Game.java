package Game;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.sql.Timestamp;
import java.time.Instant;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import Game.Render.DisplayManager;
import Game.Render.Renderer;
import Game.Render.SubRenderers.FontRenderer;
import Game.Render.SubRenderers.GuiRenderer;
import Game.World.GameTickUpdater;
import Game.World.World;
import Game.tools.Input;
import Game.tools.Loader;
import Gui.GuiManager;

public class Game {
	
	public static Loader loader;
	private static Renderer renderer;
	
	public static World world;
	
	private static GuiRenderer guiRenderer;
	private static FontRenderer fontRenderer;
	
	public static boolean gameInput = true;
	
	public static void main(String args[]) {
		
		DisplayManager.createDisplay();
		init();
		
		while(!Display.isCloseRequested()) {
			renderer.prepare();
			renderer.renderStandardBlocks(world.getBlockData().getStandardBlockModel(),
								  world.getBlockData().getNoOfBlocks(),
								  world.getSun(),
								  world.getPlayer());
			
			renderer.renderCustomBlocks(world.getBlockData().getCustomBlocks(), world.getSun(), world.getPlayer());
			renderer.renderEntities(world.getEntities(), world.getPlayer().getCam());
			
			guiRenderer.renderGuis(GuiManager.getActiveGuis());
			fontRenderer.renderTexts(GuiManager.getActiveTexts());
			
			GuiManager.update();
			
			world.update();
			
			DisplayManager.updateDisplay();
			GameTickUpdater.update();
			
			Input.update();
			
			checkForSceenShot();
		}
		
		renderer.cleanUp();
		loader.cleanUp();
		
		DisplayManager.closeDisplay();
		
	}
	
	public static void freezeGameInput()
	{
		gameInput = false;
	}
	
	public static void unFreezeGameInput()
	{
		gameInput = true;
	}
	
	private static void init() {
		GameTickUpdater.init();
		
		loader = new Loader();
		renderer = new Renderer();
		
		guiRenderer = new GuiRenderer();
		fontRenderer = new FontRenderer();
		
		GuiManager.init();
		Input.init();
		
		world = new World();
	}
	
	private static void checkForSceenShot()
	{
		if(Input.isKeyPressed((char)65))
		{
			GL11.glReadBuffer(GL11.GL_FRONT);
			int width = Display.getDisplayMode().getWidth();
			int height= Display.getDisplayMode().getHeight();
			int bpp = 4; // Assuming a 32-bit display with a byte each for red, green, blue, and alpha.
			ByteBuffer buffer = BufferUtils.createByteBuffer(width * height * bpp);
			GL11.glReadPixels(0, 0, width, height, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buffer );
			
			File file = new File("res/screenshot_" + System.currentTimeMillis() + ".png"); // The file to save to.
			String format = "PNG"; // Example: "PNG" or "JPG"
			BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			   
			for(int x = 0; x < width; x++) 
			{
			    for(int y = 0; y < height; y++)
			    {
			        int i = (x + (width * y)) * bpp;
			        int r = buffer.get(i) & 0xFF;
			        int g = buffer.get(i + 1) & 0xFF;
			        int b = buffer.get(i + 2) & 0xFF;
			        image.setRGB(x, height - (y + 1), (0xFF << 24) | (r << 16) | (g << 8) | b);
			    }
			}
			   
			try {
			    ImageIO.write(image, format, file);
			} catch (IOException e) { e.printStackTrace(); }
		}
	}

}
