package Game.tools;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class Input {
	
	private static boolean[] keysPressed;
	private static boolean[] keysReleased;
	
	private static boolean[] mousePressed;
	private static boolean[] mouseReleased;
	
	public static void init()
	{
		keysPressed = new boolean[256];
		keysReleased = new boolean[256];
		mousePressed = new boolean[3];
		mouseReleased = new boolean[3];
		
		clearArrays();
	}
	
	public static void update() 
	{
		clearArrays();
		
		while(Keyboard.next())
		{
			char key = Keyboard.getEventCharacter();
			
			if(Keyboard.getEventKeyState())
			{
				keysPressed[key] = true;
			}
			else
			{
				keysReleased[key] = true;
			}
		}
		
		while(Mouse.next())
		{
			try {
				if(Mouse.getEventButtonState())
				{
					mousePressed[Mouse.getEventButton()] = true;
				}
				else
				{
					mouseReleased[Mouse.getEventButton()] = true;
				}
			}
			catch (ArrayIndexOutOfBoundsException e)
			{
				continue;
			}
		}
	}
	
	public static int[] getKeysPressed()
	{
		List<Integer> resultList = new ArrayList<Integer>();
		
		for(int i=0;i<keysPressed.length;i++)
		{
			if(keysPressed[i])
			{
				resultList.add(i);
			}
		}
		
		return toArray(resultList);
	}
	
	public static boolean isMouseButtonPressed(int button)
	{
		return mousePressed[button];
	}
	
	public static boolean isMouseButtonReleased(int button)
	{
		return mouseReleased[button];
	}
	
	public static boolean isKeyPressed(char key)
	{
		return keysPressed[key];
	}
	
	public static boolean isKeyReleased(char key)
	{
		return keysReleased[key];
	}
	
	private static void clearArrays()
	{
		for(int i=0;i<256;i++)
		{
			keysPressed[i] = false;
			keysReleased[i] = false;
		}
		
		for(int i=0;i<3;i++)
		{
			mousePressed[i] = false;
			mouseReleased[i] = false;
		}
		
	}
	
	private static int[] toArray(List<Integer> list)
	{
		int[] result = new int[list.size()];
		
		for(int i=0;i<result.length;i++)
		{
			result[i] = list.get(i);
		}
		
		return result;
	}
	
}
