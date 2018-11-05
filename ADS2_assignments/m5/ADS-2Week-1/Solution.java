/**
 * class Solution.
 */
import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * Class for page rank.
 */
class PageRank {
    /**
     * { var_description }.
     */
    private HashMap<Integer, Double> pageHash;
    private HashMap<Integer, Double> presentHash = new HashMap<Integer, Double>();


    // int iterations;

    private Digraph di;
    /**
     * ..
     *
     */
    private int iniPR;
    /**
     * .
     */
    private int pageRank;
    /**
     * Constructs the object.
     *
     * @param      web   The web
     */
    PageRank(final Digraph web) {
        this.di = web.reverse();
        this.iniPR = 1 / web.V();

    }
    /**
     * ..
     *
     * @param      d    { parameter_description }
     */
    void PR(final Digraph d) {
        pageHash = new HashMap<Integer, Double>();
            for (int k = 0; k < d.V(); k++) {
                if(d.outdegree(k) == 0){
                    for (int x = 0; x<d.V(); x++) {
                        if(x!=k){d.addEdge(x,k);}
                        
                    }
                }
                
            }
        for (int i = 0; i < d.V(); i++) {

                Double pas = (double) 1 / di.V();
                pageHash.put(i, pas);
        }


    }

    void getPR(int v) {
     // for (int j = 0; j < 1000; j++) {
         for (int i : di.adj(v)) {
             Double temp = pageHash.get(i);
             Double pres = temp / di.outdegree(i) ;
             Double perfect = pres;

             presentHash.put(i, perfect);
         }


     // }
    }
    void calcuation(){
        PR(di);
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < di.V(); j++) {
                getPR(j);
            }
        pageHash.putAll(presentHash);
        }
    }
    void printer() {
        // PR(di);
        // for(int i = 0; i < di.V(); i++){
        //  getPR(i);
        // }
        
        for (Integer s : presentHash.keySet()) {
            // if (s != null) {
            System.out.println(s + " - " + presentHash.get(s));

            // }
        }
        // System.out.println(pageHash.keySet());
        //

    }

}

// class WebSearch {

// }

/**
 * Class for solution.
 */
public class Solution {

    /**
     * Constructs the object.
     */
    Solution(){

    }
    /**
     * mainmethod.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        // read the first line of the input to get the number of vertices
        Scanner sc = new Scanner(System.in);
        int vertices = Integer.parseInt(sc.nextLine());
        Digraph di = new Digraph(vertices);
        for (int i = 0; i < vertices; i++) {
            String[] input = sc.nextLine().split(" ");
            for (int k = 1; k < input.length; k++) {
                di.addEdge(
            Integer.parseInt(input[0]), Integer.parseInt(input[k]));
            }
        }
        System.out.println(di);
        PageRank siri = new PageRank(di);
        siri.calcuation();
        siri.printer();




        
    }
}
