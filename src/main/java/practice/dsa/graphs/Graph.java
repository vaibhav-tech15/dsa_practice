package practice.dsa.graphs;

import java.util.List;

public class Graph {
	private int vertices;
	private int edges;
	private List<GraphNode> verticesList;
	private int[][] weights;
	
	public int getVertices() {
		return vertices;
	}
	public void setVertices(int vertices) {
		this.vertices = vertices;
	}
	public int getEdges() {
		return edges;
	}
	public void setEdges(int edges) {
		this.edges = edges;
	}
	public List<GraphNode> getVerticesList() {
		return verticesList;
	}
	public void setVerticesList(List<GraphNode> verticesList) {
		this.verticesList = verticesList;
	}
	public int[][] getWeights() {
		return weights;
	}
	public void setWeights(int[][] weights) {
		this.weights = weights;
	}
}
