package data.struct.graph;

import java.util.Iterator;

/**
 * 邻接矩阵表示图（无向图）
 * @author maj
 *
 */
public class GraphAdjacencyMatrix implements GraphInterface{
	/**
	 * 矩阵
	 */
	int[][] graph;
	public GraphAdjacencyMatrix(int n) {
		graph = new int[n][n];
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				graph[i][j] = 0;
			}
		}
	}
	
	@Override
	public boolean addEdgeNo(int m, int n){
		if(m >= graph.length || n >= graph.length){
			return false;
		}
		
		graph[m][n] = 1;
		graph[n][m] = 1;
		
		return true;
	}
	
	@Override
	public boolean addEdge(int m, int n){
		if(m >= graph.length || n >= graph.length){
			return false;
		}
		// 如果边已经存在，就重新设置一次，天然避免平行边
		graph[m][n] = 1;
		
		return true;
	}

	@Override
	public int size() {
		return graph.length;
	}
	
	@Override
	public Iterator<Integer> getIterator(int v){
		return new MyIterator(v);
	}
	
	@Override
	public void print(){
		for(int i = 0; i < size(); i++){
			for(int j = 0; j < size(); j++){
				System.out.print(graph[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	/**
	 * 根据每个顶点，迭代所有的边
	 * @author maj
	 *
	 * @param <Integer>
	 */
	private class MyIterator implements Iterator<Integer>{

		int index; //访问到哪个下标了
		int v; //访问哪个元素（哪个边）
		
		public MyIterator(int v) {
			index = 0;
			this.v = v;
		}
		
		/**
		 * 是否有下一个元素
		 */
		@Override
		public boolean hasNext() {
			while(index < graph[v].length){
				// 如果存在连接，返回true，由next方法返回连接的点（index）。
				if(graph[v][index] > 0){
					return true;
				}
				// 如果不存在连接，index边后移，继续判断
				index++;
			}
			return false;
			
		}

		/**
		 * 获取下一个元素
		 */
		@Override
		public Integer next() {
			// 如果有相邻元素，返回index，并将index指向下一位
			return  index++;
		}

	}
}
