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

				Person mom = null;
				Person dad = null;

				if (!mother.equals("?"))
				{
					if (!people.containsKey(mother))
					{
						mom = new Person(mother, 'M', 0, null, null);
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
						dad = new Person(father, 'F', 0, null, null);
						people.put(father, dad);
					}
					else
					{
						dad = people.get(father);
					} 
				}

				if ( dad != null && mom != null )
				{
					mom.spouse = dad;
					dad.spouse = mom;
				}
				else if ( dad != null && mom == null )
				{
					dad.spouse = null;
				}
				else if ( dad == null && mom != null )
				{
					mom.spouse = null;
				}

				Person person = new Person(personName, personGender, personDOB, mom, dad);
				people.put(personName, person);
			}
			else
			{
				throw new Exception("Invalid member length: "+ personTokens.length);
			}
		}

		return people;
	}

}
