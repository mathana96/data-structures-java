/**
 * @author mathana
 * 
 * Test class for the sifting up and sifting down in a heap
 */
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SiftTest
{
	ArrayList<Integer> numbers = new ArrayList<>();

	@Before
	public void setup()
	{
		//Populating an ArrayList
		for (int i=10; i>=0; i--)
		{
			numbers.add(i);
		}
	}
	
	@After
	public void teardown()
	{
		numbers.clear();
	}
	
	//@Test
	public void testSiftUp()
	{
		numbers.add(30);
		siftUp();
		System.out.println(numbers);
	}
	
	@Test
	public void testSiftDown()
	{
		numbers.add(0,1);
		siftDown();
		System.out.println(numbers);
	}
	/**
	 * @param k is a child node
	 * @param p is a parent node
	 */
	public void siftUp()
	{
		boolean greater = true;
		int k = numbers.size() - 1;
		
		while (k > 0 && greater)
		{
			System.out.println(numbers);

			int p = (k-1)/2;
			Integer child = numbers.get(k);
			Integer parent = numbers.get(p);
			
			if (child.compareTo(parent) > 0)
			{
				numbers.set(p, child);
				numbers.set(k, parent);
				
				
				k = p;
			}
			else
				greater = false;
			
		}
	}

	public void siftDown()
	{
		int k = 0;
		int l = 2*k+1;
		boolean greater = true;
		
		while(l < numbers.size() && greater)
		{
			System.out.println(numbers);
			int max = l, r = l+1;
			if (r < numbers.size()) //Avoiding indexOutOfBounds
			{
				//Left child and right child comparison
				if (numbers.get(r).compareTo(numbers.get(l)) > 0) //If right > left
					max++; //Max becomes right
			}
			if (numbers.get(k).compareTo(numbers.get(max)) < 0) //Parent and child comparison
			{
				int temp = numbers.get(k);
				numbers.set(k, numbers.get(max));
				numbers.set(max, temp);
				
				k = max; //Updating k
				l = 2*k+1; //Updating new left child
			}
			else
				greater = false;
		}
	}
}
