import java.util.Stack;

/**
 * The main part of the calculator doing the calculations.
 * 
 * @author  M. Kolling 
 * @version 0.1 (incomplete)
 */
public class CalcEngine
{

  String displayValue = "";
  
  Stack<String> operatorStack = new Stack<>();
  Stack<String> calcNumStack = new Stack<>();
  Stack<String> calcOpStack = new Stack<>();
  private static final int plusMinusPrecedence = 1;
  private static final int multDividePrecedence = 2;
  private static final int expPrecedence = 3;

  /**
   * Create a CalcEngine instance. Initialise its state so that it is ready 
   * for use.
   */
  public CalcEngine()
  {
	
  }

  /**
   * Return the value that should currently be displayed on the calculator
   * display.
   */
  public String getDisplayValue()
  {
	return(displayValue);
  }

  /**
   * A number button was pressed. Do whatever you have to do to handle it.
   * The number value of the button is given as a parameter.
   */
  public void numberPressed(String number)
  {
	displayValue += number;
  }

  //    /**
  //     * The 'plus' button was pressed. 
  //     */
  //    public void plus()
  //    {
  //       operand1 = displayValue;
  //	   displayValue = 0;
  //       operator = '+';
  //    }
  //    
  //    /**
  //     * The 'minus' button was pressed.
  //     */
  //    public void minus()
  //    {
  //        operand1 = displayValue;
  //	   displayValue = 0;
  //       operator = '-'; 
  //    }
  //
  //public void multiply()
  //    {
  //        operand1 = displayValue;
  //	   displayValue = 0;
  //       operator = '*'; 
  //    }
  //
  //public void divide()
  //    {
  //        operand1 = displayValue;
  //	   displayValue = 0;
  //       operator = '/'; 
  //    }

  /**
   * The '=' button was pressed.
   */
  public void equals()
  {
	String postfix = toPostfix(displayValue);
	String ans = parseRPN(postfix);
	displayValue = "Postfix: " + postfix + " Ans: " + ans;

  }



  public String toPostfix(String infix)
  {
	String postfix = "";
	String currentElement = "";

	infix = infix.replaceAll("\\s+",""); //removing whitespace

	String[]tokens = infix.split("(?<=-)|(?=-)|(?<=\\+)|(?=\\+)|(?<=\\*)|(?=\\*)|(?<=/)|(?=/)|(?<=\\()|(?=\\()|(?<=\\))|(?=\\))|(?<=\\^)|(?=\\^)"); ; 

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
  
  public String parseRPN(String postfix)
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
	return calcNumStack.pop(); //Final answer
	
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

  /**
   * The 'C' (clear) button was pressed.
   */
  public void clear()
  {
	displayValue = "";
	//operand1 = 0;

  }

  /**
   * Return the title of this calculation engine.
   */
  public String getTitle()
  {
	return("'Cool'culator");
  }

  /**
   * Return the author of this engine. This string is displayed as it is,
   * so it should say something like "Written by H. Simpson".
   */
  public String getAuthor()
  {
	return("Built by: Various cool people");
  }

  /**
   * Return the version number of this engine. This string is displayed as 
   * it is, so it should say something like "Version 1.1".
   */
  public String getVersion()
  {
	return("Ver. Ayam Percik");
  }

}