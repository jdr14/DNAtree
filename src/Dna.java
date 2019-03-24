import java.io.*;
import java.util.*;

/**
 * 
 * @author Jovany Cabrera jovanyc4
 * @author Joey Rodgers
 * @version 2
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
		if (!compareThis.equalsIgnoreCase("Error: Cannot insert duplicate Sequence.\n"))
		{
			System.out.print("Sequence " + newSeq + 
					" successfully inserted at depth " + compareThis.substring( 0 , 2 ));
			count++;
		}
		else
		{
			System.out.println("Error: Cannot insert duplicate Sequence.");
		}
	}
	
	/**
	 * 
	 * @param node of type DNAType
	 */
	public void remove(String oldSeq)
	{
		if (this.count > 0)
		{
			ByteArrayOutputStream outContent = new ByteArrayOutputStream();
			System.setErr(new PrintStream(outContent));
			root = root.remove(oldSeq);
			String compareThis = outContent.toString();
			if (compareThis.equalsIgnoreCase("Error: Sequence not found and not removed.\n"))
			{
				System.out.println("Error: Sequence not found and not removed.");
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
     * @param node of type DNAType
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
			System.out.println("Error: Cannot search on an empty tree.");
		}
	}
	
//	private void searchPrint(Pair<Integer, Node> printThis)
//	{
//		System.out.println("# of nodes visited: " + printThis.getKey());
//		if(printThis.getValue().getValue().getSequence() == null)
//		{
//			System.out.println("no sequence found");
//		}
//		else if (printThis.getValue().getValue().getSequence() == "")
//		{
//			System.out.println("no sequence found");
//		}
//		else
//		{
//			System.out.println("sequence: " + printThis.getValue().getValue().getSequence());
//		}
//	}

//	/**
//	 * Returns all the Nodes beginning with the prefix specified along with
//	 * the number of nodes visited
//	 * @param sequence
//	 * @return
//	 */
//public Pair<Integer, List<String>> searchByPrefix(String sequence)
//{
//	Node tempRoot;
//	if (root.isInternal)
//	{
//		tempRoot = new InternalNode(root.depth);
//	}
//	else if (root.isFlyWeight)
//	{
//		tempRoot = new FlyWeightNode(root.depth);
//	}
//	else if (root.isLeaf)
//	{
//		tempRoot = new LeafNode(root.depth);
//	}
//	//tempRoot = root;
//	
//	int nodesVisited = 0;
//	
//	for (int i = 0; i < sequence.length(); i++)
//	{
//	    if (tempRoot.isLeaf)
//	    {
//	    	break;
//	    }
//	    
//	    Character c = sequence.charAt(i);
//	    
//	    if (c.equals('A'))
//	    {
//	    	tempRoot = tempRoot.aChild();
//	    	nodesVisited += 1;
//	    }
//	    else if (c.equals('C'))
//	    {
//	    	tempRoot = tempRoot.cChild();
//	    	nodesVisited += 1;
//	    }
//	    else if (c.equals('G'))
//	    {
//	    	tempRoot = tempRoot.gChild();
//	    	nodesVisited += 1;
//	    }
//	    else if (c.equals('T'))
//	    {
//	    	tempRoot = tempRoot.tChild();
//	    	nodesVisited += 1;
//	    }
//	}
//	
//	// Find all of the DNA info in the subtree of the tempRoot
//      List<Pair<Integer, DNAType>> preorderList = 
//      		new ArrayList<Pair<Integer, DNAType>>();
//      preorderHelp(tempRoot, preorderList);
//	
//      // Update the number of nodes visited
//      nodesVisited += preorderList.size();
//      
//      // Populate the subtree nodes
//	List<DNAType> nodesInSubtree = new ArrayList<DNAType>();
//	for (int i = 0; i < preorderList.size(); i++)
//	{
//		nodesInSubtree.add(preorderList.get(i).getValue());
//	}
//	
//	// Create the return pair
//	return new Pair<Integer, List<DNAType>>(nodesVisited, nodesInSubtree);
//}
//
//	/**
//	 * Return a list of all the DNATypes in a preorder fashion
//	 * @return preorderList (tree nodes)
//	 */
//	private List<Pair<Integer, DNAType>> preorder()
//	{
//		List<Pair<Integer, DNAType>> preorderList = new 
//				ArrayList<Pair<Integer, DNAType>>();
//		preorderHelp(root, preorderList);
//		return preorderList;
//	}
//	
//	/**
//	 * Return a list of the DNATypes in the DNATree in a preorder order.
//	 * preorder = root, childA, childC, childG, childT, child$
//	 * @param rt
//	 * @param retList
//	 * @return
//	 */
//	private void preorderHelp(Node<DNAType> rt, 
//			List<Pair<Integer, DNAType>> retList)
//	{
//		if (rt == null)
//		{
//			return;
//		}
//		
//		/*
//		if (rt.aChild().getValue().isEmpty() && 
//				rt.cChild().getValue().isEmpty() &&
//				rt.gChild().getValue().isEmpty() &&
//				rt.tChild().getValue().isEmpty() &&
//				rt.$Child().getValue().isEmpty())
//		{
//			return;
//		}
//		*/
//		
//		Pair<Integer, DNAType> p = new 
//				Pair<Integer, DNAType>(rt.getDepth(), rt.getValue());
//		
//		retList.add(p);
//		preorderHelp(rt.aChild(), retList);
//		preorderHelp(rt.cChild(), retList);
//		preorderHelp(rt.gChild(), retList);
//		preorderHelp(rt.tChild(), retList);
//		preorderHelp(rt.$Child(), retList);
//	}
//	
//	/**
//	 * Method print called with no arguments which will then print out
//	 * the tree in a preorder order
//	 */
//	public void print(PrintOptions option)
//	{
//		List<Pair<Integer, DNAType>> nodeInfo = preorder();
//		
//		for (int i = 0; i < nodeInfo.size(); i++)
//		{
//			Integer depth = nodeInfo.get(i).getKey();
//			DNAType dna = nodeInfo.get(i).getValue();
//			
//			String spaces = new String(new char[depth]).replace("\0", "  ");
//			String out = "";
//			
//			if (dna.isDNA())
//			{
//				out = spaces + dna.getSequence();
//				
//			    switch (option)
//				{
//				case DEFAULT:
//					break;
//				case LENGTHS:
//					out += (": length: " + dna.getSequence().length());
//					break;
//				case STATS:
//					String pa = String.format("%.2f", dna.getPercentA());
//					String pc = String.format("%.2f", dna.getPercentC());
//					String pg = String.format("%.2f", dna.getPercentG());
//					String pt = String.format("%.2f", dna.getPercentT());
//					out += (": A(" + pa + "),");
//					out += (" C(" + pc + "),");
//					out += (" G(" + pg + "),");
//					out += (" T(" + pt + ")");
//					break;
//				}
//			}
//			else if (dna.isEmpty())
//			{
//				out = spaces + "E";
//			}
//			else if (dna.isInternal())
//			{
//				out = spaces + "I";
//			}
//			
//			// Finally, print the output to console
//			System.out.println(out);
//		}
//	}
}