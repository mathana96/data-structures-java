package controllers;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Stack;


import models.Node;

public class HuffmanTree
{
	
	PriorityQueue<Node> pq = new PriorityQueue<>();
	HashMap<Character, Integer> freqMap = new HashMap<>();
	
	public Node root;
	/**
	 * 
	 * Inner class. Some guidelines here
	 * http://stackoverflow.com/questions/18396016/when-to-use-inner-classes-in-java-for-helper-classes
	 *
	 */
	
	public HuffmanTree()
	{
		this.root = null;
	}
	
	public void buildMap(Character data)
	{
//		Node node = new Node(data);
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
	
	public void buildTrie()
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
		
		while (!pq.isEmpty())
		{
			System.out.println(pq.poll());
		}
//		Join them up nice and good
//		while(pq.size() > 1)
//		{
//			
//		}
	}
	
	
//	
//	/**
//	 * Public client method used for recursive calls to private helper methods
//	 * 
//	 */
//	public void insert(int data)
//	{
//		root = insert(root, data);
//	}
//	
//	/**
//	 * Private helper method. Recursive insert
//	 * @param node the node
//	 * @param data the data of the node
//	 */
//	private Node insert(Node node, int data) //Given a pointer to a node, insert data
//	{
//		if (node == null)
//			node = new Node(data); //Create a new node if null at where pointer is
//		else
//		{
//			if (data < node.data)
//				node.left = insert(node.left, data); //Put whatever returned by the call into the left child
//			else if (data > node.data)
//				node.right = insert(node.right, data);
//			else // if (data is the same as the node's data), replace it 
//				node.data = data;
//		}	
//		return node;
//	}
//	
//	public int search(int data)
//	{
//		return search(root, data);
//	}
//	
//	private int search(Node node, int data)
//	{
//		if (node == null)
//			return 0;
//		else
//		{
//			if (data < node.data)
//				return search(node.left, data);
//			else if (data > node.data)
//				return search(node.right, data);
//			else
//				return node.data;
//		}
//	}
//	
//	/**
//	 * Class taken from;
//	 * https://github.com/camluca/Samples/blob/master/Tree.java
//	 */
//	public void displayTree()
//	{
//		Node r = root;
//		Stack<Node> globalStack = new Stack<>();
//		globalStack.push(r);	
//		int emptyLeaf = 32;
//		boolean isRowEmpty = false;
//		System.out.println("****......................................................****");
//		while(isRowEmpty==false)
//		{
//
//			Stack<Node> localStack = new Stack<>();
//			isRowEmpty = true;
//			for(int j=0; j<emptyLeaf; j++)
//				System.out.print(' ');
//			while(globalStack.isEmpty()==false)
//			{
//				Node temp = globalStack.pop();
//				if(temp != null)
//				{
//					System.out.print(temp.data);
//					localStack.push(temp.left);
//					localStack.push(temp.right);
//					if(temp.left != null ||temp.right != null)
//						isRowEmpty = false;
//				}
//				else
//				{
//					System.out.print("--");
//					localStack.push(null);
//					localStack.push(null);
//				}
//				for(int j=0; j<emptyLeaf*2-2; j++)
//					System.out.print(' ');
//			}
//			System.out.println();
//			emptyLeaf /= 2;
//			while(localStack.isEmpty()==false)
//				globalStack.push( localStack.pop() );
//		}
//	System.out.println("****......................................................****");
//	} 
//
//	
}
