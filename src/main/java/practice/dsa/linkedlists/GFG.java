package practice.dsa.linkedlists;

import java.io.*;

class GFG {
    private static int testCases, size1, size2;
    
	private static int[] arr1;
	
	private static int[] arr2;
	
	private static void getInputs(BufferedReader reader) throws IOException {
	    String[] sizeArray = reader.readLine().trim().split("\\s+");
	    size1 = Integer.parseInt(sizeArray[0]);
	    size2 = Integer.parseInt(sizeArray[1]);
	    
	    arr1 = new int[size1];
	    arr2 = new int[size2];
	    
	    int index = 0;
	    for(String str : reader.readLine().trim().split("\\s+")) {
	        arr1[index++] = Integer.parseInt(str);
	    }
	    index = 0;
	    for(String str : reader.readLine().trim().split("\\s+")) {
	        arr2[index++] = Integer.parseInt(str);
	    }
	}
	
	/*private static int leftChild(int root, int i, int heapEnd) {
	    int left = (2 * (i - root)) + 1 + root;
	    if(left >= heapEnd) {
	        left = -1;
	    }
	    return left;
	}
	
	private static int rightChild(int root, int i, int heapEnd) {
	    int right = (2 * (i - root)) + 2 + root;
	    if(right >= heapEnd) {
	        right = -1;
	    }
	    return right;
	}
	
	private static void swap(int left, int right) {
	    if(left != right) {
	        if(left < size1 && right < size1) {
	            arr1[left] += arr1[right];
	            arr1[right] = arr1[left] - arr1[right];
	            arr1[left] -= arr1[right];
	        } else if (left < size1 && right >= size1) {
	            right -= size1;
	            arr1[left] += arr2[right];
	            arr2[right] = arr1[left] - arr2[right];
	            arr1[left] -= arr2[right];
	        } else {
	            left -= size1;
	            right -= size1;
	            arr2[left] += arr2[right];
	            arr2[right] = arr2[left] - arr2[right];
	            arr2[left] -= arr2[right];
	        }
	    }
	}
	
	private static int getArr(int i) {
	    if(i>=size1) {
	        return arr2[i-size1];
	    } else {
	        return arr1[i];
	    }
	}
	
	private static void percolateUp(int i, int root) {
	    int heapEnd = size1 + size2;
	    int left = leftChild(root, i, heapEnd);
	    int right = rightChild(root, i, heapEnd);
	    int min = i;
	    
	    if(left != -1 && getArr(left) < getArr(i)) {
	        min = left;
	    }
	    if(right != -1 && getArr(right) < getArr(min)) {
	        min = right;
	    }
	    
	    if(min != i) {
	        swap(i, min);
	        percolateUp(min, root);
	    }
	}
	
	private static void heapify(int root) {
	   int size = size1 + size2;
	   
	   for(int i = ((size - 1) / 2) - 1; i >=root; i--) {
	       percolateUp(i, root);
	   }
	}
	
	private static void runAlgorithm() {
	    for(int i=0; i<((size1 + size2 - 1) / 2); i++) {
	        heapify(i);
	    }
	    
	    StringBuilder sb = new StringBuilder();
	    for(int cur : arr1) {
	        sb.append(cur + " ");
	    }
	    for(int cur : arr2) {
	        sb.append(cur + " ");
	    }
	    
	    System.out.println(sb);
	}*/
	
	private static int leftChild(int i, int heapSize) {
	    int left = (2 * i) + 1;
	    if(left >= heapSize) {
	        left = -1;
	    }
	    return left;
	}
	
	private static int rightChild(int i, int heapSize) {
	    int right = (2 * i) + 2;
	    if(right >= heapSize) {
	    	right = -1;
	    }
	    return right;
	}
	
	private static void percolateDown(int[] arr, int i, int heapSize) {
		int left = leftChild(i, heapSize);
		int right = rightChild(i, heapSize);
		int max = i;
		
		if(left != -1 && arr[left] > arr[i]) {
			max = left;
		}
		if(right != -1 && arr[right] > arr[max]) {
			max = right;
		}
		
		if(max != i) {
			swap(arr, max, i);
			percolateDown(arr, max, heapSize);
		}
	}
	
	private static void heapify(int[] arr, int heapSize) {
		for(int i=(heapSize-1)/2; i>=0; i--) {
			percolateDown(arr,i,heapSize);
		}
	}
	
	private static void heapSort(int[] arr) {
		for(int i=0; i<arr.length; i++) {
	        heapify(arr, arr.length - i);
	        System.out.println("i=" + i);
	        StringBuilder sb = new StringBuilder();
		    for(int cur : arr) {
		        sb.append(cur + " ");
		    }
		    System.out.println(sb);
	        swap(arr, 0, arr.length - i - 1);
	        sb = new StringBuilder();
		    for(int cur : arr) {
		        sb.append(cur + " ");
		    }
		    System.out.println(sb);
	    }
	}
	
	private static void swap(int[] arr, int i, int j) {
		arr[i] += arr[j];
		arr[j] = arr[i] - arr[j];
		arr[i] -= arr[j];
	}
	
	private static void swap(int i, int j) {
		arr1[i] += arr2[j];
		arr2[j] = arr1[i] - arr2[j];
		arr1[i] -= arr2[j];
	}
	
	private static void runAlgorithm() {
		int pos1 = 0, pos2 = 0;
		
		while(pos1 + pos2 < size1 && pos2 < size2) {
			if(arr1[pos1] <= arr2[pos2]) {
				pos1++;
			} else {
				pos2++;
			}
		}
		
		int insertAt = size1 - pos2;
		for(int i=0; i<pos2; i++) {
			swap(insertAt+i, i);
		}
		StringBuilder sb = new StringBuilder();
	    for(int cur : arr1) {
	        sb.append(cur + " ");
	    }
	    /*for(int cur : arr2) {
	        sb.append(cur + " ");
	    }*/
	    
	    System.out.println(sb);
	    
		heapSort(arr1);
		
		sb = new StringBuilder();
	    for(int cur : arr1) {
	        sb.append(cur + " ");
	    }
	    for(int cur : arr2) {
	        sb.append(cur + " ");
	    }
	    
	    System.out.println(sb);
		
		heapSort(arr2);
		
		sb = new StringBuilder();
	    for(int cur : arr1) {
	        sb.append(cur + " ");
	    }
	    for(int cur : arr2) {
	        sb.append(cur + " ");
	    }
	    
	    System.out.println(sb);
	}
	
	public static void main (String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        testCases = Integer.parseInt(reader.readLine());    
        
        for(int i = 0; i < testCases; i++) {
	        getInputs(reader);
	        runAlgorithm();
	    }
	}
}
