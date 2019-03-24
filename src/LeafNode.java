
/**
 * Leaf node to contain the DNAsequence information
 * @author Joey Rodgers jdr14
 * @author Jovany Cabrera jovanyc4
 *
 */
public class LeafNode extends Node
{
	/**
	 * To store the sequence of the node
	 */
    private String sequence;
    
	/**
	 * This will be the percentage (up to 2 decimal places) of the amount of
	 * As in the sequence
	 */
	private Double percentA = 0.0;
	
	/**
	 * This will be the percentage (up to 2 decimal places) of the amount of
	 * Cs in the sequence
	 */
	private Double percentC = 0.0;
	
	/**
	 * This will be the percentage (up to 2 decimal places) of the amount of
	 * Gs in the sequence
	 */
	private Double percentG = 0.0;
	
	/**
	 * This will be the percentage (up to 2 decimal places) of the amount of
	 * Ts in the sequence
	 */
	private Double percentT = 0.0;
	
    /**
     * Default constructor
     */
	public LeafNode() 
	{
		super(0);
		sequence = "";
		hasInfo = true;
	}
	
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
	 * Constructor that will set the sequence with default depth of 0
	 * @param seq
	 */
	public LeafNode(String seq)
	{
		super(0);
		sequence = seq;
		calculatePercentages(seq);
		hasInfo = true;
	}
	
	/**
	 * 
	 * @param d
	 * @param seq
	 */
	public LeafNode(int d, String seq)
	{
		super(d);
		sequence = seq;
		calculatePercentages(seq);
		hasInfo = true;
	}
	
	/**
	 * 
	 * @param n
	 * @param seq
	 */
	public LeafNode(Node n, String seq)
	{
		super(n.depth);
		sequence = seq;
		calculatePercentages(seq);
		hasInfo = true;
	}
    
	/**
	 * 
	 * @return
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
		calculatePercentages(seq);
	}
	
	/**
	 * Accessor method to return the percentage of As with respect to its
	 * containing sequence
	 * @return percentA (Double)
	 */
	public Double getPercentA()
	{
		return percentA;
	}
	
	/**
	 * Accessor method to return the percentage of Cs with respect to its
	 * containing sequence
	 * @return percentC (Double)
	 */
	public Double getPercentC()
	{
		return percentC;
	}
	
	/**
	 * Accessor method to return the percentage of Gs with respect to its
	 * containing sequence
	 * @return percentG (Double)
	 */
	public Double getPercentG()
	{
		return percentG;
	}
	
	/**
	 * Accessor method to return the percentage of Ts with respect to its
	 * containing sequence
	 * @return percentT (Double)
	 */
	public Double getPercentT()
	{
		return percentT;
	}
	
	/**
	 * Private helper method to handle the calculation of percentages of
	 * each character in relation to its containing sequence
	 * @param seq (String)
	 */
	private void calculatePercentages(String seq)
	{
		// Temp variables to keep track of the character count in the sequence
		Double countA = 0.0;
		Double countC = 0.0;
		Double countG = 0.0;
		Double countT = 0.0;
		
		// Iterate through the sequence and obtain counts for each character
		for (int i = 0; i < seq.length(); i++)
		{
			if (seq.charAt(i) == 'A')
			{
				countA++;
			}
			else if (seq.charAt(i) == 'C')
			{
				countC++;
			}
			else if (seq.charAt(i) == 'G')
			{
				countG++;
			}
			else if (seq.charAt(i) == 'T')
			{
				countT++;
			}
		}  // End for
		
		// Calculate the final percentages
		percentA = (countA / seq.length()) * 100.00;
		percentC = (countC / seq.length()) * 100.00;
		percentG = (countG / seq.length()) * 100.00;
		percentT = (countT / seq.length()) * 100.00;
	}
	
	/**
	 * @param newSeq as sequence to insert
	 */
	public Node insert (String newSeq)
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
	public Node remove (String oldSeq)
	{
		return new FlyWeightNode(this.depth);
	}
	
	/**
	 * @param newSeq as sequence to search for
	 */
	public void search (String newSeq)
	{
		System.out.println("# of nodes visited: " + (this.depth+1));
		System.out.println("sequence: " + this.sequence);
	}
}
