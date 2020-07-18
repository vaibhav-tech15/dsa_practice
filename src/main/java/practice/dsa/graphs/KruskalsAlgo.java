package practice.dsa.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class KruskalsAlgo {

	private static class Edge {
		int srcId;
		int destId;
		int weight;
	}

	public static void main(String[] args) {
		Graph graph = GraphFactory.createGraph("undirected", true, false);
		runAlgorithm(graph, 0);
	}

	private static void runAlgorithm(Graph graph, int source) {
		PriorityQueue<Edge> pQue = new PriorityQueue<>((e1, e2) -> e1.weight - e2.weight);
		int[] forest = new int[graph.getVertices()];
		List<List<Integer>> spanningTree = new ArrayList<>();

		for (int i = 0; i < graph.getVertices(); i++) {
			forest[i] = -1;
			spanningTree.add(new ArrayList<>());
		}

		for (int i = 0; i < graph.getWeights().length; i++) {
			for (int j = i; j < graph.getWeights().length; j++) {
				if (graph.getWeights()[i][j] > 0) {
					Edge edge = new Edge();
					edge.srcId = i;
					edge.destId = j;
					edge.weight = graph.getWeights()[i][j];
					pQue.add(edge);
				}
			}
		}

		int forestCounter = 0;
		while (!pQue.isEmpty()) {
			Edge edge = pQue.poll();
			if (forest[edge.srcId] == -1 && forest[edge.destId] == -1) {
				forest[edge.srcId] = forestCounter;
				forest[edge.destId] = forestCounter;
				forestCounter++;
				addEdge(spanningTree, edge.srcId, edge.destId);
			} else if (forest[edge.srcId] == -1) {
				forest[edge.srcId] = forest[edge.destId];
				addEdge(spanningTree, edge.srcId, edge.destId);
			} else if (forest[edge.destId] == -1) {
				forest[edge.destId] = forest[edge.srcId];
				addEdge(spanningTree, edge.srcId, edge.destId);
			} else {
				if (forest[edge.srcId] != forest[edge.destId]) {
					for (int i = 0; i < forest.length; i++) {
						if (forest[i] == forest[edge.destId]) {
							forest[i] = forest[edge.srcId];
						}
					}
					addEdge(spanningTree, edge.srcId, edge.destId);
				}
			}
		}

		System.out.println("Edges in spanning Tree: ");
		for (int i = 0; i < spanningTree.size(); i++) {
			System.out.print(graph.getVerticesList().get(i).getNodeLabel() + " -> ");
			System.out.println(
					spanningTree.get(i).stream().map(nodeId -> graph.getVerticesList().get(nodeId).getNodeLabel())
							.collect(Collectors.joining(",")));
		}
	}

	private static void addEdge(List<List<Integer>> spanningTree, int srcId, int destId) {
		spanningTree.get(srcId).add(destId);
		spanningTree.get(destId).add(srcId);
	}
}
