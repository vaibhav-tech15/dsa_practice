package practice.dsa.hashing;

public class HashTableNode {
	private int blockSize;
	private ListNode blockListNode;
	public int getBlockSize() {
		return blockSize;
	}
	public void setBlockSize(int blockSize) {
		this.blockSize = blockSize;
	}
	public ListNode getBlockListNode() {
		return blockListNode;
	}
	public void setBlockListNode(ListNode blockListNode) {
		this.blockListNode = blockListNode;
	}
}
