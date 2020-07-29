package Game.Spell;

import java.util.ArrayList;
import java.util.List;

import Gui.Components.SpellEditor.GuiSpellNode;
import Gui.Components.SpellEditor.NodeTypes.GuiCasterNode;
import Gui.Components.SpellEditor.NodeTypes.GuiEntityDirectionNode;
import Gui.Components.SpellEditor.NodeTypes.GuiNumberNode;
import Gui.Components.SpellEditor.NodeTypes.GuiPushNode;

public enum SpellNodeType {

	CASTER(GuiCasterNode.class, 0, SpellDataType.ENTITY),
	NUMBER(GuiNumberNode.class, 0, SpellDataType.FLOAT),
	PUSH(GuiPushNode.class, 3, SpellDataType.ENTITY, SpellDataType.FLOAT, SpellDataType.VECTOR),
	LOOKING(GuiEntityDirectionNode.class, 1, SpellDataType.ENTITY, SpellDataType.VECTOR);
	
	private List<SpellDataType> inputs;
	private List<SpellDataType> outputs;
	
	private Class<? extends GuiSpellNode> type;
	
	SpellNodeType(Class<? extends GuiSpellNode> type, int inputSize, SpellDataType ... params)
	{
		this.inputs = new ArrayList<SpellDataType>();
		this.outputs = new ArrayList<SpellDataType>();
		this.type = type;
		
		for(int i=0;i<inputSize;i++)
		{
			inputs.add(params[i]);
		}
		
		for(int i=inputSize;i<params.length;i++)
		{
			outputs.add(params[i]);
		}
		
	}
	
	public Class<? extends GuiSpellNode> getTypeClass()
	{
		return type;
	}
	
	public List<SpellDataType> getInputs()
	{
		return inputs;
	}
	
	public List<SpellDataType> getOutputs()
	{
		return outputs;
	}
	
	public int getNoOfInputs()
	{
		return inputs.size();
	}
	
	public int getNoOfOutputs()
	{
		return outputs.size();
	}
	
	public SpellDataType getInputDataType(int port)
	{
		return inputs.get(port);
	}
	
	public SpellDataType getOutputDataType(int port)
	{
		return outputs.get(port);
	}
}
