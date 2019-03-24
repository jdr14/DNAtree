
public class FlyWeightNode extends Node
{

	public FlyWeightNode()
	{
		super();
	}
	
	public FlyWeightNode(int d) 
	{
		super(d);
	}
	
	/**
	 * @param newSeq as sequence to insert
	 */
	public Node insert (String newSeq)
	{
		LeafNode newChild = new LeafNode(this.depth);
		newChild.insert(newSeq);
		if (depth != 0)
		{
//			newChild.depth++;
		}
		return newChild;
	}
	
	/**
	 * @param oldSeq as sequence to remove
	 */
	public void remove (String oldSeq)
	{
		
	}
	
	/**
	 * @param newSeq as sequence to search for
	 */
	public void search (String newSeq)
	{
		
	}

}
