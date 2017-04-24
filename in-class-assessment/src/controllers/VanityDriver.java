/**
 * @author Mathana Nair Sreedaran
 * This is the driver class of the Family Tree program which includes all (or most) functional methods of the program
 */
package controllers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


import models.Rule;

public class VanityDriver
{

	private static String path = "././data/input.txt"; //Path to data
	Map<Integer, Rule> rules = new HashMap<>();		// Map of all people in the kingdom
	Scanner input;

	public VanityDriver(Map<Integer, Rule> r)
	{
		rules = r;
		input = new Scanner(System.in);
	}

	public void engine(Rule rule)
	{
		if (!rule.question.equals("Take it"))
		{
			System.out.println(rule.question);
			System.out.println("Anser by typing 'yes' or 'no'");
			String ans = input.nextLine();

			if (ans.equals("yes"))
				engine(rule.yes);
			else if (ans.equals("no"))
				engine(rule.no);
			else
			{
				System.out.println("Wrong option, start again");
				System.exit(0);
			}

		}
		else
		{
			System.out.println(rule.question);
		}
	}

}
