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

	public BinarySearchTree() {
		BinarySearchTree.root = null;
	}

	public static void main(String[] args) {
		BSTNode node = new BSTNode(2);
		BSTNode node2 = new BSTNode(4);
		BSTNode node3 = new BSTNode(1);
		BSTNode node4 = new BSTNode(3);
		BSTNode node5 = new BSTNode(5);
		treeInsert(node);
		treeInsert(node2);
		treeInsert(node3);
		treeInsert(node4);
		treeInsert(node5);
		System.out.print("pre-order traversal: ");
		preOrder(root);
		System.out.print("\nin-order traversal: ");
		inOrderTraversal(root);
		System.out.print("\npost-order traversal: ");
		postOrder(root);
		System.out.println("\nheight of tree: " + height());
		// treeDelete(t, node3);
		// inOrderTraversal(t.root);
		// System.out.println("--------");
	}

	/**
	 * traverse left subtree, root, then right subtree
	 * 
	 * @param node
	 */
	private static void inOrderTraversal(BSTNode node) {
		if (node != null) {
			inOrderTraversal(node.left);
			System.out.print(node.key + " ");
			inOrderTraversal(node.right);
		}
	}

	/**
	 * traverse root, left subtree, then right subtree
	 * 
	 * @param node
	 */
	private static void preOrder(BSTNode node) {
		if (node != null) {
			System.out.print(node.key + " ");
			preOrder(node.left);
			preOrder(node.right);
		}
	}

	/**
	 * Traverse left subtree then right then root
	 * 
	 * @param node
	 */
	private static void postOrder(BSTNode node) {
		if (node != null) {
			postOrder(node.left);
			postOrder(node.right);
			System.out.print(node.key + " ");
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
	 * Get the height, in terms of links, of the tree from the root
	 * 
	 * @return
	 */
	public static int height() {
		return (height(root));
	}

	/**
	 * get the height, in terms of links, of the tree. Starts at the node
	 * parameter
	 * 
	 * @param node
	 * @return
	 */
	private static int height(BSTNode node) {
		// base case
		if (node == null) {
			return -1;
		} else {
			int leftSize = height(node.left);
			int rightSize = height(node.right);
			// use larger value + 1
			return (Math.max(leftSize, rightSize) + 1);
		}
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
	public static void treeInsert(BSTNode z) {
		BSTNode node1 = null;
		BSTNode node2 = root;
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
			root = z;
		} else if (z.key < node1.key) {
			node1.left = z;
		} else {
			node1.right = z;
		}
	}

	public static void transplant(BSTNode node1, BSTNode node2) {
		if (node1.parent == null) {
			root = node2;
		} else if (node1 == node1.parent.left) {
			node1.parent.left = node2;
		} else {
			node1.parent.right = node2;
		}
		if (node2 != null) {
			node2.parent = node1.parent;
		}
	}

	/**
	 * executes four possible cases: (lines 194-195) node has no left child.
	 * (lines 196-197) node has a left child but no right child. (lines 199-208)
	 * node has two children. Runs in O(h) time, where h = tree height.
	 * 
	 * @param t
	 * @param node
	 */
	public static void treeDelete(BinarySearchTree t, BSTNode node) {
		BSTNode y = new BSTNode();
		if (node.left == null) {
			transplant(node, node.right);
		} else if (node.right == null) {
			transplant(node, node.left);
		} else {
			y = treeMinimum(node.right);
			if (y.parent != node) {
				transplant(y, y.right);
				y.right = node.right;
				y.right.parent = y;
			}
			transplant(node, y);
			y.left = node.left;
			y.left.parent = y;
		}
	}

	/**
	 * pivots around the link from node to node2. it makes node2 the new root of
	 * the subtree with node as node2's left child and node2's left child as
	 * node's right child. Right child of node can not be null. Runs in O(1)
	 * time.
	 * 
	 * @param t
	 * @param node
	 */
	public static void leftRotate(BinarySearchTree t, BSTNode node) {
		BSTNode node2 = node.right;
		node.right = node2.left;
		if (node2.left != null) {
			node2.left.parent = node;
		}
		node2.parent = node.parent;
		if (node.parent == null) {
			root = node2;
		} else if (node == node.parent.left) {
			node.parent.left = node2;
		} else {
			node.parent.right = node2;
		}
		node2.left = node;
		node.parent = node2;
	}

	public static void rightRotate(BinarySearchTree t, BSTNode node) {
		BSTNode node2 = node.left;
		node.left = node2.right;
		if (node2.right != null) {
			node2.right.parent = node;
		}
		node2.parent = node.parent;
		if (node.parent == null) {
			root = node2;
		} else if (node == node.parent.right) {
			node.parent.right = node2;
		} else {
			node.parent.left = node2;
		}
		node2.right = node;
		node.parent = node2;
	}
}