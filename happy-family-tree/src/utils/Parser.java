package utils;

import java.io.File;
import edu.princeton.cs.introcs.In;
import models.Person;

public class Parser
{

	public Parser()
	{

	}

	public void parsePersonData(String path) throws Exception
	{

		File usersFile = new File(path);
		In inUsers = new In(usersFile);
		String delim = "\\s"; //whitespace

		while (!inUsers.isEmpty())
		{
			String personDetails = inUsers.readLine();
			String[] personTokens = personDetails.split(delim);

			if (personTokens.length == 5) 
			{
				String personName = personTokens[0];
				char personGender = personTokens[1].charAt(0);
				int personDOB = Integer.parseInt(personTokens[2]);
				
				Person person = new Person(personName, personGender, personDOB);

			}
			else
			{
				throw new Exception("Invalid member length: "+ personTokens.length);
			}
		}
	}
}
