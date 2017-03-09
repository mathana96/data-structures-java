package controllers;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Stack;


import models.Node;

public class HuffmanTree
{
	
	PriorityQueue<Node> pq = new PriorityQueue<>();
	HashMap<Character, Integer> freqMap = new HashMap<>();
	HashMap<Character, String> huffmanMap = new HashMap<>();
	
	static final Character special = '\u2020'; 

	String code = "";
	
	public HuffmanTree()
	{
		
	}
	
	public void buildMap(String s)
	{
		for (int i=0; i<s.length(); i++)
		{
			buildMap(s.charAt(i));
		}
		buildMap(special);
		
	}
	
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
	
	public void printMap()
	{
		System.out.println(freqMap);
	}
	
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
	
	public boolean isLeaf(Node node)
	{
		if ( (node.left == null) && (node.right == null) )
			return true;
		else
			return false;
	}
	
	/**
	 * Gets individual character's huffman code
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
	
	public String generateHuffman(String s)
	{
		String huffman = "";
		for (int i=0; i<s.length(); i++)
		{
			huffman += huffmanMap.get(s.charAt(i));
		}
		return huffman += huffmanMap.get(special);
	}
	
	
}
