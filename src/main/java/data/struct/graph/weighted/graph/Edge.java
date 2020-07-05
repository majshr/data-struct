package data.struct.graph.weighted.graph;

import java.lang.reflect.WildcardType;

public class Edge {
	/**
	 * 边，希望能同时表示有向边和无向边
	 * 表示有向边的话，是a指向b
	 */
	int a, b;
	double weight;
	public Edge(int a, int b, double weight) {
		super();
		this.a = a;
		this.b = b;
		this.weight = weight;
	}
	
	public Edge() {
		super();
	}



	/**
	 * <B>方法名称：获取权值</B><BR>
	 * <B>概要说明：</B><BR>
	 * @return
	 */
	double weight(){
		return weight;
	}
	
	/**
	 * 
	 * <B>方法名称：知道一个顶点，获取另一个顶点</B><BR>
	 * <B>概要说明：</B><BR>
	 * @param x
	 * @return
	 */
	int other(int x){
		return x == a ? b : a;
	}
}
