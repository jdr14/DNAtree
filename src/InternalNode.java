/**
 * InternalNode class that describes functionality of the internal nodes
 * @author Jovany Cabrera jovanyc4
 * @author Joey Rodgers jdr14
 * @version 2.0
 */
public class InternalNode extends Node
{
	/**
	 * 
	 */
	private Node aChild;
	
	/**
	 * 
	 */
    private Node cChild;
    
    /**
     * 
     */
    private Node gChild;
    
    /**
     * 
     */
    private Node tChild;
    
    /**
     * 
     */
    private Node dChild;
    
    /**
     *  default constructor
     */
	public InternalNode() 
	{
		super(0);
		aChild = new FlyWeightNode(1);
		cChild = new FlyWeightNode(1);
		gChild = new FlyWeightNode(1);
		tChild = new FlyWeightNode(1);
		dChild = new FlyWeightNode(1);
		hasInfo = false;
		isInternal = true;
	}
	
	/**
	 * constructor to set depth
	 * @param d depth as an integer
	 */
	public InternalNode(int d)
	{
		super(d);
		aChild = new FlyWeightNode(d + 1);
		cChild = new FlyWeightNode(d + 1);
		gChild = new FlyWeightNode(d + 1);
		tChild = new FlyWeightNode(d + 1);
		dChild = new FlyWeightNode(d + 1);
		hasInfo = false;
		isInternal = true;
	}
    
	/**
	 * 
	 * @param a of generic node
	 */
	public void setAChild(Node a)
	{
		aChild = a;
	}
	
	/**
	 * 
	 * @param c of generic node
	 */
	public void setCChild(Node c)
	{
		cChild = c;
	}
	
	/**
	 * 
	 * @param g of generic node
	 */
	public void setGChild(Node g)
	{
		gChild = g;
	}
	
	/**
	 * 
	 * @param t of generic node
	 */
	public void setTChild(Node t)
	{
		tChild = t;
	}
	
	/**
	 * 
	 * @param d of generic node
	 */
	public void setdChild(Node d)
	{
		dChild = d;
	}
	
	/**
	 * 
	 * @return the a child node
	 */
	public Node getAChild()
	{
		return aChild;
	}
	
	/**
	 * 
	 * @return the c child node
	 */
	public Node getCChild()
	{
		return cChild;
	}
	
	/**
	 * 
	 * @return the g child node
	 */
	public Node getGChild()
	{
		return gChild;
	}
	
	/**
	 * 
	 * @return the t child node
	 */
	public Node getTChild()
	{
		return tChild;
	}
	
	/**
	 * 
	 * @return the $ child node
	 */
	public Node getdChild()
	{
		return dChild;
	}
	
	/**
	 * @param newSeq as sequence to insert
	 * @return Node as a reference
	 */
	public Node insert(String newSeq)
	{
		// case where insert at $ branch
		if (this.depth >= newSeq.length())
		{
			dChild = dChild.insert(newSeq);
		}
		// case where more traversal is needed
		else
		{
			Character currentChar = newSeq.charAt(depth);
			if (currentChar.equals('A'))
			{    // go down the A branch
				aChild = this.aChild.insert(newSeq);
			}
			else if (currentChar.equals('C'))
			{    // go down the C branch
				cChild = this.cChild.insert(newSeq);
			}
			else if (currentChar.equals('G'))
			{    // go down the G branch
				gChild = this.gChild.insert(newSeq);
			}
			else if (currentChar.equals('T'))
			{    // go down the T branch
				tChild = this.tChild.insert(newSeq);
			}
		}
		return this;
	}
	
	/**
	 * @param oldSeq as sequence to remove
	 * @return Node as a reference
	 */
	public Node remove(String oldSeq)
	{
		// check case where need to remove $ child
		if (this.depth >= oldSeq.length())
		{
			dChild = dChild.remove(oldSeq);
		}
		// case where more traversal is needed
		else
		{
			Character currentChar = oldSeq.charAt(depth);
			if (currentChar.equals('A'))
			{    // go down the A branch
				aChild = this.aChild.remove(oldSeq);
			}
			else if (currentChar.equals('C'))
			{    // go down the C branch
				cChild = this.cChild.remove(oldSeq);
			}
			else if (currentChar.equals('G'))
			{    // go down the G branch
				gChild = this.gChild.remove(oldSeq);
			}
			else if (currentChar.equals('T'))
			{    // go down the T branch
				tChild = this.tChild.remove(oldSeq);
			}
		}
		// MUST HAVE CHECK TO SEE IF THIS CHECK IS NECESSARY
		// check if node has any more children
		if (this.hasChildren() == 1)
		{
			int checkThis = this.whichChild();
			if (checkThis == 1)
			{
				this.aChild.depth--;
				return this.aChild;
			}
			else if (checkThis == 2)
			{
				this.cChild.depth--;
				return this.cChild;
			}
			else if (checkThis == 3)
			{
				this.gChild.depth--;
				return this.gChild;
			}
			else if (checkThis == 4)
			{
				this.tChild.depth--;
				return this.tChild;
			}
			else if (checkThis == 5)
			{
				this.dChild.depth--;
				return this.dChild;
			}
		}
		return this;
	}
	
	/**
	 * 
	 * @return true if any child is a Leaf node
	 */
    private int hasChildren()
	{
		int result = 0;
		if ((aChild.hasInfo) || (aChild.isInternal))
		{
			result++;
		}
		if ((cChild.hasInfo) || (cChild.isInternal))
		{
			result++;
		}
		if ((gChild.hasInfo) || (gChild.isInternal))
		{
			result++;
		}
		if ((tChild.hasInfo) || (tChild.isInternal))
		{
			result++;
		}
		if ((dChild.hasInfo) || (dChild.isInternal))
		{
			result++;
		}
		return result;
	}
	
	/**
	 * 
	 * @return enum that tells which child is Leaf
	 */
    private int whichChild()
	{
		int result = 0;
		if (aChild.hasInfo)
		{
			result++;
		}
		else if (cChild.hasInfo)
		{
			result = 2;
		}
		else if (gChild.hasInfo)
		{
			result = 3;
		}
		else if (tChild.hasInfo)
		{
			result = 4;
		}
		else if (dChild.hasInfo)
		{
			result = 5;
		}
		return result;
	}
	
	/**
	 * @param newSeq as sequence to search for
	 */
    public void search(String newSeq)
	{
		if (this.depth >= (newSeq.length() - 1))
		{
			this.dChild.search(newSeq);
		}
		else
		{
			Character currentChar = newSeq.charAt(depth);
			if (currentChar.equals('A'))
			{    // go down the A branch
				this.aChild.search(newSeq);
			}
			else if (currentChar.equals('C'))
			{    // go down the C branch
				this.cChild.search(newSeq);
			}
			else if (currentChar.equals('G'))
			{    // go down the G branch
				this.gChild.search(newSeq);
			}
			else if (currentChar.equals('T'))
			{    // go down the T branch
				this.tChild.search(newSeq);
			}
		}
	}
}
