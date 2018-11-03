import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;

class PageRank {

}

class WebSearch {

}


public class Solution {

	public static void main(String[] args) {
		// read the first line of the input to get the number of vertices
		Scanner sc = new Scanner(System.in);
		int vertices = Integer.parseInt(sc.nextLine());
		Digraph di = new Digraph(vertices);
		HashMap<Integer, ArrayList<Integer>> hs = new HashMap<Integer, ArrayList<Integer>>();

		for (int i = 0; i < vertices ; i++) {
			String[] input = sc.nextLine().split(" ");
			for (int j = 1; j < input.length; j++) {
				if (hs.containsKey(input[0])) {
					ArrayList<Integer> s = hs.get(input[0]);
					s.add(Integer.parseInt(input[j]));
					hs.put(Integer.parseInt(input[0]), s);

				} else {
					ArrayList<Integer> h = new ArrayList<Integer>();
					h.add(Integer.parseInt(input[j]));
					hs.put(Integer.parseInt(input[0]), h);
				}

			}
			for (int k = 1; k < input.length; k++) {
                di.addEdge(Integer.parseInt(input[0]), Integer.parseInt(input[k]));



				
			}



		}
		System.out.println(di);


		// iterate count of vertices times
		// to read the adjacency list from std input
		// and build the graph


		// Create page rank object and pass the graph object to the constructor

		// print the page rank object

		// This part is only for the final test case

		// File path to the web content
		String file = "WebContent.txt";

		// instantiate web search object
		// and pass the page rank object and the file path to the constructor

		// read the search queries from std in
		// remove the q= prefix and extract the search word
		// pass the word to iAmFeelingLucky method of web search
		// print the return value of iAmFeelingLucky

	}
}
