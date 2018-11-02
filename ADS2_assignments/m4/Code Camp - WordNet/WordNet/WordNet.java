import java.io.*;
import java.util.*;
public class WordNet {
    Bag<String>[]syn;
    int size;

    public WordNet(String synsets, String hypernyms) {
        In file = new In("/Users/harinathareddy/Desktop/MSIT COURSES/ADS2_20186033/ADS2_20186033/ADS2_assignments/m4/Code Camp - WordNet/WordNet/Files/" + synsets);
        while (file.hasNextLine()) {
            String line = file.readLine();
            size++;
        }
        Digraph di = new Digraph(size);
        syn = (Bag<String>[]) new Bag[size];
        for(int i =0; i < size; i++) {
            syn[i] = new Bag<String>();
        }
        file = new In("/Users/harinathareddy/Desktop/MSIT COURSES/ADS2_20186033/ADS2_20186033/ADS2_assignments/m4/Code Camp - WordNet/WordNet/Files/" + synsets);
        while (file.hasNextLine()) {
            String line = file.readLine();
            String[] tokens = line.split(",");
            String[] field1 = tokens[1].split(" ");
            for (int i = 0; i < field1.length; i++) {
                syn[Integer.parseInt(tokens[0])].add(field1[i]);

            }

        }
        file = new In("/Users/harinathareddy/Desktop/MSIT COURSES/ADS2_20186033/ADS2_20186033/ADS2_assignments/m4/Code Camp - WordNet/WordNet/Files/" + hypernyms);
        while (file.hasNextLine()) {
            String line = file.readLine();
            String[] tokens = line.split(",");
            for (int i = 1; i < tokens.length; i++) {
                di.addEdge(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[i]));
                if(di.outdegree(Integer.parseInt(tokens[0])) > 1) {
                    System.out.println("Multiple roots");
                    return;
                }
            }
        }
        DirectedCycle dc = new DirectedCycle(di);
        if(dc.hasCycle()){
            System.out.println("Cycle detected");
            return;
        }
        // for(int i )
            System.out.println(di);

    }
    


    // returns all WordNet nouns
    public Iterable<String>[] nouns() {
        return syn;
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        for (Bag s : syn) {
            for (int i = 0; i < s.size(); i++) {

                if (s.iterator().next().equals(word)) {
                    return true;
                }
            }
        }
        return false;
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        return 0;
    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        return "hari";

    }
}
