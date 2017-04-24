/**
 * @author Mathana Nair Sreedaran
 * Utility method to read in information from file and parse it. 
 */
package utils;

import java.io.File;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import models.Rule;

public class Parser
{

	Map<Integer, Rule> rules = new HashMap<>();

	public Parser()
	{
//		empty constructor
	}

	/**
	 * Each user is created as an instance of Person. Their parents are processed in the same iteration
	 * Also adds the relationships (father, mother, children, spouse)
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public Map<Integer, Rule> parseRuleData(String path) throws Exception
	{

		File file = new File(path);
		Scanner inUsers = new Scanner(file);
		String delim = ":"; //whitespace

		while (inUsers.hasNextLine())
		{
			String ruleDetails = inUsers.nextLine();
			String[] ruleTokens = ruleDetails.split(delim);

			if (ruleTokens.length == 4) //For most cases
			{
				Integer ruleNumber = Integer.parseInt(ruleTokens[0]); //Used to identify the rule in the map
				String ruleText = ruleTokens[1];

				Integer ruleYes = Integer.parseInt(ruleTokens[2]);
				Integer ruleNo = Integer.parseInt(ruleTokens[3]);


				Rule currentRule = null; //Initialised to null
				Rule yes = null; //Initialised to null
				Rule no = null; //Initialised to null


				if (!rules.containsKey(ruleYes)) //If current rule's yes number not found in map
				{
					yes = new Rule("", null, null);
					rules.put(ruleYes, yes);
				}
				else
				{
					yes = rules.get(ruleYes);
				}

				if (!rules.containsKey(ruleNo)) //If current rule's no number not found in map
				{
					no = new Rule("", null, null);
					rules.put(ruleNo, no);
				}
				else
				{
					no = rules.get(ruleNo);
				}


				if (!rules.containsKey(ruleNumber)) //If current rule not found in map
				{
					currentRule = new Rule(ruleText, yes, no);
					rules.put(ruleNumber, currentRule);
				}
				else
				{
					Rule rule = rules.get(ruleNumber);
					rule.question = ruleText;
					rule.yes = yes;
					rule.no = no;
				}

			}
			else if (ruleTokens.length == 2) //For the last rule with no options. Does the same as above but without yes and no options
			{
				Integer ruleNumber = Integer.parseInt(ruleTokens[0]);
				String ruleText = ruleTokens[1];

				Rule currentRule = null;
				Rule yes = null;
				Rule no = null;

				if (!rules.containsKey(ruleNumber))
				{
					currentRule = new Rule(ruleText, yes, no);
					rules.put(ruleNumber, currentRule);
				}
				else
				{
					Rule rule = rules.get(ruleNumber);
					rule.question = ruleText;
					rule.yes = yes; //Yes always null
					rule.no = no; //No always null
				}

			}
			else
			{
				throw new Exception("Invalid member length: "+ ruleTokens.length);
			}
		}

		inUsers.close();
		return rules;
	}

}
