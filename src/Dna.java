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
				root = node;
//				count++;
			}
			else
			{
				if(root.getValue().isDNA())    // case where second node is inserted
				{
					String compareResult = compareSeq(root, node);
					Node<DNAType> rootTemp = root;
					root = new Node<DNAType>(new DNAType(Types.INTERNAL, null, null));
					if (compareResult.equals(""))    // sequences have nothing in common
					{
						addChildren(root, rootTemp);
						addChildren(root, node);
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
		if(node.getValue().isDNA())
		{
			
		}
	}
	
    /**
     * 
     * @param node of type DNAType
     * @return
     */
	public DNAType search (Node<DNAType> node)
	{
		if(node.getValue().isDNA())
		{
			
		}
		return null;
		
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
			if(temp.equals('A'))
			{
				rt.setAChild(newInternal);
			}
			else if (temp.equals('C'))
			{
				rt.setCChild(newInternal);
			}
			else if(temp.equals('G'))
			{
				rt.setGChild(newInternal);
			}
			else if(temp.equals('T'))
			{
				rt.setTChild(newInternal);
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
					if(rt.aChild() == null)
					{
						rt.setAChild(node);
						return;
					}
					rt = rt.aChild();
				}
				else if(temp.equals('C'))
				{
					if(rt.cChild() == null)
					{
						rt.setCChild(node);
						return;
					}
					rt = rt.cChild();
				}
				else if(temp.equals('G'))
				{
					if(rt.gChild() == null)
					{
						rt.setGChild(node);
						return;
					}
					rt = rt.gChild();
				}
				else if(temp.equals('T'))
				{
					if(rt.tChild() == null)
					{
						rt.setTChild(node);
						return;
					}
					rt = rt.tChild();
				}
			}
			else if(!rt.getValue().isInternal())
			{
				Node<DNAType> exTend = new Node<DNAType>();
				exTend.setValue(rt.getValue());
				rt.getValue().setType(Types.INTERNAL);
//				rt.getValue().setSequence("");
//				rt.getValue().setCommand("");
				setChildSelect(rt, exTend, exTend.getValue().getSequence().charAt(i+1));
				setChildSelect(rt, node, node.getValue().getSequence().charAt(i+1));
				return;
			}
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
	}
	
}