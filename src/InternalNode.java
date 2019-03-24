
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
	}
	
	public InternalNode(int d)
	{
		super(d);
		aChild = new FlyWeightNode(d+1);
		cChild = new FlyWeightNode(d+1);
		gChild = new FlyWeightNode(d+1);
		tChild = new FlyWeightNode(d+1);
		$Child = new FlyWeightNode(d+1);
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
	}
	
	public InternalNode(Node n)
	{
		super(n.depth);
		
		aChild = new FlyWeightNode(n.depth+1);
		cChild = new FlyWeightNode(n.depth+1);
		gChild = new FlyWeightNode(n.depth+1);
		tChild = new FlyWeightNode(n.depth+1);
		$Child = new FlyWeightNode(n.depth+1);
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
	public void remove (String oldSeq)
	{
		
	}
	
	/**
	 * @param newSeq as sequence to search for
	 */
	public void search (String newSeq)
	{
		
	}
}
