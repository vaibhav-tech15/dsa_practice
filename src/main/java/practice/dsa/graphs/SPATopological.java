package practice.dsa.graphs;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Single Source Shortest Path Algorithm for DAG (Directed Acyclic Graph) Using
 * Topological Sorting
 * 
 * @author vaibhavjain
 *
 */
public class SPATopological {
	public static void main(String[] args) {
		Graph graph = GraphFactory.createGraph("directed", true, true);
		runAlgorithm(graph, 0);
	}

	private static void runAlgorithm(Graph graph, int source) {
		List<Integer> sortedNodeIds = TopologicalSort.runTopologicalSort(graph);

		System.out.println("Topologically Sorted Nodes : " + sortedNodeIds.stream().map(nodeId -> graph.getVerticesList().get(nodeId).getNodeLabel())
				.collect(Collectors.joining(" -> ")));

		int[] distance = new int[graph.getVertices()];
		int[] previous = new int[graph.getVertices()];

		for (int i = 0; i < graph.getVertices(); i++) {
			distance[i] = Integer.MAX_VALUE;
			previous[i] = -1;
		}
		distance[source] = 0;

		int i = -1;
		while (sortedNodeIds.get(++i) != source)
			;

		for (; i < sortedNodeIds.size(); i++) {
			// Relax all outgoing edges from current node
			GraphNode currentNode = graph.getVerticesList().get(sortedNodeIds.get(i));
			GraphNode adjNode = currentNode.getNext();

			while (adjNode != null) {
				if (distance[adjNode.getNodeId()] > (distance[currentNode.getNodeId()]
						+ graph.getWeights()[currentNode.getNodeId()][adjNode.getNodeId()])) {
					distance[adjNode.getNodeId()] = distance[currentNode.getNodeId()]
							+ graph.getWeights()[currentNode.getNodeId()][adjNode.getNodeId()];
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
