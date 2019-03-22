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
	
	private static boolean isQuiet = false;
    
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
    		if (temp.getValue().getCommand().equalsIgnoreCase("insert"))
    		{
    			tree.insert(temp);    // call insert method
    		}
    		else if (temp.getValue().getCommand().equalsIgnoreCase("remove"))
    		{
    			tree.remove(temp);    // call remove function
    		}
    		else if (temp.getValue().getCommand().equalsIgnoreCase("print"))
    		{
    			// Case: singular print command passed in
    			if (temp.getValue().getSequence() == null)
    			{
    				tree.print(PrintOptions.DEFAULT);
    			}
    			else if (temp.getValue().getSequence().equalsIgnoreCase("lengths"))
    			{
    				tree.print(PrintOptions.LENGTHS);
    			}
    			else if (temp.getValue().getSequence().equalsIgnoreCase("stats"))
    			{
    				tree.print(PrintOptions.STATS);
    			}
    		}
    		else if (temp.getValue().getCommand().equalsIgnoreCase("search"))
    		{
    			if (tree.getCount() > 0)
    			{ 
    				String s = temp.getValue().getSequence();
    				if (s.endsWith("$"))
    				{
            			Node<DNAType> result = new Node<DNAType>();
            			tree.search(temp);  // call search function
            			setChildrenEmpty(result);
    				}
    				else
    				{
    				    Pair<Integer, List<DNAType>> prefixResults = 
    				    		tree.searchByPrefix(s);
    				    
    				    // Finally, print the results
    				    System.out.println("# of nodes visited: " + 
    				    		prefixResults.getKey());
    				    
    				    if (prefixResults.getValue().isEmpty())
    				    {
    				    	System.out.println("no sequence found");
    				    }
    				    else
    				    {
    				    	for (int j = 0; 
    				    			j < prefixResults.getValue().size(); j++)
    				    	{
    				    		System.out.println("sequence: " + 
    				    	prefixResults.getValue().get(j).getSequence());
    				    	}
    				    }
    				}
    			}
    			else
    			{
    				System.out.println("Tree is empty");
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
		
		//boolean isQuiet = false;
		
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
