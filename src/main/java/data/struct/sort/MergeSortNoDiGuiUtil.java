package data.struct.sort;

public class MergeSortNoDiGuiUtil {
	public static void mergeSort(int[] arr){
		// 临时存储数据数组
		int[] temp = new int[arr.length];
		
		// len表示归并子数组长度, 1表示一个一个的归并, 归并后程度为2; 2表示两个两个的归并; 归并后长度为4, 一次类推
		for(int len = 1; len < arr.length; len = 2 * len){
			// 按照长度len归并数组, 归并后长度翻倍
			for(int start = 0; start < arr.length; start = start + 2 * len){
				/**************两个数组进行归并一次的操作**********/
				// 归并的中间值
				int mid = start + len - 1;
				// 两个数组合并, 数组长度为2*len; 对于数组长度不满足2的x次幂的数组，end可能会大于数组长度
				// 并且mid可能会大于end
				int end = Math.min(start + 2 * len - 1, arr.length-1);
				
				// 如果mid大于end, 说明此时数组剩余元素都是归并左侧元素, 不需要合并了
				if(mid < end){
					mergeArrs(arr, start, mid, end, temp);	
				}
			}
		}
	}
	
	/**
	 * 两个有序数组合并为有序数组
	 * @param arr
	 * @param left
	 * @param mid
	 * @param right
	 * @param temp
	 */
	private static void mergeArrs(int arr[], int left, int mid, int right, int[] temp){
		int start = left;
		int end = right;
		int first = left; // 左边一段
		int second = mid + 1; // 右边一段
		int index = 0;
		for(; first <= mid && second <= right; ){
			if(arr[first] < arr[second]){
				temp[index++] = arr[first++];
			}else{
				temp[index++] = arr[second++];
			}
		}
		
		while(first <= mid){
			temp[index++] = arr[first++];
		}
		while(second <= right){
			temp[index++] = arr[second++];
		}
		
		// temp中存储的是合并好, 排好序的元素, 复制到数组中
		index = 0;
		while(start <= end){
			arr[start++] = temp[index++];
		}
	}
}
