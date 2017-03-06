import static org.junit.Assert.*;

import org.junit.Test;

import controllers.HuffmanTree;
import javafx.util.Pair;

public class ReadCharTest
{

	HuffmanTree huffmantree = new HuffmanTree();

	@Test
	public void test()
	{
		String word = "hello world";
		for (int i=0; i<word.length(); i++)
		{
			huffmantree.buildMap(word.charAt(i));
		}
		
		huffmantree.printMap();
	}

	

}
