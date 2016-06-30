package redblacktree;

class RBNode {
	int key;
	String color;
	RBNode parent;
	RBNode left;
	RBNode right;
	
	public RBNode() {

	}

	public RBNode(int key, String color) {
		this.key = key;
		this.parent = null;
		this.left = null;
		this.right = null;
		this.color = null;
	}
}

class RedBlackTree {

	RBNode root;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static void rBInsert(RedBlackTree t, RBNode node) {
		RBNode nodeY = null;
		RBNode nodeX = t.root;
		while (nodeX != null) {
			nodeY = nodeX;
			if (node.key < nodeX.key) {
				nodeX = nodeX.left;
			} else {
				nodeX = nodeX.right;
			}
		}
		node.parent = nodeY;
		if (nodeY == null) {
			t.root = node;
		} else if (node.key < nodeY.key) {
			nodeY.left = node;
		}else{
			nodeY.right = node;
		}
		node.left = null;
		node.right = null;
		node.color = "RED";
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
	public static void leftRotate(RedBlackTree t, RBNode node) {
		RBNode node2 = node.right;
		node.right = node2.left;
		if (node2.left != null) {
			node2.left.parent = node;
		}
		node2.parent = node.parent;
		if (node.parent == null) {
			t.root = node2;
		} else if (node == node.parent.left) {
			node.parent.left = node2;
		} else {
			node.parent.right = node2;
		}
		node2.left = node;
		node.parent = node2;
	}

	public static void rightRotate(RedBlackTree t, RBNode node) {
		RBNode node2 = node.left;
		node.left = node2.right;
		if (node2.right != null) {
			node2.right.parent = node;
		}
		node2.parent = node.parent;
		if (node.parent == null) {
			t.root = node2;
		} else if (node == node.parent.right) {
			node.parent.right = node2;
		} else {
			node.parent.left = node2;
		}
		node2.right = node;
		node.parent = node2;
	}

}
