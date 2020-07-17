package practice.dsa.graphs;

import java.util.Arrays;

public class BellmanFordSimple {

	public static void main(String[] args) {
		Graph graph = GraphFactory.createGraph("directed", true, true);
		runAlgorithm(graph, 0);
	}

	private static void runAlgorithm(Graph graph, int source) {
		int[] distance = new int[graph.getVertices()];
		int[] previous = new int[graph.getVertices()];

		for (int i = 0; i < graph.getVertices(); i++) {
			distance[i] = Integer.MAX_VALUE;
			previous[i] = -1;
		}
		distance[source] = 0;

		for (int i = 0; i < graph.getVertices() - 1; i++) {
			graph.getVerticesList().forEach(vertex -> {
				GraphNode adjNode = vertex.getNext();
				while (adjNode != null) {
					if (distance[adjNode.getNodeId()] > (distance[vertex.getNodeId()]
							+ graph.getWeights()[vertex.getNodeId()][adjNode.getNodeId()])) {
						distance[adjNode.getNodeId()] = distance[vertex.getNodeId()]
								+ graph.getWeights()[vertex.getNodeId()][adjNode.getNodeId()];
						previous[adjNode.getNodeId()] = vertex.getNodeId();
					}
					adjNode = adjNode.getNext();
				}
			});
		}
		
		System.out.print("Distances from Node-" + source + " -> ");
		Arrays.stream(distance).forEach(dist -> System.out.print(dist + " "));
		
		System.out.println();
		
		System.out.print("Previous Vertices- ");
		Arrays.stream(previous).forEach(prev -> System.out.print(prev + " "));
	}
}
