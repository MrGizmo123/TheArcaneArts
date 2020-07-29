package Gui.Components.SpellEditor.NodeTypes;

import Game.Spell.SpellNodeType;
import Gui.GuiLayout;
import Gui.Components.GuiTextField;
import Gui.Components.SpellEditor.GuiSpellNode;
import Gui.Constraints.AbsolutePositionConstraint;
import Gui.Constraints.AspectConstraint;
import Gui.Constraints.ScaleConstraint;

public class GuiNumberNode extends GuiSpellNode{

	private GuiTextField number;
	
	public GuiNumberNode(int pointer, GuiLayout parentLayout) {
		super(SpellNodeType.NUMBER, pointer, parentLayout);
		
		this.number = new GuiTextField(0.3f, true, parentLayout);
		
		this.number.addXPosConstraint(new AbsolutePositionConstraint((int) super.getPositionInViewPort().x));
		this.number.addYPosConstraint(new AbsolutePositionConstraint((int) super.getPositionInViewPort().y));
		
		this.number.addScaleConstraint(new ScaleConstraint(0.05f, ScaleConstraint.WIDTH));
		this.number.addAspectConstraint(new AspectConstraint(2.5f));
		
		number.show();
	}

}
