
/**
 * FlyWeight (empty) node class to describe functionality of
 * FlyWeight nodes
 * @author Joey Rodgers jdr14
 * @author Jovany Cabrera jovanyc4
 */
public class FlyWeightNode extends Node
{
    /**
     * Default constructor
     */
    public FlyWeightNode()
	{
		super();
		hasInfo = false;
		isInternal = false;
	}
	
	/**
	 * Constructor with depth param
	 * @param d is the depth passed in to set depth var. from parent
	 */
	public FlyWeightNode(int d) 
	{
		super(d);
		hasInfo = false;
	}
	
	/**
	 * @param newSeq as sequence to insert
	 * @return Node (return the new Child (ref to root) as Node
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
