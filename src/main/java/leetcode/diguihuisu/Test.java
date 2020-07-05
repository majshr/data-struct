package leetcode.diguihuisu;

import java.util.ArrayList;
import java.util.List;

public class Test {
	
	private static List<String> letterMap = new ArrayList<>(10);
	static{
		letterMap.add(""); // 0
		letterMap.add(""); // 1
		letterMap.add("abc"); // 2
		letterMap.add("def"); // 3
		letterMap.add("ghi"); // 4
		letterMap.add("jkl"); // 5
		letterMap.add("mno"); // 6
		letterMap.add("pqrs"); // 7
		letterMap.add("tuv"); // 8
		letterMap.add("wxyz"); // 9
	}
	
	/**
	 * 17题
	 * <B>方法名称：</B><BR>
	 * <B>概要说明：</B><BR>
	 * @param digits
	 * @return
	 */
    public List<String> letterCombinations(String digits) {
        List<String> list = new ArrayList<>();
        if(digits == null || digits.isEmpty()){
        	return list;
        }
        for(int i = 0; i < digits.length(); i++){
        	if(digits.charAt(i) >= '0' && digits.charAt(i) <= '9'){
        		continue;
        	}
        	return list;
        }
        findCoombination(digits, 0, "", list);
        
        return list;
    }
    
    /**
     * 
     * @param digits 数字字符串
     * @param index index下标
     * @param s 从【0，index-1】翻译得到的一个字母字符串
     */
    void findCoombination(String digits, int index, String s, List<String> result){
    	if(index >= digits.length()){
    		// 此时s就是一个解；
    		result.add(s);
    		return;
    	}
    	
    	char c = digits.charAt(index);
    	
    	String letters = letterMap.get(c - '0');
    	for(int i = 0; i < letters.length(); i++){
    		// 加上当前位得到字符串，然后处理下一位
    		findCoombination(digits, index + 1, s + letters.charAt(i), result);
    	}
    	
    	return;
    }
    
    
    /**
     * 46
     * <B>方法名称：</B><BR>
     * <B>概要说明：</B><BR>
     * @param nums
     * @return
     */
    boolean[] isUsed;
    public List<List<Integer>> permute(int[] nums) {
    	
    	List<List<Integer>> res = new ArrayList<>();
    	if(nums.length == 0){
    		return res;
    	}
    	
    	// 用于判断数字是否被使用
    	isUsed = new boolean[nums.length];
    	for(int i = 0; i < nums.length; i++){
    		isUsed[i] = false;
    	}
    	
    	generatePermutation(nums, 0, new ArrayList<>(), res);
    	
    	return res;
    }
    /**
     * 
     * @param nums 
     * @param index
     * @param oneRes 一个排列结果
     */
    public void generatePermutation(int[] nums, int index, List<Integer> oneRes, List<List<Integer>> res){
    	if(index == nums.length){
    		// 一个结果
    		res.add(new ArrayList<>(oneRes));
    		return;
    	}
    	
    	for(int i = 0; i < nums.length; i++){
    		// 判断数组nums[i]是否已经使用
    		if(!isUsed[i]){
    			
    			// 没被使用，可以添加
    			int num = nums[i];
    			oneRes.add(num);
    			isUsed[i] = true;
    			
    			// 继续向下递归
    			generatePermutation(nums, index + 1, oneRes, res);
    			
    			// 获取到结果后，进行新一次判断时，回退，修改为未使用
    			oneRes.remove(i);
    			isUsed[i]= false;
    		}
    		
    	}
    }
    
    List<List<Integer>> res = new ArrayList<>();
    
    /**
     * 77
     * <B>方法名称：</B><BR>
     * <B>概要说明：</B><BR>
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine(int n, int k) {
        return null;
    }
    
    private void generateCo(int n, int k, int start, List<Integer> oneRes){
    	if(oneRes.size() == k){
    		
    	}
    }
    
    /**
     * 79
     * <B>方法名称：</B><BR>
     * <B>概要说明：</B><BR>
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {
    	// 从一个节点开始查找
        for(int i = 0; i < board.length; i++){
        	for(int j = 0; j < board[i].length; j++){
        		// 递归
        	}
        }
        
        return false;
    }
    /**偏移量数组*/
    int d[][] = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    /**标识是否访问了元素*/
    boolean[][] visited;
    boolean searchWorld(char[][] board, String word, int index, int startx, int starty){
    	if(index == word.length() - 1){
    		return board[startx][starty] == word.charAt(index);
    	}
    	
    	if(board[startx][starty] == word.charAt(index)){
    		//设置visit为true
//    		visit[][] = true;
    		
    		// 从 startx,starty触发，向四个方向查找
    		for(int i = 0; i < 4; i++){
    			
    			int newx = startx + d[i][0];
    			int newy = starty + d[i][1];
    			//判断边界符合条件，没有访问过；继续访问
//    			if(search()){return true}
    		}
    	}
    	
    	return false;
    }
    
    public static void main(String[] args) {
    	Test test = new Test();
		System.out.println(test.letterCombinations("234"));
	}
}





























