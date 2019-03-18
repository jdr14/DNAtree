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
	 * Root node
	 */
	private Node<V> root;
	
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
	 * @param x of type Value
	 */
	public void setValue(V x)
	{
		value = x;
	}
	
	/**
	 * @param x of type integer
	 */
	public void setDepth(int x)
	{
		depth = x;
	}
	
	/**
	 * @param rt Node<V> for the root
	 */
	public void setRoot(Node<V> rt)
	{
		root = rt;
	}
	
	/**
	 * @param rt Node<V> for the a child
	 */
	public void setAChild(Node<V> rt)
	{
		aChild = rt;
	}
	
	/**
	 * @param rt Node<V> for the c Child
	 */
	public void setCChild(Node<V> rt)
	{
		cChild = rt;
	}
	
	/**
	 * @param rt Node<V> for the g Child
	 */
	public void setGChild(Node<V> rt)
	{
		gChild = rt;
	}
	
	/**
	 * @param rt Node<V> for the t Child
	 */
	public void setTChild(Node<V> rt)
	{
		tChild = rt;
	}
	
	/**
	 * @param rt Node<V> for the $ Child
	 */
	public void set$Child(Node<V> rt)
	{
		$Child = rt;
	}
	
	/**
	 * @return Value value
	 */
	public V getValue()
	{
		return value;
	}
	
	/**
	 * @return int the depth
	 */
	public int getDepth()
	{
		return depth;
	}
	
	/**
	 * @return Node<V> for the root
	 */
	public Node<V> root()
	{
		return root;
	}
	
	/**
	 * @return Node<V> for the a Child
	 */
	public Node<V> aChild()
	{
		return aChild;
	}
	
	/**
	 * @return Node<V> for the c Child
	 */
	public Node<V> cChild()
	{
		return cChild;
	}
	
	/**
	 * @return Node<V> for the g Child
	 */
	public Node<V> gChild()
	{
		return gChild;
	}
	
	/**
	 * @return Node<V> for the t Child
	 */
	public Node<V> tChild()
	{
		return tChild;
	}
	
	/**
	 * @return Node<V> for the $ Child
	 */
	public Node<V> $Child()
	{
		return $Child;
	}
}