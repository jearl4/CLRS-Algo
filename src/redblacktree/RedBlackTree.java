package redblacktree;

public class RedBlackTree<Key extends Comparable<Key>, Value> {
	private static final boolean RED = true;
	private static final boolean BLACK = false;

	private Node root;

	// node properties
	private class Node {
		private Key key;
		private Value val;
		private Node left, right;
		private boolean color;

		Node(Key key, Value val) {
			this.key = key;
			this.val = val;
			this.color = RED;
		}
	}

	private boolean isRed(Node node) {
		if (node == null) {
			return false;
		}
		return node.color == RED;
	}

	private Node rotateLeft(Node node) {
		Node x = node.right;
		node.right = x.left;
		x.left = node;
		x.color = node.color;
		node.color = RED;
		return x;
	}

	private Node rotateRight(Node node) {
		Node x = node.left;
		node.left = x.right;
		x.right = node;
		x.color = node.color;
		node.color = RED;
		return x;
	}

	private void colorFlip(Node node) {
		node.color = !node.color;
		node.left.color = !node.left.color;
		node.right.color = !node.right.color;
	}

	public Value search(Key key) {
		Node x = root;
		while (x != null) {
			int cmp = key.compareTo(x.key);
			if (cmp == 0) {
				return x.val;
			} else if (cmp < 0) {
				x = x.left;
			} else if (cmp > 0) {
				x = x.right;
			}
		}
		return null;
	}

	public void insert(Key key, Value value) {
		root = insert(root, key, value);
		root.color = BLACK;
	}

	private Node insert(Node node, Key key, Value value) {
		if (node == null)
			return new Node(key, value);
		// move this line to the end of method to get 2-3 trees
		if (isRed(node.left) && isRed(node.right))
			colorFlip(node);
		int cmp = key.compareTo(node.key);
		if (cmp == 0) {
			node.val = value;
		} else if (cmp < 0) {
			node.left = insert(node.left, key, value);
		} else {
			node.right = insert(node.right, key, value);
		}
	}

	private Node moveRedLeft(Node node) {
		colorFlip(node);
		if (isRed(node.right.left)) {
			node.right = rotateRight(node.right);
			node = rotateLeft(node);
			colorFlip(node);
		}
		return node;
	}

	private Node moveRedRight(Node node) {
		colorFlip(node);
		if (isRed(node.left.left)) {
			node = rotateRight(node);
			colorFlip(node);
		}
		return node;
	}

	public void delete(Key key) {
		root = delete(root, key);
		root.color = BLACK;
	}

	private Node delete(Node node, Key key) {
		if (key.compareTo(node.key) < 0) {
			if (!isRed(node.left) && !isRed(node.left.left)) {
				node = moveRedLeft(node);
			}
			node.left = delete(node.left, key);
		} else {
			if (isRed(node.left))
				node = rotateRight(node);
			if (key.compareTo(node.key) == 0 && (node.right == null))
				return null;
			if (!isRed(node.right) && !isRed(node.right.left))
				node = moveRedRight(node);
			if (key.compareTo(node.key) == 0) {
				node.val = get(h.right, min(h.right).key);
				node.key = min(node.right).key;
				node.right = deleteMin(node.right);
			} else {
				node.right = delete(node.right, key);
			}
		}
		return fixUp(node);
	}
}