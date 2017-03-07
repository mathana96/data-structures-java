import static org.junit.Assert.*;

import org.junit.Test;

import controllers.HuffmanTree;


public class ReadCharTest
{

	HuffmanTree huffmantree = new HuffmanTree();

	@Test
	public void testBuildMap()
	{
		String word = "hello world";
		for (int i=0; i<word.length(); i++)
		{
			huffmantree.buildMap(word.charAt(i));
		}
		
		huffmantree.printMap();
	}
	
	@Test
	public void testBuildTrie()
	{
		String word = "hello world";
		for (int i=0; i<word.length(); i++)
		{
			huffmantree.buildMap(word.charAt(i));
		}
		
		huffmantree.buildTrie();
	}

	

}
