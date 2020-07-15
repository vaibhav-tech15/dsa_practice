package practice.dsa.graphs;

public class GraphNode {
	private int nodeId;
	private String nodeLabel;
	private GraphNode next;
	
	public int getNodeId() {
		return nodeId;
	}
	public void setNodeId(int nodeId) {
		this.nodeId = nodeId;
	}
	public String getNodeLabel() {
		return nodeLabel;
	}
	public void setNodeLabel(String nodeLabel) {
		this.nodeLabel = nodeLabel;
	}
	public GraphNode getNext() {
		return next;
	}
	public void setNext(GraphNode next) {
		this.next = next;
	}
}
