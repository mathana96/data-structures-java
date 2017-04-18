package controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import models.Person;

public class FamilyDriver
{

	private static String path = "././data/input.txt";
	Map<String, Person> people = new HashMap<String, Person>();
	Scanner input;

	public FamilyDriver(Map<String,Person> p)
	{
		people = p;
		input = new Scanner(System.in);
	}

	public void listAllPeople()
	{
		for (Person person : people.values()) 
		{
			System.out.println(person.name + " " + person.gender + " " + person.DOB);
		}
	}

	public void listNobleFolk()
	{
		List<Person> noble = new ArrayList<>(people.values());
		Collections.shuffle(noble);

		for (int i=0; i<50; i++)
		{
			Person person = noble.get(i);
			System.out.println(person.name + " " + person.gender + " " + person.DOB);
		}
	}

	public void nosey()
	{
		System.out.println("Why you nosey little ...\nThis process is delicate. Be gentle with those peasantly hands of yours!");
		System.out.println("\nSpeaketh the name of the poor fool who you wish to pry upon");
		String victim = input.nextLine().toLowerCase();
		victim = victim.substring(0, 1).toUpperCase() + victim.substring(1);

		System.out.println("\nExcellent... Now read the following carefully as these are precise spells which only work if you say them right!");

		System.out.println("\n\n***************Spell Guide (Note: Grammar is still evolving)***************");

		System.out.println("\nThe 'who' spell. Chant 'who' followed by one these words.");
		System.out.println("(mother, father, siblings, cousins, spouse, grandparents, uncles, aunties");

		String answer = input.nextLine();
		String[] ansTokens = answer.split("\\s");
		if (ansTokens.length == 2)
		{
			String key = ansTokens[0].toLowerCase();
			String val = ansTokens[1].toLowerCase();
			noseySwitch(victim, key, val);
		}
		else
		{
			System.out.println("Unruly peasant! The spell didn't work HA!");
		}

	}

	public void noseySwitch(String victim, String key, String val)
	{
		if (people.containsKey(victim))
		{
			switch(key)
			{
				case "who":
					switch (val)
					{
						case "mother":
							getMother(victim);
							break;
							
						case "father":
							getFather(victim);
							break;
						
						default:
							System.out.println("Look what you've done! I warned that this is a deliate process! Ugh");
					}
				
				default:
					System.out.println("Look what you've done! I warned that this is a deliate process! Ugh");

			}
		}
		else
		{
			System.out.println("This soul belongs to a different kingdom");
		}

	}
	public void getMother(String v)
	{
		Person victim = people.get(v);
		
		if (victim.mother != null)
		{
			String name = victim.mother.name;
			Person mama = people.get(name);
			System.out.println(mama.name + " mother of " + v + ". Came to be on " + mama.DOB);
		}
		else
		{
			System.out.println("Doesn't have a mother, the poor .....");
		}
	}

	public void getFather(String v)
	{
		Person victim = people.get(v);
		
		if (victim.father != null)
		{
			String name = victim.father.name;
			Person papa = people.get(name);
			System.out.println(papa.name + " father of " + v + ". Came to be on " + papa.DOB);
		}
		else
		{
			System.out.println("Doesn't have a father, the poor .....");
		}
	}
}
