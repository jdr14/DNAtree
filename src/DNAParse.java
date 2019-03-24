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
     * @return a list of rectangles main function in the parsing class
     */
    public List<Pair<String, String>> parseMain()
    {
        File testFile = new File(this.getFileName());
        
        List<Pair<String, String>> parsedList = new ArrayList<>();
        
        // Try/Catch block to account for case if file is not found
        try
        {
            Scanner inFileStream = new Scanner(testFile);
            
            // Iterate through the entire file
            while (inFileStream.hasNextLine())
            {
                // currentLine is defined to be the next line of the file
                String currentLine = inFileStream.nextLine().trim();
                if (!lineIsEmpty(currentLine))
                {
                    List<String> listedLine = lineAsList(currentLine);
                    
                    if (listedLine.size() == 2)
                    {
                        parsedList.add(new 
                                Pair<String, String>(listedLine.get(0), 
                                listedLine.get(1)));
                    }
                    else if (listedLine.size() == 1)
                    {
                        parsedList.add(new 
                                Pair<String, String>(listedLine.get(0), ""));
                    }
                }
            }
            
            // Close the file stream
            inFileStream.close();
            
            return parsedList;
        }
        catch (FileNotFoundException err)
        {
            // Print a custom error along with the stack trace
            System.out.println("ERR" + this.getFileName() + " not found");
            err.printStackTrace();
        }
        return parsedList;
    }
    
    /**
     * Set up a new DNA type to prepare it for being saved as a node 
     * in the tree
     * @param lineAsList (Single line as String containing command and 
     * possible sequence)
     * @return a new DNAType with the internal data fields populated
     */
    /*private DNAType setDNA(List<String> lineAsList)
    {
        // Initially, create an empty DNA
        DNAType newDNA;
        
        if (lineAsList.size() == 1 && lineAsList.get(0).equals("print"))
        {
            newDNA = new DNAType("print");
        }
        else if (lineAsList.size() == 2 && lineAsList.get(0).equals("print"))
        {
            newDNA = new DNAType("print", lineAsList.get(1));
        }
        else if ((lineAsList.size() == 2) && (commandValid(lineAsList.get(0))))
        {
            newDNA = new DNAType(lineAsList.get(0), lineAsList.get(1));
        }
        else
        {
            // Set to an empty DNAType (type = empty) which will 
            // translate to an empty node
            newDNA = new DNAType();
        }
        
        return newDNA;
    }*/
}
