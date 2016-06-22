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

	static BSTNode root;

	public static void main(String[] args) {
		BSTNode node = new BSTNode(8);
		BSTNode node2 = new BSTNode(3);
		BSTNode node3 = new BSTNode(9);
		BSTNode node4 = new BSTNode(2);
		BSTNode node5 = new BSTNode(5);
		BSTNode node6 = new BSTNode(7);
		BSTNode node7 = new BSTNode(6);
		treeInsert(node);
		treeInsert(node2);
		treeInsert(node3);
		treeInsert(node4);
		treeInsert(node5);
		treeInsert(node6);
		treeInsert(node7);
		inOrderTraversal(node);
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
		/*
		 * if right subtree of node is empty. Node2 is the lowest ancestor of
		 * node whose left child is also an ancestor of node.
		 */
		BSTNode node2 = node.parent;
		while (node2 != null && node == node2.right) {
			node = node2;
			node2 = node2.parent;
		}
		return node2;
	}

	/**
	 * takes newNode and modifies the tree and some attributes of newNode such
	 * that newNode is inserted in the right place.
	 * 
	 * @param newNode
	 * @param key
	 */
	public static void treeInsert(BSTNode newNode, int key) {
		BSTNode node1 = null;
		BSTNode node2 = root;
		while (node2 != null) {
			node1 = node2;
			if (newNode.key < node1.key) {
				node2 = node2.left;
			} else {
				node2 = node2.right;
			}
		}
		newNode.parent = node1;
		if (node1 == null) {
			root = newNode;
		} else if (newNode.key < node1.key) {
			node1.left = newNode;
		} else {
			node1.right = newNode;
		}
	}

	/**
	 * takes newNode and modifies the tree and some attributes of newNode such
	 * that newNode is inserted in the right place. Overloaded method that
	 * doesn't need a key value as input
	 * 
	 * @param newNode
	 */
	public static void treeInsert(BSTNode newNode) {
		BSTNode node1 = null;
		BSTNode node2 = root;
		while (node2 != null) {
			node1 = node2;
			if (newNode.key < node1.key) {
				node2 = node2.left;
			} else {
				node2 = node2.right;
			}
		}
		newNode.parent = node1;
		if (node1 == null) {
			root = newNode;
		} else if (newNode.key < node1.key) {
			node1.left = newNode;
		} else {
			node1.right = newNode;
		}
	}
}