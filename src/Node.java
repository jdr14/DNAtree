import java.io.*;

/**
 * 
 * @author Jovany Cabrera
 * @author Joey Rodgers
 * @param <V> is the value of the node
 * @verion 2
 *
 */
public class Node 
{
	
	/**
	 * Depth of node
	 */
	public int depth;
	
	public boolean isFlyWeight;
	
	public boolean isLeaf;
	
	public boolean isInternal;
	
    /**
     * Provides a default constructor that sets everything to null
     */
	public Node(int d, Types t)
	{
		depth = d;
		switch(t)
		{
		case INTERNAL:
			isInternal = true;
			isLeaf = false;
			isFlyWeight = false;
			break;
		case FLYWEIGHT:
			isFlyWeight = true;
			isLeaf = false;
			isFlyWeight = false;
			break;
		case LEAF:
			isLeaf = true;
			isFlyWeight = false;
			isFlyWeight = false;
			break;
		}
	}
	
	public void setLeaf()
	{
		this.isLeaf = true;
		this.isFlyWeight = false;
		this.isInternal = false;
	}

}