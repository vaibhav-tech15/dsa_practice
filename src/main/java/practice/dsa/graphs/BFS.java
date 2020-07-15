package practice.dsa.graphs;

import java.util.LinkedList;
import java.util.Queue;

public class BFS {

	private static void runAlgorithm(Graph graph) {
		Queue<GraphNode> mQue = new LinkedList<>();
		mQue.add(graph.getVerticesList().get(0));
		
		int[] visited = new int[graph.getVertices()];
		StringBuilder sb = new StringBuilder();
		
		while(!mQue.isEmpty()) {
			GraphNode currentNode = mQue.poll();
			
			if(visited[currentNode.getNodeId()] == 0) {
				visited[currentNode.getNodeId()] = 1;
				sb.append(currentNode.getNodeLabel() + " -> ");
			}
			
			GraphNode adjNode = graph.getVerticesList().get(currentNode.getNodeId()).getNext();
			while(adjNode != null) {
				if(visited[adjNode.getNodeId()] == 0) {
					mQue.add(adjNode);
				}
				adjNode = adjNode.getNext();
			}
		}
		
		System.out.println("BFS Output: " + sb.toString().substring(0, sb.lastIndexOf(" -> ")));
	}
	
	public static void main(String[] args) {
		Graph graph = GraphFactory.createGraph("Undirected");
		runAlgorithm(graph);
	}
}
