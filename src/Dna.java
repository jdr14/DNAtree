import java.io.*;
import java.util.*;

/**
 * 
 * @author Jovany Cabrera jovanyc4
 * @author Joey Rodgers
 * @version 2
 *
 */

public class Dna
{	
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
		root = new FlyWeightNode();
	}
	
	/**
	 * 
	 * @param newSeq is sequence being inserted
	 */
	public void insert(String newSeq)
	{
		// ADD BOOL
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setErr(new PrintStream(outContent));
		
		// case where tree is empty
		if (count == 0)
		{
			root = root.insert(newSeq);
		}
		// case where root node is a Leaf
		else if (count == 1)
		{
			LeafNode oldRoot =  (LeafNode) root;
			root = new InternalNode();
			root = root.insert(newSeq);
			root = root.insert(oldRoot.getSequence());
		}
		// all other cases
		else
		{
			root = root.insert(newSeq);
		}
		String compareThis = outContent.toString();
		if (!compareThis.equalsIgnoreCase("Error: "
				+ "Cannot insert duplicate Sequence.\n"))
		{
			System.out.print("Sequence " + newSeq + 
					" successfully inserted at depth: " + 
					compareThis.substring(0 , 2));
			count++;
		}
		else
		{
			System.out.println("Error: Cannot insert duplicate Sequence.");
		}
	}
	
	/**
	 * 
	 * @param oldSeq
	 */
	public void remove(String oldSeq)
	{
		if (this.count > 0)
		{
			ByteArrayOutputStream outContent = new ByteArrayOutputStream();
			System.setErr(new PrintStream(outContent));
			root = root.remove(oldSeq);
			String compareThis = outContent.toString();
			if (compareThis.equalsIgnoreCase("Error: "
					+ "Sequence not found and not removed.\n"))
			{
				System.out.println("Error: "
						+ "Sequence not found and not removed.");
			}
			else
			{
				count--;
			}
		}
		else
		{
			System.out.println("Error: Cannot call remove on an empty tree");
		}
	}
	
    /**
     * 
     * @param currSeq
     * @return
     */
	public void search (String currSeq)
	{
		if (this.count > 0)
		{
			root.search(currSeq);
		}
		else
		{
			System.out.println("# of nodes visited: 0");
			System.out.println("no sequence found");
		}
	}
	
	/**
	 * Returns all the Nodes beginning with the prefix specified along with
	 * the number of nodes visited
	 * @param sequence as a prefix string
	 * @return Pair of number of nodes visited and a list of sequences.
	 */
	public Pair<Integer, List<String>> searchByPrefix(String sequence)
	{
		List<String> retList = new ArrayList<String>();
		
		// Case: Tree contains no nodes
		if (count == 0)
		{
			return new Pair<Integer, List<String>>(count, retList);
		}
		
		// Case: Tree contains 1 node
		else if (count == 1)
		{
			LeafNode ln = (LeafNode) root;
			if (sequence.length() >= 1 && 
					sequence.charAt(0) == ln.getSequence().charAt(0))
			{
				retList.add(ln.getSequence());
				return new Pair<Integer, List<String>>(count, retList);
			}
			return new Pair<Integer, List<String>>(count, retList);
		}
		
		// Case: Tree contains more than one node 
		// This means we know that root node is an internal
		Node tempRoot = root;
        InternalNode in;
        int nodesVisited = 0;
		
		for (int i = 0; i < sequence.length(); i++)
		{
			Character c = sequence.charAt(i);
			
		    if (c.equals('A'))
		    {
		    	in = (InternalNode) tempRoot;
		    	tempRoot = in.getAChild();
		    	nodesVisited += 1;
		    }
		    else if (c.equals('C'))
		    {
		    	in = (InternalNode) tempRoot;
		    	tempRoot = in.getCChild();
		    	nodesVisited += 1;
		    }
		    else if (c.equals('G'))
		    {
		    	in = (InternalNode) tempRoot;
		    	tempRoot = in.getGChild();
		    	nodesVisited += 1;
		    }
		    else if (c.equals('T'))
		    {
		    	in = (InternalNode) tempRoot;
		    	tempRoot = in.getTChild();
		    	nodesVisited += 1;
		    }
		    
		    // If a Leaf node has been reached before end of the sequence
		    if (!tempRoot.isInternal && tempRoot.hasInfo)
		    {
		    	LeafNode ln = (LeafNode) tempRoot;
		    	if (ln.getSequence().startsWith(sequence))
		    	{
		    		retList.add(ln.getSequence());
		    		return new Pair<Integer, List<String>>(nodesVisited, 
		    				retList);
		    	}
		    	else
		    	{
		    		return new Pair<Integer, List<String>>(nodesVisited, 
		    				retList);
		    	}
		    }
		    // If a FlyWeight node has been reached before end of sequence
		    else if (!tempRoot.isInternal && !tempRoot.hasInfo)
		    {
		    	return new Pair<Integer, List<String>>(nodesVisited, retList);
		    }
		}  // End for loop
		
		List<Pair<Integer, String>> preorderList = new 
				ArrayList<Pair<Integer, String>>();
		
		// Add nodes contained in subtree in a preorder fashion
		preorderHelp(tempRoot, preorderList);
		
		// Update the nodeVisited count
		nodesVisited += preorderList.size();
		
		// Build the final returnList
		for (int i = 0; i < preorderList.size(); i++)
		{
			String strVal = preorderList.get(i).getValue();
			if (!strVal.equals("I") && !strVal.equals("E"))
			{
				retList.add(strVal);
			}
		}
		
		// Finally return...
		return new Pair<Integer, List<String>>(nodesVisited, retList);
	}

	/**
	 * Return a list of all the DNATypes in a preorder fashion
	 * @return preorderList (tree nodes)
	 */
	private List<Pair<Integer, String>> preorder()
	{
		List<Pair<Integer, String>> preorderList = new 
				ArrayList<Pair<Integer, String>>();
		preorderHelp(root, preorderList);
		return preorderList;
	}
	
	/**
	 * Return a list of the DNATypes in the DNATree in a preorder order.
	 * preorder = root, childA, childC, childG, childT, child$
	 * @param rt
	 * @param retList
	 * @return
	 */
	private void preorderHelp(Node rt, 
			List<Pair<Integer, String>> retList)
	{
		// If node is a Leaf 
		if (rt.hasInfo)
		{
			LeafNode n = (LeafNode) rt;
			retList.add(new Pair<Integer, String>(rt.depth, n.getSequence()));
			return;
		}
		
		// If node is a FlyWeight
		if (!rt.hasInfo && !rt.isInternal)
		{
			retList.add(new Pair<Integer, String>(rt.depth, "E"));
			return;
		}
		
		// Node is internal
		InternalNode in = (InternalNode) rt; 
		retList.add(new Pair<Integer, String>(rt.depth, "I"));
		
		preorderHelp(in.getAChild(), retList);
		preorderHelp(in.getCChild(), retList);
		preorderHelp(in.getGChild(), retList);
		preorderHelp(in.getTChild(), retList);
		preorderHelp(in.get$Child(), retList);
	}
	
	/**
	 * Method print called with no arguments which will then print out
	 * the tree in a preorder order
	 */
	public void print(PrintOptions option)
	{
		List<Pair<Integer, String>> nodeInfo = preorder();
		
		for (int i = 0; i < nodeInfo.size(); i++)
		{
			// Temp variables for node sequence and corresponding depth
			Integer depth = nodeInfo.get(i).getKey();
			String nodeSeq = nodeInfo.get(i).getValue();
			
			// Create spacing based on the depth of the node
			String spaces = new String(new char[depth]).replace("\0", "  ");
			String out = "";
			
			out = spaces + nodeSeq;
			
			// If the node sequence is an internal or empty node
			if (nodeSeq.matches("I") || nodeSeq.matches("E"))
			{
			    System.out.println(out);
			}
			
			else
			{
				// Handle all three print types using switch statement & enum
			    switch (option)
				{
				case DEFAULT:
					break;
				case LENGTHS:
					out += (": length: " + nodeSeq.length());
					break;
				case STATS:
					PercentageType pt = new PercentageType(nodeSeq);
					String percentA = String.format("%.2f", pt.getPercentA());
					String percentC = String.format("%.2f", pt.getPercentC());
					String percentG = String.format("%.2f", pt.getPercentG());
					String percentT = String.format("%.2f", pt.getPercentT());
					out += (": A:" + percentA);
					out += (" C:" + percentC);
					out += (" G:" + percentG);
					out += (" T:" + percentT);
					break;
				}
			    // Finally, print out
				System.out.println(out);
			}
		}
	}  // End print
	

}