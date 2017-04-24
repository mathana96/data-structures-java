/**
 * @author Mathana Nair Sreedaran
 * This is the driver class of the Vanity program which includes all (or most) functional methods of the program
 */
package controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


import models.Rule;

public class VanityDriver
{
	Map<Integer, Rule> rules = new HashMap<>();		// Map of all rules
	Scanner input;

	public VanityDriver(Map<Integer, Rule> r)
	{
		rules = r;
		input = new Scanner(System.in);
	}

	/**
	 * Recursive method to process the users yes or no and shows them the appropriate next question
	 * @param rule The first rule
	 */
	public void engine(Rule rule)
	{
		if (!rule.question.equals("Take it")) //While undecided
		{
			System.out.println(rule.question);
			System.out.println("Anser by typing 'yes' or 'no'. Anything else will end the world");
			String ans = input.nextLine();

			if (ans.equals("yes"))
				engine(rule.yes);
			else if (ans.equals("no"))
				engine(rule.no);
			else
			{
				System.out.println("Wrong option, start again"); //Quits the system if anything other than yes or no is typed
				System.exit(0);
			}

		}
		else
		{
			System.out.println(rule.question); //The last one, number 20. Take it!
		}
	}

}
