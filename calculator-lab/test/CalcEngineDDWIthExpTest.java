/**
 * @author Mathana Sreedaran
 * 
 * Test class to investigate if the exponent property of the calculator is working 
 * as intended. Double digits included.
 */
import static org.junit.Assert.*;

import java.util.Stack;

import org.junit.Test;


public class CalcEngineDDWIthExpTest
{

  Stack<String> operatorStack = new Stack<>();
  Stack<String> calcNumStack = new Stack<>();
  Stack<String> calcOpStack = new Stack<>();
  private static final int plusMinusPrecedence = 1;
  private static final int multDividePrecedence = 2;
  private static final int expPrecedence = 3;

  
  @Test
  public void test()
  {

	String infix2 = "((11 + 2) * 3 - 4) * 5"; //ans = 175
	String postfix = toPostfix(infix2);
	assertEquals("11 2 + 3 * 4 - 5 * ", postfix);
	assertFalse(expCheck("-3"));
	parseRPN(postfix); //gives 175, correct


  }
  public boolean expCheck(String expression)
  {
	String[]tokens = splitToTokens(expression);  
	
	int indicator = -1;
	
	for (int i=0; i<tokens.length; i++)
	{
	  
	  if (tokens[i].matches("\\d+.\\d+|\\d+")) 
	  {
		indicator++;
	  }
	  else if ( (tokens[i].equals("+")) || (tokens[i].equals("-"))
		  ||	(tokens[i].equals("*")) || (tokens[i].equals("/")) || (tokens[i].equals("^")) )
	  {
		indicator--;
	  }
	  if (indicator < 0)
	  {
		return false;
	  }
	}
	return true;
  }
  
  public void equals()
  {
	
  }	

  public String toPostfix(String infix)
  {
	String postfix = "";
	String currentElement = "";
	
	infix = infix.replaceAll("\\s+",""); //removing whitespace
	
	String[]tokens = splitToTokens(infix);  
	
	for (int i=0; i<tokens.length; i++)
	{
	  //	  System.out.println(i);
	  currentElement = tokens[i];
	  //	  System.out.println(currentElement);
	  if (currentElement.matches("\\d+.\\d+|\\d+"))
	  {
		postfix += currentElement + " "; 
	  }
	  else if (currentElement.equals("("))
	  {
		operatorStack.push(currentElement);
	  }
	  else if (currentElement.equals(")"))
	  {
		boolean openParen = false;
		
		while ( (!operatorStack.empty()) && (openParen == false))
		{
		  if (!operatorStack.peek().equals("(")) 
		  {
			postfix += operatorStack.pop() + " "; //Pop all operands until reach an open parenthesis
		  }
		  else
		  {
			operatorStack.pop(); //Pop the first open parenthesis seen
			openParen = true;
		  }
		}
	  }
	  else if ( (currentElement.equals("+")) || (currentElement.equals("-"))
		  ||	(currentElement.equals("*")) || (currentElement.equals("/")) || (currentElement.equals("^")) )
	  {
		boolean noPreced = false;

		while ( (!operatorStack.empty()) && (noPreced == false) )
		{		   
		  if (precedenceCheck(currentElement, operatorStack.peek()) < 0)
		  {
			postfix += operatorStack.pop() + " ";
		  } 
		  else
		  {
			noPreced = true;
		  }
		}
		operatorStack.push(currentElement);

	  }	   

	}
	while (!operatorStack.empty())
	{
	  postfix += operatorStack.pop() + " ";
	}
	System.out.println(postfix);
	return postfix;	
  }
  
  public void parseRPN(String postfix)
  {
	String currentElement = "";
	
	postfix = postfix.trim(); //removing leading and trailing whitespaces

	String[]tokens = postfix.split("\\s");
	
	for (int i=0; i<tokens.length; i++)
	{

	  currentElement = tokens[i];
	  
	  if (currentElement.matches("\\d+.\\d+|\\d+")) //If it's a double...
	  {
		calcNumStack.push(currentElement); 
	  }
	  else if ( (currentElement.equals("+")) || (currentElement.equals("-"))
		  ||	(currentElement.equals("*")) || (currentElement.equals("/")) || (currentElement.equals("^")) )
	  {
		if (calcNumStack.size() >= 2)
		{
		  Double operand2 = Double.parseDouble(calcNumStack.pop()); //first popped
		  Double operand1 = Double.parseDouble(calcNumStack.pop());
		  String operator = currentElement;

		  calcNumStack.push(calcSum(operand1, operand2, operator));;
		}
	  }

	}
	System.out.println(calcNumStack.pop()); //Final answer
	
  }
  
  public String calcSum(Double operand1, Double operand2, String operator)
  {
	switch (operator)
	{
	  case "+":
		return Double.toString(operand1 + operand2);
		
	  case "-":
		return Double.toString(operand1 - operand2);
		
	  case "*":
		return Double.toString(operand1 * operand2);
		
	  case "/":
		return Double.toString(operand1 / operand2);
		
	  case "^":
		return Double.toString(Math.pow(operand1, operand2));
		
		
	  default:
		  return("Invalid postfix");
		  
	}
	
  }
  
  
  public String[] splitToTokens(String string)
  {
	return string.split("(?<=-)|(?=-)|(?<=\\+)|(?=\\+)|(?<=\\*)|(?=\\*)|(?<=/)|(?=/)|(?<=\\()|(?=\\()|(?<=\\))|(?=\\))|(?<=\\^)|(?=\\^)"); 
  }
  public int precedenceCheck(String current, String topOfStack)
  {
	int currentVal = 0;
	int topOfStackVal = 0;
	//If current - top = negative, pop top and put current. else, just put current
	switch (current)
	{
	  case "+":
	  case "-":
		currentVal = plusMinusPrecedence;
		break;

	  case "*":
	  case "/":
		currentVal = multDividePrecedence;
		break;
		
	  case "^":
		currentVal = expPrecedence;
		break;

	  default:
		currentVal = 0;
		break;
	}

	switch (topOfStack)
	{
	  case "+":
	  case "-":
		topOfStackVal = plusMinusPrecedence;
		break;

	  case "*":
	  case "/":
		topOfStackVal = multDividePrecedence;
		break;
		
	  case "^":
		currentVal = expPrecedence;
		break;
	  default:
		topOfStackVal = 0;
		break;
	}
	return (currentVal - topOfStackVal);
  }

}
