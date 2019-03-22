import java.io.*;
import java.util.*;

/**
 * Main/Central class containing the main loop
 * @author Joey Rogders jdr14
 * @author Jovany Cabrera jovanyc4
 * @version 1.0.0
 */
@SuppressWarnings("unused")
public class DNAtree {
    
	/**
	 * 
	 * @param msg
	 * @param quiet
	 */
	public static void print(String msg, boolean quiet)
	{
		if(!quiet)
		{
			System.out.println(msg);
		}
	}
	
	/**
	 * 
	 * @param dnaList
	 * @return
	 */
    private static List<String> runInstructions(List<DNAType> dnaList)
    {
    	
    	Dna tree = new Dna();
    	for (int i = 0; i < dnaList.size(); i++)
    	{
    		Node<DNAType> temp = new Node<DNAType>(dnaList.get(i));
    		setChildrenEmpty(temp);
    		if(temp.getValue().getCommand().equalsIgnoreCase("insert"))
    		{
    			tree.insert(temp);    // call insert method
    		}
    		else if(temp.getValue().getCommand().equalsIgnoreCase("remove"))
    		{
    			tree.remove(temp);    // call remove function
    		}
    		else if(temp.getValue().getCommand().equalsIgnoreCase("print"))
    		{
    			if(temp.getValue().getSequence().equalsIgnoreCase("lengths"))
    			{
    				// print lengths of tree
    			}
    			else if(temp.getValue().getSequence().equalsIgnoreCase("stats"))
    			{
    				// print stats of tree
    			}
    			else
    			{
    				// print entire tree
    			}
    		}
    		else if(temp.getValue().getCommand().equalsIgnoreCase("search"))
    		{
    			if(tree.getCount() > 0)
    			{
        			Node<DNAType> result = new Node<DNAType>();
        			tree.search(temp);  // call search function
        			setChildrenEmpty(result);
    			}
    		}
    		
    	}
    	
    	return new ArrayList<String>();
    }
    
    /**
	 * 
	 * @param node
	 */
	private static void setChildrenEmpty(Node<DNAType> node)
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
     * Main loop for the DNA Node Tree project (i.e. project 2)
     * Ties the project functionality together
     * @author: Joey Rogders jdr14
     * @author: Jovany Cabrera jovanyc4
     * @version 1.0.0
     * @param args
     */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		boolean isQuiet = true;
		
		if (args.length != 1)
		{
			System.out.println("Failed because of an incorrect "
					+ "amount of arguments");
			System.exit(0);
		}
		
        DNAParse p = new DNAParse(args[0]);  // create a new parser object
        
        List<DNAType> structuredInput = p.parseMain();
        
        for (int i = 0; i < structuredInput.size(); i++)
        {
        	DNAType temp = structuredInput.get(i);
        	
        	print("Line: " + i, isQuiet);
        	print(temp.getCommand(), isQuiet);
        	print(temp.getSequence(), isQuiet);
        }
        
        List<String> results = runInstructions(structuredInput);
        
	}  // End main
} 
