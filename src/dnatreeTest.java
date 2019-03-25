//import java.io.ByteArrayOutputStream;
//import java.io.PrintStream;
import java.util.*;

import student.TestCase;

/**
 * 
 * @author Jovany Cabrera jovanyc4
 * @author Joey Destin Rodgers jdr14
 * @version 2.1.3
 *
 */
public class dnatreeTest extends TestCase
{
    private Dna testTree1;
    
    @Override
    /**
     * Runs before each test case.  Set up the BST to be used in all tests
     */
    public void setUp() 
    {   
        // Set up first using default constructors
        testTree1 = new Dna();
    }
    
    /**
     * test function for DNAParse
     */
    public void testDNAParse()
    {
        DNAParse p = new DNAParse("test2.txt");
        List<Pair<String, String>> listedIn = p.parseMain();
        
        assertEquals(listedIn.get(0).getKey(), "insert");
        assertEquals(listedIn.get(0).getValue(), "ACGT");
        assertEquals(listedIn.get(1).getKey(), "print");
        assertEquals(listedIn.get(1).getValue().isEmpty(), true);
        assertEquals(listedIn.get(2).getKey(), "print");
        assertEquals(listedIn.get(2).getValue(), "lengths");
        assertEquals(listedIn.get(3).getKey(), "print");
        assertEquals(listedIn.get(3).getValue(), "stats");
        assertEquals(listedIn.get(4).getKey(), "search");
        assertEquals(listedIn.get(4).getValue(), "ACGT");
        assertEquals(listedIn.get(5).getKey(), "remove");
        assertEquals(listedIn.get(5).getValue(), "ACGT");
        assertEquals(listedIn.get(6).getKey(), "invalid");
        assertEquals(listedIn.get(6).getValue(), "command");
    }
    
    /**
     * test function for print options
     */
    public void testPrintOptions()
    {
        PrintOptions p;
        p = PrintOptions.DEFAULT;
        assertEquals(p, PrintOptions.DEFAULT);
        p = PrintOptions.LENGTHS;
        assertEquals(p, PrintOptions.LENGTHS);
        p = PrintOptions.STATS;
        assertEquals(p, PrintOptions.STATS);
    }
    
    /**
     * test function for percentage type
     */
    public void testPercentageType()
    {
        String s1 = "ACGT$";
        String s2 = "AACCT";
        PercentageType pt1 = new PercentageType();
        PercentageType pt2 = new PercentageType(s2);
        
        // Test Default constructor first
        assertEquals(pt1.getSequence().isEmpty(), true);
        assertEquals(pt1.getPercentA(), 0.0, 0.0);
        assertEquals(pt1.getPercentC(), 0.0, 0.0);
        assertEquals(pt1.getPercentG(), 0.0, 0.0);
        assertEquals(pt1.getPercentT(), 0.0, 0.0);
        
        // Test the setSequence method
        pt1.setSequence(s1);
        assertEquals(pt1.getSequence().isEmpty(), false);
        assertEquals(pt1.getSequence(), "ACGT$");
        assertEquals(pt1.getPercentA(), 25.0, 0.00);
        assertEquals(pt1.getPercentC(), 25.0, 0.00);
        assertEquals(pt1.getPercentG(), 25.0, 0.00);
        assertEquals(pt1.getPercentT(), 25.0, 0.00);
        
        // Test the 2nd constructor
        assertEquals(pt2.getSequence().isEmpty(), false);
        assertEquals(pt2.getSequence(), "AACCT");
        assertEquals(pt2.getPercentA(), 40.0, 0.00);
        assertEquals(pt2.getPercentC(), 40.0, 0.00);
        assertEquals(pt2.getPercentG(), 0.00, 0.00);
        assertEquals(pt2.getPercentT(), 20.0, 0.00);
    }
    
    /**
     * test function for pair
     */
    public void testPair()
    {
        Pair<Integer, String> p = new Pair<Integer, String>();
        Pair<Integer, String> p2 = new Pair<Integer, String>(2, "test");
        
        p.setKey(1);
        p.setValue("testing");
        
        int i = p.getKey();
        String j = p.getValue();
        
        assertEquals(i, 1);
        assertEquals(j, "testing");
        
        i = p2.getKey();
        j = p2.getValue();
        assertEquals(i, 2);
        assertEquals(j, "test");
    }
    
