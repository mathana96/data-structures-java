package models;

import static com.google.common.base.MoreObjects.toStringHelper;

import com.google.common.base.Objects;

public class Node implements Comparable<Node>
{
	private Character data;
	private int freq;
	private Node left;
	private Node right;
	
	public Node(Character data, int freq, Node left, Node right)
	{
		this.data = data;
		this.freq = freq;
		this.left = left;
		this.right = right;
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

	@Override
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
	public String toString()
	{
		return toStringHelper(this).addValue(data)
				.addValue(freq).toString();

	}
}
