/**
 * @author mathana
 * 
 * Driver class to run the Huffman Code program. This project acts as a 
 * proof of concept only. 
 */
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
		System.out.println("Char" + "\t" + "Freq" + "\t" + "Huffman");
		huffmanTree.printTrie(node);
		String compressed = huffmanTree.generateHuffman(toCompress);
		
		huffmanTree.preOrderWrite(node);

//		System.out.println("Prerder : " + huffmanTree.preOrderWrite.length());
		System.out.println(huffmanTree.preOrderWrite.length());
		
		
		String longdata = huffmanTree.generateHeaderIdentifier() + huffmanTree.getPreOrderWrite() + compressed;
		
		byte[] longdataarray = huffmanTree.parseStringToBytes(longdata);
		
		huffmanTree.writeBytesToFile(longdataarray);
//		byte[] data = huffmanTree.parseStringToBytes(compressed);
//		
//		
//		byte[] ident = huffmanTree.generateHeaderIdentifier();
//		byte[] preOrder = huffmanTree.generateHeaderPreOrder();
//		
//		huffmanTree.writeBytesToFile(ident, preOrder, data);
		

//		huffmanTree.readBytesFromFile();
		
		
//		WriteToFile writer = new WriteToFile(compressed);
	}

}
