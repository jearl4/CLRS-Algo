package insertionsort;

import java.util.Random;

/**
 * Implementation of the pseudo-code found in C.L.R.S. introduction to algorithm
 * book. Insertion sort should take O(n^2) time but may be useful for sorting
 * small arrays. Insertion sort is also in-place and only takes up theta(1)
 * memory
 * 
 * @author J.T. Earl
 *
 */
public class InsertionSort {

	public static void main(String[] args) {
		Random rand = new Random();
		int[] array = new int[5];
		// fill array with random numbers 0-9
		for (int i = 0; i < array.length; i++) {
			array[i] = rand.nextInt(10);
		}

		// sort array
		insertSort(array);

		// print sorted array
		for (int j = 0; j < array.length; j++) {
			System.out.print(array[j] + " ");
		}
	}

	/**
	 * insertion sort with O(n^2) time
	 * 
	 * @param array
	 * @return sorted array
	 */
	private static int[] insertSort(int[] array) {
		for (int i = 1; i < array.length; i++) {
			int key = array[i];
			// insert array[j] into the sorted sequence array[1..j-1]
			int j = i - 1;
			while (j >= 0 && array[j] > key) {
				array[j + 1] = array[j];
				j = j - 1;
			}
			array[j + 1] = key;
		}
		return array;
	}

}
