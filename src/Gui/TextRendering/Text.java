package Gui.TextRendering;

import org.lwjgl.util.vector.Vector2f;

import Game.tools.Maths;

public class Text {

	private StringBuilder text;
	
	private TextMeshData mesh;
	private Font font;
	private float size;
	
	private Vector2f pos;
	
	public Text(String text, float size, Font font, Vector2f pos, boolean centered)
	{
		this.pos = pos;
		
		this.text = new StringBuilder(text);
		this.size = size;
		this.font = font;
		
		this.mesh = new TextMeshData(text, size, font, centered);
	}
	
	public void newline()
	{
		
	}
	
	public void changePos(Vector2f newPos)
	{
		this.pos = newPos;
	}
	
	public Vector2f getPosNDC()
	{
		return Maths.viewportToNDC(pos);
	}
	
	public void append(String extra)
	{
		text.append(extra);
		mesh.append(extra);
	}
	
	public void append(char extra)
	{
		text.append(extra);
		mesh.append(extra);
	}
	
	public void removeChar(int index)
	{
		text.deleteCharAt(index);
		mesh.removeChar(index);
	}
	
	public void updateText(String text, float size, Font f)
	{
		this.text = new StringBuilder(text);
		this.size = size;
		this.font = f;
		
		mesh.updateMesh(text, size, f);
	}
	
	public TextMeshData getMesh()
	{
		return mesh;
	}
	
	public Font getFont()
	{
		return font;
	}
	
	public float getSize()
	{
		return size;
	}
	
	public int getLength()
	{
		return text.length();
	}
	
}
