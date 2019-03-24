
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
    
    private Node gChild;
    
    private Node tChild;
    
    private Node $Child;
    
	public InternalNode() 
	{
		super(0);
		aChild = new FlyWeightNode(1);
		cChild = new FlyWeightNode(1);
		gChild = new FlyWeightNode(1);
		tChild = new FlyWeightNode(1);
		$Child = new FlyWeightNode(1);
		hasInfo = false;
	}
	
	public InternalNode(int d)
	{
		super(d);
		aChild = new FlyWeightNode(d+1);
		cChild = new FlyWeightNode(d+1);
		gChild = new FlyWeightNode(d+1);
		tChild = new FlyWeightNode(d+1);
		$Child = new FlyWeightNode(d+1);
		hasInfo = false;
	}
	
	public InternalNode(int d, Node a, 
			Node c, Node g, Node t, Node $) 
	{
		super(d);
		aChild = a;
		cChild = c;
		gChild = g;
		tChild = t;
		$Child = $;
		hasInfo = false;
	}
	
	public InternalNode(Node n)
	{
		super(n.depth);
		
		aChild = new FlyWeightNode(n.depth+1);
		cChild = new FlyWeightNode(n.depth+1);
		gChild = new FlyWeightNode(n.depth+1);
		tChild = new FlyWeightNode(n.depth+1);
		$Child = new FlyWeightNode(n.depth+1);
		hasInfo = false;
	}
    
	public void setAChild(Node a)
	{
		aChild = a;
	}
	
	public void setCChild(Node c)
	{
		cChild = c;
	}
	
	public void setGChild(Node g)
	{
		gChild = g;
	}
	
	public void setTChild(Node t)
	{
		tChild = t;
	}
	
	public void set$Child(Node $)
	{
		$Child = $;
	}
	
	public Node getAChild()
	{
		return aChild;
	}
	
	public Node getCChild()
	{
		return cChild;
	}
	
	public Node getGChild()
	{
		return gChild;
	}
	
	public Node getTChild()
	{
		return tChild;
	}
	
	public Node get$Child()
	{
		return $Child;
	}
	
	/**
	 * @param newSeq as sequence to insert
	 */
	public Node insert (String newSeq)
	{
		// case where insert at $ branch
		if (this.depth >= newSeq.length())
		{
			$Child = $Child.insert(newSeq);
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
	 */
	public Node remove (String oldSeq)
	{
		// check case where need to remove $ child
		if (this.depth >= oldSeq.length())
		{
			$Child = $Child.remove(oldSeq);
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
				return this.aChild;
			}
			else if (checkThis == 2)
			{
				return this.cChild;
			}
			else if (checkThis == 3)
			{
				return this.gChild;
			}
			else if (checkThis == 4)
			{
				return this.tChild;
			}
			else if (checkThis == 5)
			{
				return this.$Child;
			}
		}
		return this;
	}
	
	/**
	 * 
	 * @return true if any child is a Leaf node
	 */
	private int hasChildren ()
	{
		int result = 0;
		if (aChild.hasInfo)
		{
			result++;
		}
		if (cChild.hasInfo)
		{
			result++;
		}
		if (gChild.hasInfo)
		{
			result++;
		}
		if (tChild.hasInfo)
		{
			result++;
		}
		if ($Child.hasInfo)
		{
			result++;
		}
		return result;
	}
	
	/**
	 * 
	 * @return enum that tells which child is Leaf
	 */
	private int whichChild ()
	{
		int result = 0;
		if (aChild.hasInfo)
		{
			result++;
		}
		else if (cChild.hasInfo)
		{
			result++;
		}
		else if (gChild.hasInfo)
		{
			result++;
		}
		else if (tChild.hasInfo)
		{
			result++;
		}
		else if ($Child.hasInfo)
		{
			result++;
		}
		return result;
	}
	
	/**
	 * @param newSeq as sequence to search for
	 */
	public void search (String newSeq)
	{
		
	}
}
