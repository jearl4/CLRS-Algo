package redblacktree;

import java.util.*;

class RBNode {
	int key;
	boolean color;
	RBNode parent;
	RBNode left;
	RBNode right;

	public RBNode() {

	}

	public RBNode(int key, boolean color) {
		this.key = key;
		this.parent = null;
		this.left = null;
		this.right = null;
		this.color = color;
	}

}

class RedBlackTree {
	private static final boolean RED = true;
	private static final boolean BLACK = false;
	private static RBNode root;

	public static void main(String[] args) {
		RBNode node = new RBNode(10, RED);

	}

	/**
	 * is node red? False if null
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

	public static void rBInsert(int key) {
		root = rBInsert(root, key);
		root.color = BLACK;
	}

	// insert the node into the subtree rooted at node
	private static RBNode rBInsert(RBNode node, int key) {
		if (node == null) {
			return new RBNode(key, RED);
		}
		if (key < node.key) {
			node.left = rBInsert(node.left, key);
		} else if (key > node.key) {
			node.right = rBInsert(node.right, key);
		}
		if (isRed(node.right) && !isRed(node.left))
			node = leftRotate(node);
		if (isRed(node.left) && isRed(node.left.left))
			node = rightRotate(node);
		if (isRed(node.left) && isRed(node.right))
			flipColors(node);
		return node;
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
	private static RBNode leftRotate(RBNode node) {
		RBNode x = node.left;
		node.left = x.right;
		x.right = node;
		x.color = x.right.color;
		x.right.color = RED;
		return x;
	}

	private static RBNode rightRotate(RBNode node) {
		RBNode x = node.left;
		node.left = x.right;
		x.right = node;
		x.color = x.right.color;
		x.right.color = RED;
		return x;
	}

	private static void flipColors(RBNode node) {
		node.color = !node.color;
		node.left.color = !node.left.color;
		node.right.color = !node.right.color;
	}
}
