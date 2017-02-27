
public class BinarySearchTree
{
	TreeNode root;
	
	public BinarySearchTree()
	{
		this.root = null;
	}
	
	public TreeNode insert(TreeNode node, int num) //Given a node pointer, insert num recursively
	{
		if (node == null)
			node = new TreeNode(num);
		else
		{
			if (num < node.number)
				node.leftChild = insert(node.leftChild, num);
			else
				node.rightChild = insert(node.rightChild, num);
				
		}	
		return node;
	}
	
}
