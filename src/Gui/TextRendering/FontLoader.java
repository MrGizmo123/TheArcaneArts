package Gui.TextRendering;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import Game.Game;

public class FontLoader {

	public static Font loadFont(String fntFile, String texFile)
	{
		Map<Character, CharacterData> chars = new HashMap<Character, CharacterData>();
		
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(new File(fntFile)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		
		StringBuilder string = new StringBuilder();
		String line = "";
		
		try {
			
			while((line = reader.readLine()) != null)
			{
				String[] parts = line.split(" ");
				if(parts[0].equals("char"))
				{
					String charData = line.substring(5);
					String[] fields = charData.split("=");
					
					int id = Integer.parseInt(fields[1].split(" ")[0]);
					int x = Integer.parseInt(fields[2].split(" ")[0]);
					int y = Integer.parseInt(fields[3].split(" ")[0]);
					int width = Integer.parseInt(fields[4].split(" ")[0]);
					int height = Integer.parseInt(fields[5].split(" ")[0]);
					int xOffset = Integer.parseInt(fields[6].split(" ")[0]);
					int yOffset = Integer.parseInt(fields[7].split(" ")[0]);
					int xAdvance = Integer.parseInt(fields[8].split(" ")[0]);
					
					chars.put((char)id, new CharacterData(id, x, y, width, height, xOffset, yOffset, xAdvance));
					
				}
			}
			
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return new Font(Game.loader.loadTexture(texFile), 512, chars);
	}
	
}
