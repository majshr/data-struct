package leetcode.dongtaiguihua;

import java.util.List;

public class Test {
	// 记录中间计算的值，记忆化搜素，时间复杂度变为了O（n）级别的
	int[] fibVal;
	int fib(int n){
		if(n == 0){
			return 0;
		}
		if(n == 1){
			return 1;
		}
		// 没有计算过，需要计算
		if(fibVal[n] == -1){
			fibVal[n] = fib(n - 1) + fib(n - 2);
		}
		return fibVal[n];
	}
	
	int[] climbStairsReady;
	private void initClimbStairs(int n){
		climbStairsReady = new int[n];
		for(int i = 0; i < n; i++){
			climbStairsReady[i] = -1;
		}
	}
	/**
	 * 
	 * 70,爬台阶
	 * <B>方法名称：</B><BR>
	 * <B>概要说明：</B><BR>
	 * @param n
	 * @return
	 */
    public int climbStairs(int n) {
    	// 如果只剩一层，只有一种，就只爬一阶
        if(n == 1){
        	return 1;
        }
        // 如果剩两层，有两种，爬两次一阶或一次两阶
        if(n == 2){
        	return 2;
        }
        
        //climbStairs(n - 1) 爬一阶台阶，剩余的台阶可爬得种类数
        //climbStairs(n - 2) 爬两阶台阶，剩余的台阶可爬的种类数
//        return climbStairs(n - 1) + climbStairs(n - 2);
        
        if(climbStairsReady[n] == -1){
        	climbStairsReady[n] = climbStairs(n - 1) + climbStairs(n - 2);
        }
        return climbStairsReady[n];
        
    }
	
	public static void main(String[] args) {
		Test test = new Test();
		test.rob2(new int[]{2,7,9,3,1});
	}
	
	
	
	int[] rob;
    public int rob(int[] nums) {
    	rob = new int[nums.length];
    	for(int i = 0; i < nums.length; i++){
    		rob[i] = -1;
    	}
        return tryRob(nums, 0);
    }
    /**
     * 考虑抢劫nums[index....nums.size()]这个范围所有房子，找到最大值
     * @param nums
     * @param index
     * @return
     */
    public int tryRob(int[] nums, int index){
    	if(index >= nums.length){
    		return 0;
    	}
    	
    	if(rob[index] != -1){
    		return rob[index];
    	}
    	
    	int res = 0;
    	for(int i = index; i < nums.length; i++){
    		// 找出当前值 + 之后查找的最大值
    		res = Math.max(res, nums[i] + tryRob(nums, i + 2));
    	}
    	
    	rob[index] = res;
    	
    	return res;
    }
    
    public int rob2(int[] nums) {
    	int length = nums.length;
    	if(length == 0){
    		return 0;
    	}
    	if(length == 1){
    		return nums[0];
    	}
    	if(length == 2){
    		return Math.max(nums[0], nums[1]);
    	}
    	
    	rob = new int[length];
    	for(int i = 0; i < length; i++){
    		rob[i] = -1;
    	}
    	
    	rob[length - 1] = nums[length - 1];
    	rob[length - 2] = nums[length - 2];
    	
    	int res = Math.max(rob[length - 1], rob[length - 2]);
    	
    	for(int i = length - 3; i >= 0; i--){
    		// 当前值nums[i] + 当前值之后两位开始的最大值：rob[i + 2]
    		rob[i] = nums[i] + rob[i + 2];
    		res = Math.max(res, rob[i]);
    	}
    	
    	return res;
    }
	
}




















