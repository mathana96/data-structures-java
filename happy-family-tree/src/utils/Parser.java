package utils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

import edu.princeton.cs.introcs.In;
import models.Person;

public class Parser
{

	ArrayList<Person> people = new ArrayList<>();
	
	
	public Parser()
	{

	}

	public ArrayList<Person> parsePersonData(String path) throws Exception
	{

		Stream<String> stream = Files.lines(Paths.get(path));
		
//
//			
//		File usersFile = new File(path);
//		In inUsers = new In(usersFile);
		String delim = "\\s"; //whitespace

		stream.forEach(
		while (!inUsers.isEmpty())
		{
			String personDetails = inUsers.readLine();
			String[] personTokens = personDetails.split(delim);

			if (personTokens.length == 5) 
			{
				String personName = personTokens[0];
				char personGender = personTokens[1].charAt(0);
				int personDOB = Integer.parseInt(personTokens[2]);
				String mother = personTokens[3];
				String father= personTokens[4];
				
				Person person = new Person(personName, personGender, personDOB, mother, father);
				people.add(person);
			}
			else
			{
				throw new Exception("Invalid member length: "+ personTokens.length);
			}
		}
		
		return people;
	}
}
