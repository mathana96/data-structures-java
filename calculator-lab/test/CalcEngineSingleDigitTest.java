import static org.junit.Assert.*;

import java.util.Stack;

import org.junit.Test;

public class CalcEngineSingleDigitTest
{

  private char operator;
  private int displayValue, operand1;
  
  private static final int plusMinusPrecedence = 1;
//  private static final int minusPrecedence = 1;
  private static final int multDividePrecedence = 2;
//  private static final int dividePrecedence = 2;
  
  
  Stack<Character> operatorStack = new Stack<>();
  
  @Test
  public void testRPNWithoutParenthesis()
  {
	String infix = "3 * 2 - 1";
	assertEquals("32*1-", toPostfix(infix));
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
		if (!operatorStack.empty())
		{
		  while (precedenceCheck(currentChar, operatorStack.peek()) < 0) //current char < what's on stack
		  {
			postfix.concat(Character.toString(operatorStack.pop()));
			//		  operatorStack.push(currentChar);
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
	int currentVal;
	int topOfStackVal;
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
	
	switch (current)
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
