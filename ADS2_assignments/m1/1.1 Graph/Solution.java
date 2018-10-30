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
    public Iterable<Integer> adj(int v);
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
        System.out.println(Arrays.toString(states));


        if (input.equals("List")) {
            System.out.println("hvfg");
            for (int i = 0; i < edges; i++) {
                String[] v_e = sc.nextLine().split(" ");
                System.out.println(Arrays.toString(v_e));
                gl.addEdge(Integer.parseInt(v_e[0]), Integer.parseInt(v_e[1]));
                System.out.println("-----------------------------");

            for (int j = 0 ; j < vertices; j++) {
                System.out.print(states[j] + " :");
                    String str = "";

                for (int s : gl.adj[j]) {
                    str= str+states[s]+" ";
                    
                }
                if(!str.equals(""))
                System.out.println(str.substring(0, str.length()-1));



            }




        }

    }
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
        adj[v].add(w);
        System.out.println("----------------");
        adj[w].add(v);
        System.out.println("===============");

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
        return (adj[v].iterator().next() == w);

    }

}

// /**
//  * Class for graphmatrix.
//  */
// class Graphmatrix implements Graph {
//     /**
//      * number of vertices.
//      *
//      * @return     int type.
//      */
//     public int V() {

//     }
//     /**
//      * number of edges.
//      *
//      * @return     void.
//      */
//     public int E() {

//     }
//     /**
//      * Adds an edge.
//      *
//      * @param      v    vertices.
//      * @param      w    verties.
//      */
//     public void addEdge(int v, int w) {

//     }
//     /**
//      * adjacent vertices of v.
//      *
//      * @param      v    vertices.
//      *
//      * @return     { description_of_the_return_value }
//      */
//     public Iterable<Integer> adj(int v) {

//     }
//     /**
//      * Determines if it has edge.
//      *
//      * @param      v     vertices.
//      * @param      w     vertices.
//      *
//      * @return     True if has edge, False otherwise.
//      */
//     public boolean hasEdge(int v, int w) {

//     }
// }

