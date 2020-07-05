package data.struct.sort;

public class QuickSort {
	/**
	 * 快速排序
	 * 选一个基数, 找到基数正确的位置, 使基数左边的数都比它小, 基数右边的数都比它大,
	 * 然后对基数左边的数组, 基数右边的数组进行同样的操作
	 * 
	 * 选取下标left的元素为基数
	 * 选一个left指针, 一个right指针 
	 * while(left < right){
	 * 		rignt往左走, 比基数小时停止
	 * 		left指针往右走, 比基数大时停止
	 *      交换两个位置的元素
	 * }
	 *  
	 *  
	 *  优化
	 *  	(1)为了避免数组中有很多排好序的, 排序退化, 可以随机选择一个数, 与基准值下标交换, 进行比较
	 *  	(2)三路快排
	 */
	public static void quickSort(Comparable[] a) {
		quickSortPaiXu(a, 0, a.length - 1);
		System.out.println();
	}
	
	private static void quickSortPaiXu(Comparable[] a, int left, int right) {
		if(left >= right) {
			return;
		}
		//选取左边第一个数为基数
		Comparable val = a[left];
		//基数的下标
		int valIndex = left;
		int l = left;
		int r = right;
		while(left < right) {
			//(1)找比基数小的数(可能第一个基数就是最小的数, 所以考虑边界情况)
			while(right >= 0 && less(a[right], val)) {
				right--;
			}
			//(2)第一个条件在前边, 因为如果left == a.length时, 判断less(), 会有数组越界异常
			//可能基数是最大的数, 所以考虑边界情况
			while(left < a.length && !less(a[left], val)) {
				left++;
			}
			//(3)如果是正常的left  和   right  交换两个位置的数
			if(left < right) {
				change(a, left, right);
			}
		}
		//将基数放到正确的位置(right为比)
		change(a, valIndex, right);
		
		//排好基数后, 在对左边的数组和右边的数组进行基数查找排序
		quickSortPaiXu(a, l, right - 1);
		quickSortPaiXu(a, right + 1, r);
	}
	
	/**
	 * @Description: 
	 * a>b,返回true  a<b,返回false
	 */
	public static boolean less(Comparable a, Comparable b) {
		if(a.compareTo(b) > 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * @Description: 
	 * 交换数组a的下标index1和index2的位置
	 */
	public static void change(Comparable[] a, int index1, int index2) {
		if(index1 == index2) {
			return;
		}
		Comparable val = a[index1];
		a[index1] = a[index2];
		a[index2] = val;
	}
	
	//*******************************************************
	// 三路快排
	/**
	 * 三路快排, 讲数组分成了三部分, 小于V, 等于V, 大于V; 排序仅需要排序两边的元素即可
	 * @param arr
	 * @param l
	 * @param r
	 */
	public static void quickSortFindThreeWays(int arr[], int l, int r){
		if(r - l <= 1){
			return;
		}
		
		// 4, 7, 5, 2, 9
		/*
		 *  以baseIndex=1, baseVal4为基准, lt=2指向7, gt=末尾指向len; 此时lt之前的都比base小, gt之后都比基准大; 从index=1向后遍历(即i)
		 *  	index=1, val=7, val > baseVal, 移向末尾, 和9交换, 4, 9, 5, 2, 7 ; gt--前移一位
		 *  	index=1, val=9, val > baseVal, 移向末尾, 和2交换, 4, 2, 5, 9, 7 ; gt--前移一位
		 *  	index=1, val=2; val < baseVal, 移向前边, 和2交换, 4, 2, 5, 9, 7 ; lt++后移一位, index++后移一位
		 *  	index=2, val=5, val > baseVal, 移向末尾,  4, 2, 5, 9, 7 gt--前移一位, 此时index = gt了
		 *  	一次遍历完成, 此时baseIndex和lt下标交换
		 *  
		 */
		int baseVal = arr[l];
		int lt = l + 1; 
		int gt = r + 1; 
		int index = l + 1; 
		
		// gt指向len, 所以gt-1为最会一个元素, i可以等于gt-1
		while(index < gt){
			// 当前值比baseVal小, 在i左侧
			if(arr[index] < baseVal){
				change(arr, index, lt);
				lt++;
				index++;
			}
			// 当前值比baseVal大, 在i右侧
			else if(arr[index] > baseVal){
				change(arr, index, gt - 1);
				gt--;
			}
			else{
				index++;
			}
		}
		// 将基准值放到找到的位置
		change(arr, l, lt - 1);
		quickSortFindThreeWays(arr, l, lt - 2);
		quickSortFindThreeWays(arr, gt, r);
	}
	
	/**
	 * 交换数组两个位置元素
	 * @param arr
	 * @param i
	 * @param j
	 */
	public static void change(int[] arr, int i, int j){
		if(i != j){
			int temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;	
		}
	}
	//*******************************************************
}
