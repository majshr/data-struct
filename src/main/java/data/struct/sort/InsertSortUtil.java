package data.struct.sort;

public class InsertSortUtil {
	/**
	 * 插入排序
	 * 	当前索引左边都是排好序的, 将当前位置插入到左边合适的位置; 遍历左边元素, 比当前元素大的右移
	 * 特点:
	 * 	当前索引的左边都是排好序的, 但他们的最终位置还不确定, 可能给更小的元素让位置, 右移
	 * 	所需时间取决于输入中元素的初始顺序, 对一个很大且有的元素已经有序, 回避对随机顺序的数组或是逆序数组进行排序快得多	
	 */
	public static void insertSort(Comparable[] a) {
		
		//i为当前位置, i之前的元素已经排好序了, 将a[i]插入到左边合适的位置
		//i从1开始, 因为第0位只有一位, 直接是排好的
		for(int i = 1; i < a.length; i++) {
			//记录需要排序的元素, i之前的元素已经排好序了
			Comparable temp = a[i];
			//在排好序的数组中, 倒着往前比较, 比a[i]大的, 直接往后移一位
			int j = i - 1;
			for(; j >= 0; j--) {
				//a[j] > temp, a[j]后移一位, 而不是进行交换操作, 可以提高效率
				if(less(a[j], temp)) {
					//change(a, j, j + 1);
					a[j + 1] = a[j];
				}else {
					break;
				}
			}
			//j可能在正常位置, 此时a[j] <= temp, 在j的后一位设置值; j也可能为-1, 在下标0处设置值
			a[j + 1] = temp;
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
