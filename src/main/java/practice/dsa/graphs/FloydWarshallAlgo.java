package practice.dsa.graphs;

/**
 * All Source Shortest Path Algorithm
 * 
 * @author vaibhavjain
 *
 */
public class FloydWarshallAlgo {

	public static void main(String[] args) {
		Graph graph = GraphFactory.createGraph("undirected", true, false);
		
		//Testing the example from Cormen
		/*graph.setVertices(5);
		
		int[][] weights = new int[graph.getVertices()][graph.getVertices()];
		weights[0][1] = 3;
		weights[0][2] = 8;
		weights[0][4] = -4;
		weights[1][3] = 1;
		weights[1][4] = 7;
		weights[2][1] = 4;
		weights[3][0] = 2;
		weights[3][2] = -5;
		weights[4][3] = 6;
		
		
		graph.setWeights(weights);*/
		
		
		runAlgorithm(graph);
	}

	private static void runAlgorithm(Graph graph) {
		int[][] pathWeights = new int[graph.getVertices()][graph.getVertices()];
		int[][] previous = new int[graph.getVertices()][graph.getVertices()];

		// Initialize non-existent edges with weight as infinity
		for (int i = 0; i < graph.getVertices(); i++) {
			for (int j = 0; j < graph.getVertices(); j++) {
				if (i != j) {
					pathWeights[i][j] = (graph.getWeights()[i][j] == 0 ? Integer.MAX_VALUE : graph.getWeights()[i][j]);
					previous[i][j] = (graph.getWeights()[i][j] == 0 ? -1 : i);
				} else {
					pathWeights[i][j] = 0;
					previous[i][j] = -1;
				}
			}
		}

		for (int k = 0; k < graph.getVertices(); k++) {
			for (int i = 0; i < graph.getVertices(); i++) {
				for (int j = 0; j < graph.getVertices(); j++) {
					if (i != j && pathWeights[i][k] != Integer.MAX_VALUE && pathWeights[k][j] != Integer.MAX_VALUE) {
						if (pathWeights[i][j] > (pathWeights[i][k] + pathWeights[k][j])) {
							pathWeights[i][j] = pathWeights[i][k] + pathWeights[k][j];
							previous[i][j] = k;
						}
					}
				}
			}
			
			//displayMatrix(pathWeights, 5);
		}

		System.out.println("Original Weight Matrix: ");
		for (int i = 0; i < graph.getVertices(); i++) {
			for (int j = 0; j < graph.getVertices(); j++) {
				System.out.print(graph.getWeights()[i][j] + "\t");
			}
			System.out.println("\n");
		}

		System.out.println("Shortest Path Weight Matrix: ");
		for (int i = 0; i < graph.getVertices(); i++) {
			for (int j = 0; j < graph.getVertices(); j++) {
				if (pathWeights[i][j] == Integer.MAX_VALUE) {
					System.out.print("NA" + "\t");
				} else {
					System.out.print(pathWeights[i][j] + "\t");
				}
			}
			System.out.println("\n");
		}

		System.out.println("Shortest Path Matrix: ");
		for (int i = 0; i < graph.getVertices(); i++) {
			for (int j = 0; j < graph.getVertices(); j++) {
				System.out.print(previous[i][j] + "\t");
			}
			System.out.println("\n");
		}
	}
	
	private static void displayMatrix(int[][] pathWeights, int vertices) {
		System.out.println("*************** DISPLAY MATRIX ***************");
		for (int i = 0; i < vertices; i++) {
			for (int j = 0; j < vertices; j++) {
				if (pathWeights[i][j] == Integer.MAX_VALUE) {
					System.out.print("NA" + "\t");
				} else {
					System.out.print(pathWeights[i][j] + "\t");
				}
			}
			System.out.println("\n");
		}
		System.out.println("**********************************************");
	}
}
