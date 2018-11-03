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
        this.di = web;
        this.iniPR = 1 / web.V();

    }
    /**
     * ..
     *
     * @param      d    { parameter_description }
     */
    void PR(final Digraph d) {
        pageHash = new HashMap<Integer, Double>();
        for (int i = 0; i < d.V(); i++) {
            if (di.outdegree(i) == 0) {
                Double pas = (double) 1 / di.V();
                Double pa = pas;
                pageHash.put(i, pa);
            } else {
                Double pas = (double) 1 / di.V();
                Double pa = pas;
                pageHash.put(i, pa);
            }


        }


    }

    // void getPR(int v) {
    //  for (int j = 0; j < 1000; j++) {
    //      for (int i : di.adj(v)) {
    //          Double temp = pageHash.get(i);
    //          Double pres = temp / di.outdegree(i) ;
    //          Double perfect = temp + pres;

    //          pageHash.put(i, perfect);
    //      }


    //  }
    // }
    // Set keys = pageHash.keySet();
    // /**



    /**
     * .
     */
    void printer() {
        PR(di);
        // for(int i = 0; i < di.V(); i++){
        //  getPR(i);
        // }
        for (Integer s : pageHash.keySet()) {
            // if (s != null) {
            System.out.println(s + " - " + pageHash.get(s));

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
        HashMap<Integer, ArrayList<Integer>> hs = 
        new HashMap<Integer, ArrayList<Integer>>();

        for (int i = 0; i < vertices; i++) {
            String[] input = sc.nextLine().split(" ");
            for (int j = 1; j < input.length; j++) {
                if (hs.containsKey(input[0])) {
                    ArrayList<Integer> s = hs.get(input[0]);
                    s.add(Integer.parseInt(input[j]));
                    hs.put(Integer.parseInt(input[0]), s);

                } else {
                    ArrayList<Integer> h = new ArrayList<Integer>();
                    h.add(Integer.parseInt(input[j]));
                    hs.put(Integer.parseInt(input[0]), h);
                }

            }
            for (int k = 1; k < input.length; k++) {
                di.addEdge(
            Integer.parseInt(input[0]), Integer.parseInt(input[k]));




            }



        }
        System.out.println(di);
        // System.out.println();
        PageRank siri = new PageRank(di);
        siri.printer();




        
    }
}
