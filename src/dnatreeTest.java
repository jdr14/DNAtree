import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;

import student.TestCase;

public class dnatreeTest extends TestCase
{
    private Dna testTree1;
    
    private DNAType dnaType, dnaType2;
    
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
        dnaType2 = new DNAType();
        
        testNode = new Node<DNAType>();
    }
    
    public void testDNAType()
    {
    	// Tests for the default constructor
    	assertEquals(dnaType.isNone(), true);
    	assertEquals(dnaType.isDNA(), false);
    	assertEquals(dnaType.isInternal(), false);
    	assertEquals(dnaType.isEmpty(), false);
        assertEquals(dnaType.containsInstruction(), false);
    	assertEquals(dnaType.getCommand(), null);
    	assertEquals(dnaType.getSequence(), null);
    	
    	// Tests for the constructor that takes a command as parameter
    	dnaType = new DNAType("print");
    	assertEquals(dnaType.isDNA(), true);
    	assertEquals(dnaType.isNone(), false);
    	assertEquals(dnaType.isInternal(), false);
    	assertEquals(dnaType.isEmpty(), false);
    	assertEquals(dnaType.getCommand(), "print");
    	assertEquals(dnaType.getSequence(), null);
    	
    	// Tests for the third constructor which takes in sequence and command
    	dnaType = new DNAType("insert", "ACAA");
    	assertEquals(dnaType.isDNA(), true);
    	assertEquals(dnaType.isNone(), false);
    	assertEquals(dnaType.isInternal(), false);
    	assertEquals(dnaType.isEmpty(), false);
    	assertEquals(dnaType.getCommand(), "insert");
    	assertEquals(dnaType.getSequence(), "ACAA");
        
        // Test calculations for percentages of each letter in the sequence
        assertEquals(dnaType.getPercentA(), 75.0, 0.00);
        assertEquals(dnaType.getPercentC(), 25.0, 0.00);
        assertEquals(dnaType.getPercentG(), 0.00, 0.00);
        assertEquals(dnaType.getPercentT(), 0.00, 0.00);
        
        // Test the set command method
        dnaType.setCommand("remove");
        assertEquals(dnaType.getCommand(), "remove");
        assertEquals(dnaType.containsInstruction(), true);
        
        // Test the constructor with type, command, and sequence passed in
        dnaType = new DNAType(Types.INTERNAL, "insert", "ACGT$");
        assertEquals(dnaType.isInternal(), true);
    	assertEquals(dnaType.isNone(), false);
    	assertEquals(dnaType.isDNA(), false);
    	assertEquals(dnaType.isEmpty(), false);
        assertEquals(dnaType.getCommand(), "insert");
        assertEquals(dnaType.getSequence(), "ACGT$");
        assertEquals(dnaType.hasSeparator(), true);
        assertEquals(dnaType.getPercentA(), 25.00, 0.00);
        assertEquals(dnaType.getPercentC(), 25.00, 0.00);
        assertEquals(dnaType.getPercentG(), 25.00, 0.00);
        assertEquals(dnaType.getPercentT(), 25.00, 0.00);
        
        // Test the setType method
        dnaType.setType(Types.EMPTY);
        assertEquals(dnaType.isEmpty(), true);

        // Test the final constructor which takes another dnaType in as an arg
        dnaType2 = new DNAType(dnaType);
        dnaType2.setSequence("AACCT$");
        assertEquals(dnaType2.isEmpty(), true);
    	assertEquals(dnaType.isNone(), false);
    	assertEquals(dnaType.isInternal(), false);
    	assertEquals(dnaType.isDNA(), false);
        assertEquals(dnaType2.getCommand(), "insert");
        assertEquals(dnaType2.getSequence(), "AACCT$");
        assertEquals(dnaType2.hasSeparator(), true);
        assertEquals(dnaType2.getPercentA(), 40.00, 0.00);
        assertEquals(dnaType2.getPercentC(), 40.00, 0.00);
        assertEquals(dnaType2.getPercentG(), 0.00, 0.00);
        assertEquals(dnaType2.getPercentT(), 20.00, 0.00);
    }
    
    /**
     * Test the Pair class
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
    
    /**
     * Test the PrintOptions enum
     */
    public void testPrintOptions()
    {
    	PrintOptions po; 
        po = PrintOptions.DEFAULT;
    	assertEquals(po, PrintOptions.DEFAULT);
    	po = PrintOptions.LENGTHS;
    	assertEquals(po, PrintOptions.LENGTHS);
    	po = PrintOptions.STATS;
    	assertEquals(po, PrintOptions.STATS);
    }
    
    /**
     * Test DNAParse
     */
    public void testDNAParse()
    {	
    	DNAParse p = new DNAParse("test2.txt");
    	
    	List<DNAType> l = p.parseMain();
    	
    	assertEquals(l.get(0).getCommand(), "insert");
    	assertEquals(l.get(0).getSequence(), "ACGT");
    	assertEquals(l.get(0).isDNA(), true);
    	assertEquals(l.get(1).getCommand(), "print");
    	assertEquals(l.get(1).getSequence(), null);
    	assertEquals(l.get(1).isDNA(), true);
    	assertEquals(l.get(2).getCommand(), "print");
    	assertEquals(l.get(2).getSequence(), "lengths");
    	assertEquals(l.get(2).isDNA(), true);
    	assertEquals(l.get(3).getCommand(), "print");
    	assertEquals(l.get(3).getSequence(), "stats");
    	assertEquals(l.get(3).isDNA(), true);
    	assertEquals(l.get(4).getCommand(), "search");
    	assertEquals(l.get(4).getSequence(), "ACGT");
    	assertEquals(l.get(4).isDNA(), true);
    	assertEquals(l.get(5).getCommand(), "remove");
    	assertEquals(l.get(5).getSequence(), "ACGT");
    	assertEquals(l.get(5).isDNA(), true);
    	
    	// The next tests the 'else' case because the 'invalid command' passed
    	// in will call the default DNAType constructor
    	// assertEquals(l.get(6).getCommand(), null);
    	// assertEquals(l.get(6).getSequence(), null);
    	// assertEquals(l.get(6).isNone(), true);
    }
    
    // Use this to test the Dna.java class containing the tree methods
    public void testDna()
    {
        // Create new print streams to temporarily redirect the out and err
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final PrintStream oStream = new PrintStream(out);
        final ByteArrayOutputStream err = new ByteArrayOutputStream();
        final PrintStream eStream = new PrintStream(err);
        
        // Redirect System.out and System.err to the print stream created ^
        System.setOut(oStream);
        System.setErr(eStream);
        
        // Test that the main throws error when specifying incorrect file name
        String[] args = new String[1];
        args[0] = "filedoesntexist.txt";
        DNAtree dnatree = new DNAtree();
        dnatree.main(args);
        
        String compareMsg = "java.io.FileNotFoundException: "
                + "filedoesntexist.txt "
                + "(No such file or directory)";
        assertEquals(err.toString().split("\\n")[0], compareMsg);
        
        // Test the expected output to console
        args[0] = "test.txt";
        
        String[] mainResult = out.toString().split("\\n");
        
        String[] expectedOutput = new String[165];
        expectedOutput[1] = "I";
        expectedOutput[2] = "  I";
        expectedOutput[3] = "    I";
        expectedOutput[4] = "      I";
        expectedOutput[5] = "        AAAA";
        expectedOutput[6] = "        AAACCCCGGTGAAAACGTA";
        expectedOutput[7] = "        E";
        expectedOutput[8] = "        E";
        expectedOutput[9] = "        E";
        expectedOutput[10] = "      E";
        expectedOutput[11] = "      E";
        expectedOutput[12] = "      E";
        expectedOutput[13] = "      AA";
        expectedOutput[14] = "    I";
        expectedOutput[15] = "      E";
        expectedOutput[16] = "      ACCTT";
        expectedOutput[17] = "      E";
        expectedOutput[18] = "      I";
        expectedOutput[19] = "        E";
        expectedOutput[20] = "        E";
        expectedOutput[21] = "        ACTGGGAA";
        expectedOutput[22] = "        ACTTA";
        expectedOutput[23] = "        E";
        expectedOutput[24] = "      E";
        expectedOutput[25] = "    E";
        expectedOutput[26] = "    E";
        expectedOutput[27] = "    E";
        expectedOutput[28] = "  E";
        expectedOutput[29] = "  E";
        expectedOutput[30] = "  E";
        expectedOutput[31] = "  E";
        expectedOutput[32] = "I";
        expectedOutput[33] = "  I";
        expectedOutput[34] = "    I";
        expectedOutput[35] = "      I";
        expectedOutput[36] = "        AAAA: length: 4";
        expectedOutput[37] = "        AAACCCCGGTGAAAACGTA: length: 19";
        expectedOutput[38] = "        E";
        expectedOutput[39] = "        E";
        expectedOutput[40] = "        E";
        expectedOutput[41] = "      E";
        expectedOutput[42] = "      E";
        expectedOutput[43] = "      E";
        expectedOutput[44] = "      AA: length: 2";
        expectedOutput[45] = "    I";
        expectedOutput[46] = "      E";
        expectedOutput[47] = "      ACCTT: length: 5";
        expectedOutput[48] = "      E";
        expectedOutput[49] = "      I";
        expectedOutput[50] = "        E";
        expectedOutput[51] = "        E";
        expectedOutput[52] = "        ACTGGGAA: length: 8";
        expectedOutput[53] = "        ACTTA: length: 5";
        expectedOutput[54] = "        E";
        expectedOutput[55] = "      E";
        expectedOutput[56] = "    E";
        expectedOutput[57] = "    E";
        expectedOutput[58] = "    E";
        expectedOutput[59] = "  E";
        expectedOutput[60] = "  E";
        expectedOutput[61] = "  I";
        expectedOutput[62] = "    TATA: length: 4";
        expectedOutput[63] = "    TCG: length: 3";
        expectedOutput[64] = "    E";
        expectedOutput[65] = "    E";
        expectedOutput[66] = "    E";
        expectedOutput[67] = "  E";
        expectedOutput[68] = "I";
        expectedOutput[69] = "  I";
        expectedOutput[70] = "    I";
        expectedOutput[71] = "      I";
        expectedOutput[72] = "        AAAA: A(100.00), C(0.00), G(0.00), T(0.00)";
        expectedOutput[73] = "        AAACCCCGGTGAAAACGTA: A(42.11), C(26.32), G(21.05), T(10.53)";
        expectedOutput[74] = "        E";
        expectedOutput[75] = "        E";
        expectedOutput[76] = "        E";
        expectedOutput[77] = "      E";
        expectedOutput[78] = "      E";
        expectedOutput[79] = "      E";
        expectedOutput[80] = "      AA: A(100.00), C(0.00), G(0.00), T(0.00)";
        expectedOutput[81] = "    I";
        expectedOutput[82] = "      E";
        expectedOutput[83] = "      ACCTT: A(20.00), C(40.00), G(0.00), T(40.00)";
        expectedOutput[84] = "      E";
        expectedOutput[85] = "      I";
        expectedOutput[86] = "        E";
        expectedOutput[87] = "        E";
        expectedOutput[88] = "        ACTGGGAA: A(37.50), C(12.50), G(37.50), T(12.50)";
        expectedOutput[89] = "        ACTTA: A(40.00), C(20.00), G(0.00), T(40.00)";
        expectedOutput[90] = "        E";
        expectedOutput[91] = "      E";
        expectedOutput[92] = "    E";
        expectedOutput[93] = "    E";
        expectedOutput[94] = "    E";
        expectedOutput[95] = "  E";
        expectedOutput[96] = "  E";
        expectedOutput[97] = "  I";
        expectedOutput[98] = "    TATA: A(50.00), C(0.00), G(0.00), T(50.00)";
        expectedOutput[99] = "    TCG: A(0.00), C(33.33), G(33.33), T(33.33)";
        expectedOutput[100] = "    E";
        expectedOutput[101] = "    E";
        expectedOutput[102] = "    E";
        expectedOutput[103] = "  E";
        expectedOutput[104] = "# of nodes visited: 5";
        expectedOutput[105] = "sequence: AAAA";
        expectedOutput[106] = "# of nodes visited: 13";
        expectedOutput[107] = "sequence: AAAA";
        expectedOutput[108] = "sequence: AAACCCCGGTGAAAACGTA";
        expectedOutput[109] = "sequence: AA";
        expectedOutput[110] = "# of nodes visited: 4";
        expectedOutput[111] = "no sequence found";
        expectedOutput[112] = "I";
        expectedOutput[113] = "  I";
        expectedOutput[114] = "    I";
        expectedOutput[115] = "      I";
        expectedOutput[116] = "        AAAA";
        expectedOutput[117] = "        AAACCCCGGTGAAAACGTA";
        expectedOutput[118] = "        E";
        expectedOutput[119] = "        E";
        expectedOutput[120] = "        E";
        expectedOutput[121] = "      E";
        expectedOutput[122] = "      E";
        expectedOutput[123] = "      E";
        expectedOutput[124] = "      E";
        expectedOutput[125] = "    I";
        expectedOutput[126] = "      E";
        expectedOutput[127] = "      ACCTT";
        expectedOutput[128] = "      E";
        expectedOutput[129] = "      I";
        expectedOutput[130] = "        E";
        expectedOutput[131] = "        E";
        expectedOutput[132] = "        ACTGGGAA";
        expectedOutput[133] = "        ACTTA";
        expectedOutput[134] = "        E";
        expectedOutput[135] = "      E";
        expectedOutput[136] = "    E";
        expectedOutput[137] = "    E";
        expectedOutput[138] = "    E";
        expectedOutput[139] = "  I";
        expectedOutput[140] = "    I";
        expectedOutput[141] = "      I";
        expectedOutput[142] = "        E";
        expectedOutput[143] = "        E";
        expectedOutput[144] = "        E";
        expectedOutput[145] = "        CAAT";
        expectedOutput[146] = "        CAA";
        expectedOutput[147] = "      E";
        expectedOutput[148] = "      E";
        expectedOutput[149] = "      CAT";
        expectedOutput[150] = "      E";
        expectedOutput[151] = "    E";
        expectedOutput[152] = "    E";
        expectedOutput[153] = "    E";
        expectedOutput[154] = "    C";
        expectedOutput[155] = "  I";
        expectedOutput[156] = "    GAC";
        expectedOutput[157] = "    E";
        expectedOutput[158] = "    E";
        expectedOutput[159] = "    E";
        expectedOutput[160] = "    G";
        expectedOutput[161] = "  TCG";
        expectedOutput[162] = "  E";
        
        for (int i = 1; i < mainResult.length; i++)
        {
        	assertEquals(mainResult[i], expectedOutput[i+1]);
        }
    }
}

