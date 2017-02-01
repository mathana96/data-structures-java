import java.util.Stack;

/**
 * The main part of the calculator doing the calculations.
 * 
 * @author  M. Kolling, Mathana Sreedaran
 */
public class CalcEngine
{

	CheckBalance checkBalance = new CheckBalance();
	String displayValue = "";

	Stack<String> operatorStack = new Stack<>();
	Stack<String> calcNumStack = new Stack<>();
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
		if (number.equals("negative")) //If special negative button is pressed
			displayValue += "-"; //Dont add white spaces around it so it can stick to an operand
		else if ( (number.equals("+")) || (number.equals("-"))
				||	(number.equals("*")) || (number.equals("/")) || (number.equals("^")) 
				||	(number.equals("(")) || (number.equals(")")))
		{
			displayValue += " " + number + " "; //Wrap with white space for normal operator
		}	
		else
		{
			displayValue += number; //Normal operand, both float and standard

		}
	}


	/**
	 * The '=' button was pressed.
	 */
	public void equals()
	{
		try
		{
			if (checkBalance.checker(displayValue)) //Check for parenthesis match
			{
				String postfix = toPostfix(displayValue);
				String ans = parseRPN(postfix);
				displayValue = "Postfix: " + postfix + " | Ans: " + ans;
			}
			else
			{
				displayValue = "Parenthesis error. Clear and try again";
			}

		}
		catch (Exception e) //Catch any exceptions which may occur due to incorrect input
		{
			displayValue = "Invalid expression. Clear and try again";
			System.out.println(e); //Print out the exception
		}


	}

	/**
	 * Method to convert infix expression entered by the user to postfix
	 */
	public String toPostfix(String infix)
	{
		String postfix = "";
		String currentElement = "";

		String[]tokens = splitToTokens(infix);  //Split the expression int tokens

		for (int i=0; i<tokens.length; i++)
		{
			currentElement = tokens[i];
			if (currentElement.matches("[-]\\d+.\\d+|[-]\\d+|\\d+.\\d+|\\d+")) //If is a normal digit or floating point digit, both neg or pos
			{
				postfix += currentElement + " "; //Add whitespace
			}
			else if (currentElement.equals("("))
			{
				operatorStack.push(currentElement);
			}
			else if (currentElement.equals(")")) //Perform stack check to ensure parenthesis precedence
			{
				boolean openParen = false;

				while ( (!operatorStack.empty()) && (openParen == false)) //Only if the stack has something
				{
					if (!operatorStack.peek().equals("(")) 
					{
						postfix += operatorStack.pop() + " "; //Pop all operands until reach an open parenthesis
					}
					else //WIll go here when it sees an open parenthesis
					{
						operatorStack.pop(); //Pop the first open parenthesis seen
						openParen = true; //Kick out of loop
					}
				}
			}
			else if ( (currentElement.equals("+")) || (currentElement.equals("-"))
					||	(currentElement.equals("*")) || (currentElement.equals("/")) || (currentElement.equals("^")) )
			{
				boolean noPreced = false;

				while ( (!operatorStack.empty()) && (noPreced == false) ) //Check operator precedence 
				{		   
					if (precedenceCheck(currentElement, operatorStack.peek()) < 0) //If current element does not have greater precedence than stack peek operator
					{
						postfix += operatorStack.pop() + " ";
					} 
					else
					{
						noPreced = true; //Break out of loop
					}
				}
				operatorStack.push(currentElement); //Push the current element either way

			}	   

		}
		while (!operatorStack.empty()) //Remaining stuff in stack
		{
			postfix += operatorStack.pop() + " ";
		}
		System.out.println(postfix);
		return postfix;	
	}

	/**
	 * 
	 * Method to parse the postfix expression and calculate
	 */
	public String parseRPN(String postfix)
	{
		String currentElement = "";

//		postfix = postfix.trim(); //removing leading and trailing whitespace

		String[]tokens = splitToTokens(postfix);

		for (int i=0; i<tokens.length; i++)
		{

			currentElement = tokens[i];

			if (currentElement.matches("[-]\\d+.\\d+|[-]\\d+|\\d+.\\d+|\\d+")) //If it's a normal digit or floating point digit, neg or pos
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

					calcNumStack.push(calcSum(operand1, operand2, operator)); //Calculate the expression
				}
			}

		}
		return calcNumStack.pop(); //Final answer

	}

	/**
	 * 
	 * Perform calcilations on an expression with two operands and one operator
	 */
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
	
	/**
	 * 
	 * Method to check the precedence of operators
	 */
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
			topOfStackVal = expPrecedence;
			break;
		default:
			topOfStackVal = 0;
			break;
		}
		return (currentVal - topOfStackVal); //Returns either positive or negative value
	}

	/**
	 * 
	 * Method to split a string into tokens where whitespace is the delimiter
	 */
	public String[] splitToTokens(String string)
	{
		string.trim();
		return string.split("\\s"); 
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