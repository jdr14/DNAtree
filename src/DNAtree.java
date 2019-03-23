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
	
	private static boolean isQuiet = true;
    
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
    private static List<String> 
    runInstructions(List<Pair<String, String>> dnaList)
    {	
    	Dna tree = new Dna();
    	for (int i = 0; i < dnaList.size(); i++)
    	{
    		String command = dnaList.get(i).getKey();
    		String sequence = dnaList.get(i).getValue();
    		
    		if(command.equalsIgnoreCase("insert"))
    		{
    			tree.insert(sequence);    // call insert method
    		}
//    		else if (command.equalsIgnoreCase("remove"))
//    		{
//    			if(tree.getCount() > 0)
//    			{
//    				tree.remove(sequence);    // call remove function
//    			}
//    		}
//    		else if (command.equalsIgnoreCase("print"))
//    		{
//    			// Case: singular print command passed in
//    			if (sequence.isEmpty())
//    			{
//    				tree.print(PrintOptions.DEFAULT);
//    			}
//    			else if (sequence.equalsIgnoreCase("lengths"))
//    			{
//    				tree.print(PrintOptions.LENGTHS);
//    			}
//    			else if (sequence.equalsIgnoreCase("stats"))
//    			{
//    				tree.print(PrintOptions.STATS);
//    			}
//    		}
//    		else if (command.equalsIgnoreCase("search"))
//    		{
//    			if (tree.getCount() > 0)
//    			{ 
//    				if (sequence.endsWith("$"))
//    				{
//            			//Node<DNAType> result = new Node<DNAType>();
//            			tree.search(sequence);  // call search function
//            			//setChildrenEmpty(result);
//    				}
//    				else
//    				{
//    				    Pair<Integer, List<LeafNode>> prefixResults = 
//    				    		tree.searchByPrefix(sequence);
//    				    
//    				    // Finally, print the results
//    				    System.out.println("# of nodes visited: " + 
//    				    		prefixResults.getKey());
//    				    
//    				    List<LeafNode> listN = prefixResults.getValue();
//    				    
//    				    if (listN.isEmpty())
//    				    {
//    				    	System.out.println("no sequence found");
//    				    }
//    				    else
//    				    {
//    				    	for (int j = 0; j < listN.size(); j++)
//    				    	{
//    				    		if (listN.get(j).getSequence() != null &&
//    				    				!listN.get(j).getSequence().isEmpty())
//    				    		{
//        				    		System.out.println("sequence: " + 
//            				    	        listN.get(j).getSequence());
//    				    		}
//    				    	}
//    				    }
//    				}
//    			}
//    			else
//    			{
//    				System.out.println("Tree is empty");
//    			}
//    		}
    		
    	}
    	
    	return new ArrayList<String>();
    }
    
    /**
	 * 
	 * @param node
	 */
	/*private static void setChildrenEmpty(Node<DNAType> node)
	{
		DNAType innerEmplaceThis = new DNAType(Types.EMPTY, null, null);
		Node<DNAType> emplaceThis = new Node<DNAType>(innerEmplaceThis);
		node.setAChild(emplaceThis);
		node.aChild().setDepth(node.getDepth()+1);
		node.setCChild(emplaceThis);
		node.cChild().setDepth(node.getDepth()+1);
		node.setGChild(emplaceThis);
		node.gChild().setDepth(node.getDepth()+1);
		node.setTChild(emplaceThis);
		node.tChild().setDepth(node.getDepth()+1);
		node.set$Child(emplaceThis);
		node.$Child().setDepth(node.getDepth()+1);
	}*/
	
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
		
//		boolean isQuiet = false;
		
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
