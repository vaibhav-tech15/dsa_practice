package practice.dsa.graphs;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class TopologicalSort {
	public static void main(String[] args) {
		Graph graph = GraphFactory.createGraph("directed", false, false);
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
		Map<Integer, Integer> inDegree = getInDegree(graph);

		Queue<Integer> mQue = new LinkedList<>();
		inDegree.entrySet().forEach(entry -> {
			if (entry.getValue() == 0) {
				mQue.add(entry.getKey());
			}
		});

		StringBuilder sb = new StringBuilder();
		int resultCounter = 0;

		while (!mQue.isEmpty()) {
			GraphNode currentNode = graph.getVerticesList().get(mQue.poll());
			sb.append(currentNode.getNodeLabel() + " -> ");
			resultCounter++;

			
			currentNode = currentNode.getNext();
			while (currentNode != null) {
				int ind = inDegree.get(currentNode.getNodeId());
				inDegree.put(currentNode.getNodeId(), --ind);
				if (ind == 0) {
					mQue.add(currentNode.getNodeId());
				}
				currentNode = currentNode.getNext();
			}
		}

		System.out.println("Result Counter: " + resultCounter);
		System.out.println("Sorted Result: " + sb.toString().substring(0, sb.lastIndexOf(" -> ")));
	}
}
