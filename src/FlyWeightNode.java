
public class FlyWeightNode extends Node
{

	public FlyWeightNode()
	{
		super();
		hasInfo = false;
		isInternal = false;
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
		System.err.println("Error: Sequence not found and not removed.");
		return this;
	}
	
	/**
	 * @param newSeq as sequence to search for
	 */
	public void search (String newSeq)
	{
		System.out.println("# of nodes visited: " + (this.depth + 1));
		System.out.println("no sequence found");
	}
}
