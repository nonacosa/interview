package algorithm;

import java.sql.SQLOutput;

/**
 * @author wenda.zhuang
 * @Date 2021/4/27 下午12:42
 * @Description 快排
 * @E-mail sis.nonacosa@gmail.com
 */
public class QuickSort {

	public void quickSort (int[] arr, int left, int right) {

		if(left >= right ) {}

		int base = arr[left];
		int i = left,j = right;

		while (i != j) {

			while (arr[j] < base && i < j) {
					j--;
			}

			while (arr[i] > base && i < j) {
				i++;
			}

			if(i < j) {
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}

		}

		arr[left] = arr[i];
		arr[i] = base;
		quickSort(arr,left,i - 1);
		quickSort(arr,i + 1,right);
		System.out.println(arr);
	}

	public static void main(String[] args) {
		new QuickSort().quickSort(new int[]{3,51,1,25,5,7},0,5);
	}
}
