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

	public RBNode(int key, RBNode parent, RBNode left, RBNode right, boolean color) {
		this.key = key;
		this.parent = parent;
		this.left = left;
		this.right = right;
		this.color = color;
	}

}

class RedBlackTree {
	
	private void insert() {

	}
}
