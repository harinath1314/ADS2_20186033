/**
 * ADS2- assignment 1.
 * author harinath areddy
 * date 29-10-18
 * problem - Graph.
 */
import java.util.Scanner;
import java.util.Arrays;
/**
 * Interface for graph.
 */
interface Graph {
    public int V();
    public int E();
    public void addEdge(int v, int w);
    // public Iterable<Integer> adj(int v);
    public boolean hasEdge(int v, int w);
}
/**
 * Solution class.
 */
public final class Solution {
    /**
     * main method starts here.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        int vertices = Integer.parseInt(sc.nextLine());
        GraphList gl = new GraphList(vertices);
        int edges = Integer.parseInt(sc.nextLine());
        String[] states = sc.nextLine().split(",");
        // if (input.equals("List")) {
        for (int i = 0; i < edges; i++) {
            String[] v_e = sc.nextLine().split(" ");
            gl.addEdge(Integer.parseInt(v_e[0]), Integer.parseInt(v_e[1]));
        }
        switch (input) {
        case"List":

            System.out.println(vertices + " vertices, " + gl.E() + " edges");
            if (gl.E() != 0) {
                for (int j = 0 ; j < vertices; j++) {
                    System.out.print(states[j] + ": ");
                    String str = "";

                    for (int s : gl.adj[j]) {
                        str = str + states[s] + " ";
                    }
                    if (!str.equals("")) {
                        System.out.println(str.substring(0, str.length() ));

                    }



                }


            } else {
                System.out.println("No edges");
            }


            break;
        case"Matrix":

            Graphmatrix m = new Graphmatrix(vertices, edges, states);
            // for (int k = 0; k < edges; k++) {
            //     String[] tokens = sc.nextLine().split(" ");
            //     m.addEdge(Integer.parseInt(tokens[0]),
            //               Integer.parseInt(tokens[1]));
            // }
            System.out.println(m.V()
                               + " vertices, " + m.E() + " edges");
            m.print();


        default:
            break;
        }





        // }

    }

}

/**
 * Class for graphity.
 */
class GraphList implements Graph {
    private int vertex;
    private int edges;
    public Bag<Integer>[] adj;

    GraphList(int v) {
        this.vertex = v;
        adj = (Bag<Integer>[]) new Bag[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new Bag<Integer>();
        }

    }
    /**
     * number of vertices.
     *
     * @return     int type.
     */
    public int V() {
        return vertex;

    }
    /**
     * number of edges.
     *
     * @return     void.
     */
    public int E() {
        return edges;
    }
    /**
     * Adds an edge.
     *
     * @param      v    vertices.
     * @param      w    verties.
     */
    public void addEdge(int v, int w) {
        if (v == w || hasEdge(v, w)) {
            return;
        }
        adj[v].add(w);
        adj[w].add(v);
        edges++;

    }
    /**
     * adjacent vertices of v.
     *
     * @param      v    vertices.
     *
     * @return     { description_of_the_return_value }
     */
    public Iterable<Integer> adj(int v) {
        return adj[v];

    }
    /**
     * Determines if it has edge.
     *
     * @param      v     vertices.
     * @param      w     vertices.
     *
     * @return     True if has edge, False otherwise.
     */
    public boolean hasEdge(int v, int w) {
        for (int each : adj[v]) {
            if (each == w) {
                return true;
            }
        }
        return false;
    }

}

/**
* Class for graphmatrix.
*/
class Graphmatrix implements Graph {
    /**
     * cities list.
     */
    private String[] cities;
    /**
     * matix.
     */
    private int[][] matrix;
    /**
     * no of vertices.
     */
    private int vertex;
    /**
     * edges count.
     */
    private int edge;
    /**
     * Constructs the object.
     *
     * @param      v     { parameter_description }
     * @param      e     { parameter_description }
     * @param      c     { parameter_description }
     */
    Graphmatrix(final int v, final int e, final String[] c) {
        cities = c;
        matrix = new int[v][v];
        this.vertex = v;
        this.edge = 0;
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
        if (v == w || hasEdge(v, w)) {
            return;
        }
        matrix[v][w] = 1;
        matrix[w][v] = 1;
        edge++;
    }
    /**
     * adj.
     *
     * @param      v     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public int[] adj(int v) {
        return matrix[v];
    }
    /**
     * no of vertices.
     *
     * @return     { description_of_the_return_value }
     */
    public int V() {
        return this.vertex;
    }
    /**
     * no of edges.
     *
     * @return     { description_of_the_return_value }
     */
    public int E() {
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

    /**
     * prints.
     */
    public void print() {
        if (edge == 0) {
            System.out.println("No edges");
        } else {
            for (int i = 0; i < vertex - 1; i++) {
                String s = "";
                for (int each : matrix[i]) {
                    s += each + " ";
                }
                System.out.println(s);
            }
            String s = "";
            for (int each : matrix[vertex - 1]) {
                s += each + " ";
            }
            System.out.println(s.substring(0, s.length() - 1));
        }
    }
}

