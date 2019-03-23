import java.util.*;

/**
 * Defines the class that outlines the DNAType to actually contain the 
 * data to be used and stored in the DNA Node Tree
 * @author Joey Rodgers jdr14
 * @version 1.0.0
 * @param <E> Should be an ENUM consisting of types relevant to the DNATree
 */
class DNAType
{
	/**
	 * Generic type E for the type 
	 * Should be an enum containing the list of types
	 */
	private Types type;
	
    /**
     * This will be argument 1.  This will be used to store one of the
     * following commands.  (1) insert (2) remove (3) print (4) search
     */
	private String command;
	
	/**
	 * This will be argument 2 and will be an actual sequence if the
	 * commands are either 'insert', 'remove', or 'search'.
	 * Otherwise, this will be used to store the 'lengths' and 'stats'
	 * options if they are specified after the 'print' command.
	 */
	private String sequence;
	
	/**
	 * This will be the percentage (up to 2 decimal places) of the amount of
	 * As in the sequence
	 */
	private Double percentA = 0.0;
	
	/**
	 * This will be the percentage (up to 2 decimal places) of the amount of
	 * Cs in the sequence
	 */
	private Double percentC = 0.0;
	
	/**
	 * This will be the percentage (up to 2 decimal places) of the amount of
	 * Gs in the sequence
	 */
	private Double percentG = 0.0;
	
	/**
	 * This will be the percentage (up to 2 decimal places) of the amount of
	 * Ts in the sequence
	 */
	private Double percentT = 0.0;
	
	/**
	 * This is the boolean switch which will be set to true if a '$' is found
	 * in the given sequence
	 */
	private boolean containsSeparator = false;
	
	/**
	 * Provide a default constructor
	 * Sets string values to null and type to empty by default
	 */
	public DNAType()
	{
		this.type = Types.NONETYPE;
		this.command = null;
		this.sequence = null;
	}
	
	/**
	 * Constructor to accept a command passed in as a string
	 * @param cmd (as String
	 */
	public DNAType(String cmd)
	{
		this.type = Types.DNATYPE;
		this.command = cmd;
		this.sequence = null;
	}
	
	/**
	 * Constructor to accept a command and sequence as a string
	 * @param cmd (as String)
	 * @param seq (as String)
	 */
	public DNAType(String cmd, String seq)
	{
		this.type = Types.DNATYPE;
		this.command = cmd;
		this.sequence = seq;
		
		if (cmd != null && !cmd.equalsIgnoreCase("print")) 
		{
		    calculatePercentages(seq);	
		}
		
	}
	
	/**
	 * Constructor to accept a type (enum) and the commands
	 * and sequences as strings
	 * @param type (custom enum type defined in 'Types.java')
	 * @param cmd (command as a String)
	 * @param seq (sequence as a String)
	 */
	public DNAType(Types type, String cmd, String seq)
	{
		this.type = type;
		this.command = cmd;
		this.sequence = seq;
		
		
		if (cmd != null && !cmd.equalsIgnoreCase("print") && (seq != null))
		{
			calculatePercentages(seq);
		}
	}
	
	/**
	 * Another constructor to take in a type of itself
	 * Useful when creating more nodes
	 * @param dna
	 */
	public DNAType(DNAType dna)
	{
		this.type = dna.type;
		this.command = dna.command;
		this.sequence = dna.sequence;
		this.percentA = dna.percentA;
		this.percentC = dna.percentC;
		this.percentG = dna.percentG;
		this.percentT = dna.percentT;
		this.containsSeparator = dna.containsSeparator;
	}
	
	/**
	 * Mutator method to set the type as one of the custom types
	 * defined in the enum contained in 'Types.java'
	 * @param type (custom enum type defined in 'Types.java')
	 */
	public void setType(Types type)
	{
		this.type = type;
	}
	
	/**
	 * Method to inform user if node is empty
	 * @return returns boolean value based on if node is empty or not
	 */
	public boolean isEmpty()
	{
		return (this.type == Types.EMPTY);
	}
	
