/**
 * harinatha.
 * author harinath a
 * date 5-11-18.
 *
 */

import java.util.Scanner;
/**
 * solution classs.
 */
public final class Solution {
    /**
     * Constructs the object. for check style/.
     */
    private Solution() {

    }
    /**
     * mainmethod starts here.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = Integer.parseInt(sc.nextLine());
        int b = Integer.parseInt(sc.nextLine());
        EdgeWeightedGraph ewg = new EdgeWeightedGraph(a, b, sc);
        KruskalMST kmst = new KruskalMST(ewg);
        System.out.printf("%.5f", kmst.weight());

    }
}



/**
 * Class for kruskal mst.
 */
class KruskalMST {
    /**
     * FLOATING point.
     */
    private static final double FLOATING_POINT_EPSILON = 1E-12;
    /**
     * weight of MST.
     */
    private double weight;
    /**
     * edges in MST.
     */
    private Queue<Edge> mst = new Queue<Edge>();

    /**
     * Compute a minimum spanning tree (or forest) of an edge-weighted graph.
     * @param g the edge-weighted graph
     */
    KruskalMST(final EdgeWeightedGraph g) {
        // more efficient to build heap by passing array of edges
        MinPQ<Edge> pq = new MinPQ<Edge>();
        for (Edge e : g.edges()) {
            pq.insert(e);
        }

        // run greedy algorithm
        UF uf = new UF(g.V());
        while (!pq.isEmpty() && mst.size() < g.V() - 1) {
            Edge e = pq.delMin();
            int v = e.either();
            int w = e.other(v);
            if (!uf.connected(v, w)) { // v-w does not create a cycle
                uf.union(v, w);  // merge v and w components
                mst.enqueue(e);  // add edge e to mst
                weight += e.weight();
            }
        }

        // check optimality conditions
        assert check(g);
    }

    /**
     * Returns the edges in a minimum spanning tree (or forest).
     * @return the edges in a minimum spanning tree (or forest) as
     *    an iterable of edges
     */
    public Iterable<Edge> edges() {
        return mst;
    }

    /**
     * weight .
     *
     * @return     { description_of_the_return_value }
     */
    public double weight() {
        return weight;
    }

    /**
     * check .
     *
     * @param      g     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    private boolean check(final EdgeWeightedGraph g) {

        // check total weight
        double total = 0.0;
        for (Edge e : edges()) {
            total += e.weight();
        }
        if (Math.abs(total - weight()) > FLOATING_POINT_EPSILON) {
            System.err.printf(
                "Weight of edges does not equal weight(): %f vs. %f\n",
                total, weight());
            return false;
        }

        // check that it is acyclic
        UF uf = new UF(g.V());
        for (Edge e : edges()) {
            int v = e.either(), w = e.other(v);
            if (uf.connected(v, w)) {
                System.err.println("Not a forest");
                return false;
            }
            uf.union(v, w);
        }

        // check that it is a spanning forest
        for (Edge e : g.edges()) {
            int v = e.either(), w = e.other(v);
            if (!uf.connected(v, w)) {
                System.err.println("Not a spanning forest");
                return false;
            }
        }

        for (Edge e : edges()) {

            // all edges in MST except e
            uf = new UF(g.V());
            for (Edge f : mst) {
                int x = f.either(), y = f.other(x);
                if (f != e) {
                    uf.union(x, y);
                }
            }

            // check that e is min weight edge in crossing cut
            for (Edge f : g.edges()) {
                int x = f.either(), y = f.other(x);
                if (!uf.connected(x, y)) {
                    if (f.weight() < e.weight()) {
                        System.err.println("Edge " + f
                           + " violates cut optimality conditions");
                        return false;
                    }
                }
            }

        }

        return true;
    }

}
