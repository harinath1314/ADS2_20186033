/**
 * Class for percolation through connected components..
 * Author harinatha reddy.
 * date 30-10-18.
 */

/**
 * Class for graph.
 */
public class Graph {
    /**
     * Newline.
     */
    private static final String NEWLINE = System.getProperty("line.separator");
    /**
     * v num of vertices.
     */
    private final int v;
    /**
     * edges.
     */
    private int e;
    /**
     * Bag object.
     */
    private Bag<Integer>[] adj;

    /**
     * Initializes an empty graph with {@code V} vertices and 0 edges.
     * param V the number of vertices
     *
     * @param  ver number of vertices
     * @throws IllegalArgumentException if {@code V < 0}
     */
    public Graph(final int ver) {
        if (ver < 0) {
            throw new IllegalArgumentException("Num of vertices nonnegative");
        }
        this.v = ver;
        this.e = 0;
        adj = (Bag<Integer>[]) new Bag[v];
        for (int i = 0; i < ver; i++) {
            adj[i] = new Bag<Integer>();
        }
    }

    /**
     * Returns the number of vertices in this graph.
     *
     * @return the number of vertices in this graph
     */
    public int V() {
        return v;
    }

    /**
     * Returns the number of edges in this graph.
     *
     * @return the number of edges in this graph
     */
    public int E() {
        return e;
    }

    /**
     * validate vertex function.
     *
     * @param      ver     { parameter_description }
     */
    private void validateVertex(final int ver) {
        if (ver < 0 || ver >= v) {
            throw new IllegalArgumentException("vertex "
             + v + " is not between 0 and " + (v - 1));
        }

    }

    /**
     * Adds an edge.
     *
     * @param      vert     { int type. }
     * @param      w     { int type. }
     */
    public void addEdge(final int vert, final int w) {
        validateVertex(vert);
        validateVertex(w);
        e++;
        adj[vert].add(w);
        adj[w].add(vert);
    }

    /**
     * Determines if it has edge.
     *
     * @param      vert     { parameter_description }
     * @param      w     { parameter_description }
     *
     * @return     True if has edge, False otherwise.
     */
    public boolean hasEdge(final int vert, final int w) {
        for (int each : adj[w]) {
            if (each == vert) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the vertices adjacent to vertex {@code v}.
     *
     * @param  vert the vertex
     * @return the vertices adjacent to vertex {@code v}, as an iterable
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public Iterable<Integer> adj(final int vert) {
        validateVertex(vert);
        return adj[vert];
    }

    /**
     * Returns the degree of vertex {@code v}.
     *
     * @param  vert the vertex
     * @return the degree of vertex {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public int degree(final int vert) {
        validateVertex(vert);
        return adj[vert].size();
    }


    /**
     * Returns a string representation of the object.
     *
     * @return     String representation of the object.
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(v + " vertices, " + e + " edges " + NEWLINE);
        for (int ver = 0; ver < v; ver++) {
            s.append(ver + ": ");
            for (int w : adj[ver]) {
                s.append(w + " ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }
}