	/**
	 * Method to inform user if node is internal
	 * @return returns boolean value based on if node is internal or not
	 */
	public boolean isInternal()
	{
		return (this.type == Types.INTERNAL);
	}
	
	/**
	 * Method to inform user if node is of DNA type 
	 * @return returns boolean value based on if node is DNA type or not
	 */
	public boolean isDNA()
	{
		return (this.type == Types.DNATYPE);
	}
	
	/**
	 * Method to inform user if node is of None type 
	 * @return boolean value based on if DNA type is none or not
	 */
	public boolean isNone()
	{
		return (this.type == Types.NONETYPE);
	}
	
	/**
	 * Determine if an instruction has been set or not for parsing purposes
	 * @return boolean value based on whether the DNA node contains
	 * an instruction or not
	 */
	public boolean containsInstruction()
	{
		return (this.command != null);
	}
	
	/**
	 * Accessor method to return the command as a string
	 * @return (command as a string)
	 */
	public String getCommand()
	{
		return this.command;
	}
	
	/**
	 * Mutator method to set the internal command field to the one passed in
	 * @param cmd 
	 */
	public void setCommand(String cmd)
	{
		this.command = cmd;
	}
	
	/**
	 * Accessor method to return the sequence as a string
	 * @return sequence 
	 */
	public String getSequence()
	{
		return this.sequence;
	}
	
	/**
	 * Mutator method to set the internal sequence field to the one passed in
	 * @param seq
	 */
	public void setSequence(String seq)
	{
		this.sequence = seq;
        calculatePercentages(seq);
	}
	
	/**
	 * Accessor method to inform user if a separator exists in the 
	 * given sequence.
	 * @return containsSeparator
	 */
	public boolean hasSeparator()
	{
		return containsSeparator;
	}
	
	/**
	 * Accessor method to return the percentage of As with respect to its
	 * containing sequence
	 * @return percentA (Double)
	 */
	public Double getPercentA()
	{
		return percentA;
	}
	
	/**
	 * Accessor method to return the percentage of Cs with respect to its
	 * containing sequence
	 * @return percentC (Double)
	 */
	public Double getPercentC()
	{
		return percentC;
	}
	
	/**
	 * Accessor method to return the percentage of Gs with respect to its
	 * containing sequence
	 * @return percentG (Double)
	 */
	public Double getPercentG()
	{
		return percentG;
	}
	
	/**
	 * Accessor method to return the percentage of Ts with respect to its
	 * containing sequence
	 * @return percentT (Double)
	 */
	public Double getPercentT()
	{
		return percentT;
	}

	/**
	 * Private helper method to handle the calculation of percentages of
	 * each character in relation to its containing sequence
	 * @param seq (String)
	 */
	private void calculatePercentages(String seq)
	{
		// Temp variables to keep track of the character count in the sequence
		Double countA = 0.0;
		Double countC = 0.0;
		Double countG = 0.0;
		Double countT = 0.0;
		Integer count$ = 0;
		
		// Iterate through the sequence and obtain counts for each character
		for (int i = 0; i < seq.length(); i++)
		{
			if (seq.charAt(i) == 'A')
			{
				countA++;
			}
			else if (seq.charAt(i) == 'C')
			{
				countC++;
			}
			else if (seq.charAt(i) == 'G')
			{
				countG++;
			}
			else if (seq.charAt(i) == 'T')
			{
				countT++;
			}
			else if (seq.charAt(i) == '$')
			{
				containsSeparator = true;
				count$++;
			}
		}  // End for
		
		// Calculate the final percentages
		percentA = (countA / (seq.length() - count$)) * 100.00;
		percentC = (countC / (seq.length() - count$)) * 100.00;
		percentG = (countG / (seq.length() - count$)) * 100.00;
		percentT = (countT / (seq.length() - count$)) * 100.00;
	}

}  // End DNAType




