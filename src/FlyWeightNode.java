
public class FlyWeightNode extends Node
{

	public FlyWeightNode()
	{
		super();
		hasInfo = false;
	}
	
	public FlyWeightNode(int d) 
	{
		super(d);
		hasInfo = false;
	}
	
	/**
	 * @param newSeq as sequence to insert
	 */
	public Node insert (String newSeq)
	{
		LeafNode newChild = new LeafNode(this.depth);
		newChild.insert(newSeq);
		return newChild;
	}
	
	/**
	 * @param oldSeq as sequence to remove
	 */
	public Node remove (String oldSeq)
	{
		return this;
	}
	
	/**
	 * @param newSeq as sequence to search for
	 */
	public void search (String newSeq)
	{
		
	}

}
