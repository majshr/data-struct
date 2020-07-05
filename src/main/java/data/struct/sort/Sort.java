package data.struct.sort;

import java.util.Random;

public class Sort {
	
	/**
	 * 快速排序
	 * @param arr
	 * @param len
	 */
	public static void quickSort(int arr[]){
//		quickSortFind(arr, 0, arr.length - 1);
//		quickSortFind2(arr, 0, arr.length - 1);
//		quickSortFind3(arr, 0, arr.length - 1);
		quickSortFindThreeWays(arr, 0, arr.length - 1);
	}
	
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
	 * 存在大量重复元素的情况
	 * @param arr
	 * @param left
	 * @param right
	 */
	public static void quickSortFind(int arr[], int left, int right){
		if(left >= right){
			return;
		}
		
		//随机选择一个元素作为基准值
		int randIndex = new Random().nextInt(right - left + 1) + left;
		// 将该元素与第一个位置交换即可
		change(arr, left, randIndex);
		
		// 找出基准值位置, 使左边值都比他小, 右边值都比他大, 再对基准值左右两边递归操作
		//[left +1, start]<=baseVal, []
		int start = left + 1; //start为基准值下一位
		int end = right; // end为数组末尾
		int val = arr[left];
		while(start < end){
			/**存在大量相同元素, 让相同元素参与进交换, 使相同元素分布在左右两边*/
			/**如果全是重复元素, start没办法向右移动, end没办法向左移动了, 需要交换完元素, start右移, end左移*/
			// 从右往前找出比基准值小的元素(大于基准值的在右侧)
			while(arr[end] > val && start < end){
				end--;
			}
			// 从左往右找出比基准值大的元素(小于基准值的在左侧)
			while(arr[start] < val && start < end){
				start++;
			}
			
			// 交换
			change(arr, start, end);
			
			start++; end--;
		}
		
		// start == end为退出循环的条件,  此时start就是找到的正确的位置, 
		// (1)但是可能基准值就是当前数组最小值, 如 [1 3 2 4]; 此时start == end ==1, 但是不需要交换值
		// (2)可能基准值就是当前数组最大值, 且有多个, 此时也需要更新基准值位置, 如[17, 0, 17, 17] 
		// 找到下标3的位置, 符合条件, 如果baseIndex不标记新位置3, baseIndex右边的元素, 0就比baseIndex小了 
		int baseIndex = left;
		if(start == end && arr[start] <= val){
			change(arr, left, start);
			baseIndex = start;
		}
		// 如果start>end 为退出条件, 如[4, 5, 2], 
		// start=1, end=2, 交换位置之后, start++, end--, 此时start--为正确的位置
		if(start > end && arr[--start] <= val){
			change(arr, left, start);
			baseIndex = start;
		}
		
		quickSortFind(arr, left, baseIndex - 1);
		quickSortFind(arr, baseIndex + 1, right);
	}
	
	/**
	 * 与基准值相同元素存在于基准值的一边
	 * @param arr
	 * @param left
	 * @param right
	 */
	public static void quickSortFind2(int arr[], int left, int right){
		if(left >= right){
			return;
		}
		
		// 随机选择一个元素作为基准值
//		int randIndex = new Random().nextInt(right - left + 1) + left;
//		if(randIndex < left || randIndex > right){
//			System.out.println(left + " " + randIndex + " " + right);
//		}
//		// 将该元素与第一个位置交换即可
//		change(arr, left, randIndex);
		
		// 找出基准值位置, 使左边值都比他小, 右边值都比他大, 再对基准值左右两边递归操作
		int start = left + 1; //start为基准值下一位
		int end = right; // end为数组末尾
		int val = arr[left];
		while(start < end){
			// 从右往前找出比基准值小的元素
			while(arr[end] > val && start < end){
				end--;
			}
			// 从左往右找出比基准值大的元素(小于等于基准值的在左侧)
			while(arr[start] <= val && start < end){
				start++;
			}
			
			// 交换
			change(arr, start, end);
		}
		
		// start == end为退出循环的条件,  此时start就是找到的正确的位置, 
		// (1)但是可能基准值就是当前数组最小值, 如 [1 3 2 4]; 此时start == end ==1, 但是不需要交换值
		// (2)可能基准值就是当前数组最大值, 且有多个, 此时也需要更新基准值位置, 如[17, 0, 17, 17] 
		// 找到下标3的位置, 符合条件, 如果baseIndex不标记新位置3, baseIndex右边的元素, 0就比baseIndex小了 
		int baseIndex = left;
		if(start == end && arr[start] <= val){
			change(arr, left, start);
			baseIndex = start;
		}
		
		quickSortFind2(arr, left, baseIndex - 1);
		quickSortFind2(arr, baseIndex + 1, right);
	}
	
