package controllers;

import models.Node;
import utils.ReadFromFile;
import utils.WriteToFile;

public class HuffmanDriver
{

	private final static String path = "././data/textdata.txt";
	
	public static void main(String[] args) throws Exception
	{
		ReadFromFile reader = new ReadFromFile(path);
		String toCompress = reader.getFileContent();
		
		HuffmanTree huffmanTree = new HuffmanTree();
		huffmanTree.buildMap(toCompress);
		Node node = huffmanTree.buildTrie();
		huffmanTree.printTrie(node);
		String compressed = huffmanTree.generateHuffman(toCompress);
		
		WriteToFile writer = new WriteToFile(compressed);
	}

}
