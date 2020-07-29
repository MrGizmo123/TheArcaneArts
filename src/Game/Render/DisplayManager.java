package Game.Render;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;

import Game.tools.GameResourcesAndSettings;

public class DisplayManager {
	
	public static float aspectRatio;
	
	public static void createDisplay(){
		ContextAttribs attribs = new ContextAttribs(3,3).withForwardCompatible(true).withProfileCore(true);
		try {
			Display.setDisplayMode(new DisplayMode(1280, 720));
			Display.create(new PixelFormat(), attribs);
			//Display.setFullscreen(true);
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		GL11.glViewport(0, 0, Display.getWidth(), Display.getHeight());
		aspectRatio = (float)Display.getWidth() / (float)Display.getHeight();
	}
	
	public static void updateDisplay(){
		Display.update();                  
		//GL11.glViewport(0, 0, Display.getWidth(), Display.getHeight());
		//aspectRatio = (float)Display.getWidth() / (float)Display.getHeight();
	}
	
	public static void closeDisplay(){
		Display.destroy();
	}
	
}
