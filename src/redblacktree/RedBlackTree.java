package redblacktree;

import java.util.*;

class RBNode {
	int key;
	boolean color; // false is black, true is red
	RBNode parent;
	RBNode left;
	RBNode right;

	public RBNode() {

	}

	public RBNode(boolean color) {
		this.key = 0;
		this.parent = null;
		this.left = null;
		this.right = null;
		this.color = color;
	}

	public RBNode(int key, boolean color) {
		this.key = key;
		this.parent = null;
		this.left = null;
		this.right = null;
		this.color = color;
	}

	public RBNode(int key, RBNode parent, RBNode left, RBNode right, boolean color) {
		this.key = key;
		this.parent = parent;
		this.left = left;
		this.right = right;
		this.color = color;
	}

}

class RedBlackTree {

	private static final boolean RED = true;
	private static final boolean BLACK = false;
	private static RBNode root = new RBNode(5, BLACK);

	protected final static RBNode nil = new RBNode(BLACK);

	public static void main(String[] args) {
		RBNode r1 = new RBNode(3, RED);
		RBNode b1 = new RBNode(9, BLACK);
		insert(root);
		insert(r1);
		insert(b1);
		inOrderTraversal(root);
	}

	/**
	 * check if a node is red. Return true if node is red, false if node is
	 * black or null
	 * 
	 * @param node
	 * @return
	 */
	private static boolean isRed(RBNode node) {
		if (node == null) {
			return false;
		}
		return node.color == RED;
	}

	private static void insert(RBNode node) {
		RBNode y = nil;
		RBNode x = root;
		while (x != nil) {
			y = x;
			if (node.key < x.key) {
				x = x.left;
			} else {
				x = x.right;
			}
		}
		node.parent = y;
		if (y == nil) {
			root = node;
		} else if (node.key < y.key) {
			y.left = node;
		} else {
			y.right = node;
		}
		node.left = nil;
		node.right = nil;
		node.color = RED;
		fixUp(node);
	}

	private static void fixUp(RBNode node) {
		RBNode y;
		while (node.parent.color == RED) {
			if (node.parent == node.parent.parent.left) {
				y = node.parent.parent.right;
				// case 1 y is red. Change to black
				if (y.color == RED) {
					node.parent.color = BLACK;
					y.color = BLACK;
					node.parent.parent.color = RED;
					node = node.parent.parent;
					// case 2 y is black and node is right child. rotate into
					// case 3
				} else if (node == node.parent.right) {
					node = node.parent;
					leftRotate(node);
				}
				// case 3 y is black and node is left child
				node.parent.color = BLACK;
				node.parent.parent.color = RED;
				rightRotate(node.parent.parent);
			} else if (node.parent == node.parent.parent.right) {
				y = node.parent.parent.right;
				// case 1 y is red
				if (y.color == RED) {
					node.parent.color = BLACK;
					y.color = BLACK;
					node.parent.parent.color = RED;
					node = node.parent.parent;
					// case 2 y is black node
				} else if (node == node.parent.left) {
					node = node.parent;
					rightRotate(node);
				} // case 3 y is black
				node.parent.color = BLACK;
				node.parent.parent.color = RED;
				leftRotate(node.parent.parent);
			}
		}
	}

	private static void leftRotate(RBNode node) {
		RBNode y = node.right;
		node.right = y.left;
		if (y.left != nil)
			y.left.parent = node;
		y.parent = node.parent;
		if (node.parent == nil) {
			root = y;
		} else if (node == node.parent.left) {
			node.parent.left = y;
		} else {
			node.parent.right = y;
		}
		y.left = node;
		node.parent = y;
	}

	private static void rightRotate(RBNode node) {
		RBNode y = node.left;
		node.right = y.right;
		if (y.right != nil)
			y.right.parent = node;
		y.parent = node.parent;
		if (node.parent == nil) {
			root = y;
		} else if (node == node.parent.right) {
			node.parent.right = y;
		} else {
			node.parent.left = y;
		}
		y.right = node;
		node.parent = y;
	}

	public static void inOrderTraversal(RBNode node) {
		if (node != null) {
			inOrderTraversal(node.left);
			System.out.println(node.key);
			inOrderTraversal(node.right);
		}
	}
}
