/**
 * Rule class defining a node in the family tree. 
 */
package models;

import static com.google.common.base.MoreObjects.toStringHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

//	@Override
//	public int compareTo(Rule Rule)
//	{
//		if (this.name.compareTo(Rule.name)>0)
//			return 1;
//		else if (this.name.compareTo(Rule.name)<0)
//			return -1;
//		else 
//			return 0;
//	}
//	
//	@Override
//	public String toString()
//	{
//		return toStringHelper(this).addValue(name)
//				.addValue(gender).addValue(DOB).toString();
//
//	}
}
