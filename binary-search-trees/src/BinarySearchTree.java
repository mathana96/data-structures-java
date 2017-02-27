import java.util.Stack;

public class BinarySearchTree
{
	public Node root;
	/**
	 * 
	 * Inner class. Some guidelines here
	 * http://stackoverflow.com/questions/18396016/when-to-use-inner-classes-in-java-for-helper-classes
	 *
	 */
	private class Node
	{
		private int data;
		private Node left;
		private Node right;
		
		private Node(int data)
		{
			this.data = data;
			this.left = null;
			this.right = null;
		}
	}
	
	
	public BinarySearchTree()
	{
		this.root = null;
	}
	
	/**
	 * Public client method used for recursive calls to private helper methods
	 * 
	 */
	public void insert(int data)
	{
		root = insert(root, data);
	}
	
	/**
	 * Private helper method. Recursive insert
	 * @param node the node
	 * @param data the data of the node
	 */
	private Node insert(Node node, int data) //Given a pointer to a node, insert data
	{
		if (node == null)
			node = new Node(data); //Create a new node if null at where pointer is
		else
		{
			if (data < node.data)
				node.left = insert(node.left, data); //Put whatever returned by the call into the left child
			else if (data > node.data)
				node.right = insert(node.right, data);
			else // if (data is the same as the node's data), replace it 
				node.data = data;
		}	
		return node;
	}
	
	/**
	 * Class taken from;
	 * https://github.com/camluca/Samples/blob/master/Tree.java
	 */
	public void displayTree()
	{
		Node r = root;
		Stack<Node> globalStack = new Stack<>();
		globalStack.push(r);	
		int emptyLeaf = 32;
		boolean isRowEmpty = false;
		System.out.println("****......................................................****");
		while(isRowEmpty==false)
		{

			Stack<Node> localStack = new Stack<>();
			isRowEmpty = true;
			for(int j=0; j<emptyLeaf; j++)
				System.out.print(' ');
			while(globalStack.isEmpty()==false)
			{
				Node temp = globalStack.pop();
				if(temp != null)
				{
					System.out.print(temp.data);
					localStack.push(temp.left);
					localStack.push(temp.right);
					if(temp.left != null ||temp.right != null)
						isRowEmpty = false;
				}
				else
				{
					System.out.print("--");
					localStack.push(null);
					localStack.push(null);
				}
				for(int j=0; j<emptyLeaf*2-2; j++)
					System.out.print(' ');
			}
			System.out.println();
			emptyLeaf /= 2;
			while(localStack.isEmpty()==false)
				globalStack.push( localStack.pop() );
		}
	System.out.println("****......................................................****");
	} 

	
}
