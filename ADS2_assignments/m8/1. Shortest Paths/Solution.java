/**
 * m8 ads2 assignment-1;
 * author harinatha reddy.
 * date 6-11-18.
 */
import java.util.Scanner;
import java.util.Arrays;
import java.util.HashMap;
/**
 * class Solution.
 */
public final class Solution {
    /**
     * Constructs the object.
     */
    Solution() {

    }
    /**
     * main method starts here.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner sc = new Scanner(System.in);
        int nofrs = sc.nextInt();
        int nofdirectpaths = sc.nextInt();
        String[] stations = sc.nextLine().split(" ");
        HashMap<String, Integer> hs  = new HashMap<String, Integer>();
        for (int j = 0; j < nofrs; j++) {
            hs.put(stations[j], j);
            
        }
        EdgeWeightedDigraph ewdg = new EdgeWeightedDigraph(nofrs);
        for (int i = 0; i < nofdirectpaths; i++) {
            String[] edgedetails = sc.nextLine().split(" ");
            DirectedEdge de = new DirectedEdge(hs.get(edgedetails[0]), hs.get(edgedetails[1]), Double.parseDouble(edgedetails[2]));
            ewdg.addEdge(de);


            
        }
        
        // DijkstraSP dsp = new DijkstraSP(ewdg);



    }
}
