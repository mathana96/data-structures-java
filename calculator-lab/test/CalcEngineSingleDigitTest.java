import static org.junit.Assert.*;

import java.util.Stack;

import org.junit.Test;

public class CalcEngineSingleDigitTest
{

  private static final int plusMinusPrecedence = 1;
  private static final int multDividePrecedence = 2;
  //  private static final int dividePrecedence = 2;


  Stack<Character> operatorStack = new Stack<>();

  @Test
  public void testRPNWithoutParenthesis()
  {
	String infix = "3 * 4 + 6";
	assertEquals("34*6+", toPostfix(infix));
	
	String infix2 = "3 - 4 / 6";
	assertEquals("346/-", toPostfix(infix2));
  }


  public String toPostfix(String infix)
  {
	String postfix = "";
	Character currentChar;

	infix = infix.replaceAll("\\s+",""); //removing whitespace
	//	System.out.println(infix);
	for (int i=0; i<infix.length(); i++)
	{
	  //	  System.out.println(i);
	  currentChar = infix.charAt(i);
	  //	  System.out.println(currentChar);
	  if (Character.isDigit(currentChar))
	  {
		postfix += Character.toString(currentChar);
	  }
	  else if ( (currentChar.equals('+')) || (currentChar.equals('-'))
		  ||	(currentChar.equals('*')) || (currentChar.equals('/')) )
	  {
		boolean noPreced = false;

		while ( (!operatorStack.empty()) && (noPreced == false) )
		{		   
		  if (precedenceCheck(currentChar, operatorStack.peek()) < 0)
		  {
			postfix += Character.toString(operatorStack.pop());
		  } 
		  else
		  {
			noPreced = true;
		  }
		}
		operatorStack.push(currentChar);

	  }	  

	}
	while (!operatorStack.empty())
	{
	  postfix += Character.toString(operatorStack.pop());
	}

	return postfix;
  }


  public int precedenceCheck(char current, char topOfStack)
  {
	int currentVal = 0;
	int topOfStackVal = 0;
	//If current - top = negative, pop top and put current. else, just put current
	switch (current)
	{
	  case '+':
	  case '-':
		currentVal = plusMinusPrecedence;
		break;

	  case '*':
	  case '/':
		currentVal = multDividePrecedence;
		break;

	  default:
		currentVal = 0;
		break;
	}

	switch (topOfStack)
	{
	  case '+':
	  case '-':
		topOfStackVal = plusMinusPrecedence;
		break;

	  case '*':
	  case '/':
		topOfStackVal = multDividePrecedence;
		break;

	  default:
		topOfStackVal = 0;
		break;
	}
	return (currentVal - topOfStackVal);
  }
}
