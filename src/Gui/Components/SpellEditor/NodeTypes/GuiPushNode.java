package Gui.Components.SpellEditor.NodeTypes;

import Game.Spell.SpellNodeType;
import Gui.GuiLayout;
import Gui.Components.SpellEditor.GuiSpellNode;

public class GuiPushNode extends GuiSpellNode{

	public GuiPushNode(int pointer, GuiLayout parentLayout) {
		super(SpellNodeType.PUSH, pointer, parentLayout);
	}

}
