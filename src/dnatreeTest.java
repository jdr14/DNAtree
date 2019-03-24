import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;

import student.TestCase;

public class dnatreeTest extends TestCase
{
    private Dna testTree1;
    
//    private DNAType dnaType, dnaType2;
    
    private Node testNode;

	/**
     * 
     * @param msg is a string
     * @param quiet is a boolean
     */
	private void print(String msg, Boolean quiet)
    {
        if (!quiet)
        {
            System.out.println(msg);
        }
    }
    
    private Boolean silent = false;

    @Override
    /**
     * Runs before each test case.  Set up the BST to be used in all tests
     */
    public void setUp() 
    {   
        // Set up first using default constructors
        testTree1 = new Dna();
    }
//    
//    public void testDNAType()
//    {
//    	// Tests for the default constructor
//    	assertEquals(dnaType.isNone(), true);
//        assertEquals(dnaType.containsInstruction(), false);
//    	assertEquals(dnaType.getCommand(), null);
//    	assertEquals(dnaType.getSequence(), null);
//    	
//    	// Tests for the constructor that takes a command as parameter
//    	dnaType = new DNAType("print");
//    	assertEquals(dnaType.isDNA(), true);
//    	assertEquals(dnaType.getCommand(), "print");
//    	assertEquals(dnaType.getSequence(), null);
//    	
//    	// Tests for the third constructor which takes in sequence and command
//    	dnaType = new DNAType("insert", "ACAA");
//    	assertEquals(dnaType.isDNA(), true);
//    	assertEquals(dnaType.getCommand(), "insert");
//    	assertEquals(dnaType.getSequence(), "ACAA");
//        
//        // Test calculations for percentages of each letter in the sequence
//        assertEquals(dnaType.getPercentA(), 75.0, 0.00);
//        assertEquals(dnaType.getPercentC(), 25.0, 0.00);
//        assertEquals(dnaType.getPercentG(), 0.00, 0.00);
//        assertEquals(dnaType.getPercentT(), 0.00, 0.00);
//        
//        // Test the set command method
//        dnaType.setCommand("remove");
//        assertEquals(dnaType.getCommand(), "remove");
//        assertEquals(dnaType.containsInstruction(), true);
//        
//        // Test the constructor with type, command, and sequence passed in
//        dnaType = new DNAType(Types.INTERNAL, "insert", "ACGT$");
//        assertEquals(dnaType.isInternal(), true);
//        assertEquals(dnaType.getCommand(), "insert");
//        assertEquals(dnaType.getSequence(), "ACGT$");
//        assertEquals(dnaType.hasSeparator(), true);
//        assertEquals(dnaType.getPercentA(), 25.00, 0.00);
//        assertEquals(dnaType.getPercentC(), 25.00, 0.00);
//        assertEquals(dnaType.getPercentG(), 25.00, 0.00);
//        assertEquals(dnaType.getPercentT(), 25.00, 0.00);
//        
//        // Test the setType method
//        dnaType.setType(Types.FLYWEIGHT);
//        assertEquals(dnaType.isEmpty(), true);
//
//        // Test the final constructor which takes another dnaType in as an arg
//        dnaType2 = new DNAType(dnaType);
//        assertEquals(dnaType.isEmpty(), true);
//        assertEquals(dnaType.getCommand(), "insert");
//        assertEquals(dnaType.getSequence(), "ACGT$");
//        assertEquals(dnaType.hasSeparator(), true);
//        assertEquals(dnaType.getPercentA(), 25.00, 0.00);
//        assertEquals(dnaType.getPercentC(), 25.00, 0.00);
//        assertEquals(dnaType.getPercentG(), 25.00, 0.00);
//        assertEquals(dnaType.getPercentT(), 25.00, 0.00);
//    }
//    
    
    // Use this to test the Dna.java class containing the tree methods
    public void testDna()
    {	
    	// Test tree created using default constructor
    	assertEquals(testTree1.getCount(), 0);
    	assertEquals(testTree1.getRoot().isInternal, false);
    	
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
    }
}

