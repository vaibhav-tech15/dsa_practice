package practice.dsa.graphs;

import java.util.Stack;

public class DFS {

	private static void runAlgorithm(Graph graph) {
		Stack<GraphNode> mStack = new Stack<>();
		mStack.push(graph.getVerticesList().get(0));
		
		int[] visited = new int[graph.getVertices()];
		StringBuilder sb = new StringBuilder();
		
		while(!mStack.isEmpty()) {
			GraphNode currentNode = mStack.pop();
			
			if(visited[currentNode.getNodeId()] == 0) {
				visited[currentNode.getNodeId()] = 1;
				sb.append(currentNode.getNodeLabel() + " -> ");
			}
			
			GraphNode adjNode = graph.getVerticesList().get(currentNode.getNodeId()).getNext();
			while(adjNode != null) {
				if(visited[adjNode.getNodeId()] == 0) {
					mStack.push(adjNode);
				}
				adjNode = adjNode.getNext();
			}
		}
		
		System.out.println("DFS Output: " + sb.toString().substring(0, sb.lastIndexOf(" -> ")));
	}
	
	public static void main(String[] args) {
		Graph graph = GraphFactory.createGraph("undirected", false, false);
		runAlgorithm(graph);
	}
}
