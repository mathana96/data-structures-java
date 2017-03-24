/**
 * @author mathana
 * 
 * The HuffmanTree class has all the components of the Huffmantree and methods needed to generate the code
 */
package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.PriorityQueue;


import models.Node;

public class HuffmanTree
{

	PriorityQueue<Node> pq = new PriorityQueue<>();
	HashMap<Character, Integer> freqMap = new HashMap<>();
	HashMap<Character, String> huffmanMap = new HashMap<>();

	//	static final Character special = '\u2020'; //EOF character

	String code = "";
	String preOrderWrite = "";

	public HuffmanTree()
	{

	}

	/**
	 * Method used to call private method used to build a map of character to frequencies
	 * 
	 * @param s String to be encoded
	 */
	public void buildMap(String s)
	{
		for (int i=0; i<s.length(); i++)
		{
			buildMap(s.charAt(i));
		}
		//		buildMap(special); //Add EOF character to the tree

	}

	/**
	 * Method to  build a map of character to frequencies
	 * @param data
	 */
	private void buildMap(Character data)
	{

		if (freqMap.containsKey(data))
		{
			Integer freq = freqMap.get(data);
			freqMap.replace(data, freq+1);
		}
		else
		{
			freqMap.put(data, 1);
		}

	}

	/**
	 * Method to print Map, used in test case
	 */
	public void printMap()
	{
		System.out.println(freqMap);
	}

	/**
	 * Method to build individual tries
	 * @return
	 */
	public Node buildTrie()
	{
		//		Build individual tries
		freqMap.forEach((k,v) -> 
		{
			if (v > 0)
			{
				Node node = new Node(k, v, null, null);
				pq.add(node);
			}
		}); 

		//		Join them up nice and good
		while(pq.size() > 1)
		{
			Node left = pq.poll();
			Node right = pq.poll();

			Node parent = new Node('z', left.freq + right.freq, left, right);

			pq.add(parent);
			//			System.out.println(pq);
		}
		return pq.poll();
	}

	/**
	 * Check if a node is a leaf or not
	 * @param node
	 * @return
	 */
	public boolean isLeaf(Node node)
	{
		if ( (node.left == null) && (node.right == null) )
			return true;
		else
			return false;
	}

	/**
	 * Gets individual character's huffman code and print 
	 * @param node
	 */
	public void printTrie(Node node)
	{		
		if (isLeaf(node))
		{
			System.out.println(node.data + "\t" + node.freq + "\t" + code);
			huffmanMap.put(node.data, code);

		}
		else
		{
			code += "0";
			printTrie(node.left);

			code = code.substring(0,code.length()-1);

			code += "1";
			printTrie(node.right);

			code = code.substring(0,code.length()-1);
		}

	}

	/**
	 * Returns the huffman code for input text
	 * @param s
	 * @return
	 */
	public String generateHuffman(String s)
	{
		String huffman = "";
		for (int i=0; i<s.length(); i++)
		{
			huffman += huffmanMap.get(s.charAt(i));
		}
		return huffman;
	}



	public byte[] parseStringToBytes(String b) throws Exception
	{		
		int arraySize = ( ( (b.length()/6) + b.length() ) /7 ) + 1;
		//		Length of huffman string / 7 (7 bits in an array position) + 1 extra for left over
		byte[] array = new byte[arraySize];

		int i = 0; //Start index
		int j = 0; //End index
		int k = 0; //Byte array index
		while ( (b.length() - i) >= 6 ) 
		{
			j = i + 6; 
			String chunk = b.substring(i, j); //Extracting a chunk of 7 bits
			chunk = "1" + chunk;

			array[k] = Byte.parseByte(chunk, 2); //Converting into 1 byte and putting byte into array
			Integer decimalInt = Byte.toUnsignedInt(array[k]);

			//			System.out.println(Integer.toString(decimalInt, 2));

			i = j;
			k++;
		}


		if ((b.length() % 6.0) != 0.0) //Only do if there are extra bits 
		{

			//		The left over bits. Need to be padded with zeros
			String remainingChunk = b.substring(i, b.length());
			
			remainingChunk = padWithZeros(remainingChunk);

			remainingChunk = "1" + remainingChunk;
			//			while (chunk.length() < (7 - remainingChunk.length()) )
			//			{
			//				chunk += "0"; //Padding 0s
			//			}
//			chunk += remainingChunk;
			array[k] = Byte.parseByte(remainingChunk, 2);
		
		} 

		return array;

	}

	public void preOrderWrite(Node node)
	{

		if (isLeaf(node))
		{
			preOrderWrite += "1";
			Integer ascii = (int) node.data;
			String asciiBinary = Integer.toBinaryString(ascii);
			if (asciiBinary.length() < 7)
			{
				asciiBinary = correctLeadingZeros(asciiBinary);
			}
			preOrderWrite += asciiBinary;
		}
		else
		{

			preOrderWrite += "0";

			preOrderWrite(node.left);
			preOrderWrite(node.right);
		}
	}


	public String padWithZeros(String s)
	{
		while (s.length() < 6)
		{
			s += "0";
		}
		return s;
	}

	public String correctLeadingZeros(String s)
	{
		while (s.length() < 6)
		{
			s = "0" + s;
		}
		return s;
	}

	public String generateHeaderIdentifier() throws Exception
	{
		int identifier = 0xCADD099;
		String binIdent = Integer.toBinaryString(identifier);

//		System.out.println("Identifier :" + binIdent);
//		//		binIdent = "0000" + binIdent;
//		byte[] headerIdentArray = parseStringToBytes(binIdent);
		//		System.out.println(headerIdentArray[0]);
		//		System.out.println(headerIdentArray[1]);
		//		System.out.println(headerIdentArray[2]);
		//		System.out.println(headerIdentArray[3]);
		// 0 , 1, 2, 3		

		return binIdent;

	}


	public void writeBytesToFile(byte[] byteArray) throws IOException
	{
		File file = new File("././data/compressed.dat");
		FileOutputStream fout = new FileOutputStream(file);



//		fout.write(headerIdentArray);
//		fout.write(headerPreOrderArray);
		fout.write(byteArray);

		System.out.println("Write to file complete");
		//		fout.write(array);
		fout.close();

	}
	
	public String getPreOrderWrite()
	{
		int treeSize = preOrderWrite.length();
		String tree = Integer.toBinaryString(treeSize);
//		System.out.println(preOrderWrite);
		
		if (tree.length() < 14)
			tree = correctLeadingZerosTree(tree);
		
		return tree + preOrderWrite;
	}
	
	public String correctLeadingZerosTree(String s)
	{
		while (s.length() < 14)
		{
			s = "0" + s;
		}
		return s;
	}

}
