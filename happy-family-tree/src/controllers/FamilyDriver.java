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

		if (people.containsKey(victim))
		{

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
		else
		{
			System.out.println("This soul belongs to a different kingdom");
		}
	}

	public void noseySwitch(String victim, String key, String val)
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

					case "spouse":
						getSpouse(victim);
						break;

					case "siblings":
						getSiblings(victim);
						break;
		
					case "cousins":
						getCousins(victim);
						break;
		
					case "uncles":
						getFather(victim);
						break;
		
					case "aunties":
						getFather(victim);
						break;
		
					case "grandparents":
						getFather(victim);
						break;

					default:
						System.out.println("Look what you've done! I warned you that this is a deliate process! Ugh");
				}
				break;

			default:
				System.out.println("Look what you've done! I warned you that this is a deliate process! Ugh");

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

	public void getSpouse(String v)
	{
		Person victim = people.get(v);

		if (victim.spouse != null)
		{
			String name = victim.spouse.name;
			Person spouse = people.get(name);
			System.out.println(spouse.name + " spouse of " + v + ". Married on god knows when.");
		}
		else
		{
			System.out.println("Doesn't have a spouse, the poor .....");
		}
	}
	
	public void getSiblings(String v)
	{
		Person victim = people.get(v);

		if ( (victim.father != null) || (victim.mother != null) )
		{

			if (victim.father != null) 
			{
				String name = victim.father.name;
				Person father = people.get(name);
				List<Person> children = father.children;
				System.out.println("Siblings of " + v + ". If none found, none there are");
				for (Person child: children)
				{
					if (!child.name.equals(v))
						System.out.println(child.name + " sibling of " + v + ". Born on " + child.DOB);
				}

			}
			else if (victim.mother != null)
			{
				String name = victim.mother.name;
				Person mother = people.get(name);
				List<Person> children = mother.children;
				System.out.println("Siblings of " + v + ". If none found, none there are");
				for (Person child: children)
				{
					if (!child.name.equals(v))
						System.out.println(child.name + " sibling of " + v + ". Born on " + child.DOB);
				}
			}
		}
		else
		{
			System.out.println("Doesn't have siblings, the blessed soul");
		}
	}
	
	public void getCousins(String v)
	{
		Person victim = people.get(v);

		if ( (victim.father != null) || (victim.mother != null) )
		{

			if (victim.father != null) 
			{
				String fatherName = victim.father.name;
				Person father = people.get(fatherName);

				if ( (father.father != null) || (father.mother != null) )
				{

					if (father.father != null)
					{
						String grandFatherName = father.father.name;
						Person grandfather = people.get(grandFatherName);
						List<Person> uncleAndAunts = grandfather.children;

						for (Person unclaunt: uncleAndAunts)
						{
							List<Person> children = unclaunt.children;
							for (Person child: children)
							{
								if (!child.name.equals(v))
									System.out.println(child.name + " paternal cousin of " + v + ". Born on " + child.DOB);
							}
						}			
					}
					
					else if (father.mother != null)
					{
						String grandMotherName = father.mother.name;
						Person grandmother = people.get(grandMotherName);
						List<Person> uncleAndAunts = grandmother.children;

						for (Person unclaunt: uncleAndAunts)
						{
							List<Person> children = unclaunt.children;
							for (Person child: children)
							{
								if (!child.name.equals(v))
									System.out.println(child.name + " paternal cousin of " + v + ". Born on " + child.DOB);
							}
						}			
					}
									
				}
				else
				{
					System.out.println("No paternal cousins. The lineage is dead");	
				}
			}	
						
			else if (victim.mother != null) 
			{
				String motherName = victim.mother.name;
				Person mother = people.get(motherName);

				if ( (mother.father != null) || (mother.mother != null) )
				{

					if (mother.father != null)
					{
						String grandFatherName = mother.father.name;
						Person grandfather = people.get(grandFatherName);
						List<Person> uncleAndAunts = grandfather.children;

						for (Person unclaunt: uncleAndAunts)
						{
							List<Person> children = unclaunt.children;
							for (Person child: children)
							{
								if (!child.name.equals(v))
									System.out.println(child.name + " maternal cousin of " + v + ". Born on " + child.DOB);
							}
						}			
					}
					
					else if (mother.mother != null)
					{
						String grandMotherName = mother.mother.name;
						Person grandmother = people.get(grandMotherName);
						List<Person> uncleAndAunts = grandmother.children;

						for (Person unclaunt: uncleAndAunts)
						{
							List<Person> children = unclaunt.children;
							for (Person child: children)
							{
								if (!child.name.equals(v))
									System.out.println(child.name + " maternal cousin of " + v + ". Born on " + child.DOB);
							}
						}			
					}
									
				}
				else
				{
					System.out.println("No maternal cousins. The lineage is dead");	
				}
			}	
		}
		else
		{
			System.out.println("Doesn't have cousins, the sad soul");
		}
	}
}
