

import java.util.Stack;

public class CheckBalance
{
	boolean isBalanced;
	char nextChar;
	Stack<Character> stack = new Stack<>(); //Stack of open delimiters 
	
	public static void main(String args [])
	{
	  CheckBalance cBalance = new CheckBalance();
	  if (cBalance.checker("Hello World("))
		System.out.println("Is balanced");
	  else
		System.out.println("Not balanced");
	}
	public CheckBalance()
	{	
	}
	
	public boolean checker(String expression)
	{
	  isBalanced = true;
	  int i = 0;
	  
	  while((isBalanced == true) && (i < expression.length()))
	  {
		nextChar = expression.charAt(i);
		//System.out.println(nextChar);

		switch(nextChar)
		{
		  case '(': //Like a gateway where once in, it will fall down and exe code
		  case '{':
		  case '[':
		  	stack.push(nextChar);
		  	break; // will keep falling if no break
		  	
		  case ']':
		  case '}':
		  case ')':
			if (stack.empty()) //missing open delimiter like (, [, {
			{
			  isBalanced = false;
			}
			else
			{
			  char openDelim = stack.peek();
			  if (checkPairs(openDelim, nextChar))
			  {
				stack.pop();
			  }
			  else
			  {
				isBalanced = false;
			  }
			}
			break;
			
		  default:
			break;			
		}
		
		i++; //Increment counter/ pointer on char
	  }
	  
	  if (!stack.empty())
		isBalanced = false;
	  return isBalanced;
		
	}
	
	public boolean checkPairs(char openDelim, char nextChar)
	{
	  return ( (openDelim == '(' && nextChar == ')') 
		  	|| (openDelim == '{' && nextChar == '}')  
		  	|| (openDelim == '[' && nextChar == ']') );

	}
}