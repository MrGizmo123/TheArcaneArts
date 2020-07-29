package Gui.Components;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;

import Game.tools.GameResourcesAndSettings;
import Game.tools.Input;
import Game.tools.utils.AABB;
import Gui.Gui;
import Gui.GuiLayout;
import Gui.TextRendering.Text;
import Gui.TextRendering.TextMeshData;

public class GuiButton extends Gui{

	private AABB boundingBox;
	private Text text;
	private ClickListener listener;
	
	private int normal_texture;
	private int hover_texture;
	
	private Vector2f textOffset;
	
	public GuiButton(String t, float scale, GuiLayout parentLayout) 
	{
		super(GameResourcesAndSettings.GUI_GREY, parentLayout);
		this.boundingBox = calculateAABB();
		this.listener = new ClickListener() {
			@Override
			public void clicked() {}
		};
		
		normal_texture = GameResourcesAndSettings.GUI_GREY;
		hover_texture = GameResourcesAndSettings.GUI_LIGHT_GREY;
		
		textOffset = new Vector2f(0,0);
		
		this.text = new Text(t, scale,GameResourcesAndSettings.GAME_FONT, new Vector2f(), true);
		positionText();
		
		super.parentLayout.addText(text);
	}
	
	public GuiButton(String t, float scale, int texture, Vector2f textOffset, GuiLayout parentLayout) 
	{
		super(GameResourcesAndSettings.GUI_GREY, parentLayout);
		this.boundingBox = calculateAABB();
		this.textOffset = textOffset;
		
		this.listener = new ClickListener() {
			@Override
			public void clicked() {}
		};
		
		normal_texture = texture;
		hover_texture = GameResourcesAndSettings.GUI_LIGHT_GREY;
		
		textOffset = new Vector2f(0,0);
		
		this.text = new Text(t, scale,GameResourcesAndSettings.GAME_FONT, new Vector2f(), true);
		positionText();
		
		super.parentLayout.addText(text);
	}
	
	public GuiButton(String t, float scale, int texture, GuiLayout parentLayout) 
	{
		super(texture, parentLayout);
		this.boundingBox = calculateAABB();
		this.listener = new ClickListener() {
			@Override
			public void clicked() {}
		};
		
		normal_texture = texture;
		hover_texture = GameResourcesAndSettings.GUI_LIGHT_GREY;
		
		textOffset = new Vector2f(0,0);
		
		this.text = new Text(t, scale,GameResourcesAndSettings.GAME_FONT, new Vector2f(), true);
		positionText();
		
		super.parentLayout.addText(text);
	}
	
	public GuiButton(int texture, GuiLayout parentLayout)
	{
		super(texture, parentLayout);
		this.boundingBox = calculateAABB();
		
		this.listener = new ClickListener() {
			@Override
			public void clicked() {}
		};
		
		normal_texture = texture;
		hover_texture = GameResourcesAndSettings.GUI_LIGHT_GREY;
		
		textOffset = new Vector2f(0,0);
		
		this.text = new Text("", 1, GameResourcesAndSettings.GAME_FONT, new Vector2f(), false);
	}
	
	public GuiButton(int texture, GuiLayout parentLayout, Vector2f textOffset)
	{
		super(texture, parentLayout);
		this.boundingBox = calculateAABB();
		this.textOffset = textOffset;
		
		this.listener = new ClickListener() {
			@Override
			public void clicked() {}
		};
		
		normal_texture = texture;
		hover_texture = GameResourcesAndSettings.GUI_LIGHT_GREY;
		
		textOffset = new Vector2f(0,0);
		
		this.text = new Text("", 1, GameResourcesAndSettings.GAME_FONT, new Vector2f(), false);
	}
	
	@Override
	protected void constraintsUpdated() 
	{
		positionText();
		recalculateAABB();
	}
	
	@Override
	public void update()
	{
		super.texture = normal_texture;
		
		Vector2f mousePos = new Vector2f(Mouse.getX(),Mouse.getY());
		if(boundingBox.isIntersecting(mousePos))
		{
			super.texture = hover_texture;
			if (Input.isMouseButtonPressed(0)) {
				listener.clicked();
			}
		}
	}
	
	private void positionText()
	{
		Vector2f viewport = super.getPositionInViewPort();
		Vector2f translatedPos = Vector2f.add(viewport, textOffset, null);
		
		text.changePos(new Vector2f(translatedPos.x, translatedPos.y + ((float)GameResourcesAndSettings.GAME_FONT.getMaxHeight() * text.getSize() * Display.getHeight() / Display.getWidth())));
	}
	
	private void recalculateAABB()
	{
		boundingBox = calculateAABB();
	}
	
	private AABB calculateAABB() 
	{
		Vector2f scale = super.getScale();
		Vector2f pos = super.getPositionInViewPort();
		
		int widthInPixels = (int) (scale.x * Display.getWidth());
		int heightInPixels = (int) (scale.y * Display.getHeight());
		
		int x1 = (int) (pos.x - ((float)widthInPixels / (float)2));
		int y1 = (int) (pos.y - ((float)heightInPixels / (float)2));
		
		int x2 = (int) (pos.x + ((float)widthInPixels / (float)2));
		int y2 = (int) (pos.y + ((float)heightInPixels / (float)2));
		
		return new AABB(x1, y1, x2, y2);
		
	}
	
	public void setClickListener(ClickListener l)
	{
		this.listener = l;
	}
	
	@Override
	public void show()
	{
		this.parentLayout.addGui(this);
		this.parentLayout.addText(text);
	}
	
	@Override
	public void hide()
	{
		this.parentLayout.removeGui(this);
		this.parentLayout.removeText(text);
	}
	
}
