package practice.dsa.hashing;

import java.util.Random;

public class HashingOperations {
		
	public static void main(String[] args) {
		HashTable hashTable = HashTable.createHashTable(32, 8);
		Random random = new Random(); 
		while(hashTable.getSize() < 28) {
			hashTable.insert(random.nextInt(100));
		}
		System.out.println("Table: " + hashTable);
		System.out.println("Table Size: " + hashTable.getSize());
		System.out.println("Capacity: " + hashTable.getCapacity());
		
		int num = 0;
		while(hashTable.search(num=random.nextInt(100)));
		hashTable.insert(num);
		System.out.println("Table: " + hashTable);
		System.out.println("Table Size: " + hashTable.getSize());
		System.out.println("Capacity: " + hashTable.getCapacity());		
	}
}
