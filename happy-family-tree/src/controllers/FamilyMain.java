package controllers;

import java.util.Map;
import java.util.Scanner;

import models.Person;
import utils.Parser;

public class FamilyMain
{
	private static String path = "././data/input.txt";
	FamilyDriver driver;
	Map<String, Person> people;
	Scanner input;
	
	public FamilyMain() throws Exception
	{	
		Parser parser = new Parser();
		input = new Scanner(System.in);
		people = parser.parsePersonData(path);
		driver = new FamilyDriver(people);
	}
	
	public static void main (String[] args) throws Exception
	{
		FamilyMain main = new FamilyMain();
		main.menuRun();
	}
	
	
	public int menu()
	{
		boolean errorFree = false; //Ensures options entered are correct
		int option = 0;

		while (!errorFree) 
		{
			option = 0;

			try
			{
				System.out.println("\n======TREE Options======\n");

				System.out.println("This system is highly volatile. Any silly input will result in ......\nPick one you fancy below\n");
				System.out.println("1) People of Exclusiveland. BEWARE o' fool that this will list all " + people.size() + " names!");
				System.out.println("2) Retrieve names of the 50 noblefolk of the land");
				System.out.println("3) Be nosey about who's related to who");
				System.out.println("4) Somebody got married!");
				System.out.println("5) Somebody got divorced!");
				System.out.println("6) A poor soul was born, bless them");
				
				System.out.println("\n0) Exit system");


				option = input.nextInt();

				errorFree = true;
			}
			catch (Exception e)
			{
				input.nextLine();
				System.out.println("Your selection is incorrect or not available, please try again");	
			}
		}
		return option;
	}
	
	public void menuRun() throws Exception
	{
		System.out.println("\nWelcome to the Family Tree of the people of Exclusiveland where every name is unique!" + "\nPopulation count: " + people.size() 
				+ "\nRead ALL instructions carefully as you proceed you peasant! Press enter to continue");
		input.hasNextLine();
		
		int option = menu();

		while (option != 0)
		{
			switch(option)
			{
			case 1:
				driver.listAllPeople(); //Calls method for login
				break;

			case 2:
				driver.listNobleFolk();
				break;
				
			case 3:
				System.out.println("hi");
				break;
				
			case 4:
				
				break;
				
			case 5:
				
				break;
				
			case 6:
				
				break;

			default:
				System.out.println("Put on those reading glasses and try again!");
			}
			
			option = menu();

		}
		System.out.println("Goodbye, never come back.");		//The last message when you log out
	}

}
