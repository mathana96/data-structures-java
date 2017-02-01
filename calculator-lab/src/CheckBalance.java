import java.util.Stack;

public class CheckBalance
{
	boolean isBalanced;
	String nextChar;
	Stack<String> stack = new Stack<>(); //Stack of open delimiters 

	public static void main(String args [])
	{
		CheckBalance cBalance = new CheckBalance();
		if (cBalance.checker("Hello World ( )  )"))
			System.out.println("Is balanced");
		else
			System.out.println("Not balanced");
	}

	public boolean checker(String expression)
	{
		isBalanced = true;
		int i = 0;
		String[] tokens = splitToTokens(expression);
		
		while(i < tokens.length)
		{
			nextChar = tokens[i];
			//System.out.println(nextChar);

			switch(nextChar)
			{
			case "(": //Like a gateway where once in, it will fall down and exe code
			case "{":
			case "[":
				stack.push(nextChar);
				break; // will keep falling if no break

			case "]":
			case "}":
			case ")":
				if (stack.empty()) //missing open delimiter like (, [, {
				{
					return false;
				}
				else
				{
					String openDelim = stack.peek();
					if (checkPairs(openDelim, nextChar))
					{
						stack.pop();
					}
					else
					{
						return false;
					}
				}
				break;

			default:
				break;			
			}

			i++; //Increment counter/ pointer on char
		}

		if (!stack.empty())
		{
			stack.clear();
			return false;
		}
			
		return true;

	}

	public boolean checkPairs(String openDelim, String nextChar)
	{
		if ( (openDelim.equals("(")) && (nextChar.equals(")")) )
		{
			return true;
		}
		return false;

	}
	
	public String[] splitToTokens(String string)
	{
		string.trim();
		return string.split("\\s"); 
	}
}