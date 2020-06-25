package practice.dsa.recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EVENM {
	private static int n;
	private static int testCases;
	
	private static void getInputs(BufferedReader reader) throws IOException {
	    n = Integer.parseInt(reader.readLine());
	}
	
	public static void createMatrix(int matSize, int[][] result) {
		if(matSize == 1) {
			result[0][0] = 1;
			return;
		} else {
			createMatrix(matSize-1, result);
			int start = ((matSize-1)*(matSize-1)) + 1;
			for(int i=0; i<matSize; i++) {
				result[i][matSize-1] = start++;
			}
			for(int i=matSize-2; i>=0; i--) {
				result[matSize-1][i] = start++;
			}
		}
	}
	
	public static void runAlgorithm() {
		int[][] result = new int[n][n];
		createMatrix(n, result);
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				System.out.print(result[i][j] + "\t");
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        testCases = Integer.parseInt(reader.readLine());    
        
        for(int i = 0; i < testCases; i++) {
	        getInputs(reader);
	        runAlgorithm();
	    }
	}
 }
