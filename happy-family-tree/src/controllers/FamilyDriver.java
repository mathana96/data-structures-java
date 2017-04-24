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

import models.Person;

public class FamilyDriver
{

	private static String path = "././data/input.txt"; //Path to data
	Map<String, Person> people = new HashMap<String, Person>();		// Map of all people in the kingdom
	Scanner input;

	public FamilyDriver(Map<String,Person> p)
	{
		people = p;
		input = new Scanner(System.in);
	}

	/**
	 * Method to list all the people in the kingdom!
	 */
	public void listAllPeople()
	{
		for (Person person : people.values()) 
		{
			System.out.println(person.name + " " + person.gender + " " + person.DOB);
		}
	}

	/**
	 * A special method to list "noblefolk" which are essentially n random people from the kingdom 
	 */
	public void listNobleFolk()
	{
		List<Person> noble = new ArrayList<>(people.values());
		Collections.shuffle(noble); //Shuffle to make the returned valued random at each function call

		for (int i=0; i<5; i++)
		{
			Person person = noble.get(i);
			System.out.println(person.name + " " + person.gender + " " + person.DOB);
		}
	}

	/**
	 * The method for the nosey option in the menu. Allows users to extract information about people in the population
	 */
	public void nosey()
	{
		System.out.println("Why you nosey little ...\nThis process is delicate. Be gentle with those peasantly hands of yours!");
		System.out.println("\nSpeaketh the name of the poor fool who you wish to pry upon");
		String victim = input.nextLine().toLowerCase(); //Input the name of the person you wish to pry upon
		victim = victim.substring(0, 1).toUpperCase() + victim.substring(1);

		if (people.containsKey(victim)) //Execute only if the person exists in the list/kingdom
		{

			System.out.println("Here are some personal details of " + victim + " you slimy scum!");
			Person person = people.get(victim);
			System.out.println(victim + " born on " + person.DOB + ". Classified as '" + person.gender + "'");
			System.out.println("\nExcellent... Now read the following carefully as these are precise spells which only work if you say them right!");

			System.out.println("\n\n***************Spell Guide (Note: Grammar is still evolving)***************");

			System.out.println("\nThe 'who' spell. Chant 'who' followed by one these words.");
			System.out.println("(mother, father, siblings, cousins, spouse, grandparents, uncles, aunties, children)");

			
			String answer = input.nextLine();
			String[] ansTokens = answer.split("\\s"); //Spliting the spell into two distinct words
			if (ansTokens.length == 2)
			{
				String key = ansTokens[0].toLowerCase(); //Ensuring lowercase
				String val = ansTokens[1].toLowerCase(); //Ensuring lowercase
				noseySwitch(victim, key, val); //Send spell input to switch
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

	/**
	 * Nosey switch method. Gets appropriate information in relation to "spell"
	 * @param victim The person under scrutiny 
	 * @param key First word of spell
	 * @param val Second word of spell
	 */
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
						getUncles(victim);
						break;
		
					case "aunties":
						getAunties(victim);
						break;
		
					case "grandparents":
						getGrandparents(victim);
						break;
						
					case "children":
						getChildren(victim);
						break;

					default:
						System.out.println("Look what you've done! I warned you that this is a deliate process! Ugh");
				}
				break;

			default:
				System.out.println("Look what you've done! I warned you that this is a deliate process! Ugh");

		}


	}
	
	/**
	 * Method to build spouse relationship for two people who get married
	 */
	public void marry()
	{
		System.out.println("Festive times ahead! Feasts and bells hohoho\nBe sure to wash yourself peasant!");

		System.out.println("\nSpeaketh the name of the bride");
		String bride = input.nextLine().toLowerCase(); //Enter bride's name
		bride = bride.substring(0, 1).toUpperCase() + bride.substring(1);

		System.out.println("\nSpeaketh the name of the groom");
		String groom = input.nextLine().toLowerCase(); //Enter grooms name
		groom = groom.substring(0, 1).toUpperCase() + groom.substring(1);

		if ( (people.containsKey(bride)) && (people.containsKey(groom)) ) //Execute only if bride and groom are in the kingdom
		{
			Person girl = people.get(bride);
			Person guy = people.get(groom);
			
			girl.spouse = guy;
			guy.spouse = girl;
			
			System.out.println("\nHere! Here! " + bride + " and " + groom + " are now husband and wife!");		
		}
		else
		{
			System.out.println("How dare you! Find a bride/groom within this kingdom!"); //LOL
		}


	}
	
