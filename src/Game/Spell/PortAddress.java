package Game.Spell;

public class PortAddress {

	private int nodeAddress;
	private int portIndex;
	
	public PortAddress(int nodeAddr, int portIndex)
	{
		this.nodeAddress = nodeAddr;
		this.portIndex = portIndex;
	}
	
	public int getNode()
	{
		return nodeAddress;
	}
	
	public int getPort()
	{
		return portIndex;
	}
	
}
