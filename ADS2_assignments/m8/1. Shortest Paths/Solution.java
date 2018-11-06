/**
 * m8 ads2 assignment-1;
 * author harinatha reddy.
 * date 6-11-18.
 */
import java.util.Scanner;
import java.util.HashMap;
/**
 * class Solution.
 */
public final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {

    }
    /**
     * main method starts here.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        // reads input from cmd.
        Scanner sc = new Scanner(System.in);
        int nofrs = sc.nextInt();
        int nofdirectpaths = sc.nextInt();
        sc.nextLine();
        String[] stations = sc.nextLine().split(" ");
        // to store the stations as index valsues which are used for vertices.
        HashMap<String, Integer> hs  = new HashMap<String, Integer>();
        for (int j = 0; j < nofrs; j++) {
            hs.put(stations[j], j);

        }
        // complexity of edgeweighted graph is E.
        // where E is the Number of EDGES.
        EdgeWeightedGraph ewdg = new EdgeWeightedGraph(nofrs);
        for (int i = 0; i < nofdirectpaths; i++) {
            String[] edgedetails = sc.nextLine().split(" ");
            Edge de = new Edge(hs.get(edgedetails[0]),
               hs.get(edgedetails[1]), Double.parseDouble(edgedetails[2]));
            // addEdge Has a complexity of O(1).
            ewdg.addEdge(de);



        }

        int qyer = Integer.parseInt(sc.nextLine());
        for (int q = 0; q < qyer; q++) {
            String[] querydetails = sc.nextLine().split(" ");
            //Dijkstra's algorithm computes a SPT in any edge-weighted.
            //digraph with nonnegative weights..
            // object for DijkstrasShortest path algorith.
            // it has a complexity depending upon the PQ implementation.
            // here we used IndexPQ using Binary Heap.
            // hence we have a complexity of. ------  "ElogV"
            DijkstraUndirectedSP dsp = new
            DijkstraUndirectedSP(ewdg, hs.get(querydetails[0]));
            System.out.println(Math.round(dsp.distTo(hs.get(querydetails[1]))));

        }

    }
}

