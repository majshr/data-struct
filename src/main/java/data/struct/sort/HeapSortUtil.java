package data.struct.sort;

public class HeapSortUtil {
	/**
	 * 堆排序 堆排序是利用堆这种数据结构而设计的一种排序算法，堆排序是一种选择排序， 它的最坏，最好，平均时间复杂度均
	 * 为O(nlogn)，它也是不稳定排序。
	 * 
	 * 堆是一棵完全二叉树 每个结点的值都大于或等于其左右孩子结点的值，称为大顶堆；(升序使用)
	 * 或者每个结点的值都小于或等于其左右孩子结点的值，称为小顶堆。(降序使用)
	 * 
	 * 堆在数组中的性质 A[i]的左节点为A[2i+1],右节点为A[2i+2]，父节点为A[i/2]
	 * 
	 * 大根堆, 父节点元素不小于任意子节点元素
	 * 
	 * 给定一个完全二叉树，除了根节点以外，此二叉树满足“堆序性”，
	 * “元素下沉”的目的是在此二叉树中将根节点的元素放至合适的坑位，相应地调整其他元素，最终使得包括根节点在内的
	 * 整棵二叉树都满足“堆序性”。
	 * 
	 * 当父节点的元素值小于左子节点的元素值或者右子节点的元素值时，将父节点的元素值与左右子节点较大的元素值进行交换，
	 * 针对交换后的父节点，
	 * 循环执行“元素下沉”操作， “元素下沉”的终止条件就是父节点的元素值不小于其任意左右子节点的元素值，或者
	 * 当前父节点无子节点（即当前节点为叶子节点）。
	 * 
	 * a.将无需序列构建成一个堆，根据升序降序需求选择大顶堆或小顶堆;
	 * b.将堆顶元素与末尾元素交换，将最大或最小元素"沉"到数组末端;
	 * c.重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素，反复执行调整+交换步骤，直到整个序列有序。
	 */
	public static void heapSort(Comparable[] a) {
		
		//(1)总共需要调整(len-1)次最大堆, 才能找到(len - 1)次最大值, 才能排好序
		for(int k = 1; k < a.length; k++) {
			//(2)一次将数组调整为最大堆(对每个节点进行设置为大顶堆)
			for(int i = a.length - 1; i >= 0; i--) {
				heapify(a, i, a.length - k + 1);
			}	
			
			//(3)将堆顶元素(最大值)放到末尾
			change(a, 0, a.length - k);
		}
	}
	
	
	/**
	 * 将当前节点设置为大顶堆
	 * @param a 当成是完全二叉树的数组
	 * @param curIndex 当前父节点的位置(对此节点设置大顶堆)
	 * @param size 节点总数
	 */
	private static void heapify(Comparable[] a, int curIndex, int size) {
		if(curIndex < size) {
			//当前节点的左右节点(公式获得)
			int left = 2 * curIndex + 1;
			int right = 2 * curIndex + 2;
			
			//记录父节点与左右子节点最大值得那个节点的下标
			int maxIndex = curIndex;
			if(right < size) {
				if(less(a[right], a[maxIndex])) {
					maxIndex = right;
				}
			}
			if(left < size) {
				if(less(a[left], a[maxIndex])) {
					maxIndex = left;
				}
			}
			
			//如果最大的元素不是根元素, 交换元素, 并根据交换后的子节点继续设置大顶堆
			if(maxIndex != curIndex) {
				//交换最大值与当前顶的元素
				change(a, curIndex, maxIndex);
				
				//对交换的顶点继续设置大顶堆
				heapify(a, maxIndex, size);
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
