package data.struct.sort.util;

public class BubbleSortUtil {
	/************交换排序:交换排序的基本思想都为通过比较两个数的大小，当满足某些条件时对它进行交换从而达到排序的目的。*****************/
	/**
	 * 1,冒泡排序
	 * 思路:
	 * 比较相邻的两个数，如果前者比后者大，则进行交换。每一轮排序结束，选出一个未排序中最大的数放到数组后面。
	 * 最差时间复杂度为O(n^2),平均时间复杂度为O(n^2)。稳定性：稳定。辅助空间O(1)。
	 */
	public static void bubbleSort(Comparable[] a) {
		//每次将最大值替换到最后一位, 总共需要替换len - 1 次, 就排好序了
		for(int i = 1; i < a.length; i++) {
			//每次需要交换的位也会减少
			for(int j = 0; j < a.length - i; j++) {
				if(less(a[j], a[j + 1])) {
					change(a, j, j + 1);
				}
			}
		}
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
}
