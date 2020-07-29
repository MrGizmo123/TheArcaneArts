package Gui.Components.SpellEditor;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector2f;

import Game.Spell.SpellNodeType;
import Game.tools.Input;
import Gui.GuiLayout;
import Gui.Components.SpellEditor.NodeTypes.GuiPushNode;
import Gui.Layouts.SpellProgrammingMenu;

public class GuiSpellGrid {

	private static Map<Integer, GuiSpellNode> nodes;
	int currentPtr;
	
	private GuiLayout parent;
	
	public GuiSpellGrid(GuiLayout l)
	{
		nodes = new HashMap<Integer, GuiSpellNode>();
		currentPtr = 0;
		this.parent = l;
	}
	
	public void update()
	{
		if(Input.isKeyPressed('a'))
		{
			if(parent instanceof SpellProgrammingMenu)
			{
				SpellProgrammingMenu menu = (SpellProgrammingMenu) parent;
				
				menu.showSelector(new Vector2f(Mouse.getX(),Mouse.getY()));
			}
		}
	}
	
	public void addNode(SpellNodeType type)
	{
		Class<? extends GuiSpellNode> typeClass = type.getTypeClass();
		Constructor<?> c = typeClass.getConstructors()[0];
		
		
		
		GuiSpellNode node = null;
		try {
			node = (GuiSpellNode) c.newInstance(currentPtr, parent);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		}
		this.nodes.put(currentPtr, node);
		currentPtr++;
	}
	
	public GuiSpellNode getNodeFromPtr(int ptr)
	{
		return nodes.get(ptr);
	}
	
}
