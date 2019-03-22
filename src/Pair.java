
public class Pair<K, V> 
{
	/**
	 * key to be paired with the value
	 */
    private K key;
    
    /**
     * Value paired with the key
     */
    private V value;
    
    /**
     * Default constructor
     */
    public Pair()
    {
    	key = null;
    	value = null;
    }
    
    /**
     * Parameterized constructor
     * @param k
     * @param val
     */
    public Pair(K k, V val)
    {
    	key = k;
    	value = val;
    }
    
    /**
     * Method to set the key
     * @param k
     */
    public void setKey(K k)
    {
    	key = k;
    }
    
    /**
     * Method to set the value
     * @param v
     */
    public void setValue(V v)
    {
    	value = v;
    }
    
    /**
     * Method to get the key
     * @return
     */
    public K getKey()
    {
    	return key;
    }
    
    /**
     * Method to get the value
     * @return
     */
    public V getValue()
    {
    	return value;
    }
}
