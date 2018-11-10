import java.util.Scanner;
public class Solution {

	public static void main(String[] args) {
		// Self loops are not allowed...
		// Parallel Edges are allowed...
		// Take the Graph input here...
		Scanner sc = new Scanner(System.in);
		int cities = Integer.parseInt(sc.nextLine());
		int rlines = Integer.parseInt(sc.nextLine());
		EdgeWeightedGraph ewg = new EdgeWeightedGraph(cities, rlines, sc);
		// KruskalMST ks = new KruskalMST(ewg);

		String caseToGo = sc.nextLine();
		switch (caseToGo) {
		case "Graph":
			//Print the Graph Object.
			System.out.println(ewg);
			break;

		case "DirectedPaths":
			String[] paths = sc.nextLine().split(" ");
			DijkstraSP dsp = new DijkstraSP(ewg, Integer.parseInt(paths[0]));
			if (!dsp.hasPathTo(Integer.parseInt(paths[1]))) {
				System.out.println("No Path Found.");

			} else {
				System.out.println(dsp.distTo(Integer.parseInt(paths[1])));
			}

			// Handle the case of DirectedPaths, where two integers are given.
			// First is the source and second is the destination.
			// If the path exists print the distance between them.
			// Other wise print "No Path Found."
			// System.out.println(ks.weight());
			break;

		case "ViaPaths":
			String[] via = sc.nextLine().split(" ");
			DijkstraSP spd = new DijkstraSP(ewg, Integer.parseInt(via[0]));
			DijkstraSP pds = new DijkstraSP(ewg, Integer.parseInt(via[1]));

			if (!spd.hasPathTo(Integer.parseInt(via[2]))) {
				System.out.println("No Path Found.");

			} else {
				System.out.println(spd.distTo(Integer.parseInt(via[1]))+pds.distTo(Integer.parseInt(via[2])));
				// for ( Edge s: spd.pathTo(Integer.parseInt(via[2]))) {
				// 	System.out.print(s.()+" ");
				 	
				//  } 
				Stack<Edge> pa = spd.pathTo(Integer.parseInt(via[1]));
				Stack<Edge> ap = pds.pathTo(Integer.parseInt(via[2]));

				for (int k = 0; k < pa.size(); k++) {
					System.out.print(pa.pop() + " ");
					
				}
				
			}

			// Handle the case of ViaPaths, where three integers are given.
			// First is the source and second is the via is the one where path should pass throuh.
			// third is the destination.
			// If the path exists print the distance between them.
			// Other wise print "No Path Found."
			break;




		default:
			break;
		}

	}
}
