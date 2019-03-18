import java.io.*;
import java.util.*;

/**
 * 
 * @author Jovany Cabrera jovanyc4
 * @author Joey Rodgers
 * @version 1
 *
 */

public class Dna{
	
	/**
	 * The root node for the initial DNA tree is
	 * of type empty
	 */
	private Node root;
	
	/**
	 * 
	 * @return the root of the DNA tree
	 */
	public Node getRoot()
	{
		return root;
	}
	
	/**
	 * the node count for the tree
	 */
	private int count = 0;
	
	/**
	 * 
	 * @return the node count of the tree
	 */
	public int getCount()
	{
		return count;
	}
	
	/**
	 * Default Constructor
	 */
	public Dna()
	{
		root = Node();
	}
	
	/**
	 * 
	 * @param node of type DNAType
	 */
	public void insert(Node node)
	{
		if(node.isDNA())
		{
			if(count == 0)    // case where tree is empty
			{
				root = node;
				count++;
			}
			else
			{
				if(root.isDNA())    // case where second node is inserted
				{
					compareSeq(root, node);
				}
			}
		}
	}
	
	/**
	 * 
	 * @param node of type DNAType
	 */
	public void remove(Node node)
	{
		
	}
	
    /**
     * 
     * @param node of type DNAType
     * @return
     */
	public DNAType search (Node node)
	{
		return null;
		
	}
	
	/**
	 * 
	 * @param nodeOne of type DNAType
	 * @param nodeTwo of type DNAType
	 * @return string that contains the comparison results
	 */
	private String compareSeq(Node nodeOne, Node nodeTwo)
	{
		String result = null;
		char[] listOne = nodeOne.getSequence().toCharArray();
		char[] listTwo = nodeTwo.getSequence().toCharArray();
		if(listOne.length >= listTwo.length)
		{
			for(int i = 0; i < listOne.length; i++)
			{
				if (listOne[i] == listTwo[i])
				{
					result+= listOne[i];
				}
				else
				{
					break;
				}
			}
		}
		else if (listOne.length < listTwo.length)
		{
			for(int i = 0; i < listTwo.length; i++)
			{
				if (listTwo[i] == listOne[i])
				{
					result+= listTwo[i];
				}
				else
				{
					break;
				}
			}
		}
		
		return result;
		
	}
}