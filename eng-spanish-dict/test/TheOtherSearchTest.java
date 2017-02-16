
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import models.Pair;
import utils.ParseData;

public class TheOtherSearchTest
{
	int index = 0;
	ArrayList<Pair> pairs = new ArrayList<>();
	ParseData parser = new ParseData();

	@Before
	public void setup() throws Exception
	{
		String path = "././test-data/test-data.txt"; 
		pairs = parser.readFile(path);
	}

	@Test
	public void test()
	{
		search("amsdfsdfo", index);
		//		System.out.println(pairs);
	}

	public void search(String spanish, int index)
	{

		int leftChild = 2 * (index) + 1;
		Pair pair = pairs.get(index);

		if (pair.getSpanish().equals(spanish))
		{
			System.out.println(pair.getEnglish());
		}
		else
		{
			if ( (pair.getSpanish().compareTo(spanish) > 0) && (leftChild < pairs.size()) )//if worth going down
			{
				index = leftChild;
				search(spanish, index);

				//				Crossing to the right node
				int parent = (leftChild - 1) / 2;
				int rightChild = 2 * (parent) + 2;
				if (rightChild < pairs.size())
				{
					index = rightChild;
					search(spanish, index);
				}
			}

		}


	}


}
