/**
 * @author Mathana Sreedaran
 * 
 * Test class to play around with Java regular expression and to test 
 * if the implementation of my tokenizer is as expected
 */
import static org.junit.Assert.*;

import org.junit.Test;

public class TokenizerTest
{

	//@Test
	public void test()
	{
		String infix = "-2 * 3";
		String[] tokens = tokenizer(infix);
		for (int i=0; i<tokens.length; i++)
		{
			System.out.println(tokens[i]);
		}
	}
	
	@Test
	public void testRegex2()
	{
		String infix = "-2.1 * 3";
		regex2(infix);
		
		String infix2 = "-2 * 3";
		regex2(infix2);
	}
	
	public String[] tokenizer(String infix)
	{
		infix.trim();
		return infix.split("\\s");
	}

	public void regex2(String infix)
	{
		String[] tokens = tokenizer(infix);
		for (int i=0; i<tokens.length; i++)
		{
			if (tokens[i].matches("[-]\\d+.\\d+|[-]\\d+"))  //[] is a character class
			{
				System.out.println(tokens[i] + "YAY");
			}
			else
				System.out.println(tokens[i] + "Damn");
		}
	}
	
	//	return string.split("(?<=-)|(?=-)|(?<=\\+)|(?=\\+)|(?<=\\*)|(?=\\*)|(?<=/)|(?=/)|(?<=\\()|(?=\\()|(?<=\\))|(?=\\))|(?<=\\^)|(?=\\^)");
}
