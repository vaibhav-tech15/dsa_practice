package practice.dsa.graphs;

import java.util.Arrays;
import java.util.PriorityQueue;

public class PrimsAlgo {
	public static void main(String[] args) {
		Graph graph = GraphFactory.createGraph("undirected", true, false);
		runAlgorithm(graph, 0);
	}

	private static void runAlgorithm(Graph graph, int source) {
		int[] distance = new int[graph.getVertices()];
		int[] previous = new int[graph.getVertices()];
		boolean[] added = new boolean[graph.getVertices()];
		
		for (int i = 0; i < graph.getVertices(); i++) {
			distance[i] = -1;
			previous[i] = -1;
		}
		distance[source] = 0;

		PriorityQueue<Integer> pQue = new PriorityQueue<>((node1, node2) -> {
			return distance[node1] - distance[node2];
		});
		pQue.add(source);

		while (!pQue.isEmpty()) {
			GraphNode currentNode = graph.getVerticesList().get(pQue.poll());
			added[currentNode.getNodeId()] = true;
			
			GraphNode adjNode = currentNode.getNext();
			while (adjNode != null) {
				//int newDistance = distance[currentNode.getNodeId()] + graph.getWeights()[currentNode.getNodeId()][adjNode.getNodeId()];
				if (distance[adjNode.getNodeId()] == -1) {
					distance[adjNode.getNodeId()] = graph.getWeights()[currentNode.getNodeId()][adjNode.getNodeId()];
					pQue.add(adjNode.getNodeId());
					previous[adjNode.getNodeId()] = currentNode.getNodeId();
				} else if (!added[adjNode.getNodeId()] && distance[adjNode.getNodeId()] > graph.getWeights()[currentNode.getNodeId()][adjNode.getNodeId()]) {
					distance[adjNode.getNodeId()] = graph.getWeights()[currentNode.getNodeId()][adjNode.getNodeId()];
					pQue.remove(adjNode.getNodeId());
					pQue.add(adjNode.getNodeId());
					previous[adjNode.getNodeId()] = currentNode.getNodeId();
				}
				adjNode = adjNode.getNext();
			}
		}
		
		System.out.print("Distances from Node-" + source + " : ");
		Arrays.stream(distance).forEach(dist -> System.out.print(dist + " "));

		System.out.println();

		System.out.print("Previous Vertices : ");
		Arrays.stream(previous).forEach(prev -> System.out.print(prev + " "));
	}
}
