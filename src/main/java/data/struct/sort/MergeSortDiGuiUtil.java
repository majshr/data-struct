package data.struct.sort;

public class MergeSortDiGuiUtil {
	/**
	 * 归并排序
	 */
	public static void mergeSort(int[] a) {
		// 在排序前，先建好一个长度等于原数组长度的临时数组，在归并时存储临时值使用
		// (避免递归时创建, 造成空间的频繁创建和销毁)
		int[] temp = new int[a.length];
		mergeSort(a, 0, a.length - 1, temp);
	}
	
	/**
	 * 递归排序, left为当前分解数组的左节点, right为当前分解数组的右节点
	 * 不断递归后, 在每个区间仅有一个元素时, 就是排好序的, 开始对各个区间进行合并
	 *    算法:将数组从中间截断, 对左侧归并排序, 对右侧归并排序, 再将将两边数组归并为一个数组
	 */
	private static void mergeSort(int[] a, int left, int right, int[] temp) {
		if(left < right) {
			int mid = (left + right) / 2;
			// 对左侧数据进行归并排序
			mergeSort(a, left, mid, temp);
			// 对右侧数据进行归并排序
			mergeSort(a, mid + 1, right, temp);
			// 对排好序的左侧数据和右侧数据进行归并
			merge(a, left, right, mid, temp);
		}
	}
	
	/**
	 * 合并left~mid   mid+1~right  两个数组
	 */
	private static void merge(int[] a, int left, int right, int mid, int[] temp) {
		int i = left;//左序列指针
		int j = mid + 1;//有序列指针
		int t = 0;//临时数组指针
		//(1)合并两个数组
		while(i <= mid && j <= right) {
			if(a[i] > a[j]) {
				temp[t++] = a[j++];
			}else {
				temp[t++] = a[i++];
			}
		}
		
		//(2)将剩余元素放进temp中
		while(i <= mid) {
			temp[t++] = a[i++];
		}
		while(j <= right) {
			temp[t++] = a[j++];
		}
		
		//(3)将temp中元素全部拷贝到原数组中
		t = 0;
		while(left <= right) {
			a[left++] = temp[t++];
		}
	}
	
	/**
	 * @Description: 
	 * a>b,返回true  a<b,返回false
	 */
	private static boolean less(Comparable a, Comparable b) {
		if(a.compareTo(b) > 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * @Description: 
	 * 交换数组a的下标index1和index2的位置
	 */
	private static void change(Comparable[] a, int index1, int index2) {
		if(index1 == index2) {
			return;
		}
		Comparable val = a[index1];
		a[index1] = a[index2];
		a[index2] = val;
	}
}
