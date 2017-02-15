/**
 * @author Mathana Sreedaran
 * 
 * Class for Spanish-English word pairs
 */
package models;

import com.google.common.base.Objects;
import static com.google.common.base.MoreObjects.toStringHelper;

public class Pair implements Comparable<Pair>
{
	private String english;
	private String spanish;
	
	public Pair(String spa, String eng) 
	{
		this.english = eng;
		this.spanish = spa;
	}


	@Override
	public int compareTo(Pair p)
	{
		if (this.spanish.compareTo(p.spanish) > 0)
			return -1;
		else if (this.spanish.compareTo(p.spanish) < 0)
			return 1;
		else
			return 0;
	}
	
	@Override
	public boolean equals(final Object obj)
	{
		if (obj instanceof Pair)
		{

			final Pair other = (Pair) obj;
			return Objects.equal(this.spanish, other.spanish);
		}
		else
		{
			return false;
		}
	}

	@Override
	public String toString()
	{
		return toStringHelper(this).addValue(spanish)
				.addValue(english).toString();

	}

	@Override  
	public int hashCode()  
	{  
		return Objects.hashCode(this.spanish); 
	}
	
	public String getSpanish()
	{
		return this.spanish;
	}
	
	public String getEnglish()
	{
		return this.english;
	}
}
