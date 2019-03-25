
/**
 * Leaf node to contain the DNAsequence information
 * @author Joey Rodgers jdr14
 * @author Jovany Cabrera jovanyc4
 * @version 3.2.1
 *
 */
public class LeafNode extends Node
{
    /**
     * To store the sequence of the node
     */
    private String sequence;
	
	/**
	 * Constructor that will set the depth
	 * @param d depth as integer
	 */
	public LeafNode(int d)
	{
		super(d);
		sequence = "";
		hasInfo = true;
	}
	
	/**
	 * 
	 * @return a string that is the sequence
	 */
	public String getSequence()
	{
		return sequence;
	}
	
	/**
	 * 
	 * @param seq sequence as a string
	 */
	public void setSequence(String seq)
	{
		sequence = seq;
	}
	
	/**
	 * @param newSeq as sequence to insert
	 * @return Node as a reference
	 */
	public Node insert(String newSeq)
	{
		// check to see if duplicate is being inserted
		if (!this.sequence.equalsIgnoreCase(newSeq))
		{    // if new leaf node (no previous sequence)
			if (this.sequence.isEmpty())
			{
				this.sequence = newSeq;
				System.err.println(this.depth);
				return this;
			}
			else 
			{    // previous node had a sequence
				InternalNode newNode = new InternalNode(depth);
				newNode.insert(this.sequence);
				newNode.insert(newSeq);
				return newNode;
				
			}
		}
		else
		{    // if duplicate, return original
			// MUST THROW ERROR HERE
			System.err.println("Error: Cannot insert duplicate Sequence.");
			return this;
		}
	}
	
	/**
	 * @param oldSeq as sequence to remove
	 * @return Node as a reference
	 */
	public Node remove(String oldSeq)
	{
		if ((this.depth == 0))
		{  // case of root
			if (this.sequence.equalsIgnoreCase(oldSeq))
			{
				return new FlyWeightNode(this.depth);
			}
			else
			{
				System.err.println("Error: Sequence not found and not removed.");
				return this;
			}
		}
		else
		{
			if (this.sequence.equalsIgnoreCase(oldSeq))
			{
				return new FlyWeightNode(this.depth);
			}
			else
			{
				System.err.println("Error: Sequence not found and not removed.");
				return this;
			}
			
		}
	}
	
	/**
	 * @param newSeq as sequence to search for
	 */
	public void search(String newSeq)
	{
		System.out.println("# of nodes visited: " + (this.depth + 1));
		System.out.println("sequence: " + this.sequence);
	}
}
