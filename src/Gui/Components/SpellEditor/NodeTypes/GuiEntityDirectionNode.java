package Gui.Components.SpellEditor.NodeTypes;

import Game.Spell.SpellNodeType;
import Gui.GuiLayout;
import Gui.Components.SpellEditor.GuiSpellNode;

public class GuiEntityDirectionNode extends GuiSpellNode{

	public GuiEntityDirectionNode(int pointer, GuiLayout parentLayout) {
		super(SpellNodeType.LOOKING, pointer, parentLayout);
	}

}
