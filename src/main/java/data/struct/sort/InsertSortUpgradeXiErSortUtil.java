package data.struct.sort;

public class InsertSortUpgradeXiErSortUtil {
	/**
	 * 希尔排序, 就是改进的插入排序
	 * 希尔排序是把记录按下标的一定增量分组，对每组使用直接插入排序算法排序；
	 * 随着增量逐渐减少，每组包含的关键词越来越多，当增量减至1时，整个文件恰被分成一组，算法便终止。
	 * 
	 * 一般选择增量的规则是：取上一个增量的一半作为此次子序列划分的增量，一般初始值元素的总数量。
	 */
	public static void xiErSort(Comparable[] a) {
		//增量
		int d = a.length / 2;
		while(d >= 1) {
			//从第d位开始, 因为从d位开始, 前边才会有需要排序的元素
			for(int i = d; i < a.length; i++) {
				//当前值
				Comparable val = a[i];
				int j = i - d;
				//直接根据间隔d进行插入排序, 向前找所适合的位置
				while(j >= 0 && less(a[j], val)) {
					a[j+d] = a[j];
					j = j - d;
				}
				a[j + d] = val;
			}
			
			//取下一个分量
			d = d / 2;
			
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
