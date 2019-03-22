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
		if(node.getValue().isDNA())
		{
			if(count == 0)    // case where tree is empty
			{
				node.setDepth(0);
				root = node;
			}
			else
			{
				if(root.getValue().isDNA())    // case where second node is inserted
				{
					String compareResult = compareSeq(root, node);
					Node<DNAType> rootTemp = root;
					root = new Node<DNAType>(new DNAType(Types.INTERNAL, null, null));
					setChildrenEmpty(root);
					if (compareResult.equals(""))    // sequences have nothing in common
					{
						addChildren(root, rootTemp);
						addChildren(root, node);
						rootTemp.setDepth(2);
						node.setDepth(2);
					}
					else if(compareResult.length() > 0)
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
		if(node.getValue().isDNA())
		{
			String comPair = node.getValue().getSequence();
			for (int i = 0; i < comPair.length(); i++)
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
			if (!rt.getValue().isInternal())
			{
				rt.getValue().setType(Types.EMPTY);
				rt.getValue().setCommand("");
				rt.getValue().setSequence("");
			}
			else
			{
				return;
			}
			
		}
	}
	
    /**
     * 
     * @param node of type DNAType
     * @return
     */
	public ArrayList<Node<DNAType>> search (Node<DNAType> node)
	{
		ArrayList<Node<DNAType>> result = new ArrayList<Node<DNAType>>();
		int nodeCount = 0;
		if(node.getValue().getSequence().contains("$"))
		{
			// call search help 2
			nodeCount = searchCounter(root, node);
			ArrayList<Node<DNAType>> list = searchHelpList(node, nodeCount);
			
		}
		else
		{
			result.add(searchHelp(node));
			
		}
		return result;
		
	}
	
	/**
	 * 
	 * @param node
	 * @return
	 */
	private Node<DNAType> searchHelp(Node<DNAType> node)
	{
		int count = 0;
		String comPair = node.getValue().getSequence();
		Node<DNAType> moveThis = new Node<DNAType>(root);
		setChildrenEmpty(moveThis);
		if(node.getValue().isDNA())
		{
			for(int i = 0; i < comPair.length(); i++)
			{
				Character temp = comPair.charAt(i);
				if(temp.equals('A'))
				{
					moveThis = moveThis.aChild();
				}
				else if(temp.equals('C'))
				{
					moveThis = moveThis.cChild();
				}
				else if(temp.equals('G'))
				{
					moveThis = moveThis.gChild();
				}
				else if(temp.equals('T'))
				{
					moveThis = moveThis.tChild();
				}
				count++;
			}
//			if(moveThis.getValue().isInternal())
//			{
//				moveThis = moveThis.$Child();
//				count++;
//			}
			
		}
		return moveThis;
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
	
	private ArrayList<Node<DNAType>> searchHelpList(Node<DNAType> node, int nodeCount)
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
	 * @param rt
	 * @param nodeInsert
	 */
	private void addChildren(Node<DNAType> rt, Node<DNAType> nodeInsert)
	{
		if(nodeInsert.getValue().getSequence().length() == 1)    // edit
		{
			rt.set$Child(nodeInsert);
			return;
		}
		Character initChar = nodeInsert.getValue().getSequence().charAt(0);
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
	 * @param rt
	 * @param nodeInsert
	 * @param nodeInsert2
	 * @param compared
	 */
	private void extendTree(Node<DNAType> rt, Node<DNAType> nodeInsert, Node<DNAType> nodeInsert2, String compared)
	{
		if(compared.length() > 0)
		{
			Character temp = compared.charAt(0);
			Node<DNAType> newInternal = new Node<DNAType>(new DNAType(Types.INTERNAL, null, null));
			setChildrenEmpty(newInternal);
			if(temp.equals('A'))
			{
				rt.setAChild(newInternal);
				newInternal.setDepth(newInternal.getDepth()+1);
			}
			else if (temp.equals('C'))
			{
				rt.setCChild(newInternal);
				newInternal.setDepth(newInternal.getDepth()+1);
			}
			else if(temp.equals('G'))
			{
				rt.setGChild(newInternal);
				newInternal.setDepth(newInternal.getDepth()+1);
			}
			else if(temp.equals('T'))
			{
				rt.setTChild(newInternal);
				newInternal.setDepth(newInternal.getDepth()+1);
			}
			if(compared.length() >= 1)
			{
				compared = compared.substring(1);
			}
			extendTree(newInternal, nodeInsert, nodeInsert2, compared);
		}
		else
		{
			setChildSelect(rt, nodeInsert, nodeInsert.getValue().getSequence().charAt(compared.length()+1));
			setChildSelect(rt, nodeInsert2, nodeInsert2.getValue().getSequence().charAt(compared.length()+1));
//			addChildren(rt, nodeInsert);    // chage to setChildSelect with char as length+1
//			addChildren(rt, nodeInsert2);
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

		for(int i = 0; i < comPair.length(); i++)
		{
			Character temp = comPair.charAt(i);
			
			if(rt.getValue().isInternal())
			{
				if(temp.equals('A'))
				{
					if(rt.aChild().getValue().isEmpty())
					{
						rt.setAChild(node);
						node.setDepth(rt.getDepth()+1);
						return;
					}
					rt = rt.aChild();
					node.setDepth(node.getDepth()+1);
				}
				else if(temp.equals('C'))
				{
					if(rt.cChild().getValue().isEmpty())
					{
						rt.setCChild(node);
						node.setDepth(rt.getDepth()+1);
						return;
					}
					rt = rt.cChild();
					node.setDepth(node.getDepth()+1);
				}
				else if(temp.equals('G'))
				{
					if(rt.gChild().getValue().isEmpty())
					{
						rt.setGChild(node);
						node.setDepth(rt.getDepth()+1);
						return;
					}
					rt = rt.gChild();
					node.setDepth(node.getDepth()+1);
				}
				else if(temp.equals('T'))
				{
					if(rt.tChild().getValue().isEmpty())
					{
						rt.setTChild(node);
						node.setDepth(rt.getDepth()+1);
						return;
					}
					rt = rt.tChild();
					node.setDepth(node.getDepth()+1);
				}
			}
			else if(!rt.getValue().isInternal())
			{
				rt.getValue().setType(Types.INTERNAL);
				String seqTemp = rt.getValue().getSequence();
				rt.getValue().setSequence("");
				String comTemp = rt.getValue().getCommand();
				rt.getValue().setCommand("");
				Node<DNAType> exTend = new Node<DNAType>(new DNAType(Types.DNATYPE, comTemp, seqTemp));
				setChildrenEmpty(exTend);
				exTend.getValue().setCommand(comTemp);
				exTend.getValue().setSequence(seqTemp);
				Character pass1 = exTend.getValue().getSequence().charAt(i);
				Character pass2 = node.getValue().getSequence().charAt(i);
				setChildSelect(rt, exTend, pass1);
				setChildSelect(rt, node, pass2);
				return;
			}
		}
		if(rt.getValue().isInternal())    // case where full length of seq has passed
		{
			rt.set$Child(node);
			node.setDepth(node.getDepth()+1);
		}
		else
		{
			rt.getValue().setType(Types.INTERNAL);
			String seqTemp = rt.getValue().getSequence();
			rt.getValue().setSequence("");
			String comTemp = rt.getValue().getCommand();
			rt.getValue().setCommand("");
			Node<DNAType> exTend = new Node<DNAType>(new DNAType(Types.DNATYPE, comTemp, seqTemp));
			setChildrenEmpty(exTend);
			rt.set$Child(node);
			node.setDepth(rt.getDepth()+1);
			setChildSelect(rt, exTend, exTend.getValue().getSequence().charAt(comPair.length()+1));
			
		}
		return;
	}
	
	/**
	 * 
	 * @param rt
	 * @param node
	 * @param childLetter
	 */
	private void setChildSelect(Node<DNAType> rt, Node<DNAType> node, Character childLetter)
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
		node.setDepth(rt.getDepth()+1);
	}
	
	/**
	 * 
	 * @param node
	 */
	private void setChildrenEmpty(Node<DNAType> node)
	{
		DNAType innerEmplaceThis = new DNAType(Types.EMPTY, null, null);
		Node<DNAType> emplaceThis = new Node<DNAType>(innerEmplaceThis);
		node.setAChild(emplaceThis);
		node.setCChild(emplaceThis);
		node.setGChild(emplaceThis);
		node.setTChild(emplaceThis);
		node.set$Child(emplaceThis);
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
	public void print()
	{
		List<Pair<Integer, DNAType>> nodeInfo = preorder();
		
		for (int i = 0; i < nodeInfo.size(); i++)
		{
			Integer depth = nodeInfo.get(i).getKey();
			DNAType dna = nodeInfo.get(i).getValue();
			
			String spaces = new String(new char[depth]).replace("\0", "  ");
			String out;
			if (dna.isDNA())
			{
				out = spaces + dna.getSequence();
				System.out.println(out);
			}
			else if (dna.isEmpty())
			{
				out = spaces + "E";
				System.out.println(out);
			}
			else if (dna.isInternal())
			{
				out = spaces + "I";
				System.out.println(out);
			}
		}
	}
}