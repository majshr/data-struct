package data.struct.heap;

import data.struct.util.Utils;

/**
 * 大顶堆
 * @author bhz（maj）
 * @since 2019年7月9日
 */
public class BigTopHeap<T extends Comparable<T>> {
	/**堆数组*/
	private Object[] data;
	/**堆中数据个数*/
	private int count;
	
	/**
	 * 初始化创建一个空堆
	 * @param capacity
	 */
	public BigTopHeap(int capacity){
		data = new Object[capacity];
		count = 0; // 初始堆中元素个数为0
	}
	
	/**
	 * 无序数组转换成大顶堆
	 * 讲数组转换为堆, 从第一个非叶子节点开始, 对他和之前的所有非叶子节点, 组down操作
	 * <B>构造方法</B><BR>
	 * @param arr
	 */
	public BigTopHeap(T[] arr){
		// 初始化成员变量
		data = arr;
		count = arr.length;
		
		// 从第一个不是叶子节点的节点开始, 将他向下移到自己的位置; 然后它前边的节点就都是非叶子节点, 做同样操作
		// 第一个非叶子节点是 (arr.length - 1) / 2
		for(int i = (arr.length - 1) / 2; i >= 0; i--){
			shiftDown(i);
		}
	}
	
	/**
	 * 大顶堆插入，先将元素放到末尾，向上冒泡
	 * @param val
	 */
	public void insert(T val){
		if(count == data.length){
			throw new RuntimeException("堆满了，无法插入了!");
		}
		
		// 1,将值放到尾部
		data[count] = val;
		
		// 2,count指向下一位
		count++;
		
		// 3,新添加的值向上移动
		shiftUp(count - 1);
	}
	
	/**
	 * 从堆中移除一个元素
	 * <B>方法名称：</B><BR>
	 * <B>概要说明：</B><BR>
	 * @return
	 */
	public T extractMax(){
		if(count == 0){
			throw new RuntimeException("堆已经为空了");
		}
		
		// 1,取出堆顶元素
		T top = (T) data[0];
		
		// 2,堆最后一个元素，移到堆顶，并将元素减1
		data[0] = data[count - 1];
		data[count - 1] = null;
		count--;
		
		// 3,堆顶元素，向下移动
		shiftDown(0);
 		
		return top;
	}
	
	/**
	 * 对堆数组进行堆排序；每次取出最大元素，放到数组尾部；循环完即可
	 */
	public void sort(){
		for(int i = count - 1; i >= 0; i--){
			data[i] = extractMax();
		}
	}
	
	/**
	 * 顶点下移到合适的位置
	 * <B>方法名称：</B><BR>
	 * <B>概要说明：</B><BR>
	 */
	private void shiftDown(int index){
		int curIndex = index;
		int leftIndex = curIndex * 2 + 1;
		int rightIndex = curIndex * 2 + 2;
		while(curIndex < count){
			boolean isLessLeft = false;
			boolean isLessRight = false;
			// 比左侧子节点小
			if(leftIndex < count && compare(data[curIndex], data[leftIndex]) < 0){
				isLessLeft = true;
			}
			// 比右侧子节点小
			if(rightIndex < count && compare(data[curIndex], data[rightIndex]) < 0){
				isLessRight = true;;
			}
			
			// 当前元素左右两边都比自己小，是正确的
			if(!isLessLeft && !isLessRight){
				break;
			}
			
			// 交换元素
			if(isLessLeft && isLessRight){
				if(compare(data[leftIndex], data[rightIndex]) > 0){
					Utils.change(data, curIndex, leftIndex);
					curIndex = leftIndex;
				}
				Utils.change(data, curIndex, rightIndex);
				curIndex = rightIndex;
			} else if(isLessLeft){
				Utils.change(data, curIndex, leftIndex);
				curIndex = leftIndex;
			} else if(isLessRight){
				Utils.change(data, curIndex, rightIndex);
				curIndex = rightIndex;
			}
			
			// 计算下一次比较
			leftIndex = curIndex * 2 + 1;
			rightIndex = curIndex * 2 + 2;
		}
	}
	
	/**
	 * 元素从index节点，向上移动到合适位置
	 * @param index
	 */
	private void shiftUp(int index){
		// 将当前节点向上冒泡到合适位置
		int curIndex = index;
		
		// 如果父节点是存在于数组中的（index在0-x范围内）
		// 如果curIndex为0，就表示达到了移动到了顶节点，就不用再移动了
		while(curIndex > 0){
			int parIndex = (curIndex - 1) / 2;
			if(compare(data[parIndex], data[curIndex]) < 0){
				// 交换数值，继续向上冒泡
				Utils.change(data, curIndex, parIndex);
			}
			curIndex = parIndex;
		}
	}
	
	/**
	 * 比较两个元素 obj1 - obj2
	 * @param obj1
	 * @param obj2
	 * @return
	 */
	private int compare(Object obj1, Object obj2){
		T objT1 = (T) obj1;
		T objT2 = (T) obj2;
		return objT1.compareTo(objT2);
	}
	
	private static void test1(){
		BigTopHeap<Integer> heap = new BigTopHeap<Integer>(6);
		heap.insert(100);
		heap.insert(0);
		heap.insert(1);
		heap.insert(10);
		heap.insert(8);
		heap.insert(7);
		Utils.printArr(heap.data);
		
		heap.sort();
		Utils.printArr(heap.data);
        
	}
	
	public static void test2(){
		BigTopHeap<Integer> heap = new BigTopHeap<Integer>(new Integer[]{4, 6, 8, 5, 9});
		Utils.printArr(heap.data);
		
		heap.sort();
		Utils.printArr(heap.data);
		
//        heap.extractMax();
//        Utils.printArr(heap.data);
//        
//        heap.extractMax();
//        Utils.printArr(heap.data);
//        
//        heap.extractMax();
//        Utils.printArr(heap.data);
//        
//        heap.extractMax();
//        Utils.printArr(heap.data);
//        
//        heap.extractMax();
//        Utils.printArr(heap.data);
	}
	
	public static void main(String[] args) {
		test1();
		test2();
        
	}
}
