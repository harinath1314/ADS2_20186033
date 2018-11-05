import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;


class PageRank {
    HashMap<Integer, Double>map;
    Digraph graph;
    Digraph revGraph;
    private int vertices;
    PageRank(Digraph dg) {
        graph = dg;
        vertices = graph.V();
        map = new HashMap<Integer, Double>();
        revGraph = graph.reverse();
    }
    public void calculatePr() {
        Double inip = 0.0;
        int count = 0;
        double temp = (double) vertices;
        double initialPr = (1 / temp);
        for (int i = 0; i < vertices; i++) {
            if (graph.indegree(i) == 0) {
                map.put(i, 0.0);
            } else {
                map.put(i, initialPr);
            }
        }
        double[] tempa = new double[graph.V()];
        for ( int j = 0; j < 1500; j++) {
            for ( int i = 0; i < vertices; i++) {
                inip = 0.0;
                for (int each : revGraph.adj(i)) {
                    double value = map.get(each);
                    inip += ((double)value / (double)graph.outdegree(each));
                }
                tempa[i] = inip;
            }
            for (int i = 0; i < vertices; i++) {
                map.put(i, tempa[i]);
            }
        }
    }
    public void print() {
        for ( int i = 0; i < map.size(); i++) {
            System.out.println(i + " - " + map.get(i));
        }
    }
}
/**
 * Class for web search.
 */
class WebSearch {

}


public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int vertices = Integer.parseInt(sc.nextLine());
        Digraph d = new Digraph(vertices);
            for (int i = 0; i < vertices; i++) {
                
            
            String[] arra1 = sc.nextLine().split(" ");
            for (int j = 1; j < arra1.length; j++) {
                d.addEdge(Integer.parseInt(arra1[0]), Integer.parseInt(arra1[j]));
            }
        }
        System.out.println(d);
        for (int k = 0; k < d.V(); k++) {
            if (d.outdegree(k) == 0) {
                for (int a = 0; a < d.V(); a++ ) {
                    if (k != a) {
                        d.addEdge(k, a);
                    }
                }
            }
        }
        PageRank pr = new PageRank(d);
        pr.calculatePr();
        pr.print();
    }
}

