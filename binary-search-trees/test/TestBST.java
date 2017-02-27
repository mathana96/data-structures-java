
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestBST
{

	BinarySearchTree tree = new BinarySearchTree();
	
	@Test
	public void testInsert()
	{	
		int[] numbers = {23,76,5,32,2,8,4,9,6};
		for (int num : numbers)
		{
			
			tree.insert(num);
		}
		
		tree.displayTree(); //Pretty print the tree
		
	}
	
	@Test
	public void testSearch()
	{
		int[] numbers = {23,76,5,32,2,8,4,9,6};
		for (int num : numbers)
		{
			
			tree.insert(num);
		}
		int key = 2;
		assertEquals(2, tree.search(key));
		int key2 = 2234;
		assertEquals(0, tree.search(key2));
	}
	
}
