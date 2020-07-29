package Gui.TextRendering;

import java.util.Map;

public class Font {

	private int glyphTexture;
	private int textureWidth;
	
	private float avgHeight;
	private float avgWidth;
	
	private Map<Character, CharacterData> charData;
	
	public Font(int tex, int textureWidth, Map<Character,CharacterData> data)
	{
		this.glyphTexture = tex;
		this.charData = data;
		this.textureWidth = textureWidth;
		
		avgHeight = 0;
		avgWidth = 0;
		for(CharacterData d : data.values())
		{
			//avgHeight+=((float)d.getHeight()/(float)data.size());
			//avgWidth+=((float)d.getWidth()/(float)data.size());
			
			if(avgHeight < d.getHeight())
			{
				avgHeight = d.getHeight();
			}
			if(avgWidth < d.getHeight())
			{
				avgWidth = d.getHeight();
			}
		}
		
		System.out.println(avgHeight);
	}
	
	public float getMaxHeight()
	{
		return avgHeight;
	}
	
	public float getMaxWidth()
	{
		return avgWidth;
	}
	
	public int getTextureWidth()
	{
		return textureWidth;
	}
	
	public int getGlyphTexture()
	{
		return this.glyphTexture;
	}
	
	public int getCharacterX(char key)
	{
		return this.charData.get(key).getX();
	}
	
	public int getCharacterY(char key)
	{
		return this.charData.get(key).getY();
	}
	
	public int getCharacterWidth(char key)
	{
		return this.charData.get(key).getWidth();
	}
	
	public int getCharacterHeight(char key)
	{
		return this.charData.get(key).getHeight();
	}
	
	public int getCharacterXOffset(char key)
	{
		return this.charData.get(key).getxOffset();
	}
	
	public int getCharacterYOffset(char key)
	{
		return this.charData.get(key).getyOffset();
	}
	
	public int getCharacterXAdvance(char key)
	{
		return this.charData.get(key).getxAdvance();
	}
	
	public CharacterData getCharData(char key)
	{
		return this.charData.get(key);
	}
	
}
