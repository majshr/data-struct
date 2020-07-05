package data.struct.sort;

public class SelectSortUtil {
	/**
	 * 选择排序: 
	 *     默认第一个位置为最小元素位置, 向后遍历数组, 与当前最小元素比较, 根据结果, 记录最小元素位置, 
	 *     														遍历完毕后, 将最小元素与第一个位置元素交换; 
	 *     以第二个位置元素为最小元素, 再向后遍历 ...
	 * 特点:
	 *     当前索引的左边都是排好序的
	 *     运行时间与输入无关, 为了找到最小值而扫面一遍数组并不能为下一次扫描提供什么信息
	 */
	public static void selectSort(Comparable[] a) {
		//i记录每次遍历的第一个位置
		for(int i = 0; i < a.length; i++) {
			//默认将第i位做为最小元素, 从第i+1位开始查找最小
			int minIndex = i;
			for(int j = i + 1; j < a.length; j++) {
				//如果第j位数字更小, 记录j下标为最小
				if(!less(a[j], a[minIndex])) {
					minIndex = j;
				}
			}
			change(a, i, minIndex);
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
