package data.struct.util;
/**
 * 工具类
 * (1) 交换数组的两个元素
 * (2) 打印字符串数组
 * @author bhz（maj）
 * @since 2020年7月4日
 */
public class Utils {
	/**
	 * 交换两个元素的值
	 * @param arr
	 * @param i
	 * @param j
	 */
	public static <T> void change(T[] arr, int i, int j){
		if(i != j){
			T temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;	
		}
	}
	
	/**
	 * 打印数组
	 * @param arr
	 */
	public static void printArr(Object[] arr){
		for(int i = 0; i < arr.length; i++){
			System.out.print(arr[i] + ", ");
		}
		System.out.println();
	}
	
	
	public static void main(String[] args) {
		
	}
}