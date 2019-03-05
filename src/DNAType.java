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
	 * Provide a default constructor
	 * Sets string values to null and type to empty by default
	 */
	public DNAType()
	{
		this.type = Types.EMPTY;
		this.command = null;
		this.sequence = null;
	}
	
	/**
	 * Constructor to accept a command passed in as a string
	 * @param cmd (as String
	 */
	public DNAType(String cmd)
	{
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
		this.command = cmd;
		this.sequence = seq;
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
	}
	
	/**
	 * Accessor method to return the type 
	 * @return returns the data type (meant for the node)
	 */
	public Types getType()
	{
		return this.type;
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
	 * @return
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
	}
}




