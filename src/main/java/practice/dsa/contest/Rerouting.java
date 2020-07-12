package practice.dsa.contest;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'getMinConnectionChange' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER_ARRAY connection as parameter.
     */

    public static int getMinConnectionChange(List<Integer> connection) {
        int[] tree = new int[connection.size()];
        //System.out.println("Tree size: " + tree.length);
        Stack<Integer> mStack = new Stack<>();
        int treeNumber = 0;
        int terminalNodes = 0;
        for(int i=0; i<connection.size(); i++) {
            if(connection.get(i) == (i+1)) {
                terminalNodes++;
            }
            if(tree[i] == 0) {
                int j = i;
                tree[i] = -1;
                mStack.push(i);
                int count = 10;
                System.out.println("A: " + (connection.get(j)-1));
                while(tree[connection.get(j)-1] == 0) {
                	tree[connection.get(j)-1] = -1;
                    mStack.push(connection.get(j)-1);
                    j = connection.get(j)-1;
                    System.out.println("B: " + j);
                }
                
                //System.out.println("---------------------------------");

                //System.out.println("Visited Array: ");
                //visited.forEach(vertex -> System.out.print(vertex + " "));
                //System.out.println();

                if(tree[connection.get(j)-1] > 0) {
                    while(!mStack.isEmpty()) {
                        tree[mStack.pop()] = tree[connection.get(j)-1];
                    }
                } else {
                    treeNumber++;
                    while(!mStack.isEmpty()) {
                        tree[mStack.pop()] = treeNumber;
                    }
                }
                
                //visited.clear();
                System.out.println("Tree Array: ");
                Arrays.stream(tree).forEach(node -> System.out.print(node + " "));
                System.out.println();
            }    
        }
        
        System.out.println("Trees: " + treeNumber);
        System.out.println("Terminal Nodes: " + terminalNodes);

        if(treeNumber == 1) {
            if(terminalNodes == 0) {
                return 1;
            }
            return 0;
        } else {
            return treeNumber - 1;
        }
    }
}

public class Rerouting {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());
        
        List<Integer> connection = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        int result = Result.getMinConnectionChange(connection);

        System.out.println("Output: " + result);
        //bufferedWriter.write(String.valueOf(result));
        //bufferedWriter.newLine();

        bufferedReader.close();
        //bufferedWriter.close();
    }
}
