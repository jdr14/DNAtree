/**
 * Enum to act as a switch for the print function to avoid 
 * rewriting of several functions
 * @author Joey Rodgers jdr14
 * @version 1.0.0
 */
public enum PrintOptions 
{
	/**
	 * DEFAULT print option for when lengths and stats are not specified
	 * as the second command
	 */
    DEFAULT,
    
	/**
     * LENGTHS print option for when 'lengths' is specified after 'print'
	 */
    LENGTHS,
    
	/**
     * STATS print option for when 'stats' is specified after 'print'
	 */
    STATS;
}
