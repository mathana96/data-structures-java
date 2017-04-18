import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Map;

import org.junit.Test;

import models.Person;
import utils.Parser;

public class ReadFromFileTest
{

	@Test
	public void testFileReader() throws Exception
	{
		String path = "././test/test.txt";
		
		Parser parser = new Parser();
		Map<String, Person> people = parser.parsePersonData(path);
		
		Person person1 = people.get("Colby");		
		assertEquals("Colby", person1.name);
		assertEquals('M', person1.gender);
		assertEquals(1869, person1.DOB);
		assertEquals(null, person1.mother);
		assertEquals(null, person1.father);
		
		Person person2 = people.get("Emely");		
		assertEquals("Emely", person2.name);
		assertEquals('F', person2.gender);
		assertEquals(1894, person2.DOB);
		assertEquals("Alondra", person2.mother.name);
		assertEquals("Ruben", person2.father.name);
		
		Person person1smother = person1.mother;
		Person person1sfather = person1.father;
		assertEquals(null, person1smother);
		assertEquals(null, person1sfather);

		Person person2smother = person2.mother;
		Person person2sfather = person2.father;
		assertEquals(person2sfather, person2smother.spouse);
		assertEquals(person2smother, person2sfather.spouse);
		
		Person person3 = people.get("Jaiden");		
		assertEquals("Jaiden", person3.name);
		assertEquals('M', person3.gender);
		assertEquals(1874, person3.DOB);
		assertEquals("Joyce", person3.mother.name);
		assertEquals(null, person3.father);
		
		Person person3smother = person3.mother;
		assertEquals(null, person3smother.spouse);
		
//		System.out.println(people.size());
		Person person4 = people.get("Joyce");
		System.out.println(person4.children.size());
		assertEquals(3, person4.children.size());

	}

}
