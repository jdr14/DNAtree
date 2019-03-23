import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;

import student.TestCase;

public class dnatreeTest extends TestCase
{
    private Dna testTree1;
    
    private DNAType dnaType;
    
    private Node<DNAType> testNode;

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
        
        dnaType = new DNAType();
        
        testNode = new Node<DNAType>();
    }
    
    public void testDNAType()
    {
    	// Tests for the default constructor
    	assertEquals(dnaType.isNone(), true);
    	assertEquals(dnaType.getCommand(), null);
    	assertEquals(dnaType.getSequence(), null);
    	
    	// Tests for the constructor that takes a command as parameter
    	dnaType = new DNAType("print");
    	assertEquals(dnaType.isDNA(), true);
    	assertEquals(dnaType.getCommand(), "print");
    	assertEquals(dnaType.getSequence(), null);
    	
    	// Tests for the third constructor which takes in sequence and command
    	dnaType = new DNAType("insert", "ACAA");
    	assertEquals(dnaType.isDNA(), true);
    	assertEquals(dnaType.getCommand(), "insert");
    	assertEquals(dnaType.getSequence(), "ACAA");
        
        // Test calculations for percentages of each letter in the sequence
        assertEquals(dnaType.getPercentA(), 75.0);
        assertEquals(dnaType.getPercentC(), 25.0);
        assertEquals(dnaType.getPercentG(), 0.00);
        assertEquals(dnaType.getPercentT(), 0.00);
        
        // Test the set command method
        dnaType.setCommand("remove");
        assertEquals(dnaType.getCommand(), "remove");



    }
    
    // Use this to test the Dna.java class containing the tree methods
    public void testDna()
    {

    }
}

