package practice.dsa.linkedlists;

public class NodeBlockReverse {

	public static void main(String[] args) {
		ListNode<Integer> list = LinkedListFactory.integerLinkedList(10, 200);
		
		ListNode<Integer> currentNode = list;
		
		while(currentNode != null) {
			System.out.print("->" + currentNode);
			currentNode = currentNode.getNextNode();
		}
	}
}
