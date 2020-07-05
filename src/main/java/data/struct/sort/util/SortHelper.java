package data.struct.sort.util;

import java.util.Random;
/**
 * 排序辅助类
 * 		生成无序数组
 * 		打印数组
 * @author bhz（maj）
 * @since 2020年7月4日
 */
public class SortHelper {
	/**
	 * 生成随机数组
	 * @param n
	 * @param left
	 * @param right
	 * @return
	 */
	public static int[] generateRandomArray(int n, int left, int right){
		if(right <= left){
			throw new RuntimeException("right和left参数错误!");
		}
		
		int diff = right - left;
		
		int[] arr = new int[n];
		for(int i = 0; i < n; i++){
			arr[i] = new Random().nextInt(diff) + left;
		}
		return arr;
	}
	
	/**
	 * 打印数组
	 * @param arr
	 */
	public static void printArr(int[] arr){
		for(int i = 0; i < arr.length; i++){
			System.out.print(arr[i] + ", ");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		int[] arr = generateRandomArray(100, 0, 10);
//		arr = new int[]{98, 99};
//		arr = new int[]{1, 7, 5, 2, 9};
//		arr = new int[]{66, 80, 40, 0, 97, 85, 47, 48, 65, 34, 90, 48, 74, 92, 57, 17, 98, 90, 34, 95, 22, 4, 17, 42, 66, 23, 97, 40, 27, 17, };
//		arr = new int[]{1, 1,1,1,1,1};
//		arr = new int[]{4, 7, 5, 2, 9};
//		arr = new int[]{3,1,4,5,2};
		
//		Sort.insertSort(arr, arr.length);
		
		printArr(arr);
//		int n = Sort.mergeFind(arr);
//		MaxHeap.heapSort(arr);
		printArr(arr);
	}
	

}






















