import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import models.Person;
import utils.Parser;

public class ReadFromFileTest
{

	@Test
	public void testFileReader() throws Exception
	{
		String path = "././testdata/testInput.txt";
		
		Parser parser = new Parser();
		ArrayList<Person> people = parser.parsePersonData(path);
		
		Person person1 = people.get(0);		
		assertEquals("Colby", person1.name);
		assertEquals('M', person1.gender);
		assertEquals(1869, person1.DOB);
		assertEquals("?", person1.mother);
		assertEquals("?", person1.father);
	}

}
