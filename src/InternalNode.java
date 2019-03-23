
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
		super(0, Types.INTERNAL);
		aChild = new FlyWeightNode(1);
		cChild = new FlyWeightNode(1);
		gChild = new FlyWeightNode(1);
		tChild = new FlyWeightNode(1);
		$Child = new FlyWeightNode(1);
	}
	
	public InternalNode(int d)
	{
		super(d, Types.INTERNAL);
		aChild = new FlyWeightNode(d+1);
		cChild = new FlyWeightNode(d+1);
		gChild = new FlyWeightNode(d+1);
		tChild = new FlyWeightNode(d+1);
		$Child = new FlyWeightNode(d+1);
	}
	
	public InternalNode(int d, Node a, 
			Node c, Node g, Node t, Node $) 
	{
		super(d, Types.INTERNAL);
		aChild = a;
		cChild = c;
		gChild = g;
		tChild = t;
		$Child = $;
	}
	
	public InternalNode(Node n)
	{
		super(n.depth, Types.INTERNAL);
		
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
}