	/**
	 * Method to add a new person to the kingdom. Defines as a newborn with parents who are from the kingdom. If not, null defined for parents
	 * @throws IOException
	 */
	public void newborn() throws IOException
	{
		System.out.println("New child to the kingdom!");

		Person mom = null; //Mother initialised as null
		Person dad = null; //Father initialised as null
		
		System.out.println("\nSpeaketh the name of the child");
		String name = input.nextLine().toLowerCase(); //Name of the new person/newborn
		name = name.substring(0, 1).toUpperCase() + name.substring(1); //Change to key convention
		
		if (!people.containsKey(name)) //Execute only if the name is unique
		{
			

			System.out.println("\nIs the child a 'M' or a 'F'?");
			char gender = input.nextLine().toUpperCase().charAt(0); //Gender of child

			System.out.println("\nYear the child is born");
			int DOB = input.nextInt(); //DOB of child

			input.nextLine(); //Scanner bug lol. Eat next line, otherwise stalls

			System.out.println("\nWho is the mother of this child?");
			String mother = input.nextLine().toLowerCase(); //Name of mother
			mother = mother.substring(0, 1).toUpperCase() + mother.substring(1);

			if (people.containsKey(mother))
			{
				mom = people.get(mother);
			}
			else
				System.out.println("WHAT?");

			System.out.println("\nWho is the father of this child?");
			String father = input.nextLine().toLowerCase(); //Name of father
			father = father.substring(0, 1).toUpperCase() + father.substring(1);

			if (people.containsKey(father))
			{
				dad = people.get(father);
			}
			else
				System.out.println("WHAT?");

			Person newborn = new Person(name, gender, DOB, mom, dad); //New Person object
			people.put(name, newborn); //Put person into map of people

			writeToFile(name, gender, DOB, mother, father); //Update text file
			System.out.println("Welcome, child!");
			System.exit(0);
		}
		else
		{
			System.out.println("A person by the name of " + name + " already exists. Pick another or this poor child and it's family shall perish!");
		}
	}
	
	/**
	 * Method to write information to a predefined file
	 * @param name Name of new person
	 * @param gender Gender of new person
	 * @param DOB DOB of new person
	 * @param mother Mother's name of new person
	 * @param father Father's name of new person
	 * @throws IOException
	 */
	public void writeToFile(String name, char gender, int DOB, String mother, String father) throws IOException
	{
		 File file = new File(path);
		 
		 FileWriter writer = new FileWriter(file, true); //True means append, don't overwrite
		 
		 
		 writer.write("\n" + name + " " + gender + " " + DOB + " " + mother + " " + father); 
     writer.flush();
     writer.close();
     
	}
	
	/**
	 * Get mother of victim
	 * @param v Stands for victim
	 */
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

	/**
	 * Get father of victim
	 * @param v stands for victim
	 */
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

	/**
	 * Get spouse of victim
	 * @param v is Victim
	 */
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
	
