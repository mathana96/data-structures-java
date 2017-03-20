/**
 * @author mathana
 * 
 * The HuffmanTree class has all the components of the Huffmantree and methods needed to generate the code
 */
package controllers;

import java.util.HashMap;
import java.util.PriorityQueue;


import models.Node;

public class HuffmanTree
{

	PriorityQueue<Node> pq = new PriorityQueue<>();
	HashMap<Character, Integer> freqMap = new HashMap<>();
	HashMap<Character, String> huffmanMap = new HashMap<>();

	static final Character special = '\u2020'; //EOF character

	String code = "";

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
		buildMap(special); //Add EOF character to the tree

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
			huffman += huffmanMap.get(s.charAt(i)) + "\n";
		}
		return huffman += huffmanMap.get(special);
	}


}
