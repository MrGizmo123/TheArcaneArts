package Game.Spell;

import java.util.ArrayList;
import java.util.List;

public abstract class SpellNode {

	private SpellNodeType type;
	
	private List<Object> inputs;
	private List<Object> outputs;
	
	private List<PortAddress[]> outputAddresses;
	
	private Spell parent;
	
	public SpellNode(SpellNodeType type, List<PortAddress[]> outputAddr, Spell parent)
	{
		this.outputAddresses = outputAddr;
		this.parent = parent;
		
		this.type = type;
		
		this.inputs = new ArrayList<Object>();
		this.outputs = new ArrayList<Object>();
		
		initializeArrays();
	}
	
	public void setInput(int inputIndex, Object data)
	{
		inputs.set(inputIndex, data);
		
		if(isReady())
		{
			process();
			passOutputDataToNextNodes();
		}
	}
	
	protected void process()
	{
		
	}
	
	private void passOutputDataToNextNodes()
	{
		for(int i=0;i<outputAddresses.size();i++)
		{
			PortAddress[] addrs = outputAddresses.get(i);
			for(int j=0;j<addrs.length;j++)
			{
				parent.setInputValue(addrs[i].getNode(), addrs[i].getPort(), outputs.get(i));
			}
		}
	}
	
	private boolean isReady()
	{
		boolean ready = true;
		for(int i=0;i<type.getNoOfInputs();i++)
		{
			ready&=(inputs.get(i) != null);
		}
		return ready;
	}
	
	private void initializeArrays()
	{
		for(int i=0;i<type.getNoOfInputs();i++)
		{
			inputs.add(i,null);
		}
		
		for(int i=0;i<type.getNoOfOutputs();i++)
		{
			outputs.add(i,null);
		}
	}
	
}
