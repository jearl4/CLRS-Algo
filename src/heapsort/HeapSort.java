package heapsort;

/**
 * Implements heap sort on an array using max-heapify() to maintain the max heap
 * property, build-max-heap() to create the heap, and heapsort() to sort the
 * heap.
 * 
 * @author J.T. Earl
 *
 */
public class HeapSort {

	public static void main(String[] args) {
		
		int[] array = { 0, 16, 4, 10, 14, 7, 9, 3, 2, 8, 1 };
		heapSort(array, array.length - 1);

	}

	/**
	 * gets the parent node
	 * 
	 * @param i
	 * @return the parent node of a child i
	 */
	private static int parent(int i) {
		return (i / 2);
	}

	/**
	 * gets the left child of i
	 * 
	 * @param i
	 * @return left child of i
	 */
	private static int leftChild(int i) {
		return (2 * i) + 1;
	}

	/**
	 * gets the right child of i
	 * 
	 * @param i
	 * @return right child of i
	 */
	private static int rightChild(int i) {
		return (2 * i) + 2;
	}

	private static void maxHeapify(int[] array, int i) {
		int l = leftChild(i);
		int r = rightChild(i);
		int largest = i;
		if (l <= array.length && array[l] > array[i]) {
			largest = l;
		} else {
			largest = i;
		}
		if (r <= array.length && array[r] > array[largest]) {
			largest = r;
		}
		if (largest != i) {
			array[i] = array[largest];
			maxHeapify(array, largest);
		}
	}

	private static void buildMaxHeap(int[] array) {
		int heapSize = array.length;
		for (int i = array.length / 2; i > 1; i--) {
			maxHeapify(array, i);
		}
	}

	private static void heapSort(int[] array, int heapSize) {
		buildMaxHeap(array);
		for (int i = array.length; i > 1; i--) {
			int temp = array[i];
			array[i] = array[1];
			array[1] = temp;
			heapSize--;
			maxHeapify(array, 1);
		}
	}
}
