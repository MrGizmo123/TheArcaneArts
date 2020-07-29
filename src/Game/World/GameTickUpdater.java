package Game.World;

public class GameTickUpdater {

	private static final long ONE_GAME_TICK = 50;
	
	private static long currentTime;
	private static long previousTime;
	private static long elapsedTime;
	
	private static int noOfTicksSinceStart;
	
	public static void init()
	{
		currentTime = System.currentTimeMillis();
		previousTime = 0;
		elapsedTime = 0;
		update();
	}
	
	public static void update()
	{
		previousTime = currentTime;
		currentTime = System.currentTimeMillis();
		
		elapsedTime = currentTime - previousTime;
		
		noOfTicksSinceStart = (int) (currentTime / ONE_GAME_TICK);
		
		printFPS();
	}
	
	private static void printFPS()
	{	
		if(currentTime % 2000 < 1)
		{
			if(elapsedTime == 0)
			{
				System.out.println("FPS: Infinity");
				return;
			}
			System.out.println("FPS: " + (1000 / elapsedTime));
		}
	}
	
	public static int getNoOfTicks()
	{
		return noOfTicksSinceStart;
	}
	
	public static float getFrameTime()
	{
		return (float)elapsedTime / 1000f;
	}
	
}
