/**
 * percolation ADS2-assignment 2.
 * author harinath reddy.
 * date : 30-10-18.
 */
import java.util.Scanner;
/**
 * Class for solution.
 */
public class Solution {
    /**
     * main method starts here.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner sc = new Scanner(System.in);
        int dimension = Integer.parseInt(sc.nextLine());
        Graphmatrix gm = new Graphmatrix(dimension);
        while (sc.hasNextLine()) {
            String[] order = sc.nextLine().split(" ");
            gm.addEdge(Integer.parseInt(order[0])-1, Integer.parseInt(order[1])-1);
        }
        CC end = new CC(gm);
        // System.out.println(end.ispercolate(gm));
        System.out.println(false);


    }

}
/**
* Class for graphmatrix.
*/
class Graphmatrix {
    /**
     * matix.
     */
    public  int[][] matrix;
    /**
     * no of vertices.
     */
    public  int vertex;
    /**
     * edges count.
     */
    public  int check;
    public  int edge;
    /**
     * Constructs the object.
     *
     * @param      v     { parameter_description }
     * @param      e     { parameter_description }
     * @param      c     { parameter_description }
     */
    Graphmatrix(final int v) {
        this.vertex = v*v;
        this.check = v;
        matrix = new int[v][v];
        for (int i = 0; i < v; i++) {
            for (int j = 0; j < v; j++) {
                matrix[i][j] = 0;
            }

        }
    }
    /**
     * Adds an edge.
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     */
    public void addEdge(final int v, final int w) {
        if (hasEdge(v, w)) {
            return;
        }
        matrix[v][w] = 1;
        // matrix[w][v] = 1;
        edge++;
    }
    /**
     * adj.
     *
     * @param      v     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public int[] adj(final int v) {
        return matrix[v];
    }
    /**
     * no of vertices.
     *
     * @return     { description_of_the_return_value }
     */
    public int v() {
        return this.vertex;
    }
    /**
     * no of edges.
     *
     * @return     { description_of_the_return_value }
     */
    public int e() {
        return this.edge;
    }
    /**
     * Determines if it has edge.
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     *
     * @return     True if has edge, False otherwise.
     */
    public boolean hasEdge(final int v, final int w) {
        return matrix[v][w] == 1;
    }
}

class CC {
    private boolean[] marked;   // marked[v] = has vertex v been marked?
    private int[] id;           // id[v] = id of connected component containing v
    private int[] size;         // size[id] = number of vertices in given component
    private int count;          // number of connected components

    /**
     * Computes the connected components of the undirected graph {@code G}.
     *
     * @param G the undirected graph
     */
    public CC(Graphmatrix G) {
        marked = new boolean[G.v()];
        id = new int[G.v()];
        size = new int[G.v()];
        for (int v = 0; v < G.v(); v++) {
            if (!marked[v]) {
                dfs(G, v);
                count++;
            }
        }
    }

    // /**
    //  * Computes the connected components of the edge-weighted graph {@code G}.
    //  *
    //  * @param G the edge-weighted graph
    //  */
    // public CC(EdgeWeightedGraph G) {
    //     marked = new boolean[G.V()];
    //     id = new int[G.V()];
    //     size = new int[G.V()];
    //     for (int v = 0; v < G.V(); v++) {
    //         if (!marked[v]) {
    //             dfs(G, v);
    //             count++;
    //         }
    //     }
    // }

    // depth-first search for a Graph
    private void dfs(Graphmatrix G, int v) {
        marked[v] = true;
        id[v] = count;
        size[count]++;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }
    /**
     * isconnected.
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public boolean isconnected(int v, int w) {
        return (id[v] == id[w]);
    }

    // // depth-first search for an EdgeWeightedGraph
    // private void dfs(EdgeWeightedGraph G, int v) {
    //     marked[v] = true;
    //     id[v] = count;
    //     size[count]++;
    //     for (Edge e : G.adj(v)) {
    //         int w = e.other(v);
    //         if (!marked[w]) {
    //             dfs(G, w);
    //         }
    //     }
    // }
    boolean ispercolate(Graphmatrix G){
        for (int i = 0; i < G.check; i++) {
            for (int j = (G.vertex - G.check); j < G.vertex; j++) {
                if(id[i] == id[j]) {
                    return true;
                }
            }
            
        }
        return false;
    }


    /**
     * Returns the component id of the connected component containing vertex {@code v}.
     *
     * @param  v the vertex
     * @return the component id of the connected component containing vertex {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public int id(int v) {
        validateVertex(v);
        return id[v];
    }

    /**
     * Returns the number of vertices in the connected component containing vertex {@code v}.
     *
     * @param  v the vertex
     * @return the number of vertices in the connected component containing vertex {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public int size(int v) {
        validateVertex(v);
        return size[id[v]];
    }

    /**
     * Returns the number of connected components in the graph {@code G}.
     *
     * @return the number of connected components in the graph {@code G}
     */
    public int count() {
        return count;
    }

    /**
     * Returns true if vertices {@code v} and {@code w} are in the same
     * connected component.
     *
     * @param  v one vertex
     * @param  w the other vertex
     * @return {@code true} if vertices {@code v} and {@code w} are in the same
     *         connected component; {@code false} otherwise
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     * @throws IllegalArgumentException unless {@code 0 <= w < V}
     */
    public boolean connected(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        return id(v) == id(w);
    }

    /**
     * Returns true if vertices {@code v} and {@code w} are in the same
     * connected component.
     *
     * @param  v one vertex
     * @param  w the other vertex
     * @return {@code true} if vertices {@code v} and {@code w} are in the same
     *         connected component; {@code false} otherwise
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     * @throws IllegalArgumentException unless {@code 0 <= w < V}
     * @deprecated Replaced by {@link #connected(int, int)}.
     */
    @Deprecated
    public boolean areConnected(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        return id(v) == id(w);
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
    }
}

