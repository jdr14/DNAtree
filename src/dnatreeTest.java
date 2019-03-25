//import java.io.ByteArrayOutputStream;
//import java.io.PrintStream;
import java.io.*;
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
        
        // Test where you cannot insert same sequence as root
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
    	// test for search call on  an empty tree
    	systemOut().clearHistory();
    	testTree1.search("ACGT$");
    	assertEquals("# of nodes visited: 1\r\n" + 
    			"no sequence found\n", systemOut().getHistory());
    	
    	// case where sequence being searched it almost exactly like other
    	testTree1.insert("ACGT");
    	testTree1.insert("ACGTT");
    	systemOut().clearHistory();
    	testTree1.search("ACGT$");
    	testTree1.search("ACGTT$");
    	assertEquals("# of nodes visited: 6\r\n" + 
    			"sequence: ACGT\r\n" + 
    			"# of nodes visited: 6\r\n" + 
    			"sequence: ACGTT\n", systemOut().getHistory());
    	
    	// test where all nodes removed and search for old node
    	// on an empty tree
    	testTree1.remove("ACGT");
    	testTree1.remove("ACGTT");
    	systemOut().clearHistory();
    	testTree1.search("ACGTT$");
    	assertEquals("# of nodes visited: 1\r\n" + 
    			"no sequence found\n", systemOut().getHistory());
    	
    	// test where sequence is similar to node but not match
    	testTree1.insert("A");
    	testTree1.insert("AA");
    	systemOut().clearHistory();
    	testTree1.search("AC$");
    	assertEquals("# of nodes visited: 3\r\n" + 
    			"no sequence found\n", systemOut().getHistory());
    	
    	// test search AAA$ does not exist but AA does
    	systemOut().clearHistory();
    	testTree1.search("AAA$");
    	assertEquals("# of nodes visited: 3\r\n" + 
    			"no sequence found\n", systemOut().getHistory());
    }
    
    /**
     * test function for remove
     */
    public void testRemove()
    {
    	// test for try to remove on an empty tree
    	systemOut().clearHistory();
    	testTree1.remove("AC");
    	assertEquals("sequence AC does not exist\n", systemOut().getHistory());
    	
    	// test tree size = 1 remove sequence not in tree
    	testTree1.insert("AC");
    	systemOut().clearHistory();
    	testTree1.remove("AG");
    	assertEquals("sequence AG does not exist\n", systemOut().getHistory());
    	
    	// test tree size = 2 remove sequence not in tree
    	testTree1.insert("AC");
    	testTree1.insert("ACC");
    	systemOut().clearHistory();
    	testTree1.remove("ACCG");
    	assertEquals("sequence ACCG does not "
    			+ "exist\n", systemOut().getHistory());
    }
    
    /**
     * test remaining function in leaf node class 
     */
    public void testLeafNode()
    {
    	LeafNode testNode = new LeafNode(0);
    	
    	// test set sequence function
    	testNode.setSequence("ACC");
    	assertEquals(testNode.getSequence(), "ACC");
    }
    
    /**
     * test remaining function in internal node class
     */
    public void testInternalNode()
    {
    	// parent node
    	InternalNode testNode = new InternalNode();
    	
    	//children node
    	testNode.setAChild(new FlyWeightNode());
    	testNode.setCChild(new FlyWeightNode());
    	testNode.setGChild(new FlyWeightNode());
    	testNode.setTChild(new FlyWeightNode());
    	testNode.setdChild(new FlyWeightNode());
    	
    	testNode.getAChild();
    	testNode.getCChild();
    	testNode.getGChild();
    	testNode.getTChild();
    	testNode.getdChild();
    }
    
    /**
     * Test the searchByPrefix method
     */
    public void testSearchByPrefix()
    {
    	// Test for empty tree
    	Pair<Integer, List<String>> result = testTree1.searchByPrefix("AA");
    	assertEquals((int) result.getKey(), (int) 1);
    	assertEquals(result.getValue().isEmpty(), true);
    	
    	// Test for 1 node in the tree
    	testTree1.insert("A");
    	result = testTree1.searchByPrefix("C");
    	assertEquals(result.getValue().isEmpty(), true);
        result = testTree1.searchByPrefix("AA");
    	assertEquals((int) result.getKey(), (int) 1);
    	assertEquals(result.getValue().isEmpty(), true);
    	result = testTree1.searchByPrefix("A");
    	assertEquals((int) result.getKey(), (int) 1);
    	assertEquals(result.getValue().isEmpty(), false);
    	assertEquals(result.getValue().get(0), "A");
    	
    	// Create another empty tree for testing
    	testTree1 = new Dna();
        testTree1.insert("ACGT");     
        testTree1.insert("AAAA");
        testTree1.insert("AA");     
        testTree1.insert("AAACCCCGGTGAAAACGTA");
        testTree1.insert("ACTGGGAA");
        
        result = testTree1.searchByPrefix("A");
    	assertEquals((int) result.getKey(), (int) 22);
    	assertEquals(result.getValue().isEmpty(), false);
    	assertEquals(result.getValue().get(0), "AAAA");
    	assertEquals(result.getValue().get(1), "AAACCCCGGTGAAAACGTA");
    	assertEquals(result.getValue().get(2), "AA");
    	assertEquals(result.getValue().get(3), "ACGT");
    	assertEquals(result.getValue().get(4), "ACTGGGAA");
    	
    	result = testTree1.searchByPrefix("AA");
    	assertEquals((int) result.getKey(), (int) 13);
    	assertEquals(result.getValue().isEmpty(), false);
    	assertEquals(result.getValue().get(0), "AAAA");
    	assertEquals(result.getValue().get(1), "AAACCCCGGTGAAAACGTA");
    	assertEquals(result.getValue().get(2), "AA");
    	
    	result = testTree1.searchByPrefix("AC");
    	assertEquals((int) result.getKey(), (int) 8);
    	assertEquals(result.getValue().isEmpty(), false);
    	assertEquals(result.getValue().get(0), "ACGT");
    	assertEquals(result.getValue().get(1), "ACTGGGAA");
    	
    	result = testTree1.searchByPrefix("AAA");
    	assertEquals((int) result.getKey(), (int) 9);
    	assertEquals(result.getValue().isEmpty(), false);
    	assertEquals(result.getValue().get(0), "AAAA");
    	assertEquals(result.getValue().get(1), "AAACCCCGGTGAAAACGTA");
    	
    	// Build a complex tree and execute search on that tree
    	testTree1.remove("ACGT");
    	testTree1.insert("ACCTT");
    	testTree1.insert("ACTTA");
    	testTree1.insert("TATA");
    	testTree1.insert("TCG");
    	testTree1.insert("ACCAGTTA");
    	testTree1.insert("ACCAGGTA");
    	testTree1.insert("ACCAGGTA");
    	testTree1.remove("A");
    	testTree1.remove("ACCAGGTA");
    	testTree1.insert("TCGTCG");
    	testTree1.insert("TCGTCG");
    	testTree1.insert("TACAGT");
    	testTree1.insert("TCCAGG");
    	testTree1.insert("ACCAGTTA");
    	testTree1.insert("ACCAGGTA");
    	testTree1.search("ACCAGG");
    	testTree1.insert("ACCAGGTA");
    	testTree1.remove("A");
    	testTree1.remove("ACCAGGTA");
    	testTree1.insert("TCGTCG");
    	testTree1.insert("TCGTCG");
    	testTree1.insert("TACAGT");
    	testTree1.insert("TCCAGG");
        
//    	result = testTree1.searchByPrefix("G");
//    	assertEquals((int) result.getKey(), (int) 12);
//    	assertEquals(result.getValue().isEmpty(), false);
//    	assertEquals(result.getValue().get(0), "GATG");
//    	assertEquals(result.getValue().get(1), "GCC");
//    	assertEquals(result.getValue().get(2), "GCGTAAGGCTTACG");
//    	assertEquals(result.getValue().get(3), "GGGAACCTTAC");
    	
//    	result = testTree1.searchByPrefix("GCC");
//    	assertEquals((int) result.getKey(), (int) 3);
//    	assertEquals(result.getValue().isEmpty(), false);
//    	assertEquals(result.getValue().get(0), "GCC");
    }
}

