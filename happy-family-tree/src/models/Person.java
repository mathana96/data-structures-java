package models;

import static com.google.common.base.MoreObjects.toStringHelper;

import java.util.ArrayList;

import com.google.common.base.Objects;

public class Person
{

	public String name;
	public char gender;
	public int DOB;
	public String mother;
	public String father;
	
	ArrayList<Relation> relations = new ArrayList<>();
	
	public Person(String name, char gender, int DOB, String mother, String father)
	{
		this.name = name;
		this.gender = gender;
		this.DOB = DOB;
		this.mother = mother;
		this.father = father;
		
	}
	
	@Override
	public boolean equals(final Object obj)
	{
		if (obj instanceof Person)
		{

			final Person other = (Person) obj;
			return (Objects.equal(this.name, other.name) && Objects.equal(this.DOB, other.DOB));
		}
		else
		{
			return false;
		}
	}

//	@Override
	public int compareTo(Person Person)
	{
		if (this.name.compareTo(Person.name)>0)
			return 1;
		else if (this.name.compareTo(Person.name)<0)
			return -1;
		else 
			return 0;
	}
	
	@Override
	public String toString()
	{
		return toStringHelper(this).addValue(name)
				.addValue(gender).addValue(DOB).toString();

	}
}
