package heapsort;

import java.util.Arrays;

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
		
		int[] array = { 16, 4, 10, 14, 7, 9, 3, 2, 8, 1 };
		int heapSize = array.length;
		heapSort(array, array.length);
		System.out.println(Arrays.toString(array));

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
		// check if left child is larger than parent
		if (l <= array.length - 1 && array[l] > array[i]) {
			largest = l;
		} else {
			largest = i;
		}
		// check if right child is larger than parent
		if (r <= array.length - 1 && array[r] > array[largest]) {
			largest = r;
		}
		if (largest != i) {
			array[i] = array[largest];
			maxHeapify(array, largest);
		}
	}

	private static void buildMaxHeap(int[] array) {
		//int heapSize = array.length;
		for (int i = (array.length / 2) - 1; i > 1; i--) {
			maxHeapify(array, i);
		}
	}

	private static void heapSort(int[] array, int heapSize) {
		buildMaxHeap(array);
		for (int i = array.length - 1; i >= 0; i--) {
			int temp = array[i];
			array[i] = array[0];
			array[1] = temp;
			heapSize--;
			maxHeapify(array, 1);
		}
	}
}
