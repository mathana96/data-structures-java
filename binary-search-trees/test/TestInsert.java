import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Stack;

import org.junit.Test;

public class TestInsert
{

	BinarySearchTree tree = new BinarySearchTree();
	
	@Test
	public void test()
	{	
		int[] numbers = {23,76,5,32,2,8,4,9,6};
		for (int num : numbers)
		{
			
			tree.insert(num);
		}
		
		tree.displayTree();
		
	}
	
}
