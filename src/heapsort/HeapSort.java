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
		buildMaxHeap(array);
		System.out.println(Arrays.toString(array));
		heapSort(array);
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

	private static void maxHeapify(int[] array, int i, int heapSize) {
		int l = leftChild(i);
		int r = rightChild(i);
		int largest = i;
		// check if left child is larger than parent
		if (l <= heapSize && array[l] > array[i]) {
			largest = l;
		} else {
			largest = i;
		}
		// check if right child is larger than parent
		if (r <= heapSize && array[r] > array[largest]) {
			largest = r;
		}
		if (largest != i) {
			int temp = array[i];
			array[i] = array[largest];
			array[largest] = temp;
			maxHeapify(array, largest, heapSize);
		}
	}

	private static void buildMaxHeap(int[] array) {
		int heapSize = array.length - 1;
		for (int i = (heapSize / 2); i >= 0; i--) {
			maxHeapify(array, i, heapSize);
		}
	}

	private static void heapSort(int[] array) {
		buildMaxHeap(array);
		int heapSize = array.length - 1;
		for (int i = heapSize; i > 0; i--) {
			int temp = array[i];
			array[i] = array[0];
			array[0] = temp;
			heapSize--;
			maxHeapify(array, 0, heapSize);
		}
	}
}
