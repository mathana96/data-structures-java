/**
 * @author mathana
 * 
 * Driver class to run the Huffman Code program. This project acts as a 
 * proof of concept only. 
 */
package controllers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import models.Node;
import utils.ReadFromFile;
import utils.WriteToFile;

public class HuffmanDriver
{

	private final static String pathToCompress = "././data/textdata.txt";
	private final static String pathToDecompress = "././data/compressed.dat";
	
	public static void main(String[] args) throws Exception
	{
		ReadFromFile reader = new ReadFromFile(pathToCompress);
		String toCompress = reader.getFileContent();
		
		HuffmanTree huffmanTree = new HuffmanTree();
		HuffmanTreeDecompress decompress = new HuffmanTreeDecompress();
		
		huffmanTree.buildMap(toCompress);
		Node node = huffmanTree.buildTrie();
		System.out.println("Char" + "\t" + "Freq" + "\t" + "Huffman");
		huffmanTree.printTrie(node);
		String compressed = huffmanTree.generateHuffman(toCompress);
		
		huffmanTree.preOrderWrite(node);

//		System.out.println("Prerder : " + huffmanTree.preOrderWrite.length());
//		System.out.println(huffmanTree.preOrderWrite.length());
		
		
		String longdata = huffmanTree.generateHeaderIdentifier() + huffmanTree.getPreOrderWrite() + compressed;
		
		byte[] longdataarray = huffmanTree.parseStringToBytes(longdata);
		
		huffmanTree.writeBytesToFile(longdataarray); //compress
		
//		-------------------------------------------------------------
//		Decompress
		Path path = Paths.get(pathToDecompress);
		byte[] data = Files.readAllBytes(path);
		
		String decompressed = decompress.decompress(data);
		
		WriteToFile writer = new WriteToFile(decompressed);

	}

}
