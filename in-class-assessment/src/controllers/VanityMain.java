/**
 * @author Mathana Nair Sreedaran
 * 
 * Main class of the Vanity program. Contains code for the menu system which calls upon the driver class
 */
package controllers;

import java.util.Map;
import java.util.Scanner;

import models.Rule;
import utils.Parser;

public class VanityMain
{
	private static String path = "././data/input.txt"; //Path to data
	VanityDriver driver;
	Map<Integer, Rule> rules; //Map of rules
	Scanner input;
	
	public VanityMain() throws Exception
	{	
		Parser parser = new Parser();
		input = new Scanner(System.in); //Initialising scanner
		rules = parser.parseRuleData(path); //Getting parsed data from Parser
		driver = new VanityDriver(rules); //Initialising driver
	}
	
	public static void main (String[] args) throws Exception
	{
		VanityMain main = new VanityMain();
		main.menuRun();
	}
	
	/**
	 * Method to display the menu to user of program
	 *
	 */
	public String menu()
	{
		boolean errorFree = false; //Ensures options entered are correct
		String option = "";

		while (!errorFree) 
		{
			option = "";

			try
			{
				System.out.println("\n======Vainity Rules======\n");

				System.out.println("Simply answer 'yes' or 'no'. Other silly answers like 'maybe' will not be entertained!\n");
				System.out.println("Type 'start' to begin");
				System.out.println("\nType 'exit' to exit system");

				option = input.nextLine();

				errorFree = true;
			}
			catch (Exception e)
			{
				input.nextLine();
				System.out.println("Your input is incorrect or not available, please try again");	
			}
		}
		return option;
	}
	
	/**
	 * Method to process the menu selection of user
	 * @throws Exception
	 */
	public void menuRun() throws Exception
	{
		System.out.println("\nWelcome to the VainVanity! Find the perfect vanity number. \nPress enter to continue"); 
//		input.hasNextLine();
		
		String option = menu();

		while (!option.equals("exit"))
		{
			switch(option)
			{
			case "start":
				Rule first = rules.get(1);
				driver.engine(first); //Calls the engine method which asks questions
				break;

			default:
				System.out.println("Try again!");
			}
			
			option = menu();

		}
		System.out.println("BYE!");		//BYE BYE
	}

}
