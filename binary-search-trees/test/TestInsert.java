import static org.junit.Assert.*;

import org.junit.Test;

public class TestInsert
{

	@Test
	public void test()
	{
		BinarySearchTree tree = new BinarySearchTree();
		int[] numbers = {3,76,5,32,2,8,4,9,23,6};
		for (int num : numbers)
		{
			tree.insert(tree.root, num);
		}
		
	}

}
