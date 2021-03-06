/**
 * Utility method to read in information from file and parse it. 
 */
package utils;

import java.io.File;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import models.Person;

public class Parser
{

	Map<String, Person> people = new HashMap<>();

	public Parser()
	{

	}

	/**
	 * Each user is created as an instance of Person. Their parents are processed in the same iteration
	 * Also adds the relationships (father, mother, children, spouse)
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public Map<String, Person> parsePersonData(String path) throws Exception
	{

		File file = new File(path);
		Scanner inUsers = new Scanner(file);
		String delim = "\\s"; //whitespace

		while (inUsers.hasNextLine())
		{
			String personDetails = inUsers.nextLine();
			String[] personTokens = personDetails.split(delim);

			if (personTokens.length == 5) 
			{
				String personName = personTokens[0];
				char personGender = personTokens[1].charAt(0);
				int personDOB = Integer.parseInt(personTokens[2]);
				String mother = personTokens[3];
				String father= personTokens[4];

				Person person = null;
				Person mom = null;
				Person dad = null;

				if (!mother.equals("?"))
				{
					if (!people.containsKey(mother))
					{
						mom = new Person(mother, 'F', 0, null, null);
						people.put(mother, mom);
					}
					else
					{
						mom = people.get(mother);
					}
				}


				if (!father.equals("?"))
				{
					if (!people.containsKey(father))
					{
						dad = new Person(father, 'M', 0, null, null);
						people.put(father, dad);
					}
					else
					{
						dad = people.get(father);
					} 
				}

				
				if (people.containsKey(personName))
				{
					person = people.get(personName);
					person.DOB = personDOB;
					person.gender = personGender;
//					Added these 2 in
					person.mother = mom;
					person.father = dad;
					
				}
				else
				{
					person = new Person(personName, personGender, personDOB, mom, dad);
					people.put(personName, person);
				}
				

				if ( dad != null && mom != null )
				{
					mom.spouse = dad;
					dad.spouse = mom;
					
					mom.children.add(person);
					dad.children.add(person);
				}
				else if ( dad != null && mom == null )
				{
					dad.spouse = null;
					dad.children.add(person);
				}
				else if ( dad == null && mom != null )
				{
					mom.spouse = null;
					mom.children.add(person);
				}

			}
			else
			{
				throw new Exception("Invalid member length: "+ personTokens.length);
			}
		}

		return people;
	}

}
