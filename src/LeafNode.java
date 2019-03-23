
/**
 * Leaf node to contain the DNAsequence information
 * @author Joey Rodgers jdr14
 * @author Jovany Cabrera jovanyc4
 *
 */
public class LeafNode extends Node
{	
    /**
     * Default constructor
     */
	public LeafNode() 
	{
		super(0, "", Types.LEAF);
	}
	
	/**
	 * Constructor that will set the depth
	 * @param d
	 */
	public LeafNode(int d)
	{
		super(d, "", Types.LEAF);
	}
	
	/**
	 * Constructor that will set the sequence with default depth of 0
	 * @param seq
	 */
	public LeafNode(String seq)
	{
		super(0, seq,  Types.LEAF);
	}
	
	/**
	 * 
	 * @param d
	 * @param seq
	 */
	public LeafNode(int d, String seq)
	{
		super(d, seq, Types.LEAF);
	}
	
	/**
	 * 
	 * @param n
	 * @param seq
	 */
	public LeafNode(Node n)
	{
		super(n.depth, n.sequence, Types.LEAF);
	}
   

}
