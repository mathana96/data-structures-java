package controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.Person;

public class FamilyDriver
{

	private static String path = "././data/input.txt";
	Map<String, Person> people = new HashMap<String, Person>();

	public FamilyDriver(Map<String,Person> p)
	{
		people = p;
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

	public Person getMother(String name)
	{
		Person person = people.get(name);
		return person.mother;
	}

	public Person getFather(String name)
	{
		Person person = people.get(name);
		return person.father;
	}
}
