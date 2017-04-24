/**
 * @author Mathana Nair Sreedaran
 * Rule class defining a node in the family tree. 
 */
package models;

import com.google.common.base.Objects;

public class Rule// implements Comparable<Rule>
{

	public String question;
	public Rule yes;
	public Rule no;
	
	
	
	public Rule(String question, Rule yes, Rule no)
	{
		this.question = question;
		this.yes = yes;
		this.no = no;	
	}
	
	/**
	 * Using Google's Guava library
	 */
	@Override
	public boolean equals(final Object obj)
	{
		if (obj instanceof Rule)
		{

			final Rule other = (Rule) obj;
			return (Objects.equal(this.question, other.question));
		}
		else
		{
			return false;
		}
	}

}
