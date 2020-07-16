package practice.dsa.graphs;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class TopologicalSortDFS {
	public static void main(String[] args) {
		Graph graph = GraphFactory.createGraph("directed", false);
		runAlgorithm(graph);
	}

	private static Map<Integer, Integer> getInDegree(Graph graph) {
		Map<Integer, Integer> inDegree = new HashMap<>();

		for (GraphNode headNode : graph.getVerticesList()) {
			if (!inDegree.containsKey(headNode.getNodeId())) {
				inDegree.put(headNode.getNodeId(), 0);
			}

			GraphNode currentNode = headNode.getNext();
			while (currentNode != null) {
				if (!inDegree.containsKey(currentNode.getNodeId())) {
					inDegree.put(currentNode.getNodeId(), 1);
				} else {
					inDegree.put(currentNode.getNodeId(), inDegree.get(currentNode.getNodeId()) + 1);
				}
				currentNode = currentNode.getNext();
			}
		}

		return inDegree;
	}

	private static void runAlgorithm(Graph graph) {
		Stack<Integer> mStack = new Stack<>();

		Map<Integer, Integer> inDegree = getInDegree(graph);

		mStack.push(inDegree.entrySet().stream().filter(entry -> entry.getValue() == 0).map(entry -> entry.getKey())
				.findFirst().get());

		int[] visited = new int[graph.getVertices()];
		StringBuilder sb = new StringBuilder();

		while (!mStack.isEmpty()) {
			int nodeId = mStack.pop();
			if (visited[nodeId] == 0) {
				visited[nodeId] = 1;
				sb.append(graph.getVerticesList().get(nodeId).getNodeLabel() + " -> ");
			}

			GraphNode currentNode = graph.getVerticesList().get(nodeId).getNext();
			while (currentNode != null) {
				if (visited[currentNode.getNodeId()] == 0) {
					mStack.push(currentNode.getNodeId());
				}
				currentNode = currentNode.getNext();
			}
		}

		System.out.println("Sorted Result: " + sb.toString().substring(0, sb.lastIndexOf(" -> ")));
	}
}
