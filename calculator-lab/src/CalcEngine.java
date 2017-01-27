import java.util.Stack;

/**
 * The main part of the calculator doing the calculations.
 * 
 * @author  M. Kolling 
 * @version 0.1 (incomplete)
 */
public class CalcEngine
{
  char operator;
  int displayValue, operand1;
  String dValue = "";
  private static final int plusMinusPrecedence = 1;
  private static final int multDividePrecedence = 2;
  Stack<Character> operatorStack = new Stack<>();
  /**
   * Create a CalcEngine instance. Initialise its state so that it is ready 
   * for use.
   */
  public CalcEngine()
  {
	operator =' ';
	displayValue=0;
	operand1 = 0;
  }

  /**
   * Return the value that should currently be displayed on the calculator
   * display.
   */
  public String getDisplayValue()
  {
	return(dValue);
  }

  /**
   * A number button was pressed. Do whatever you have to do to handle it.
   * The number value of the button is given as a parameter.
   */
  public void numberPressed(String number)
  {
	dValue += number;
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
	String postfix = "";
	Character currentChar;

	dValue = dValue.replaceAll("\\s+",""); //removing whitespace
	//	System.out.println(infix);
	for (int i=0; i<dValue.length() ; i++)
	{
	  //	  System.out.println(i);
	  currentChar = dValue.charAt(i);
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

	dValue = "Postfix: " + postfix;

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

  /**
   * The 'C' (clear) button was pressed.
   */
  public void clear()
  {
	dValue = "";
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