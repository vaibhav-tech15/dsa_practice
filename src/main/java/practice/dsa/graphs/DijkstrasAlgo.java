package practice.dsa.graphs;

import java.util.Arrays;
import java.util.PriorityQueue;

public class DijkstrasAlgo {

	public static void main(String[] args) {
		Graph graph = GraphFactory.createGraph("directed", true, false);
		runAlgorithm(graph, 0);
	}

	private static void runAlgorithm(Graph graph, int source) {
		int[] distance = new int[graph.getVertices()];
		int[] previous = new int[graph.getVertices()];
		for (int i = 0; i < graph.getVertices(); i++) {
			distance[i] = -1;
			previous[i] = -1;
		}
		distance[source] = 0;

		PriorityQueue<Integer> pQue = new PriorityQueue<>((v1, v2) -> {
			return distance[v1] - distance[v2];
		});

		pQue.add(source);

		while (!pQue.isEmpty()) {
			int currentNodeId = pQue.poll();
			GraphNode adjNode = graph.getVerticesList().get(currentNodeId).getNext();

			while (adjNode != null) {
				if (distance[adjNode.getNodeId()] == -1) {
					distance[adjNode.getNodeId()] = distance[currentNodeId]
							+ graph.getWeights()[currentNodeId][adjNode.getNodeId()];
					pQue.add(adjNode.getNodeId());
					previous[adjNode.getNodeId()] = currentNodeId;
				} else if (distance[adjNode.getNodeId()] > (distance[currentNodeId]
						+ graph.getWeights()[currentNodeId][adjNode.getNodeId()])) {
					distance[adjNode.getNodeId()] = distance[currentNodeId]
							+ graph.getWeights()[currentNodeId][adjNode.getNodeId()];
					pQue.remove(adjNode.getNodeId());
					pQue.add(adjNode.getNodeId());
					previous[adjNode.getNodeId()] = currentNodeId;
				}
				adjNode = adjNode.getNext();
			}
		}

		System.out.print("Distances from Node-" + source + " -> ");
		Arrays.stream(distance).forEach(dist -> System.out.print(dist + " "));
		
		System.out.println();
		
		System.out.print("Previous Vertices- ");
		Arrays.stream(previous).forEach(prev -> System.out.print(prev + " "));
	}
}
