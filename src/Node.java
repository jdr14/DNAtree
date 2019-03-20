import java.io.*;

/**
 * 
 * @author Jovany Cabrera
 * @author Joey Rodgers
 * @param <V> is the value of the node
 * @verion 1
 *
 */
public class Node<V> {
	
	/**
	 * Generic type for V value
	 */
	private V value;
	
	/**
	 * Node for branch A
	 */
	private Node<V> aChild;
	
	/**
	 * Node for branch C
	 */
	private Node<V> cChild;
	
	/**
	 * Node for branch G
	 */
	private Node<V> gChild;
	
	/**
	 * Node for branch T
	 */
	private Node<V> tChild;
	
	/**
	 * Node for branch $
	 */
	private Node<V> $Child;
	
	/**
	 * Depth of node
	 */
	private int depth = 0;
	
    /**
     * Provides a default constructor that sets everything to null
     */
	public Node()
	{
		this.value = null;
		this.aChild = null;
		this.cChild = null;
		this.gChild = null;
		this.tChild = null;
		this.$Child = null;
	}
	
	/**
	 * Provide an overload constructor that sets the value
	 * Everything else is set to null
	 * @param newValue of type V
	 */
	public Node(V newValue)
	{
		this.value = newValue;
		this.aChild = null;
		this.cChild = null;
		this.gChild = null;
		this.tChild = null;
		this.$Child = null;
	}
	
	/**
	 * 
	 * @param n
	 */
	public Node(Node<V> n)
	{
		this.value = n.getValue();
		this.aChild = n.aChild();
		this.cChild = n.cChild();
		this.gChild = n.gChild();
		this.tChild = n.tChild();
		this.$Child = n.$Child();
	}
	
	/**
	 * @param x of type Value
	 */
	public void setValue(V x)
	{
		this.value = x;
	}
	
	/**
	 * @param x of type integer
	 */
	public void setDepth(int x)
	{
		this.depth = x;
	}
	
	/**
	 * @param rt Node<V> for the a child
	 */
	public void setAChild(Node<V> rt)
	{
		this.aChild = rt;
	}
	
	/**
	 * @param rt Node<V> for the c Child
	 */
	public void setCChild(Node<V> rt)
	{
		this.cChild = rt;
	}
	
	/**
	 * @param rt Node<V> for the g Child
	 */
	public void setGChild(Node<V> rt)
	{
		this.gChild = rt;
	}
	
	/**
	 * @param rt Node<V> for the t Child
	 */
	public void setTChild(Node<V> rt)
	{
		this.tChild = rt;
	}
	
	/**
	 * @param rt Node<V> for the $ Child
	 */
	public void set$Child(Node<V> rt)
	{
		this.$Child = rt;
	}
	
	/**
	 * @return Value value
	 */
	public V getValue()
	{
		return this.value;
	}
	
	/**
	 * @return int the depth
	 */
	public int getDepth()
	{
		return this.depth;
	}
	
	/**
	 * @return Node<V> for the a Child
	 */
	public Node<V> aChild()
	{
		return this.aChild;
	}
	
	/**
	 * @return Node<V> for the c Child
	 */
	public Node<V> cChild()
	{
		return this.cChild;
	}
	
	/**
	 * @return Node<V> for the g Child
	 */
	public Node<V> gChild()
	{
		return this.gChild;
	}
	
	/**
	 * @return Node<V> for the t Child
	 */
	public Node<V> tChild()
	{
		return this.tChild;
	}
	
	/**
	 * @return Node<V> for the $ Child
	 */
	public Node<V> $Child()
	{
		return this.$Child;
	}
}