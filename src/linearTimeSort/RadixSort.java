package linearTimeSort;

import java.util.Arrays;

public class RadixSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array = {5,4,3,2,1,0};
		radixSort(array, 1);
		System.out.println(Arrays.toString(array));
	}
	
	public static void radixSort(int[] array, int highestDigit){
		for(int i = 1; i < highestDigit; i++){
			CountingSort.countingSort(array, array, i);
		}
	}
}