    // Use this to test the Dna.java class containing the tree methods
    /**
     * test function for DNA
     */
    public void testDna()
    {   
        // Test tree created using default constructor
        assertEquals(testTree1.getCount(), 0);
        assertEquals(testTree1.getRoot().isInternal, false);
    }
    
    /**
     * Test the tree insert method
     */
    public void testInsert() 
    {
        // Test insert at root node
        testTree1.insert("ACGT");
        assertEquals(testTree1.getCount(), 1);
        assertEquals(testTree1.getRoot().hasInfo, true);
        assertEquals(testTree1.getRoot().isInternal, false);
        
        // Test insert node # 2
        testTree1.insert("AAAA");
        assertEquals(testTree1.getCount(), 2);
        assertEquals(testTree1.getRoot().hasInfo, false);
        assertEquals(testTree1.getRoot().isInternal, true);
        
        // Test insert node # 3
        testTree1.insert("AA");
        assertEquals(testTree1.getCount(), 3);
        assertEquals(testTree1.getRoot().hasInfo, false);
        assertEquals(testTree1.getRoot().isInternal, true);
        
        // Test insert node # 4
        testTree1.insert("AAACCCCGGTGAAAACGTA");
        assertEquals(testTree1.getCount(), 4);
        assertEquals(testTree1.getRoot().hasInfo, false);
        assertEquals(testTree1.getRoot().isInternal, true);
        
        // Test insert node # 5
        testTree1.insert("ACTGGGAA");
        assertEquals(testTree1.getCount(), 5);
        assertEquals(testTree1.getRoot().hasInfo, false);
        assertEquals(testTree1.getRoot().isInternal, true);
        
        // Test remove node 'ACGT'
        testTree1.remove("ACGT");
        assertEquals(testTree1.getCount(), 4);
        assertEquals(testTree1.getRoot().hasInfo, false);
        assertEquals(testTree1.getRoot().isInternal, true);
        
        // Test insert node # 5
        testTree1.insert("ACCTT");
        assertEquals(testTree1.getCount(), 5);
        assertEquals(testTree1.getRoot().hasInfo, false);
        assertEquals(testTree1.getRoot().isInternal, true);
        
        // Test insert node # 6
        testTree1.insert("ACTTA");
        assertEquals(testTree1.getCount(), 6);
        assertEquals(testTree1.getRoot().hasInfo, false);
        assertEquals(testTree1.getRoot().isInternal, true);
        
        // Test remove node 'AA'
        testTree1.remove("AA");
        assertEquals(testTree1.getCount(), 5);
        assertEquals(testTree1.getRoot().hasInfo, false);
        assertEquals(testTree1.getRoot().isInternal, true);
        
        // Test insert node # 6
        testTree1.insert("TATA");
        assertEquals(testTree1.getCount(), 6);
        assertEquals(testTree1.getRoot().hasInfo, false);
        assertEquals(testTree1.getRoot().isInternal, true);
        
        // Test insert node # 7
        testTree1.insert("TCG");
        assertEquals(testTree1.getCount(), 7);
        assertEquals(testTree1.getRoot().hasInfo, false);
        assertEquals(testTree1.getRoot().isInternal, true);
        
        // Test insert node # 8
        testTree1.insert("CAT");
        assertEquals(testTree1.getCount(), 8);
        assertEquals(testTree1.getRoot().hasInfo, false);
        assertEquals(testTree1.getRoot().isInternal, true);
        
        // Test insert node # 9
        testTree1.insert("G");
        assertEquals(testTree1.getCount(), 9);
        assertEquals(testTree1.getRoot().hasInfo, false);
        assertEquals(testTree1.getRoot().isInternal, true);
        
        // Test insert node # 10
        testTree1.insert("GAC");
        assertEquals(testTree1.getCount(), 10);
        assertEquals(testTree1.getRoot().hasInfo, false);
        assertEquals(testTree1.getRoot().isInternal, true);
        
        // Test insert node # 11
        testTree1.insert("C");
        assertEquals(testTree1.getCount(), 11);
        assertEquals(testTree1.getRoot().hasInfo, false);
        assertEquals(testTree1.getRoot().isInternal, true);
        
        // Test insert copy node (must not insert/ increment count)
        testTree1.insert("CAT");
        assertEquals(testTree1.getCount(), 11);
        
        // Test insert node # 12
        testTree1.insert("CAAG");
        assertEquals(testTree1.getCount(), 12);
        assertEquals(testTree1.getRoot().hasInfo, false);
        assertEquals(testTree1.getRoot().isInternal, true);
        
        // Test insert node # 13
        testTree1.insert("CAA");
        assertEquals(testTree1.getCount(), 13);
        assertEquals(testTree1.getRoot().hasInfo, false);
        assertEquals(testTree1.getRoot().isInternal, true);
        
        // Test insert node # 14
        testTree1.insert("CAAT");
        assertEquals(testTree1.getCount(), 14);
        assertEquals(testTree1.getRoot().hasInfo, false);
        assertEquals(testTree1.getRoot().isInternal, true);
        
        // Test remove node 'CAAG'
        testTree1.remove("CAAG");
        assertEquals(testTree1.getCount(), 13);
        assertEquals(testTree1.getRoot().hasInfo, false);
        assertEquals(testTree1.getRoot().isInternal, true);
        
        // Test remove node 'TATA'
        testTree1.remove("TATA");
        assertEquals(testTree1.getCount(), 12);
        assertEquals(testTree1.getRoot().hasInfo, false);
        assertEquals(testTree1.getRoot().isInternal, true);
        
        // Test insert node # 13
        testTree1.insert("GAA");
        assertEquals(testTree1.getCount(), 13);
        assertEquals(testTree1.getRoot().hasInfo, false);
        assertEquals(testTree1.getRoot().isInternal, true);
        
        // Test insert node # 14
        testTree1.insert("GACT");
        assertEquals(testTree1.getCount(), 14);
        assertEquals(testTree1.getRoot().hasInfo, false);
        assertEquals(testTree1.getRoot().isInternal, true);
        
        // Test remove node 'GAA'
        testTree1.remove("GAA");
        assertEquals(testTree1.getCount(), 13);
        assertEquals(testTree1.getRoot().hasInfo, false);
        assertEquals(testTree1.getRoot().isInternal, true);
        
        // Test remove of node that does not exist
        testTree1.remove("GAA");
        assertEquals(testTree1.getCount(), 13);
        
        // Test search for list of nodes
        testTree1.search("AA");
        assertEquals(testTree1.getCount(), 13);
        assertEquals(testTree1.getRoot().hasInfo, false);
        assertEquals(testTree1.getRoot().isInternal, true);
        
        // Test search for exact node
        testTree1.search("AAAA$");
        assertEquals(testTree1.getCount(), 13);
        assertEquals(testTree1.getRoot().hasInfo, false);
        assertEquals(testTree1.getRoot().isInternal, true);
        
        // Test search for non existing node
        testTree1.search("GAGA");
        assertEquals(testTree1.getCount(), 13);
        assertEquals(testTree1.getRoot().hasInfo, false);
        assertEquals(testTree1.getRoot().isInternal, true);
        
        // Test search for $ child
        testTree1.insert("C");
        assertEquals(testTree1.getCount(), 13);
        assertEquals(testTree1.getRoot().hasInfo, false);
        assertEquals(testTree1.getRoot().isInternal, true);
        
        // Test to remove T branch
        testTree1.insert("TAA");
        testTree1.remove("TAA");
        assertEquals(testTree1.getCount(), 13);
        assertEquals(testTree1.getRoot().hasInfo, false);
        assertEquals(testTree1.getRoot().isInternal, true);
    }
    
    /**
     * Test the search method
     */    
    public void testSearch()
    {
    	
    }
    
    /**
     * Test the searchByPrefix method
     */
    public void testSearchByPrefix()
    {
    	// Test for empty tree
    	Pair<Integer, List<String>> result = testTree1.searchByPrefix("AA");
    	assertEquals((int) result.getKey(), (int) 0);
    	assertEquals(result.getValue().isEmpty(), true);
    	
    	// Test for 1 node in the tree
    	testTree1.insert("A");
        result = testTree1.searchByPrefix("AA");
    	assertEquals((int) result.getKey(), (int) 1);
    	
    	for (int i = 0; i < result.getValue().size(); i++)
    	{
    		System.out.println("          " + result.getValue().get(i));
    	}
    	
//    	assertEquals(result.getValue().isEmpty(), true);
//    	result = testTree1.searchByPrefix("A");
//    	assertEquals((int) result.getKey(), (int) 1);
//    	assertEquals(result.getValue().isEmpty(), false);
//    	assertEquals(result.getValue().get(0), "A");
    }
}

