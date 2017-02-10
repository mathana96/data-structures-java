/**
 * @author mathana
 * 
 * Test class for adding dictionary word pairs
 */
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import models.Pair;

public class AddPairTest
{

	ArrayList<Pair> pairs = new ArrayList<>();
	String[] spanish = {"sobre", "mucho", "amigo", "gracias"};
	String[] english = {"over", "much", "friend", "thanks"};
	
	@Before
	public void setup() throws Exception
	{
		for (int i=0; i<spanish.length; i++)
		{
			Pair pair = new Pair(spanish[i], english[i]);
			pairs.add(pair);
		}
	}
	
	@Test
	public void testAddPair()
	{
		String spanish = "pero";
		String english = "but";
		assertEquals(4, pairs.size());
		
		addPair(spanish, english);
		
		assertEquals(5, pairs.size());
		
		System.out.println(pairs);
	}

	public void addPair(String spanish, String english)
	{
		Pair pair = new Pair(spanish, english);
		pairs.add(pair);
		siftUp();
	}
	
	public void siftUp()
	{
		
		int k = pairs.size() - 1;
		boolean greater = true;
		
		while (k > 0 && greater)
		{
			System.out.println(pairs);
			int p = (k-1)/2;
			Pair child = pairs.get(k);
			Pair parent = pairs.get(p);
			
			if (child.compareTo(parent) > 0)
			{
				pairs.set(p, child);
				pairs.set(k, parent);
				
				k = p;
			}
			else
				greater = false;	
		}
	}

}
