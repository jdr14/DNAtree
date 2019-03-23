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
		root = new FlyWeightNode(0);
//		setChildrenEmpty((InternalNode) root);
	}
	
	/**
	 * 
	 * @param node of type Node<DNAType>
	 */
	public void insert(String newSeq)
	{
		insertHelp(newSeq);
		count++;
	}
	
	/**
	 * 
	 * @param node of type Node<DNAType>
	 */
	private void insertHelp(String newSeq)
	{
		if (count == 0)    // case where tree is empty
		{
		    root = new LeafNode(root, newSeq);
			root.setLeaf();
		}
		else    // case where tree is not empty
		{
			LeafNode insertNode = new LeafNode(newSeq);    // node to be inserted
			if(root.isLeaf)    // case where second node is inserted
			{
				LeafNode oldRoot = (LeafNode) root;    // copy of old root
				root = new InternalNode();    // root is now an Internal node again
				// string of similar characters in string
				String compareResult = compareSeq(oldRoot, insertNode);
				setChildrenEmpty((InternalNode) root);
				if (compareResult.equals(""))    // sequences have nothing in common
				{
					addChildren((InternalNode) root, oldRoot);
					addChildren((InternalNode) root, insertNode);
					oldRoot.depth = 2;
					insertNode.depth = 2;
				}
				else if(compareResult.length() > 0)
				{
					extendTree((InternalNode) root, oldRoot, insertNode, compareResult);
				}
			}
			else
			{
				/*Node<DNAType> tNode =*/ findInternal((InternalNode) root, insertNode);
				
			}
		}
	}
	
	/**
	 * 
	 * @param rt
	 * @param comPair
	 * @param node
	 * @return
	 */
	private void findInternal(InternalNode rt, LeafNode node)
	{
		
		String comPair = node.getSequence();

		for(int i = 0; i < comPair.length(); i++)
		{
			Character temp = comPair.charAt(i);
			
			if(rt.isInternal)
			{
				if(temp.equals('A'))
				{
					if(rt.getAChild().isFlyWeight)
					{
						rt.setAChild(node);
						node.depth = (rt.depth+1);
						return;
					}
					rt = (InternalNode) rt.getAChild();
					node.depth = (rt.depth+1);
				}
				else if(temp.equals('C'))
				{
					if(rt.getCChild().isFlyWeight)
					{
						rt.setCChild(node);
						node.depth = (rt.depth+1);
						return;
					}
					rt = (InternalNode) rt.getCChild();
					node.depth = (rt.depth+1);
				}
				else if(temp.equals('G'))
				{
					if(rt.getGChild().isFlyWeight)
					{
						rt.setGChild(node);
						node.depth = (rt.depth+1);
						return;
					}
					rt = (InternalNode) rt.getGChild();
					node.depth = (rt.depth+1);
				}
				else if(temp.equals('T'))
				{
					if(rt.getTChild().isFlyWeight)
					{
						rt.setTChild(node);
						node.depth = (rt.depth+1);
						return;
					}
					rt = (InternalNode) rt.getTChild();
					node.depth = (rt.depth+1);
				}
			}
			else if(!rt.isInternal)
			{
				LeafNode newRt = new LeafNode(rt);
				rt.getValue().setType(Types.INTERNAL);
				String seqTemp = (LeafNode ) rt.getSequence();
				rt.getValue().setSequence("");
				String comTemp = rt.getValue().getCommand();
				rt.getValue().setCommand("");
				LeafNode exTend = new LeafNode(seqTemp);
				setChildrenEmpty(rt);
				exTend.setCommand(comTemp);
				exTend.setSequence(seqTemp);
				if(exTend.getSequence().length() > i)
				{    // check case where sequence inserted is longer
					Character pass1 = exTend.getSequence().charAt(i);
					setChildSelect(rt, exTend, pass1);
				}
				else
				{
					rt.set$Child(exTend);
					exTend.depth++;
				}
				Character pass2 = node.getSequence().charAt(i);
				setChildSelect(rt, node, pass2);
				return;
			}
		}
		if(rt.isInternal)    // case where full length of seq has passed
		{
			rt.set$Child(node);
			node.depth++;
		}
		else
		{
			rt.getValue().setType(Types.INTERNAL);
			String seqTemp = rt.getValue().getSequence();
			rt.getValue().setSequence("");
			String comTemp = rt.getValue().getCommand();
			rt.getValue().setCommand("");
			Node exTend = new Node(new DNAType(Types.DNATYPE, comTemp, seqTemp));
			setChildrenEmpty(rt);
			rt.set$Child(node);
			node.setDepth(rt.getDepth()+1);
			setChildSelect(rt, exTend, exTend.getValue().getSequence().charAt(comPair.length()));
			
		}
		return;
	}
	
	/**
	 * 
	 * @param rt
	 * @param nodeInsert
	 * @param nodeInsert2
	 * @param compared
	 */
	private void extendTree(InternalNode rt, LeafNode nodeInsert, LeafNode nodeInsert2, String compared)
	{
		if(compared.length() > 0)
		{
			Character temp = compared.charAt(0);
			InternalNode newInternal = new InternalNode(root.depth+1);
			if(temp.equals('A'))
			{
				rt.setAChild(newInternal);
				newInternal.depth = newInternal.depth+1;
			}
			else if (temp.equals('C'))
			{
				rt.setCChild(newInternal);
				newInternal.depth = newInternal.depth+1;
			}
			else if(temp.equals('G'))
			{
				rt.setGChild(newInternal);
				newInternal.depth = newInternal.depth+1;
			}
			else if(temp.equals('T'))
			{
				rt.setTChild(newInternal);
				newInternal.depth = newInternal.depth+1;
			}
			if(compared.length() >= 1)
			{
				compared = compared.substring(1);
			}
			setChildrenEmpty(newInternal);
			extendTree(newInternal, nodeInsert, nodeInsert2, compared);
		}
		else
		{
			setChildSelect(rt, nodeInsert, nodeInsert.getSequence().charAt(compared.length()+1));
			setChildSelect(rt, nodeInsert2, nodeInsert2.getSequence().charAt(compared.length()+1));
		}
	}
	
	/**
	 * 
	 * @param rt
	 * @param node
	 * @param childLetter
	 */
	private void setChildSelect(InternalNode rt, Node node, Character childLetter)
	{
		if(childLetter.equals('A'))
		{
			rt.setAChild(node);
		}
		else if(childLetter.equals('C'))
		{
			rt.setCChild(node);
		}
		else if(childLetter.equals('G'))
		{
			rt.setGChild(node);
		}
		else if(childLetter.equals('T'))
		{
			rt.setTChild(node);
		}
		node.depth = (rt.depth+1);
	}
	
	/**
	 * 
	 * @param rt
	 * @param nodeInsert
	 */
	private void addChildren(InternalNode rt, LeafNode nodeInsert)
	{
		if(nodeInsert.getSequence().length() == 1)    // edit
		{
			rt.set$Child(nodeInsert);
			return;
		}
		Character initChar = nodeInsert.getSequence().charAt(0);
		if (initChar.equals('A'))
		{
			rt.setAChild(nodeInsert);
		}
		else if(initChar.equals('C'))
		{
			rt.setCChild(nodeInsert);
		}
		else if(initChar.equals('G'))
		{
			rt.setGChild(nodeInsert);
		}
		else if(initChar.equals('T'))
		{
			rt.setTChild(nodeInsert);
		}
	}
	
	/**
	 * 
	 * @param nodeOne of type DNAType
	 * @param nodeTwo of type DNAType
	 * @return string that contains the comparison results
	 */
	private String compareSeq(LeafNode nodeOne, LeafNode nodeTwo)
	{
		String result = "";
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
	
	/**
	 * 
	 * @param node of type DNAType
	 */
	public void remove(Node node)
	{
		removeHelp(root, node);
		count--;
	}
	
	/**
	 * 
	 * @param node
	 */
	private void removeHelp(Node rt, Node node)
	{
		int passThis= 0;
		if(node.getValue().isDNA())
		{
			String comPair = node.getValue().getSequence();
			for (int i = 0; i < comPair.length(); i++)
			{
				if (rt.getValue().isInternal())
				{
					Character charThis = comPair.charAt(i);
					if(charThis.equals('A'))
					{
						if (rt.aChild().getValue().isEmpty())
						{
							return;
						}
						rt = rt.aChild();
					}
					else if(charThis.equals('C'))
					{
						if(rt.cChild().getValue().isEmpty())
						{
							return;
						}
						rt = rt.cChild();
					}
					else if (charThis.equals('G'))
					{
						if (rt.gChild().getValue().isEmpty())
						{
							return;
						}
						rt = rt.gChild();
					}
					else if (charThis.equals('T'))
					{
						if (rt.tChild().getValue().isEmpty())
						{
							return;
						}
						rt = rt.tChild();
					}
				}
				else if (rt.getValue().isDNA())
				{
					rt.getValue().setType(Types.EMPTY);
					rt.getValue().setCommand("");
					rt.getValue().setSequence("");
					passThis = i-1;
					hasChildren(root, passThis, comPair);
					return;
				}
				else
				{
					return;
				}
			}
			if(rt.getValue().isInternal())
			{
				rt.$Child().getValue().setType(Types.EMPTY);
				rt.$Child().getValue().setCommand("");
				rt.$Child().getValue().setSequence("");
			}
			if(rt.getValue().isDNA())
			{
				rt.getValue().setType(Types.EMPTY);
				rt.getValue().setCommand("");
				rt.getValue().setSequence("");
			}
		}
	}
	
	/**
	 * 
	 * @param node
	 * @return
	 */
	private void hasChildren(Node node, int count, String comPair)
	{
		for (int i = 0; i < count; i++)
		{
			Character temp = comPair.charAt(i);
			if(temp.equals('A'))
			{
				node = node.getAChild();
			}
			else if (temp.equals('C'))
			{
				node = node.getCChild();
			}
			else if (temp.equals('G'))
			{
				node = node.getGChild();
			}
			else if (temp.equals('T'))
			{
				node = node.getTChild();
			}
		}
		int whatKid = hasChildrenHelper(node);
		if(node.getValue().isInternal() && (onlyOneChild(node)) && (whatKid > 1))
		{
			String seqTemp = "";
			String comTemp = "";
			if(whatKid == 2)    // case where node only has aChild
			{
				seqTemp = node.aChild().getValue().getSequence();
				comTemp = node.aChild().getValue().getCommand();
				
			}
			else if(whatKid == 3)    // case where node only has cChild
			{
				seqTemp = node.cChild().getValue().getSequence();
				comTemp = node.cChild().getValue().getCommand();
			}
			else if(whatKid == 4)    // case where node only has gChild
			{
				seqTemp = node.gChild().getValue().getSequence();
				comTemp = node.gChild().getValue().getCommand();
			}
			else if(whatKid == 5)    // case where node only has tChild
			{
				seqTemp = node.tChild().getValue().getSequence();
				comTemp = node.tChild().getValue().getCommand();
			}
			else if(whatKid == 6)    // case where node only has $child
			{
				seqTemp = node.$Child().getValue().getSequence();
				comTemp = node.$Child().getValue().getCommand();
			}
			node.getValue().setType(Types.DNATYPE);
			node.getValue().setSequence(seqTemp);
			node.getValue().setCommand(comTemp);
			node.setDepth(node.getDepth());
			setChildrenNull(node);
		}
	}
	
	private void setChildrenNull(InternalNode node)
	{
		node.setAChild(null);
		node.setCChild(null);
		node.setGChild(null);
		node.setTChild(null);
		node.set$Child(null);
	}
	
	/**
	 * 
	 * @param node
	 * @return
	 */
	private int hasChildrenHelper(InternalNode node)
	{
		int result = 0;
		if (!node.getAChild().isFlyWeight)
		{
			result = 2;
		}
		if (!node.getCChild().isFlyWeight)
		{
			result = 3;
		}
		if (!node.getGChild().isFlyWeight)
		{
			result = 4;
		}
		if (!node.getTChild().isFlyWeight)
		{
			result = 5;
		}
		if (!node.get$Child().isFlyWeight)
		{
			result = 6;
		}
		return result;
	}
	
	private boolean onlyOneChild(InternalNode node)
	{
		int result = 0;
		if (!node.getAChild().isFlyWeight)
		{
			result++;
		}
		if (!node.getCChild().isFlyWeight)
		{
			result++;
		}
		if (!node.getGChild().isFlyWeight)
		{
			result++;
		}
		if (!node.getTChild().isFlyWeight)
		{
			result++;
		}
		return result == 1;
	}
	
    /**
     * 
     * @param node of type DNAType
     * @return
     */
	public ArrayList<Pair<Integer, Node>> search (Node node)
	{
		ArrayList<Pair<Integer, Node>> result = 
				new ArrayList<Pair<Integer, Node>>();
		int nodeCount = 0;
		if(node.getValue().getSequence().contains("$"))
		{
			result.add(searchHelp(root, node));
			searchPrint(result.get(0));
			
		}
		else
		{
			// call search help 2
			nodeCount = searchCounter(root, node);
			
		}
		return result;
	}
	
	private void searchPrint(Pair<Integer, Node> printThis)
	{
		System.out.println("# of nodes visited: " + printThis.getKey());
		if(printThis.getValue().getValue().getSequence() == null)
		{
			System.out.println("no sequence found");
		}
		else if (printThis.getValue().getValue().getSequence() == "")
		{
			System.out.println("no sequence found");
		}
		else
		{
			System.out.println("sequence: " + printThis.getValue().getValue().getSequence());
		}
	}
	
	/**
	 * 
	 * @param node
	 * @return
	 */
	private Pair<Integer, Node> searchHelp(Node rt, Node node)
	{
		Node stopNow = new Node(new DNAType(Types.EMPTY, null, "no sequence found"));
		
		Pair<Integer, Node> result = new Pair<Integer, Node>();
		int count = 1;
		String comPair = node.getValue().getSequence();
		if(node.getValue().isDNA())
		{
			for(int i = 0; i < comPair.length(); i++)
			{
				Character temp = comPair.charAt(i);
				if(!rt.getValue().isDNA())
				{
					if(temp.equals('A'))
					{
						if(rt.aChild().getValue().isEmpty())
						{
							result.setKey(count);
							result.setValue(stopNow);
							break;
						}
						else
						{
							rt = rt.aChild();
							count++;
						}
					}
					else if(temp.equals('C'))
					{
						if(rt.cChild().getValue().isEmpty())
						{
							result.setKey(count);
							result.setValue(stopNow);
							break;
						}
						else
						{
							rt = rt.cChild();
							count++;
						}
					}
					else if(temp.equals('G'))
					{
						if(rt.gChild().getValue().isEmpty())
						{
							result.setKey(count);
							result.setValue(stopNow);
							break;
						}
						else
						{
							rt = rt.gChild();
							count++;
						}
					}
					else if(temp.equals('T'))
					{
						if(rt.tChild().getValue().isEmpty())
						{
							result.setKey(count);
							result.setValue(stopNow);
							break;
						}
						else
						{
							rt = rt.tChild();
							count++;
						}
					}
				}
			}
			if(rt.getValue().isInternal())
			{
				rt = rt.$Child();
				count++;
			}
			result.setKey(count);
			result.setValue(rt);
		}
		return result;
	}
	
	/**
	 * 
	 * @param node
	 * @return
	 */
	private int searchCounter(Node rt, Node node)
	{
		int count = 0;
		String seQ = node.getValue().getSequence();
		for (int i = 0; i < seQ.length(); i++)
		{
			Character temp = seQ.charAt(i);
			if (temp.equals('A'))
			{
				if (rt.aChild().getValue().isEmpty())
				{
					break;
				}
				rt = rt.aChild();
			}
			else if (temp.equals('C'))
			{
				if(rt.gChild().getValue().isEmpty())
				{
					break;
				}
				rt =rt.gChild();
			}
			else if (temp.equals('G'))
			{
				if(rt.gChild().getValue().isEmpty())
				{
					break;
				}
				rt =rt.gChild();
			}
			else if (temp.equals('T'))
			{
				if(rt.tChild().getValue().isEmpty())
				{
					break;
				}
				rt =rt.tChild();
			}
		}
		if(!rt.$Child().getValue().isEmpty())
		{
			count++;
		}
		return count;
	}
	
	/**
	 * 
	 * @param node
	 */
	private void setChildrenEmpty(InternalNode node)
	{	// have checks if node is null
		FlyWeightNode emplaceThis = new FlyWeightNode(node.depth+1);
		node.setAChild(emplaceThis);
		node.setCChild(emplaceThis);
		node.setGChild(emplaceThis);
		node.setTChild(emplaceThis);
		node.set$Child(emplaceThis);
	}
}		//    MAKE SURE TO DELETE THIS TEMPORARY FIX TO BIG COMMENT BELOW
	
	/**
	 * Returns all the Nodes beginning with the prefix specified along with
	 * the number of nodes visited
	 * @param sequence
	 * @return
	 */
