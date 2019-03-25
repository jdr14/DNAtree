import java.io.*;

/**
 * 
 * @author Jovany Cabrera
 * @author Joey Rodgers
 * @version 2.0
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
    
    /**
     * Default node constructor
     */
    public Node()
    {
        // Default constructor shouldn't set anything
    }
    
    /**
     * Provides a default constructor that sets everything to null
     * @param d depth as integer
     */
    public Node(int d)
    {
        depth = d;
        hasInfo = false;
        isInternal = false;
    }
    
    /**
     * @param newSeq as sequence to insert
     * @return Node null
     */
    public Node insert(String newSeq) 
    {
        return null;
    };
    
    /**
     * @param oldSeq as sequence to remove
     * @return Node null
     */
    public Node remove(String oldSeq) 
    {
        return null;
    };
    
    /**
     * @param newSeq as sequence to search for
     */
    public void search(String newSeq) 
    {
        // Method for the subclasses
    };
}