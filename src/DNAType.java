import java.util.*;

/**
 * Defines the interface from which the Empty, Internal, and 
 * DNAType classes should inherit.   
 * @author Joey Rodgers
 * @version 1.0.0
 * @param <E> Should be an ENUM consisting of types relevant to the DNATree
 */
interface DNANodeTypes<E>
{
	/**
	 * Abstract method to determine the type of node
	 * @return type E = type of node
	 */
	public E getType();
}

/**
 * Defines the class that outlines the EmptyType for the DNA tree nodes
 * @author Joey Rodgers
 * @version 1.0.0
 * @param <E> Should be an ENUM consisting of types relevant to the DNATree
 */
class EmptyType<E> implements DNANodeTypes<E>
{
	/**
	 * Generic type E for the type
	 * Should be an enum containing the list of types
	 */
	private E type;
	
	/**
	 * Accessor method to return the type 
	 * @return returns the data type (meant for the node)
	 */
	public E getType()
	{
		return type;
	}
}

/**
 * Defines the class that outlines the InternalType for the DNA tree nodes
 * @author Joey Rodgers
 * @version 1.0.0
 * @param <E> Should be an ENUM consisting of types relevant to the DNATree
 */
class InternalType<E> implements DNANodeTypes<E>
{
	/**
	 * Generic type E for the type 
	 * Should be an enum containing the list of types
	 */
	private E type;
	
	/**
	 * Accessor method to return the type 
	 * @return returns the data type (meant for the node)
	 */
	public E getType()
	{
		return type;
	}
}

/**
 * Defines the class that oulines the DNAType to actually contain the 
 * data to be used and stored in the DNA Node Tree
 * @author joeyrodgers
 * @version 1.0.0
 * @param <E> Should be an ENUM consisting of types relevant to the DNATree
 */
class DNAType<E> implements DNANodeTypes<E>
{
	/**
	 * Generic type E for the type 
	 * Should be an enum containing the list of types
	 */
	private E type;
	
	/**
	 * Accessor method to return the type 
	 * @return returns the data type (meant for the node)
	 */
	public E getType()
	{
		return type;
	}
}




