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
	private Node<DNAType> root;
	
	/**
	 * 
	 * @return the root of the DNA tree
	 */
	public Node<DNAType> getRoot()
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
		root = new Node<DNAType>(new DNAType(Types.INTERNAL, null, null));
		setChildrenEmpty(root);
	}
	
	/**
	 * 
	 * @param node of type Node<DNAType>
	 */
	public void insert(Node<DNAType> node)
	{
		insertHelp(node);
		count++;
	}
	
	/**
	 * 
	 * @param node of type Node<DNAType>
	 */
	private void insertHelp(Node<DNAType> node)
	{
		if (node.getValue().isDNA())
		{
			// case where tree is empty
			if (count == 0) 
			{
				node.setDepth(0);
				root = node;
			}
			else
			{
				// case where second node is inserted
				if (root.getValue().isDNA())
				{
					String compareResult = compareSeq(root, node);
					Node<DNAType> rootTemp = root;
					root = new Node<DNAType>(
							new DNAType(Types.INTERNAL, null, null));
					setChildrenEmpty(root);
					// sequences have nothing in common
					if (compareResult.equals("")) 
					{
						addChildren(root, rootTemp);
						addChildren(root, node);
						rootTemp.setDepth(2);
						node.setDepth(2);
					}
					else if (compareResult.length() > 0)
					{
						extendTree(root, rootTemp, node, compareResult);
					}
				}
				else
				{
					/*Node<DNAType> tNode =*/ findInternal(root, node);
					
				}
			}
		}
	}
	
	/**
	 * 
	 * @param node of type DNAType
	 */
	public void remove(Node<DNAType> node)
	{
		removeHelp(root, node);
		count--;
	}
	
	/**
	 * 
	 * @param node
	 */
	private void removeHelp(Node<DNAType> rt, Node<DNAType> node)
	{
		int passThis= 0;
		if (node.getValue().isDNA())
		{
			String comPair = node.getValue().getSequence();
			for (int i = 0; i < comPair.length(); i++)
			{
				if (rt.getValue().isInternal())
				{
					Character charThis = comPair.charAt(i);
					if (charThis.equals('A'))
					{
						if (rt.aChild().getValue().isEmpty())
						{
							return;
						}
						rt = rt.aChild();
					}
					else if (charThis.equals('C'))
					{
						if (rt.cChild().getValue().isEmpty())
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
			if (rt.getValue().isInternal())
			{
				rt.$Child().getValue().setType(Types.EMPTY);
				rt.$Child().getValue().setCommand("");
				rt.$Child().getValue().setSequence("");
			}
			if (rt.getValue().isDNA())
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
	private void hasChildren(Node<DNAType> node, int count, String comPair)
	{
		for (int i = 0; i < count; i++)
		{
			Character temp = comPair.charAt(i);
			if (temp.equals('A'))
			{
				node = node.aChild();
			}
			else if (temp.equals('C'))
			{
				node = node.cChild();
			}
			else if (temp.equals('G'))
			{
				node = node.gChild();
			}
			else if (temp.equals('T'))
			{
				node = node.tChild();
			}
		}
		int whatKid = hasChildrenHelper(node);
		if (node.getValue().isInternal() && (onlyOneChild(node)) 
				&& (whatKid > 1))
		{
			String seqTemp = "";
			String comTemp = "";
			if (whatKid == 2)    // case where node only has aChild
			{
				seqTemp = node.aChild().getValue().getSequence();
				comTemp = node.aChild().getValue().getCommand();
				
			}
			else if (whatKid == 3)    // case where node only has cChild
			{
				seqTemp = node.cChild().getValue().getSequence();
				comTemp = node.cChild().getValue().getCommand();
			}
			else if (whatKid == 4)    // case where node only has gChild
			{
				seqTemp = node.gChild().getValue().getSequence();
				comTemp = node.gChild().getValue().getCommand();
			}
			else if (whatKid == 5)    // case where node only has tChild
			{
				seqTemp = node.tChild().getValue().getSequence();
				comTemp = node.tChild().getValue().getCommand();
			}
			else if (whatKid == 6)    // case where node only has $child
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
	
	private void setChildrenNull(Node<DNAType> node)
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
	private int hasChildrenHelper(Node<DNAType> node)
	{
		int result = 0;
		if (!node.aChild().getValue().isEmpty())
		{
			result = 2;
		}
		if (!node.cChild().getValue().isEmpty())
		{
			result = 3;
		}
		if (!node.gChild().getValue().isEmpty())
		{
			result = 4;
		}
		if (!node.tChild().getValue().isEmpty())
		{
			result = 5;
		}
		if (!node.$Child().getValue().isEmpty())
		{
			result = 6;
		}
		return result;
	}
	
	private boolean onlyOneChild(Node<DNAType> node)
	{
		int result = 0;
		if (!node.aChild().getValue().isEmpty())
		{
			result++;
		}
		if (!node.cChild().getValue().isEmpty())
		{
			result++;
		}
		if (!node.gChild().getValue().isEmpty())
		{
			result++;
		}
		if (!node.tChild().getValue().isEmpty())
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
	public ArrayList<Pair<Integer, Node<DNAType>>> search (Node<DNAType> node)
	{
		ArrayList<Pair<Integer, Node<DNAType>>> result = 
				new ArrayList<Pair<Integer, Node<DNAType>>>();
		int nodeCount = 0;
		if (node.getValue().getSequence().contains("$"))
		{
			result.add(searchHelp(root, node));
			searchPrint(result.get(0));
			
		}
		else
		{
			// call search help 2
			nodeCount = searchCounter(root, node);
			ArrayList<Node<DNAType>> list = searchHelpList(node, nodeCount);
			
		}
		return result;
	}
	
	private void searchPrint(Pair<Integer, Node<DNAType>> printThis)
	{
		System.out.println("# of nodes visited: " + printThis.getKey());
		if (printThis.getValue().getValue().getSequence() == null)
		{
			System.out.println("no sequence found");
		}
		else if (printThis.getValue().getValue().getSequence() == "")
		{
			System.out.println("no sequence found");
		}
		else
		{
			System.out.println("sequence: " + 
		printThis.getValue().getValue().getSequence());
		}
	}
	
	/**
	 * 
	 * @param node
	 * @return
	 */
	private Pair<Integer, Node<DNAType>> searchHelp(
			Node<DNAType> rt, Node<DNAType> node)
	{
		Node<DNAType> stopNow = new Node<DNAType>(
				new DNAType(Types.EMPTY, null, "no sequence found"));
		
		Pair<Integer, Node<DNAType>> result = 
				new Pair<Integer, Node<DNAType>>();
		int count = 1;
		String comPair = node.getValue().getSequence();
		if (node.getValue().isDNA())
		{
			for (int i = 0; i < comPair.length(); i++)
			{
				Character temp = comPair.charAt(i);
				if (!rt.getValue().isDNA())
				{
					if (temp.equals('A'))
					{
						if (rt.aChild().getValue().isEmpty())
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
					else if (temp.equals('C'))
					{
						if (rt.cChild().getValue().isEmpty())
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
					else if (temp.equals('G'))
					{
						if (rt.gChild().getValue().isEmpty())
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
					else if (temp.equals('T'))
					{
						if (rt.tChild().getValue().isEmpty())
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
			if (rt.getValue().isInternal())
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
	private int searchCounter(Node<DNAType> rt, Node<DNAType> node)
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
				if (rt.gChild().getValue().isEmpty())
				{
					break;
				}
				rt =rt.gChild();
			}
			else if (temp.equals('G'))
			{
				if (rt.gChild().getValue().isEmpty())
				{
					break;
				}
				rt =rt.gChild();
			}
			else if (temp.equals('T'))
			{
				if (rt.tChild().getValue().isEmpty())
				{
					break;
				}
				rt =rt.tChild();
			}
		}
		if (!rt.$Child().getValue().isEmpty())
		{
			count++;
		}
		return count;
	}
	
	private ArrayList<Node<DNAType>> searchHelpList(
			Node<DNAType> node, int nodeCount)
	{
		
		return new ArrayList<Node<DNAType>>(nodeCount);
	}
	
	/**
	 * 
	 * @param nodeOne of type DNAType
	 * @param nodeTwo of type DNAType
	 * @return string that contains the comparison results
	 */
	private String compareSeq(Node<DNAType> nodeOne, Node<DNAType> nodeTwo)
	{
		String result = "";
		char[] listOne = nodeOne.getValue().getSequence().toCharArray();
		char[] listTwo = nodeTwo.getValue().getSequence().toCharArray();
		if (listOne.length >= listTwo.length)
		{
			for (int i = 0; i < listOne.length; i++)
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
			for (int i = 0; i < listTwo.length; i++)
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
	 * @param rt
	 * @param nodeInsert
	 */
	private void addChildren(Node<DNAType> rt, Node<DNAType> nodeInsert)
	{
		if (nodeInsert.getValue().getSequence().length() == 1)    // edit
		{
			rt.set$Child(nodeInsert);
			return;
		}
		Character initChar = nodeInsert.getValue().getSequence().charAt(0);
		if (initChar.equals('A'))
		{
			rt.setAChild(nodeInsert);
		}
		else if (initChar.equals('C'))
		{
			rt.setCChild(nodeInsert);
		}
		else if (initChar.equals('G'))
		{
			rt.setGChild(nodeInsert);
		}
		else if (initChar.equals('T'))
		{
			rt.setTChild(nodeInsert);
		}
	}
	
	/**
	 * 
	 * @param rt
	 * @param nodeInsert
	 * @param nodeInsert2
	 * @param compared
	 */
	private void extendTree(Node<DNAType> rt, Node<DNAType> nodeInsert, 
			Node<DNAType> nodeInsert2, String compared)
	{
		if (compared.length() > 0)
		{
			Character temp = compared.charAt(0);
			Node<DNAType> newInternal = new Node<DNAType>(
					new DNAType(Types.INTERNAL, null, null));
			if (temp.equals('A'))
			{
				rt.setAChild(newInternal);
				newInternal.setDepth(newInternal.getDepth()+1);
			}
			else if (temp.equals('C'))
			{
				rt.setCChild(newInternal);
				newInternal.setDepth(newInternal.getDepth()+1);
			}
			else if (temp.equals('G'))
			{
				rt.setGChild(newInternal);
				newInternal.setDepth(newInternal.getDepth()+1);
			}
			else if (temp.equals('T'))
			{
				rt.setTChild(newInternal);
				newInternal.setDepth(newInternal.getDepth()+1);
			}
			if (compared.length() >= 1)
			{
				compared = compared.substring(1);
			}
			setChildrenEmpty(newInternal);
			extendTree(newInternal, nodeInsert, nodeInsert2, compared);
		}
		else
		{
			setChildSelect(rt, nodeInsert, 
					nodeInsert.getValue().getSequence().charAt(
							compared.length()+1));
			setChildSelect(rt, nodeInsert2, 
					nodeInsert2.getValue().getSequence().charAt(
							compared.length()+1));
		}
	}
	
	/**
	 * 
	 * @param rt
	 * @param comPair
	 * @param node
	 * @return
	 */
	private void findInternal(Node<DNAType> rt, Node<DNAType> node)
	{
		
		String comPair = node.getValue().getSequence();

		for (int i = 0; i < comPair.length(); i++)
		{
			Character temp = comPair.charAt(i);
			
			if (rt.getValue().isInternal())
			{
				if (temp.equals('A'))
				{
					if (rt.aChild().getValue().isEmpty())
					{
						rt.setAChild(node);
						node.setDepth(rt.getDepth() + 1);
						return;
					}
					rt = rt.aChild();
					node.setDepth(node.getDepth() + 1);
				}
				else if (temp.equals('C'))
				{
					if (rt.cChild().getValue().isEmpty())
					{
						rt.setCChild(node);
						node.setDepth(rt.getDepth() + 1);
						return;
					}
					rt = rt.cChild();
					node.setDepth(node.getDepth() + 1);
				}
				else if (temp.equals('G'))
				{
					if (rt.gChild().getValue().isEmpty())
					{
						rt.setGChild(node);
						node.setDepth(rt.getDepth() + 1);
						return;
					}
					rt = rt.gChild();
					node.setDepth(node.getDepth() + 1);
				}
				else if (temp.equals('T'))
				{
					if (rt.tChild().getValue().isEmpty())
					{
						rt.setTChild(node);
						node.setDepth(rt.getDepth() + 1);
						return;
					}
					rt = rt.tChild();
					node.setDepth(node.getDepth() + 1);
				}
			}
			else if (!rt.getValue().isInternal())
			{
				rt.getValue().setType(Types.INTERNAL);
				String seqTemp = rt.getValue().getSequence();
				rt.getValue().setSequence("");
				String comTemp = rt.getValue().getCommand();
				rt.getValue().setCommand("");
				Node<DNAType> exTend = new Node<DNAType>(
						new DNAType(Types.DNATYPE, comTemp, seqTemp));
				setChildrenEmpty(rt);
				exTend.getValue().setCommand(comTemp);
				exTend.getValue().setSequence(seqTemp);
				if (exTend.getValue().getSequence().length() > i)
				{    // check case where sequence inserted is longer
					Character pass1 = 
							exTend.getValue().getSequence().charAt(i);
					setChildSelect(rt, exTend, pass1);
				}
				else
				{
					rt.set$Child(exTend);
					exTend.setDepth(rt.getDepth()+1);
				}
				Character pass2 = node.getValue().getSequence().charAt(i);
				setChildSelect(rt, node, pass2);
				return;
			}
		}
		if (rt.getValue().isInternal())    // case where full length of seq has passed
		{
			rt.set$Child(node);
			node.setDepth(node.getDepth() + 1);
		}
		else
		{
			rt.getValue().setType(Types.INTERNAL);
			String seqTemp = rt.getValue().getSequence();
			rt.getValue().setSequence("");
			String comTemp = rt.getValue().getCommand();
			rt.getValue().setCommand("");
			Node<DNAType> exTend = new Node<DNAType>(
					new DNAType(Types.DNATYPE, comTemp, seqTemp));
			setChildrenEmpty(rt);
			rt.set$Child(node);
			node.setDepth(rt.getDepth() + 1);
			setChildSelect(rt, exTend, 
					exTend.getValue().getSequence().charAt(comPair.length()));
			
		}
		return;
	}
	
	/**
	 * 
	 * @param rt
	 * @param node
	 * @param childLetter
	 */
	private void setChildSelect(Node<DNAType> rt, 
			Node<DNAType> node, Character childLetter)
	{
		if (childLetter.equals('A'))
		{
			rt.setAChild(node);
		}
		else if (childLetter.equals('C'))
		{
			rt.setCChild(node);
		}
		else if (childLetter.equals('G'))
		{
			rt.setGChild(node);
		}
		else if (childLetter.equals('T'))
		{
			rt.setTChild(node);
		}
		node.setDepth(rt.getDepth() + 1);
	}
	
	/**
	 * 
	 * @param node
	 */
	private void setChildrenEmpty(Node<DNAType> node)
	{	// have checks if node is null
		DNAType innerEmplaceThis = new DNAType(Types.EMPTY, null, null);
		Node<DNAType> emplaceThis = new Node<DNAType>(innerEmplaceThis);
		node.setAChild(emplaceThis);
		node.aChild().setDepth(node.getDepth() + 1);
		node.setCChild(emplaceThis);
		node.cChild().setDepth(node.getDepth() + 1);
		node.setGChild(emplaceThis);
		node.gChild().setDepth(node.getDepth() + 1);
		node.setTChild(emplaceThis);
		node.tChild().setDepth(node.getDepth() + 1);
		node.set$Child(emplaceThis);
		node.$Child().setDepth(node.getDepth() + 1);
	}
	
	/**
	 * Returns all the Nodes beginning with the prefix specified along with
	 * the number of nodes visited
	 * @param sequence
	 * @return
	 */
	public Pair<Integer, List<DNAType>> searchByPrefix(String sequence)
	{
		Node<DNAType> tempRoot = new Node<DNAType>();
		tempRoot = root;
		
		int nodesVisited = 0;
		
		for (int i = 0; i < sequence.length(); i++)
		{
		    if (tempRoot == null)
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
	private void preorderHelp(Node<DNAType> rt, 
			List<Pair<Integer, DNAType>> retList)
	{
		if (rt == null)
		{
			return;
		}
		
		/*
		if (rt.aChild().getValue().isEmpty() && 
				rt.cChild().getValue().isEmpty() &&
				rt.gChild().getValue().isEmpty() &&
				rt.tChild().getValue().isEmpty() &&
				rt.$Child().getValue().isEmpty())
		{
			return;
		}
		*/
		
		Pair<Integer, DNAType> p = new 
				Pair<Integer, DNAType>(rt.getDepth(), rt.getValue());
		
		retList.add(p);
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
}