	/**
	 * 快速排序
	 * @param a
	 * @param left
	 * @param right
	 */
	public static void quickSortFind3(int[] a, int left, int right) {
		if(left >= right) {
			return;
		}
		
		// 随机选择一个元素作为基准值
		int randIndex = new Random().nextInt(right - left + 1) + left;
		if(randIndex < left || randIndex > right){
			System.out.println(left + " " + randIndex + " " + right);
		}
		// 将随机元素与第一个位置交换即可
		change(a, left, randIndex);
		
		//选取左边第一个数为基数
		int val = a[left];
		//基数的下标
		int valIndex = left;
		int l = left;
		int r = right;
		while(left < right) {
			//(1)找比基数小的数(可能第一个基数就是最小的数, 所以考虑边界情况)
			while(right >= 0 && a[right] > val) {
				right--;
			}
			//(2)第一个条件在前边, 因为如果left == a.length时, 判断less(), 会有数组越界异常
			//可能基数是最大的数, 所以考虑边界情况
			while(left < a.length && a[left] <= val) {
				left++;
			}
			//(3)如果是正常的left  和   right  交换两个位置的数
			if(left < right) {
				change(a, left, right);
			}
		}
		//将基数放到正确的位置(right为正确位置)
		change(a, valIndex, right);
		
		//排好基数后, 在对左边的数组和右边的数组进行基数查找排序
		quickSortFind3(a, l, right - 1);
		quickSortFind3(a, right + 1, r);
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
	
	
	public static int mergeFind(int[] arr){
		if(arr.length <= 1){
			return 0;
		}
		int[] temp = new int[arr.length + 1];
		mergeSortFind(arr, 0, arr.length - 1, temp);
		return temp[arr.length];
	}

	private static void mergeSortFind(int[] arr, int left, int right, int[] temp) {
		if(right > left){
			int mid = left + (right - left) / 2;
			mergeSortFind(arr, left, mid, temp);
			mergeSortFind(arr, mid + 1, right , temp);
			mergeArrsFind(arr, left, mid, right, temp);
		}
	}

	/**
	 * 两个有序数组合并为有序数组, 此时查找逆序对
	 */
	public static void mergeArrsFind(int arr[], int left, int mid, int right, int[] temp) {
		int start = left;
		int end = right;
		int first = left; // 左边一段
		int second = mid + 1; // 右边一段
		int index = 0;
		for (; first <= mid && second <= right;) {
			if (arr[first] <= arr[second]) {
				temp[index++] = arr[first++];
			} else {
				temp[index++] = arr[second++];
				/** 如果左边比右边大, 就是一个; 因为是排好序的, 左边的剩余的也比右边当前元素大, 也加进去
				 * 之后右边小的元素归并到新数组, 而和此小元素符合的已都找到
				 * */
				temp[temp.length - 1] += (mid - first + 1);
			}
		}
		
		while (first <= mid) {
			temp[index++] = arr[first++];
		}
		while (second <= right) {
			temp[index++] = arr[second++];
		}

		// temp中存储的是合并好, 排好序的元素, 复制到数组中
		index = 0;
		while (start <= end) {
			arr[start++] = temp[index++];
		}
	}
	
}
