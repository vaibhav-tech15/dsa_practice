package practice.dsa.arrays;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PolygonTriangles {
	private static int n, b1, b2;
	private static int twoVs, threeVs;
	private static int testCases;
	
	private static void getInputs(BufferedReader reader) throws IOException {
	    n = Integer.parseInt(reader.readLine());
	    b1 = Integer.parseInt(reader.readLine());
	    b2 = Integer.parseInt(reader.readLine());
	    twoVs = 0; threeVs = 0;
	}
	
	private static void updateGroupCounts(int groupSize) {
		if(groupSize >= 2) {
			twoVs += (groupSize - 1);
		}
		if(groupSize >= 3) {
			threeVs += (groupSize - 2);
		}
	}
	
	private static void runAlgorithm() {
		updateGroupCounts(Math.abs(b1 - b2) - 1);
		updateGroupCounts(n - Math.abs(b1 - b2) - 1);
		int output = (twoVs * (n - 4)) - threeVs;
		System.out.println(output);
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
