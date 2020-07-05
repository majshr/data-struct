package leetcode.dongtaiguihua;

public class BeiBao {
	
	static int[][] median; 
	
	/**
	 * 
	 * @param w n中商品，每种商品w[index]
	 * @param v 每种商品价值, v[index]
	 * @param c 背包容量
	 */
	static int knapsack01(int[] w, int[] v, int c){
		median = new int[w.length][c + 1];
		for(int i = 0; i < w.length; i++){
			for(int j = 0; j < c + 1; j++){
				median[i][j] = -1;
			}
		}
		
		return bestValue(w, v, w.length - 1, c);
		
	}
	
	/**
	 * [0...index]的物品，填充容积为c的背包的最大价值
	 * @param w 物品
	 * @param n 物品价值
	 * @param index 第index个物品
	 * @param c 背包容量
	 * @return
	 */
	static int bestValue(int[] w, int[] v, int index, int c){
		// 无法装入物品，或下标小于0
		if(index < 0 || c <= 0){
			return 0;
		}
		
		// 将index的物品，放到容量为c的背包的最大价值
		if(median[index][c] != -1){
			return median[index][c];
		}
		
		// 判断index是否放入
		// 不放入，查最大值
		int res1 = bestValue(w, v, index - 1, c);
		// 放入，查最大值(当前价值 + 之前可放入最大价值)
		if(c >= w[index]){
			int res2 = bestValue(w, v, index - 1, c - w[index]) + v[index];
			
			res1 = Math.max(res1, res2);
		}
		
		return res1;
	}
	
	
	/**
	 * 动态规划
	 * @param w n中商品，每种商品w[index]
	 * @param v 每种商品价值, v[index]
	 * @param c 背包容量
	 */
	static int knapsack02(int[] w, int[] v, int c){
		if(w.length == 0){
			return 0;
		}
		
		median = new int[w.length][c + 1];
		for(int i = 0; i < w.length; i++){
			for(int j = 0; j < c + 1; j++){
				median[i][j] = -1;
			}
		}
		
		// 遍历列j表示背包大小
		for(int j = 0; j <= c; j++){
			// 如果可以放下第0个物品，价值为v[0]; 如果不可以放下，价值为0
			median[0][j] = (j >= w[0] ? v[0] : 0);
		}
		
		// 在每行中逐列解决这个问题
		for(int i = 1; i < w.length; i++){
			for(int j = 0; j <= c ; j++){
				// （1）不放入i物品；不管i物品（2）放入i物品，剩余放入c-v[i]背包
				median[i][j] = median[i - 1][j];
				if(j >= w[i]){
					median[i][j] = Math.max(median[i][j], v[i] + median[i - 1][j - w[i]]);
				}
			}
		}
		
		return median[w.length - 1][c];
	}
	
	public static void main(String[] args) {
		
	}
}

























