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
		huffmantree.buildMap(word);
		huffmantree.printMap();
	}
	
	@Test
	public void testBuildTrie()
	{
		String word = "hello world";
		huffmantree.buildMap(word);
		
		Node node = huffmantree.buildTrie();
//		huffmantree.displayTree(node);
		System.out.println("Char" + "\t" + "Freq" + "\t" + "Huffman");
		huffmantree.printTrie(node);
	}
	
//	@Test
//	public void testParseString()
//	{
//		Character[] characters = huffmantree.parseString("hello world");
//		for (int i=0; )
//		System.out.println(characters);
//	}

	

}
