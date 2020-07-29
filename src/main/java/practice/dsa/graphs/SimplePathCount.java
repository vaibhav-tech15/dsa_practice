package practice.dsa.graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * Program to count number of simple paths from a given source to a given destination vertex.
 * This algorithm makes use of DFS
 * 
 * @author vaibhavjain
 *
 */
public class SimplePathCount {
	
	public static void main(String[] args) throws Exception {
		Graph graph = GraphFactory.createGraph("undirected", true, false);
		runAlgorithm(graph, 0, 1);
	}

	private static void runAlgorithm(Graph graph, int source, int destination) throws Exception {
		Stack<Integer> mStack = new Stack<>();
		mStack.push(source);
		
		int[] visited = new int[graph.getVertices()];
		List<String> paths = new ArrayList<>();
		LinkedList<Integer> path = new LinkedList<>();
		
		int count = 0;
						
		while(!mStack.isEmpty()) {
			int current = mStack.pop();
			if(current == destination) {
				StringBuilder pathSb = new StringBuilder();
				path.forEach(vertex -> {
						pathSb.append(graph.getVerticesList().get(vertex).getNodeLabel());
				});
				pathSb.append(graph.getVerticesList().get(destination).getNodeLabel());
				paths.add(pathSb.toString());
				
				count++;
			} else if(current < 0) {
				path.removeLast();
				visited[Math.abs(current)-1] = 0;
			} else {
				visited[current] = 1;
				path.add(current);
				GraphNode adjNode = graph.getVerticesList().get(current).getNext();
				if(current != source) {
					mStack.push((-1)*(current + 1));
				}
				while(adjNode != null) {
					if(visited[adjNode.getNodeId()] != 1) {
						mStack.push(adjNode.getNodeId());
					}
					adjNode = adjNode.getNext();
				}
			}
		}
		
		System.out.println("Path count -> " + count);
		System.out.print("Paths -> " + paths.stream().collect(Collectors.joining(",")));
	}
}
