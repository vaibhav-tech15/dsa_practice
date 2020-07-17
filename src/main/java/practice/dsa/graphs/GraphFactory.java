package practice.dsa.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GraphFactory {

	private static Map<Integer, Integer[]> graphContent = new HashMap<>();
	private static Map<Integer, String> graphLabels = new HashMap<>();
	private static int[][] graphWeights;

	private static void initUndirectedGraph(boolean weighted) {
		graphLabels.clear();
		graphContent.clear();

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

	private static void initDirectedGraph(boolean weighted, boolean negativeEdges) {
		graphLabels.clear();
		graphContent.clear();

		if (weighted) {
			if (negativeEdges) {
				graphLabels.put(0, "A");
				graphLabels.put(1, "B");
				graphLabels.put(2, "C");
				graphLabels.put(3, "D");
				graphLabels.put(4, "E");
				graphLabels.put(5, "F");
				graphContent.put(0, new Integer[] { 1, 2, 3 });
				graphContent.put(1, new Integer[] { 4 });
				graphContent.put(2, new Integer[] { 1, 4 });
				graphContent.put(3, new Integer[] { 2, 5 });
				graphContent.put(4, new Integer[] { 5 });
				graphContent.put(5, null);

				graphWeights = new int[graphLabels.size()][graphLabels.size()];
				graphWeights[0][1] = 6;
				graphWeights[0][2] = 4;
				graphWeights[0][3] = 5;
				graphWeights[1][4] = -1;
				graphWeights[2][1] = -2;
				graphWeights[2][4] = 3;
				graphWeights[3][2] = -2;
				graphWeights[3][5] = -1;
				graphWeights[4][5] = 3;
			} else {
				graphLabels.put(0, "A");
				graphLabels.put(1, "B");
				graphLabels.put(2, "C");
				graphLabels.put(3, "D");
				graphLabels.put(4, "E");
				graphContent.put(0, new Integer[] { 1, 2 });
				graphContent.put(1, new Integer[] { 4 });
				graphContent.put(2, new Integer[] { 1, 3 });
				graphContent.put(3, new Integer[] { 4 });
				graphContent.put(4, null);

				graphWeights = new int[graphLabels.size()][graphLabels.size()];
				graphWeights[0][1] = 4;
				graphWeights[0][2] = 1;
				graphWeights[1][4] = 4;
				graphWeights[2][1] = 2;
				graphWeights[2][3] = 4;
				graphWeights[3][4] = 4;
			}
		} else {
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
	}

	public static Graph createGraph(String graphType, boolean weighted, boolean negativeEdges) {
		switch (graphType.toLowerCase()) {
		case "directed":
			initDirectedGraph(weighted, negativeEdges);
			break;
		case "undirected":
			initUndirectedGraph(weighted);
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

		if (weighted) {
			graph.setWeights(graphWeights);
		}

		return graph;
	}
}
