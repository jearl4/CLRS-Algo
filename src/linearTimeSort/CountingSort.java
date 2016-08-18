package linearTimeSort;

import java.util.Arrays;

public class CountingSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] input = { 5, 4, 3, 2, 1, 0 };
		int[] sortedArray = new int[6];
		countingSort(input, sortedArray, 6);
		System.out.println(Arrays.toString(sortedArray));

	}

	public static void countingSort(int[] inputArray, int[] sortedArray, int k) {
		int[] temp = new int[k];
		for (int i = 0; i < k; i++) {
			temp[i] = 0;
		}
		for (int j = 0; j < inputArray.length; j++) {
			temp[inputArray[j]] = temp[inputArray[j]] + 1;
		}
		for (int i = 1; i < k; i++) {
			temp[i] = temp[i] + temp[i - 1];
		}
		for (int j = inputArray.length - 1; j > 0; j--) {
			sortedArray[temp[inputArray[j]]] = inputArray[j];
			temp[inputArray[j]] = temp[inputArray[j]] - 1;
		}
	}

}
