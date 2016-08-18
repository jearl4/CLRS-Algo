package linearTimeSort;

import java.util.Arrays;

/**
 * When k equals n the sort runs in O(n) time, or O(k + n). It beats the lower
 * bound of (n lg n) due to the lack of comparisons and departure from the
 * comparison sort model.
 * 
 * Counting sort is also stable, meaning that which ever numbers appear first in
 * the input come first in the output array. This allows satellite data to be
 * moved around with the elements being sorted.
 * 
 * @author J.T. Earl
 *
 */

public class CountingSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] input = { 5, 4, 3, 2, 1, 0 };
		int[] sortedArray = new int[input.length];
		countingSort(input, sortedArray, 6);
		System.out.println(Arrays.toString(sortedArray));

	}

	public static void countingSort(int[] inputArray, int[] sortedArray, int k) {
		int[] temp = new int[k];
		for (int i = 0; i < temp.length; i++) {
			temp[i] = 0;
		}
		for (int j = 0; j < inputArray.length; j++) {
			temp[inputArray[j]] = temp[inputArray[j]] + 1;
		}
		for (int i = 1; i < k; i++) {
			temp[i] = temp[i] + temp[i - 1];
		}
		for (int j = sortedArray.length - 1; j >= 0; j--) {
			sortedArray[temp[inputArray[j]] - 1] = inputArray[j];
			temp[inputArray[j]] = temp[inputArray[j]] - 1;
		}
	}

}