public Pair<Integer, List<String>> searchByPrefix(String sequence)
{
	Node tempRoot;
	if (root.isInternal)
	{
		tempRoot = new InternalNode(root.depth);
	}
	else if (root.isFlyWeight)
	{
		tempRoot = new FlyWeightNode(root.depth);
	}
	else if (root.isLeaf)
	{
		tempRoot = new LeafNode(root.depth);
	}
	//tempRoot = root;
	
	int nodesVisited = 0;
	
	for (int i = 0; i < sequence.length(); i++)
	{
	    if (tempRoot.isLeaf)
	    {
	    	break;
	    }
	    
	    Character c = sequence.charAt(i);
	    
	    if (c.equals('A'))
	    {
	    	tempRoot = tempRoot.aChild();
	    	nodesVisited += 1;
	    }
	    else if (c.equals('C'))
	    {
	    	tempRoot = tempRoot.cChild();
	    	nodesVisited += 1;
	    }
	    else if (c.equals('G'))
	    {
	    	tempRoot = tempRoot.gChild();
	    	nodesVisited += 1;
	    }
	    else if (c.equals('T'))
	    {
	    	tempRoot = tempRoot.tChild();
	    	nodesVisited += 1;
	    }
	}
	
	// Find all of the DNA info in the subtree of the tempRoot
      List<Pair<Integer, DNAType>> preorderList = 
      		new ArrayList<Pair<Integer, DNAType>>();
      preorderHelp(tempRoot, preorderList);
	
      // Update the number of nodes visited
      nodesVisited += preorderList.size();
      
      // Populate the subtree nodes
	List<DNAType> nodesInSubtree = new ArrayList<DNAType>();
	for (int i = 0; i < preorderList.size(); i++)
	{
		nodesInSubtree.add(preorderList.get(i).getValue());
	}
	
	// Create the return pair
	return new Pair<Integer, List<DNAType>>(nodesVisited, nodesInSubtree);
}

