import java.io.*;
import java.util.*;

/**
 * Main/Central class containing the main loop
 * @author Joey Rogders jdr14
 * @author Jovany Cabrera jovanyc4
 * @version 1.0.0
 */
@SuppressWarnings("unused")
public class DNAtree 
{
    /**
	 * Switch variable for the local print function
	 */
	private static boolean isQuiet = true;
    
	/**
	 * 
	 * @param msg the message as a string to be printed
	 * @param quiet the variable that will determine if the message gets
	 * printed or not
	 */
	public static void print(String msg, boolean quiet)
	{
		if (!quiet)
		{
			System.out.println(msg);
		}
	}
	
	/**
	 * 
	 * @param dnaList
	 * @return
	 */
    private static List<String> 
        runInstructions(List<Pair<String, String>> dnaList)
    {	
    	Dna tree = new Dna();
    	for (int i = 0; i < dnaList.size(); i++)
    	{
    		String command = dnaList.get(i).getKey();
    		String sequence = dnaList.get(i).getValue();
    		
    		if (command.equalsIgnoreCase("insert"))
    		{
    			tree.insert(sequence);    // call insert method
    		}
    		else if (command.equalsIgnoreCase("remove"))
    		{
				tree.remove(sequence);    // call remove function
    		}
    		else if (command.equalsIgnoreCase("print"))
    		{
    			System.out.println("tree dump:");
    			// Case: singular print command passed in
    			if (sequence.isEmpty())
    			{
    				tree.print(PrintOptions.DEFAULT);
    			}
    			else if (sequence.equalsIgnoreCase("lengths"))
    			{
    				tree.print(PrintOptions.LENGTHS);
    			}
    			else if (sequence.equalsIgnoreCase("stats"))
    			{
    				tree.print(PrintOptions.STATS);
    			}
    		}
    		else if (command.equalsIgnoreCase("search"))
    		{
    			if (sequence.endsWith("$"))
    			{
            		//Node<DNAType> result = new Node<DNAType>();
            		tree.search(sequence);  // call search function
            		//setChildrenEmpty(result);
    			}
    			else
    			{
    			    Pair<Integer, List<String>> prefixResults = 
    			    		tree.searchByPrefix(sequence);
    			    
    			    // Finally, print the results
    			    System.out.println("# of nodes visited: " + 
    			    		prefixResults.getKey());
    			    
    			    List<String> listN = prefixResults.getValue();
    			    
    			    if (listN.isEmpty())
    			    {
    			    	System.out.println("no sequence found");
    			    }
    			    else
    			    {
    			    	for (int j = 0; j < listN.size(); j++)
    			    	{
        			    	System.out.println("sequence: " + listN.get(j));
    			    	}
    			    }
    			}
    		}
    		
    	}
    	return new ArrayList<String>();
    }
    
    /**
     * Main loop for the DNA Node Tree project (i.e. project 2)
     * Ties the project functionality together
     * @author: Joey Rogders jdr14
     * @author: Jovany Cabrera jovanyc4
     * @version 1.0.0
     * @param args should contain an appropriate file name for testing
     */
	public static void main(String[] args) 
	{	
		if (args.length != 1)
		{
			System.out.println("Failed because of an incorrect "
					+ "amount of arguments");
			System.exit(0);
		}
		
        DNAParse p = new DNAParse(args[0]);  // create a new parser object
        
        List<Pair<String, String>> structuredInput = p.parseMain();
        
        for (int i = 0; i < structuredInput.size(); i++)
        {
        	Pair<String, String> temp = structuredInput.get(i);
        	
        	print("Line: " + i, isQuiet);
        	print(temp.getKey() + "  " + temp.getValue(), isQuiet);
        }
        
        List<String> results = runInstructions(structuredInput);
        
	}  // End main
} 
