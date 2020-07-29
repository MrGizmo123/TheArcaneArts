package Gui.Components.SpellEditor.NodeTypes;

import Game.Spell.SpellNodeType;
import Gui.GuiLayout;
import Gui.Components.SpellEditor.GuiSpellNode;

public class GuiCasterNode extends GuiSpellNode{

	public GuiCasterNode(int pointer, GuiLayout parentLayout) {
		super(SpellNodeType.CASTER, pointer, parentLayout);
	}

}
