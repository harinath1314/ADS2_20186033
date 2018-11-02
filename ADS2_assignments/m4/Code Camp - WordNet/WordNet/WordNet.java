import java.io.*;
import java.util.*;
public class WordNet {
    Bag<String>[]syn;
    // Bag<Integer>[] hyper;
    int size;

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        // File synfile = new File("/Users/harinathareddy/Desktop/MSIT COURSES/ADS2_20186033/ADS2_20186033/ADS2_assignments/m4/Code Camp - WordNet/WordNet/Files/"+synsets);
        // Scanner sc = new Scanner(synfile);
        // while(sc.hasNextLine()){

        //     size++;

        // }
        // syn = new Bag[size];

        // File synfi = new File("/Users/harinathareddy/Desktop/MSIT COURSES/ADS2_20186033/ADS2_20186033/ADS2_assignments/m4/Code Camp - WordNet/WordNet/Files/"+synsets);
        // Scanner scc = new Scanner(synfi);

        // while(scc.hasNextLine()){
        //     String[] tokens = hypernyms.readLine().split(",");
        //     String[] field1 = tokens[1].split(" ");
        //     syn[Integer.parseInt(tokens[0])] = new Bag<String>();
        //     for (int i = 0;  i  < field1.length; i++) {
        //         syn[Integer.parseInt(tokens[0])].add(field1[i]);

        //     }



        // }
        // Digraph grph = new Digraph(size);


        // File hypfile = new File("/Users/harinathareddy/Desktop/MSIT COURSES/ADS2_20186033/ADS2_20186033/ADS2_assignments/m4/Code Camp - WordNet/WordNet/Files/"+hypernyms);
        // while(hypfile.hasNextLine()){
        //     String[] tokens = hypfile.readLine().split(",");
        //     for (int i = 1; i < tokens.length; i++) {
        //         grph.addEdge(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[i]));

        //     }

        // }
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
            }
        }
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
