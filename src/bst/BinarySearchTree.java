package bst;

class BSTNode {
	int key;
	BSTNode parent;
	BSTNode left;
	BSTNode right;

	public BSTNode(int key) {
		this.key = key;
		this.left = null;
		this.right = null;
		this.parent = null;
	}

	public BSTNode() {

	}
}

class BinarySearchTree {

	BSTNode root;

	public static void main(String[] args) {

	}

	public static void inOrderTraversal(BSTNode node) {
		if (node != null) {
			inOrderTraversal(node.left);
			System.out.println(node.key);
			inOrderTraversal(node.right);
		}
	}

	/**
	 * begins at root and traces down, for each key it encounters it compares
	 * the values and terminates if they are the same. On most computers the
	 * iterative version is more efficient
	 * 
	 * @param node
	 * @param key
	 * @return
	 */
	public static BSTNode treeSearch(BSTNode node, int key) {
		if (node == null || key == node.key) {
			return node;
		}
		// search left side of tree
		if (key < node.key) {
			return treeSearch(node.left, key);
		} else {
			return treeSearch(node.right, key);
		}
	}

	/**
	 * similar to treeSearch but unrolls the recursion into a while loop
	 * 
	 * @param node
	 * @param key
	 * @return
	 */
	public static BSTNode iterativeTreeSearch(BSTNode node, int key) {
		while (node != null || key != node.key) {
			if (key < node.key) {
				node = node.left;
			} else {
				node = node.right;
			}
		}
		return node;
	}

	/**
	 * follows the left child pointers from root until there is a null
	 * 
	 * @param node
	 * @return
	 */
	public static BSTNode treeMinimum(BSTNode node) {
		while (node.left != null) {
			node = node.left;
		}
		return node;
	}

	/**
	 * follows the right child pointers until there is a null
	 * 
	 * @param node
	 * @return
	 */
	public static BSTNode treeMaximum(BSTNode node) {
		while (node.right != null) {
			node = node.right;
		}
		return node;
	}

	/**
	 * successor of node is the node2 with the smallest key greater than node
	 * 
	 * @param node
	 * @return successor of node if it exists and null if node is the largest
	 *         key in the tree
	 */
	public static BSTNode treeSuccessor(BSTNode node) {
		// if the right subtree isn't empty successor is minimum of that subtree
		if (node.right != null) {
			return treeMinimum(node.right);
		}
		
		BSTNode node2 = node.parent;
		while (node2 != null && node == node2.right) {
			node = node2;
			node2 = node2.parent;
		}
		return node2;
	}
}