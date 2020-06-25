package practice.dsa.linkedlists;

import java.util.Random;

public class LinkedListFactory {

	public static ListNode<Integer> integerLinkedList(int size, int upperBound) {
		final ListNode<Integer> head = new ListNode<>();
		
		if (size > 0 && upperBound > 0) {
			Random random = new Random();
			random.ints(size, 1, upperBound).distinct().forEach(number -> { 
				insertNode(head, number);
			});
		}
		return head.getNextNode();		
	}
	
	private static <E> ListNode<E> insertNode(ListNode<E> head, E data) {
		ListNode<E> newNode = new ListNode<>();
		newNode.setData(data);
		
		ListNode<E> currentNode = head;		
		while(currentNode != null && currentNode.getNextNode() != null) {
			currentNode = currentNode.getNextNode();
		}
		
		if(head == null) {
			head = newNode;
		} else {
			currentNode.setNextNode(newNode);
		}
		
		return head;
	}
}
