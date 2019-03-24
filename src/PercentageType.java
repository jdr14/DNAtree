
public class PercentageType 
{
	
	/**
	 * This will be the percentage (up to 2 decimal places) of the amount of
	 * As in the sequence
	 */
	private Double percentA;
	
	/**
	 * This will be the percentage (up to 2 decimal places) of the amount of
	 * Cs in the sequence
	 */
	private Double percentC;
	
	/**
	 * This will be the percentage (up to 2 decimal places) of the amount of
	 * Gs in the sequence
	 */
	private Double percentG;
	
	/**
	 * This will be the percentage (up to 2 decimal places) of the amount of
	 * Ts in the sequence
	 */
	private Double percentT;
	
	private String sequence;

	public PercentageType() 
	{
		this.sequence = "";
        this.percentA = 0.0;
        this.percentC = 0.0;
        this.percentG = 0.0;
        this.percentT = 0.0;
	}
	
	public PercentageType(String seq)
	{
		this.sequence = seq;
		calculatePercentages(seq);
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
	 * 
	 * @param seq
	 */
	public void setSequence(String seq)
	{
		this.sequence = seq;
		calculatePercentages(seq);
	}
	
	/**
	 * 
	 * @return
	 */
	public String getSequence()
	{
		return this.sequence;
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
		
		int count$ = 0;
		
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
				count$++;
			}
		}  // End for
		
		// Calculate the final percentages
		percentA = (countA / (seq.length() - count$) ) * 100.00;
		percentC = (countC / (seq.length() - count$) ) * 100.00;
		percentG = (countG / (seq.length() - count$) ) * 100.00;
		percentT = (countT / (seq.length() - count$) ) * 100.00;
	}
}
