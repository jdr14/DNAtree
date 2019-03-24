
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
	 * @param d
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
	 * @param seq
	 */
	public void setSequence(String seq)
	{
		sequence = seq;
	}
	
	/**
	 * @param newSeq as sequence to insert
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
	 */
	public Node remove(String oldSeq)
	{
		return new FlyWeightNode(this.depth);
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
