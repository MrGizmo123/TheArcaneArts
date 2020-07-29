package Gui.TextRendering;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;

import Game.Game;
import Game.tools.GameResourcesAndSettings;
import Game.tools.Maths;

public class TextMeshData {
	
	private Vector2f cursor;
	private Font font;
	private float size;
	
	private boolean isCentered;
	
	private List<Float> vertexPositions;
	private List<Float> textureCoords;
	private List<Character> charIds;
	
	private int vaoID;
	private int vertexCount;
	
	private int vertexVbo;
	private int texCoordVbo;
	
	public TextMeshData(String text, float size, Font f, boolean centered)
	{
		this.isCentered = centered;
		this.size = size;
		this.font = f;
		this.cursor = new Vector2f(0, Display.getHeight() / size);
		
		//cursor.y+=(GameResourcesAndSettings.LINE_HEIGHT * size * Display.getHeight());
		
		vertexPositions = new ArrayList<Float>();
		textureCoords = new ArrayList<Float>();
		
		charIds = toCharList(text.toCharArray());
		
		generateQuads(text);
		
		float[] vertices = toFloatArray(vertexPositions);
		float[] texCoords = toFloatArray(textureCoords);
		
		vertexVbo = Game.loader.createEmptyVbo(vertices.length);
		texCoordVbo = Game.loader.createEmptyVbo(texCoords.length);
		
		Game.loader.updateVbof(vertexVbo, vertices);
		Game.loader.updateVbof(texCoordVbo, texCoords);
		
		vaoID = Game.loader.createVAO();
		Game.loader.addAttrib(vaoID, vertexVbo, 0, 3);
		Game.loader.addAttrib(vaoID, texCoordVbo, 1, 2);
		
	}
	
	public void newline()
	{
		
	}
	
	public void append(String extra)
	{
		char[] chars = extra.toCharArray();
		for(char c : chars)
		{
			charIds.add(c);
		}
		
		generateQuads(extra);
		
		updateVbos();
	}
	
	public void append(char extra)
	{
		charIds.add(extra);
		
		shiftX((float)font.getCharacterXAdvance(extra)/((float)Display.getWidth()) * size);
		cursor.x-=((float)font.getCharacterXAdvance(extra) / 2f);
		generateQuad(extra);
		
		updateVbos();
	}
	
	public void removeChar(int index)
	{
		
		if(charIds.size() == 0)
		{
			return;
		}
		
		for(int i=0;i<18;i++)
		{
			vertexPositions.remove(18 * index);
		}
		
		for(int i=0;i<12;i++)
		{
			textureCoords.remove(12 * index);
		}
		
		vertexCount-=6;
		
		shiftX(-(float)font.getCharacterXAdvance(charIds.get(index))/((float)Display.getWidth()) * size);
		cursor.x-=(font.getCharacterXAdvance(charIds.get(index)) / 2f);
		
		charIds.remove(index);
		
		updateVbos();
	}
	
	public void updateMesh(String text, float size, Font f)
	{
		this.cursor = new Vector2f(50, Display.getHeight() / size);
		
		vertexPositions = new ArrayList<Float>();
		textureCoords = new ArrayList<Float>();
		
		charIds = toCharList(text.toCharArray());
		
		generateQuads(text);
		
		updateVbos();
	
	}
	
	private void generateQuad(char c)
	{
		CharacterData data = font.getCharData(c);
		
		Vector2f bottomLeft = new Vector2f(cursor.x + data.getxOffset(), cursor.y - data.getHeight() - data.getyOffset());
		Vector2f topRight = new Vector2f(cursor.x  + data.getWidth() + data.getxOffset(), cursor.y - data.getyOffset());
		
		bottomLeft.x*=size;
		bottomLeft.y*=size;
		topRight.x*=size;
		topRight.y*=size;
		
		Vector2f p1 = new Vector2f(bottomLeft.x, bottomLeft.y);
		Vector2f p2 = new Vector2f(topRight.x, bottomLeft.y);
		Vector2f p3 = new Vector2f(bottomLeft.x, topRight.y);
		Vector2f p4 = new Vector2f(topRight.x, topRight.y);
		
		Vector2f textureBottomLeft = new Vector2f(data.getX(),data.getY() + data.getHeight());
		Vector2f textureTopRight = new Vector2f(data.getX() + data.getWidth(), data.getY());
		
		Vector2f t1 = new Vector2f(textureBottomLeft.x, textureBottomLeft.y);
		Vector2f t2 = new Vector2f(textureTopRight.x, textureBottomLeft.y);
		Vector2f t3 = new Vector2f(textureBottomLeft.x, textureTopRight.y);
		Vector2f t4 = new Vector2f(textureTopRight.x, textureTopRight.y);
		
		addVertex(p1);
		addVertex(p2);
		addVertex(p4);
		
		addVertex(p4);
		addVertex(p3);
		addVertex(p1);
		
		addTextureCoord(t1);
		addTextureCoord(t2);
		addTextureCoord(t4);
		
		addTextureCoord(t4);
		addTextureCoord(t3);
		addTextureCoord(t1);
		
		cursor.x+=data.getxAdvance();
		vertexCount+=6;
	}
	
	private void generateQuads(String text)
	{
		char[] chars = toCharArray(charIds);
		
		if(isCentered)
		{
			int width = 0;
			for(char c : chars)
			{
				width+=font.getCharacterXAdvance(c);
			}
		
			cursor.x-=((float)width/2f);
		}
		
		for(char c : chars)
		{
			generateQuad(c);
		}
	}
	
	private void updateVbos()
	{
		float[] vertices = toFloatArray(vertexPositions);
		float[] texCoords = toFloatArray(textureCoords);
		
		Game.loader.updateVbof(vertexVbo, vertices);
		Game.loader.updateVbof(texCoordVbo, texCoords);
	}
	
	public int getVertexCount()
	{
		return vertexCount;
	}
	
	public Font getFont()
	{
		return font;
	}
	
	public int getVaoId()
	{
		return vaoID;
	}
	
	private float[] toFloatArray(List<Float> list)
	{
		float[] result = new float[list.size()];
		
		for(int i=0;i<list.size();i++)
		{
			result[i] = list.get(i);
		}
		
		return result;
	}
	
	private List<Character> toCharList(char[] chars)
	{
		List<Character> result = new ArrayList<Character>();
		
		for(int i=0;i<chars.length;i++)
		{
			result.add(chars[i]);
		}
		
		return result;
	}
	
	private char[] toCharArray(List<Character> chars)
	{
		char[] result = new char[chars.size()];
		
		for(int i=0;i<chars.size();i++)
		{
			result[i] = chars.get(i);
		}
		
		return result;
	}
	
	private void shiftX(float shift)
	{
		try {
			for(int i=0;i<(charIds.size() * 6);i++)
			{
				float value = vertexPositions.get(i * 3);
				vertexPositions.set(i * 3, value - shift);
			}
		}
		catch (IndexOutOfBoundsException e)
		{
			return;
		}
	}
	
	private void addVertex(Vector2f vertex)
	{
		Vector2f ndc = Maths.viewportToNDC(vertex);
		
		vertexPositions.add(ndc.x);
		vertexPositions.add(ndc.y);
		vertexPositions.add(0f);
	}
	
	private void addTextureCoord(Vector2f coord)
	{
		textureCoords.add(coord.x / font.getTextureWidth());
		textureCoords.add(coord.y / font.getTextureWidth());
	}
	
}