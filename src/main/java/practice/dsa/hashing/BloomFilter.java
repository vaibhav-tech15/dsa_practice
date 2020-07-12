package practice.dsa.hashing;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class BloomFilter {

	private static int[] bloomVector;
	private static List<Function<Integer, Integer>> hashFunctions;
	
	private static void createFilter(int capacity, int hashFunctionCount) {
		bloomVector = new int[capacity];
		hashFunctions = getHashFunctions(hashFunctionCount, capacity);
	}
	
	private static List<Function<Integer, Integer>> getHashFunctions(int hashFunctionCount, int capacity) {
		List<Function<Integer, Integer>> hashFunctions = new ArrayList<>();
		
		int prime = 2;
		
		hashFunctions.add(createHashLambda(prime, capacity));
		
		for(int i=1; i<hashFunctionCount; i++) {
			prime = getNextPrime(prime);
			hashFunctions.add(createHashLambda(prime, capacity));
		}
		
		return hashFunctions;
	}
	
	private static Function<Integer, Integer> createHashLambda(final int shiftSize, final int capacity) {
		return key -> {
			long hash = 0;
			while(key > 0) {
				hash += (hash << shiftSize) + key;
				key = key >> shiftSize;
			}
			return Math.abs((int)(hash % capacity));
		};
	}
	
	private static int getNextPrime(int input) {
		input = (input % 2) == 0 ? input++ : input;
		
		while(!isPrime(input)) {
			input += 2;
		}
		
		return input;
	}

	private static boolean isPrime(int num) {
		if(num <= 1) {
			return false;
		}
		
		if(num <= 3) {
			return true;
		}
		
		if(num % 2 == 0 || num % 3 == 0) {
			return false;
		}
		
		for(int i = 5; i * i <= num; i += 6) {
			if(num % i == 0 || num % (i + 2) == 0) {
				return false;
			}
		}
		
		return true;
	}
	
	private static void addElement(int input) {
		hashFunctions.forEach(hashFunction -> {
			bloomVector[hashFunction.apply(input)] = 1;
		});
	}
	
	private static boolean findElement(int input) {
		return hashFunctions.stream().allMatch(hashFunction -> bloomVector[hashFunction.apply(input)] == 1);
	}
	
	public static void main(String[] args) {
		createFilter(10, 2);
		
		addElement(13456);
		System.out.println("Number present? : " + findElement(13456));
	}
	
	
}
