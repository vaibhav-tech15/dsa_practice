package practice.dsa.linkedlists;

public class ListNode<E> {
	
	private E data;
	private ListNode<E> nextNode;
	//For Random pointer questions this reference points to the random list member
	private ListNode<E> previousNode;
	public E getData() {
		return data;
	}
	public void setData(E data) {
		this.data = data;
	}
	public ListNode<E> getNextNode() {
		return nextNode;
	}
	public void setNextNode(ListNode<E> nextNode) {
		this.nextNode = nextNode;
	}
	public ListNode<E> getPreviousNode() {
		return previousNode;
	}
	public void setPreviousNode(ListNode<E> previousNode) {
		this.previousNode = previousNode;
	}
	
	public String toString() {
		return data != null ? data.toString() : null;
	}
}
