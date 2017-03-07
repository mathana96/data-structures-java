import static org.junit.Assert.*;

import org.junit.Test;

import controllers.HuffmanTree;
import models.Node;


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
		
		Node node = huffmantree.buildTrie();
//		huffmantree.displayTree(node);
		System.out.println("Char" + "\t" + "Freq" + "\t" + "Huffman");
		huffmantree.printTrie(node);
	}

	

}
