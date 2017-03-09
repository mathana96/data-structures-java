import static org.junit.Assert.*;

import org.junit.Test;

import controllers.HuffmanTree;
import models.Node;


public class HuffmanTest
{

	HuffmanTree huffmantree = new HuffmanTree();

//	@Test
	public void testBuildMap()
	{
		String word = "hello world";
		huffmantree.buildMap(word);
		huffmantree.printMap();
	}
	
//	@Test
	public void testBuildAndPrintTrie()
	{
		String word = "hello world";
		huffmantree.buildMap(word);
		
		Node node = huffmantree.buildTrie();
//		huffmantree.displayTree(node);
		System.out.println("Char" + "\t" + "Freq" + "\t" + "Huffman");
		huffmantree.printTrie(node);
	}
	
	@Test
	public void testGenerateHuffman()
	{
		String word = "hello world";
		huffmantree.buildMap(word);
		
		Node node = huffmantree.buildTrie();
		
		huffmantree.printTrie(node);
		
		System.out.println(huffmantree.generateHuffman(word));
	}

	

}
