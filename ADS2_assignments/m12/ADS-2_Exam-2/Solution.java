/**
 * ads2 week 2 module 12.
 * author harinatha reddy.
 * date 10-11-18;
 */
/**
 * .
 */
import java.util.Scanner;
import java.util.ArrayList;
/**
 * Class for solution.
 */
public class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {

    }
    /**
     * ain method starts here.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
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

            break;

        case "ViaPaths":
            // Handle the case of middlePaths, where three integers are given.
            // First is the source and second is the middle is the one where path should pass throuh.
            // third is the destination.
            // If the path exists print the distance between them.
            // Other wise print "No Path Found."
            String[] input = sc.nextLine().split(" ");
            int start = Integer.parseInt(input[0]);
            int middle = Integer.parseInt(input[1]);
            int end = Integer.parseInt(input[2]);
            DijkstraSP viapath = new DijkstraSP(ewg, start);
            double totalSum = 0.0;
            ArrayList<Integer> path = new ArrayList<>();
            if (viapath.hasPathTo(end)) {
                if (viapath.hasPathTo(middle)) {
                    path.add(start);
                    totalSum += viapath.distTo(middle);
                    for (Edge e : viapath.pathTo(middle)) {
                        int temp = e.either();
                        if (!path.contains(temp)) {
                            path.add(temp);
                        }
                        if (!path.contains(e.other(temp))) {
                            path.add(e.other(temp));
                        }
                    }
                    viapath = new DijkstraSP(ewg, middle);
                    if (viapath.hasPathTo(end)) {
                        totalSum += viapath.distTo(end);
                        for (Edge e : viapath.pathTo(end)) {
                            int temp = e.either();
                            if (!path.contains(temp)) {
                                path.add(temp);
                            }
                            if (!path.contains(e.other(temp))) {
                                path.add(e.other(temp));
                            }
                        }
                    }
                }
                System.out.println(totalSum);
                String out = path.toString().replaceAll(",", "");
                out = out.substring(1, out.length() - 1);
                System.out.println(out);
            } else {
                System.out.println("No Path Found.");
            }
            break;

        default:
            break;
        }

    }
}

