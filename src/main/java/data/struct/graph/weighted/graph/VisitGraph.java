package data.struct.graph.weighted.graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * 遍历图
 * @author maj
 *
 */
public class VisitGraph {
	/**
	 * 边是否被访问过
	 */
	boolean[] visited;
	/**
	 * 联通分量的数量（一个图可能有多个未连接的图组成）
	 */
	int count;

	/**
	 * 判断顶点是否相连；每个点的值等于属于自己的联通分量，两个节点如果值相同，就在相同的联通分量里，就是连接的
	 */
	int[] id;
	
	GraphInterface graph;
	
	/**
	 * 深度优先遍历
	 * @param graph
	 */
	public VisitGraph(GraphInterface graph) {
		this.graph = graph;
		
		visited = new boolean[graph.size()];
		for(int i = 0; i < graph.size(); i++){
			visited[i] = false;
		}
		
		id = new int[graph.size()];
		for(int i = 0; i < graph.size(); i++){
			id[i] = -1;
		}
		
		count = 0;
		
		// 遍历
		for(int i = 0; i < graph.size(); i++){
			// 如果i节点没有遍历到，从i开始，深度遍历
			if(!visited[i]){
				// 一次深度优先遍历，会将联通的顶点都遍历到，仍没有遍历到的节点就在另一个联通分量里
				dfs(i);
				count++;
			}
		}
	}
	
	/**
	 * 计算一个图联通分量的数量
	 * @return
	 */
	public int count(){
		return count;
	}
	
	/**
	 * <B>方法名称：判断两个点是否连接</B><BR>
	 * <B>概要说明：</B><BR>
	 * @param v
	 * @param w
	 * @return
	 */
	boolean isConnected(int v, int w){
		// 属于同一个连接分量，就是连接的
		return id[v] == id[w];
	}
	
	/**
	 * 从 v节点开始，深度遍历
	 * @param v
	 */
	void dfs(int v){
		// 设置节点所属联通分量的值
		id[v] = count;
		
		// 访问到了v节点
		visited[v] = true;
		System.out.println(v + " ");
		// 访问v连接的节点
		Iterator<Integer> iter = graph.getIterator(v);
		while(iter.hasNext()){
			int adjacentNode = iter.next();
			// 如果没有被访问过，对相邻节点进行深度遍历
			if(!visited[adjacentNode]){
				dfs(adjacentNode);	
			}
		}
	}
	
	/**************************求两个节点间的一条路径*******************************/
	/**
	 * 每访问一个节点，记录是从哪个节点过来的；
	 * index，val 意义：路径从val节点走到index节点
	 */
	int[] from;
	private int v; // 寻址起点
	/**
	 * 求从v节点到任意节点的路径
	 * @param graph
	 * @param v
	 */
	public VisitGraph(GraphInterface graph, int v){
		//初始化算法
		this.graph = graph;
		visited = new boolean[graph.size()];
		from = new int[graph.size()];
		for(int i = 0; i < graph.size(); i++){
			visited[i] = false;
			from[i] = -1;
		}
		this.v = v;
		
		// 寻路算法
		dfsFindWay(v);
	}
	
	
	/**
	 * 是否连接到了w
	 * @param w
	 * @return
	 */
	boolean hasPath(int w){
		// 经过一次深度优先遍历，如果访问到了w节点，说明是连接的
		return visited[w];
	}
	
	/**
	 * 查找经过w的所有路径
	 * @param w
	 * @param paths
	 */
	void path(int w, List<Integer> paths){
		// 根据from数组，往回推路径
		Stack<Integer> s = new Stack<>();
		int p = w;
		while(p != -1){
			s.push(p);
			// 从from[p] 节点走到的p节点；如果from[p] == -1，说明找到了起始的v节点
			p = from[p]; 
		}
		
		paths.clear();
		while(!s.isEmpty()){
			paths.add(s.pop());
		}
	}
	
	/**
	 * 
	 * <B>方法名称：显示一条路径</B><BR>
	 * <B>概要说明：</B><BR>
	 * @param w
	 */
	void showPath(int w){
		List<Integer> paths = new ArrayList<Integer>();
		path(w, paths);
		System.out.println(paths);
	}
	
	/**
	 * 从 v节点开始，深度优先遍历，寻路算法
	 * @param v
	 */
	void dfsFindWay(int v){
		// 访问到了v节点
		visited[v] = true;
		System.out.println(v + " ");
		// 访问v连接的节点
		Iterator<Integer> iter = graph.getIterator(v);
		
		while(iter.hasNext()){
			int adjacentNode = iter.next();
			// 如果没有被访问过，对相邻节点进行深度遍历
			if(!visited[adjacentNode]){
				// 从v节点走到adjacentNode节点
				from[adjacentNode] = v;
				dfsFindWay(adjacentNode);	
			}
		}
	}
	
	/********************广度优先，可以求最短路径************************/
	
	/**
	 * 记录从起始节点到每个节点的最短路径是多少
	 */
	int[] order;
	
	/**
	 * 求从v节点到任意节点的路径
	 * @param graph
	 * @param v
	 */
	public VisitGraph(GraphInterface graph, int v, boolean guangDuYouXian){
		//初始化算法
		this.graph = graph;
		visited = new boolean[graph.size()];
		from = new int[graph.size()];
		order = new int[graph.size()];
		for(int i = 0; i < graph.size(); i++){
			visited[i] = false;
			from[i] = -1;
			order[i] = -1;
		}
		this.v = v;
		
		// 寻路算法
		Queue<Integer> queue = new LinkedList<Integer>();
		
		queue.add(v);
		visited[v] = true;
		order[v] = 0; // v到v的最短路径为0
		while(!queue.isEmpty()){
			int cur = queue.poll();
			// 访问cur的相邻节点
			Iterator<Integer> iter = graph.getIterator(cur);
			while(iter.hasNext()){
				int adjacentNode = iter.next();
				// 如果没有访问过，1：加入到队列（加入到队列就当成访问过了，因为再出现这个元素，也不应该再加入队列了） 2：并设置路径长度
				// 3，记录节点是从哪个节点走过来的
				if(!visited[adjacentNode]){
					queue.add(adjacentNode);
					visited[adjacentNode] = true;
					order[adjacentNode] = order[cur] + 1;
					from[adjacentNode] = cur;
				}
			}
		}
	}
}




















