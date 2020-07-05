package data.struct.graph.weighted.graph;

import java.util.Iterator;

public interface GraphInterface {
	/**
	 * 插入一条边, 无向图
	 * @param m
	 * @param n
	 * @param weight
	 * @return
	 */
	boolean addEdgeNo(int m, int n, double weight);
	
	/**
	 * 插入一条边， 有向图
	 * @param m
	 * @param n
	 * @param weight
	 * @return
	 */
	boolean addEdge(int m, int n, double weight);
	
	/**
	 * 图节点数
	 * @return
	 */
	int size();
	
	/**
	 * 获取遍历顶点的所有边的迭代器
	 * @param v
	 * @return
	 */
	Iterator<Integer> getIterator(int v);
	
	void print();
}
