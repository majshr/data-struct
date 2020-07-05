package data.struct.graph.weighted.graph;

import java.util.Iterator;

public class GraphUtil {
	/**
	 * 生成邻接矩阵无向图
	 * @return
	 */
	public static GraphInterface generateGraphAdjacencyMatrix(){
		GraphInterface graph = new GraphAdjacencyMatrix(7);
		// 不带权重的操作
		graph.addEdgeNo(0, 2, 0d);
		graph.addEdgeNo(0, 3, 0d);
		graph.addEdgeNo(2, 3, 0d);
		graph.addEdgeNo(2, 1, 0d);
		graph.addEdgeNo(0, 5, 0d);
		graph.addEdgeNo(5, 6, 0d);
		graph.addEdgeNo(6, 4, 0d);
		
		return graph;
	}
	
	public static void visitGraphInterface(){
		GraphInterface gr = generateGraphAdjacencyMatrix();
//		gr.print();
//		System.out.println("===============================");
//		for(int i = 0; i < gr.size(); i++){
//			Iterator<Integer> ite =  gr.getIterator(i);
//			while(ite.hasNext()){
//				System.out.print(ite.next());
//			}
//			System.out.println();
//		}
		
		new VisitGraph(gr);
	}
	
	public static void main(String[] args) {
		visitGraphInterface();
	}
}
