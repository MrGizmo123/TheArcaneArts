package Game.Spell;

import java.util.ArrayList;
import java.util.List;

import Gui.Components.SpellEditor.GuiSpellConnector;

public class Spell {
	
	private List<SpellNode> nodes;
	
	public Spell()
	{
		nodes = new ArrayList<SpellNode>();
	}
	
	public void addNode(int id, SpellNode node)
	{
		this.nodes.add(id, node);
	}
	
	public void setInputValue(int nodeId, int portIndex, Object data)
	{
		this.nodes.get(nodeId).setInput(portIndex, data);
	}

}
