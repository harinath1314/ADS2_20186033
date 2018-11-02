import java.io.*;
import java.util.*;
public class WordNet {
    Bag<String>[]syn;
    int size;
    Digraph di;
    DirectedCycle dc;

    public WordNet(String synsets, String hypernyms) {
        try{

        
        In file = new In("/Users/harinathareddy/Desktop/MSIT COURSES/ADS2_20186033/ADS2_20186033/ADS2_assignments/m4/Code Camp - WordNet/WordNet/Files/" + synsets);
        while (file.hasNextLine()) {
            String line = file.readLine();
            size++;
        }
        di = new Digraph(size);
        syn = (Bag<String>[]) new Bag[size];
        for (int i = 0; i < size; i++) {
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
            }
        }
        dc = new DirectedCycle(di);
        int count = 0;
        for (int i = 0; i < size; i++) {
            if(di.outdegree(i) == 0) {
                count++;
            }
            
        }
        if(count > 1) {
            throw new IllegalArgumentException("Multiple roots");
        }
        if(dc.hasCycle()){
            throw new IllegalArgumentException("Cycle detected");

        }

    }catch (Exception e) {
        System.out.println(e.getMessage());
    }
    }


    public Digraph graphity(){
        return di;
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
