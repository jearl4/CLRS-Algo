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
	private static RBNode root;

	protected final RBNode nil = new RBNode(BLACK);

	public static void main(String[] args) {

	}

	/**
	 * check if a node is red. Return true if node is red, false if node is
	 * black or null
	 * 
	 * @param node
	 * @return
	 */
	private boolean isRed(RBNode node) {
		if (node == null) {
			return false;
		}
		return node.color == RED;
	}

	private void insert(RBNode node) {
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
}