/**
 * Return a list of all the DNATypes in a preorder fashion
 * @return preorderList (tree nodes)
 */
private List<Pair<Integer, DNAType>> preorder()
{
	List<Pair<Integer, DNAType>> preorderList = new 
			ArrayList<Pair<Integer, DNAType>>();
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
		List<Node> retList)
{
	if (rt.isLeaf)
	{
		retList.add(rt);
		return;
	}
	
	if (rt.isFlyWeight)
	{
		return;
	}
    
	// Otherwise rt is an internal node.
	preorderHelp(rt.aChild(), retList);
	preorderHelp(rt.cChild(), retList);
	preorderHelp(rt.gChild(), retList);
	preorderHelp(rt.tChild(), retList);
	preorderHelp(rt.$Child(), retList);
}


/**
 * Method print called with no arguments which will then print out
 * the tree in a preorder order
 */
public void print(PrintOptions option)
{
	List<Pair<Integer, DNAType>> nodeInfo = preorder();
	
	for (int i = 0; i < nodeInfo.size(); i++)
	{
		Integer depth = nodeInfo.get(i).getKey();
		DNAType dna = nodeInfo.get(i).getValue();
		
		String spaces = new String(new char[depth]).replace("\0", "  ");
		String out = "";
		
		if (dna.isDNA())
		{
			out = spaces + dna.getSequence();
			
		    switch (option)
			{
			case DEFAULT:
				break;
			case LENGTHS:
				out += (": length: " + dna.getSequence().length());
				break;
			case STATS:
			    Double a, c, g, t;
			    calculatePercentages(dna.getSequence(), a, c, g, t);
				String pa = String.format("%.2f", dna.getPercentA());
				String pc = String.format("%.2f", dna.getPercentC());
				String pg = String.format("%.2f", dna.getPercentG());
				String pt = String.format("%.2f", dna.getPercentT());
				out += (": A(" + pa + "),");
				out += (" C(" + pc + "),");
				out += (" G(" + pg + "),");
				out += (" T(" + pt + ")");
				break;
			}
		}
		else if (dna.isEmpty())
		{
			out = spaces + "E";
		}
		else if (dna.isInternal())
		{
			out = spaces + "I";
		}
		
		// Finally, print the output to console
		System.out.println(out);
	}
}

/**
 * Private helper method to handle the calculation of percentages of
 * each character in relation to its containing sequence
 * @param seq (String)
 */
private void calculatePercentages(String seq, Double percentA, Double percentC
	Double percentG, Double percentT)
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


//}