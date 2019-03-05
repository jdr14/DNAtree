import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Extends abstract class Parse and provides parsing functionality 
 * specific to the tasks outlined by the DNA Tree project (project 2)
 * @author Joey Rodgers jdr14
 * @author Jovany Cabrera jovanyc4
 * @version 1.0.0
 */
public class DNAParse extends Parse 
{
	/**
	 * Constructor which calls parent (Parse) constructor internally
	 * @param fileName (file name of a file that exists passed in as a string)
	 */
	public DNAParse(String fileName)
	{
		super(fileName);
	}
	
    /**
     * @param fileName string for file name (File for reading)
     * @return a list of rectangles main function in the parsing class
     */
	public List<DNAType> parseMain()
	{
		File testFile = new File(this.getFileName());
		
		List<DNAType> parsedList = new ArrayList<>();
		
		// Create a container variable to store the data read in
		DNAType dnaContainer = new DNAType();
		
		// Try/Catch block to account for case if file is not found
		try
		{
			Scanner inFileStream = new Scanner(testFile);
			
			// Iterate through the entire file
			while (inFileStream.hasNextLine())
			{
				String currentLine = inFileStream.nextLine();
				if (!lineIsEmpty(currentLine))
				{
					
				}
			}
		}
		catch (FileNotFoundException err)
		{
			// Print a custom error along with the stack trace
			System.out.println("ERR" + this.getFileName() + " not found");
		    err.printStackTrace();
		}
		
		return parsedList;
	}
}
