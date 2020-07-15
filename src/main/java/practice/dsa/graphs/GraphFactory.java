package practice.dsa.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GraphFactory {

	private static Map<Integer, Integer[]> graphContent = new HashMap<>();
	private static Map<Integer, String> graphLabels = new HashMap<>();

	private static void initUndirectedGraph() {
		graphLabels.put(0, "A");
		graphLabels.put(1, "B");
		graphLabels.put(2, "C");
		graphLabels.put(3, "D");
		graphLabels.put(4, "E");
		graphLabels.put(5, "F");
		graphLabels.put(6, "G");
		graphLabels.put(7, "H");
		graphContent.put(0, new Integer[] { 1 });
		graphContent.put(1, new Integer[] { 0, 7, 2 });
		graphContent.put(2, new Integer[] { 1, 3, 4 });
		graphContent.put(3, new Integer[] { 2 });
		graphContent.put(4, new Integer[] { 2, 5, 6, 7 });
		graphContent.put(5, new Integer[] { 4 });
		graphContent.put(6, new Integer[] { 4 });
		graphContent.put(7, new Integer[] { 1, 4 });
	}

	private static void initDirectedGraph() {
		graphLabels.put(0, "2");
		graphLabels.put(1, "3");
		graphLabels.put(2, "5");
		graphLabels.put(3, "7");
		graphLabels.put(4, "8");
		graphLabels.put(5, "9");
		graphLabels.put(6, "10");
		graphLabels.put(7, "11");
		graphContent.put(0, null);
		graphContent.put(1, new Integer[] { 4, 6 });
		graphContent.put(2, new Integer[] { 7 });
		graphContent.put(3, new Integer[] { 4, 7 });
		graphContent.put(4, new Integer[] { 5 });
		graphContent.put(5, null);
		graphContent.put(6, null);
		graphContent.put(7, new Integer[] { 0, 5, 6 });
	}

	public static Graph createGraph(String graphType) {
		switch (graphType.toLowerCase()) {
		case "directed":
			initDirectedGraph();
			break;
		case "undirected":
			initUndirectedGraph();
			break;
		}

		Graph graph = new Graph();
		graph.setVertices(graphContent.size());
		graph.setVerticesList(new ArrayList<>());

		graphContent.entrySet().forEach(entry -> {
			GraphNode currentNode = new GraphNode();
			currentNode.setNodeId(entry.getKey());
			currentNode.setNodeLabel(graphLabels.get(entry.getKey()));
			graph.getVerticesList().add(currentNode);

			Integer[] adjArray = graphContent.get(entry.getKey());
			if (adjArray != null) {
				for (int adjVertex : adjArray) {
					GraphNode node = new GraphNode();
					node.setNodeId(adjVertex);
					node.setNodeLabel(graphLabels.get(adjVertex));
					currentNode.setNext(node);
					currentNode = node;
				}
			}
		});

		return graph;
	}
}
