package practice.dsa.hashing;

import java.util.Arrays;

public class HashTable {
	private int tableSize;
	private int size;
	private int loadFactor;
	private HashTableNode[] tableNodes;

	public int getTableSize() {
		return tableSize;
	}

	public int getSize() {
		return size;
	}

	private HashTable() {
	}

	public static HashTable createHashTable(int capacity, int loadFactor) {
		HashTable table = new HashTable();
		table.tableSize = capacity / loadFactor;
		table.size = 0;
		table.loadFactor = loadFactor;
		table.tableNodes = new HashTableNode[table.tableSize];

		for (int i = 0; i < table.tableNodes.length; i++) {
			table.tableNodes[i] = new HashTableNode();
		}

		return table;
	}

	public boolean search(int data) {
		int index = hash(data);
		ListNode currentNode = this.tableNodes[index].getBlockListNode();
		while (currentNode != null) {
			if (currentNode.getData() == data) {
				return true;
			}
			currentNode = currentNode.getNext();
		}
		return false;
	}

	public void insert(int data) {
		if(this.search(data)) {
			return;
		}
		int index = hash(data);
		ListNode node = new ListNode();
		node.setKey(index);
		node.setData(data);
		node.setNext(this.tableNodes[index].getBlockListNode());
		this.tableNodes[index].setBlockSize(this.tableNodes[index].getBlockSize() + 1);
		this.tableNodes[index].setBlockListNode(node);
		this.size++;
		
		if(this.size > this.loadFactor * this.tableSize * 0.9) {
			reHash();
		}
	}
	
	private void reHash() {
		int oldTableSize = this.tableSize;
		this.tableSize *= 2;
		HashTableNode[] oldTable = this.tableNodes;
		this.tableNodes = new HashTableNode[this.tableSize];
		
		for(int i=0; i<this.tableSize; i++) {
			this.tableNodes[i] = new HashTableNode();
		}
		
		for(int i=0; i<oldTableSize; i++) {
			ListNode currentNode = oldTable[i].getBlockListNode();
			while(currentNode != null) {
				int index = hash(currentNode.getData());
				currentNode.setKey(index);
				ListNode nextNode = currentNode.getNext();
				currentNode.setNext(this.tableNodes[index].getBlockListNode());
				this.tableNodes[index].setBlockListNode(currentNode);
				currentNode = nextNode;
			}
		}
	}
	
	public void delete(int data) {
		int index = hash(data);
		ListNode currentNode = this.tableNodes[index].getBlockListNode();
		ListNode prevNode = null;
		while (currentNode != null) {
			if (currentNode.getData() == data) {
				ListNode temp = currentNode.getNext();
				if(prevNode != null) {
					prevNode.setNext(temp);
				} else {
					this.tableNodes[index].setBlockListNode(temp);
				}
				this.size--;
				break;
			} else {
				currentNode = currentNode.getNext();
			}
		}
	}
	
	private int hash(int data) {
		return data % this.tableSize;
	}
	
	public int getCapacity() {
		return this.loadFactor * this.tableSize;
	}
	
	public String toString() {
		if(this.tableNodes == null || this.tableNodes.length == 0) {
			return "Empty Table";
		}
		StringBuilder sb = new StringBuilder();
		Arrays.stream(this.tableNodes).filter(hashTableNode -> hashTableNode.getBlockListNode() != null).forEach(hashTableNode -> {
			ListNode currentNode = hashTableNode.getBlockListNode();
			while(currentNode != null) {
				sb.append("->" + currentNode.getKey() + "|" + currentNode.getData());
				currentNode = currentNode.getNext();
			}
			sb.append("\n");
		});
		return sb.toString();
	}
}
