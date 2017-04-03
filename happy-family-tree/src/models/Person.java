package models;

import static com.google.common.base.MoreObjects.toStringHelper;

import com.google.common.base.Objects;

public class Person
{

	String name;
	char gender;
	int DOB;
	
	public Person(String name, char gender, int DOB)
	{
		this.name = name;
		this.gender = gender;
		this.DOB = DOB;
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
