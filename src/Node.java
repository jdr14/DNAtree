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
	
	/**
	 * checks to see if node has information
	 * needed for remove case
	 */
	public boolean hasInfo;
	
	/**
	 * check to see if node is internal
	 * needed for remove case
	 */
	public boolean isInternal;
	
	public Node()
	{
		
	}
	
    /**
     * Provides a default constructor that sets everything to null
     */
	public Node(int d)
	{
		depth = d;
		hasInfo = false;
		isInternal = false;
	}
	
	/**
	 * @param newSeq as sequence to insert
	 */
	public Node insert (String newSeq) 
	{
		return null;
	};
	
	/**
	 * @param oldSeq as sequence to remove
	 */
	public Node remove (String oldSeq) 
	{
		return null;
	};
	
	/**
	 * @param newSeq as sequence to search for
	 */
	public void search (String newSeq) {};

}