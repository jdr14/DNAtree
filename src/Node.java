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
	
	public Node()
	{
		
	}
	
    /**
     * Provides a default constructor that sets everything to null
     */
	public Node(int d)
	{
		depth = d;
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
	public void remove (String oldSeq) {};
	
	/**
	 * @param newSeq as sequence to search for
	 */
	public void search (String newSeq) {};

}