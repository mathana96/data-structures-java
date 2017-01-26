import static org.junit.Assert.*;

import java.util.Stack;

import org.junit.Test;

public class CalcEngineSingleDigitTest
{

  private static final int plusMinusPrecedence = 1;
  private static final int multDividePrecedence = 2;
  //  private static final int dividePrecedence = 2;


  Stack<Character> operatorStack = new Stack<>();
  
  
  String finalAnswer = "";
  

  @Test
  public void testRPNWithoutParenthesis()
  {
	String infix = "3 * 4 + 6";
	assertEquals("34*6+", toPostfixSimple(infix));
	
	String infix2 = "3 - 4 / 6";
	assertEquals("346/-", toPostfixSimple(infix2));
  }

  @Test
  public void testRPNWithParenthesis()
  {
//	String infix = "3 * (4 + 6)";
//	assertEquals("346+*", toPostfixModerate(infix));
	
	String infix2 = "((1 + 2) * 3 - 4) * 5";
	assertEquals("12+3*4-5*", toPostfixModerate(infix2));
  }

  public String toPostfixSimple(String infix)
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


  public String toPostfixModerate(String infix)
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
	  else if (currentChar.equals('('))
	  {
		operatorStack.push(currentChar);
	  }
	  else if (currentChar.equals(')'))
	  {
		boolean openParen = false;
		
		while ( (!operatorStack.empty()) && (openParen == false))
		{
		  if (operatorStack.peek() != '(') 
		  {
			postfix += Character.toString(operatorStack.pop()); //Pop all operands until reach an open parenthesis
		  }
		  else
		  {
			operatorStack.pop(); //Pop the first open parenthesis seen
			openParen = true;
		  }
		}
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

//  public String calcPostfix(String postfix)
//  {
//	int operand1;
//	int operand2;
//	String operator = "";
//	
//	parsePostfix(postfix);
//	
//	while (condition)
//	{
//	  if (calcNumStack.size() >= 2)
//	  {
//		operand1 = Character.getNumericValue(calcNumStack.pop());
//		operand2 = Character.getNumericValue(calcNumStack.pop());
//
//		if (operatorStack.size() >= 1)
//		{
//		  operator = Character.toString(calcOpStack.pop());
//		}
//	  }
//	  
//	  double sum = calcSum(operand1, operand2, operator);
//	  if (sum != 0.0)
//	  {
//		calcNumStack.push(sum);
//	  }
//	}
//  }
  
  public double calcSum(int operand1, int operand2, String operator)
  {
	switch (operator)
	{
	  case "+":
		return operand1 + operand2;
		
	  case "-":
		return operand1 - operand2;
		
	  case "*":
		return operand1 * operand2;
		
	  case "/":
		return operand1 / operand2;
		
	  default:
		  System.out.println("Invalid postfix");
		  return 0;
	}
	
  }
  public void parsePostfix(String postfix)
  {
	Character currentChar;
//	for (int i=0; i<postfix.length(); i++)
//	{
//	  currentChar = postfix.charAt(i);
//	  
//	  if (Character.isDigit(currentChar))
//	  {
//		calcNumStack.push(currentChar);
//	  }
//	  else if ( (currentChar.equals('+')) || (currentChar.equals('-'))
//		  ||	(currentChar.equals('*')) || (currentChar.equals('/')) )
//	  {
//		calcOpStack.push(currentChar);
//	  }	 
//	}
	
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
