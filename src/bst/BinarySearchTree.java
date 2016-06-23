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
	
	public BinarySearchTree(){
		this.root = null;
	}
	
	static BinarySearchTree t = new BinarySearchTree();
	
	public static void main(String[] args) {
		BSTNode node = new BSTNode(8);
		BSTNode node2 = new BSTNode(3);
		BSTNode node3 = new BSTNode(9);
		treeInsert(t, node);
		treeInsert(t, node2);
		treeInsert(t, node3);
		inOrderTraversal(t.root);
		System.out.println("--------");
		transplant(t, node, node3);
		inOrderTraversal(t.root);
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
	public static void treeInsert(BinarySearchTree t, BSTNode z) {
		BSTNode node1 = null;
		BSTNode node2 = t.root;
		while (node2 != null) {
			node1 = node2;
			if (z.key < node1.key) {
				node2 = node2.left;
			} else {
				node2 = node2.right;
			}
		}
		z.parent = node1;
		if (node1 == null) {
			t.root = z;
		} else if (z.key < node1.key) {
			node1.left = z;
		} else {
			node1.right = z;
		}
	}

	public static void transplant(BinarySearchTree t, BSTNode node1, BSTNode node2) {
		if (node1.parent == null) {
			t.root = node2;
		} else if (node1 == node1.parent.left) {
			node1.parent.left = node2;
		} else {
			node1.parent.right = node2;
		}
		if (node2 != null) {
			node2.parent = node1.parent;
		}
	}
}