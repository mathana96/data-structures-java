package models;

import com.google.common.base.Objects;

public class Node
{
	private Character data;
	private int freq;
	private Node left;
	private Node right;
	
	public Node(Character data)
	{
		this.data = data;
		this.freq = 0;
		this.left = null;
		this.right = null;
	}
	
	public int compareTo(Node node)
	{
		if (this.freq > node.freq)
			return 1;
		else if (this.freq < node.freq)
			return -1;
		else 
			return 0;
	}
	
	@Override
	public boolean equals(final Object obj)
	{
		if (obj instanceof Node)
		{

			final Node other = (Node) obj;
			return Objects.equal(this.data, other.data);
		}
		else
		{
			return false;
		}
	}
}