	/**
	 * Get siblings of victim. Calculated by getting either mother/father and printing their children except for victim
	 * @param v
	 */
	public void getSiblings(String v)
	{
		Person victim = people.get(v);

		if ( (victim.father != null) || (victim.mother != null) ) //Either one cannot be null, otherwise orphan
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
	
	/**
	 * Get victims cousins. Done by getting mother and father. Getting their siblings. Then print their children.
	 * @param v Victim
	 */
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
							if (!unclaunt.equals(victim.father))
							{
								List<Person> children = unclaunt.children;
								for (Person child: children)
								{
									System.out.println(child.name + " paternal cousin of " + v + ". Born on " + child.DOB);
								}
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
						
			if (victim.mother != null) 
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

//						System.out.println("Grandpa " + grandFatherName);
//						System.out.println(uncleAndAunts);
						for (Person unclaunt: uncleAndAunts)
						{
						
							if (!unclaunt.equals(victim.mother))
							{
								List<Person> children = unclaunt.children;
								for (Person child : children)
								{
									System.out.println(child.name + " maternal cousin of " + v + ". Born on " + child.DOB);
								} 
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
	
	/**
	 * Get uncles of victim. Done by getting mother and father. Then, get their male siblings
	 * @param v Victim
	 */
	public void getUncles(String v)
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
							if ( (unclaunt.gender == 'M') && (!unclaunt.name.equals(victim.father.name)) )
								System.out.println(unclaunt.name + " paternal uncle of " + v + ". Born on " + unclaunt.DOB);
						}			
					}
					
					else if (father.mother != null)
					{
						String grandMotherName = father.mother.name;
						Person grandmother = people.get(grandMotherName);
						List<Person> uncleAndAunts = grandmother.children;

						for (Person unclaunt: uncleAndAunts)
						{
							if ( (unclaunt.gender == 'M') && (!unclaunt.name.equals(victim.father.name)) )
								System.out.println(unclaunt.name + " paternal uncle of " + v + ". Born on " + unclaunt.DOB);
						
						}			
					}
									
				}
				else
				{
					System.out.println("No paternal uncles. Kingdom is yours");	
				}
			}	
			if (victim.mother != null) 
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
							if ( (unclaunt.gender == 'M') && (!unclaunt.name.equals(victim.mother.name)) )
								System.out.println(unclaunt.name + " maternal uncle of " + v + ". Born on " + unclaunt.DOB);
						
						}			
					}
					
					else if (mother.mother != null)
					{
						String grandMotherName = mother.mother.name;
						Person grandmother = people.get(grandMotherName);
						List<Person> uncleAndAunts = grandmother.children;

						for (Person unclaunt: uncleAndAunts)
						{
							if ( (unclaunt.gender == 'M') && (!unclaunt.name.equals(victim.father.name)) )
								System.out.println(unclaunt.name + " maternal uncle of " + v + ". Born on " + unclaunt.DOB);
						
						}			
					}
									
				}
				else
				{
					System.out.println("No maternal uncles. Kingdom is yours!");	
				}
			}	
		}
		else
		{
			System.out.println("Doesn't have uncles or aunts, what on earth?");
		}
	}
	
	/**
	 * Get aunties of victim. Done by getting mother and father. Then, get their female siblings
	 * @param v Victim
	 */
	public void getAunties(String v)
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
							if ( (unclaunt.gender == 'F') && (!unclaunt.name.equals(victim.father.name)) )
								System.out.println(unclaunt.name + " paternal aunty of " + v + ". Born on " + unclaunt.DOB);
						}			
					}
					
					else if (father.mother != null)
					{
						String grandMotherName = father.mother.name;
						Person grandmother = people.get(grandMotherName);
						List<Person> uncleAndAunts = grandmother.children;

						for (Person unclaunt: uncleAndAunts)
						{
							if ( (unclaunt.gender == 'F') && (!unclaunt.name.equals(victim.father.name)) )
								System.out.println(unclaunt.name + " paternal aunty of " + v + ". Born on " + unclaunt.DOB);
						
						}			
					}
									
				}
				else
				{
					System.out.println("No paternal aunties. Kingdom is yours");	
				}
			}	
			if (victim.mother != null) 
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
							if ( (unclaunt.gender == 'F') && (!unclaunt.name.equals(victim.mother.name)) )
								System.out.println(unclaunt.name + " maternal aunty of " + v + ". Born on " + unclaunt.DOB);
						
						}			
					}
					
					else if (mother.mother != null)
					{
						String grandMotherName = mother.mother.name;
						Person grandmother = people.get(grandMotherName);
						List<Person> uncleAndAunts = grandmother.children;

						for (Person unclaunt: uncleAndAunts)
						{
							if ( (unclaunt.gender == 'F') && (!unclaunt.name.equals(victim.father.name)) )
								System.out.println(unclaunt.name + " maternal aunty of " + v + ". Born on " + unclaunt.DOB);
						
						}			
					}
									
				}
				else
				{
					System.out.println("No maternal aunties. Kingdom is yours!");	
				}
			}	
		}
		else
		{
			System.out.println("Doesn't have uncles or aunts, what on earth?");
		}
	}
	
	/**
	 * Get grandparents of victim. Done by getting mother and father. Then, get their parents
	 * @param v Victim
	 */
	public void getGrandparents(String v)
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
						System.out.println(grandfather.name + " paternal grandfather of " + v + ". Born on " + grandfather.DOB);
						
					}
					
					if (father.mother != null)
					{
						String grandMotherName = father.mother.name;
						Person grandmother = people.get(grandMotherName);
						System.out.println(grandmother.name + " paternal grandmother of " + v + ". Born on " + grandmother.DOB);			
					}
									
				}
				else
				{
					System.out.println("They're watching from up above the paternal side");	
				}
			}	
			if (victim.mother != null) 
			{
				String motherName = victim.mother.name;
				Person mother = people.get(motherName);

				if ( (mother.father != null) || (mother.mother != null) )
				{

					if (mother.father != null)
					{
						String grandFatherName = mother.father.name;
						Person grandfather = people.get(grandFatherName);
						System.out.println(grandfather.name + " maternal grandfather of " + v + ". Born on " + grandfather.DOB);
						
									
					}
					
					if (mother.mother != null)
					{
						String grandMotherName = mother.mother.name;
						Person grandmother = people.get(grandMotherName);
						System.out.println(grandmother.name + " maternal grandmother of " + v + ". Born on " + grandmother.DOB);
								
					}
									
				}
				else
				{
					System.out.println("They're watching from up above the maternal side");	
				}
			}	
		}
		else
		{
			System.out.println("Doesn't have uncles or aunts, what on earth?");
		}
	}
	
	/**
	 * Get victims children. Simple print array list by iterating. 
	 * @param v Victim
	 */
	public void getChildren(String v)
	{
		Person victim = people.get(v);
		if (!victim.children.isEmpty())
		{
			for (Person child: victim.children)
			{
				System.out.println(child.name + " child of " + v + ". Born on " + child.DOB);
			}
		}
	}
	
	
}
