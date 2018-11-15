/**
 * wordnet problem.
 * ads2 m4.
 */
import java.util.ArrayList;
/**
 * Class for word net.
 */
public class WordNet {
    /**
     * symbol table initializing.
     */
    private final LinearProbingHashST
    <String, ArrayList<Integer>> linearprobing;
    /**
     * variable description.
     */
    private final ArrayList<String> synId;
    /**
     * variable description.
     */
    private final SAP sap;
    /**
     * variable description.
     */
    private final Digraph graph;
    /**
     * variable description.
     */
    private final int vertices;
    /**
     * Constructs the object.
     *
     * @param      synsets    The synsets
     * @param      hypernyms  The hypernyms
     */
    public WordNet(final String synsets, final String hypernyms) {
        synId = new ArrayList<String>();
        linearprobing = new
        LinearProbingHashST<String, ArrayList<Integer>>();
        vertices = readSynset(synsets);
        graph = new Digraph(vertices);
        readHypernym(hypernyms);
        sap = new SAP(graph);

    }
    /**
     * Reads a synset.
     *
     * @param      synset     The synset
     */
    public int readSynset(final String synset) {
        int vertices = 0;
        In in = new In("./Files/" + synset);
        while (!in.isEmpty()) {
            vertices++;
            String[] synsetArray = in.readLine().split(",");
            int id = Integer.parseInt(synsetArray[0]);
            synId.add(id, synsetArray[1]);
            // synsets may contains many nouns separated by spaces.
            String[] nounsArray = synsetArray[1].split(" ");
            for (int i = 0; i < nounsArray.length; i++) {
                ArrayList<Integer> idlist;
                if (linearprobing.contains(nounsArray[i])) {
                    idlist = linearprobing.get(nounsArray[i]);
                    idlist.add(id);
                } else {
                    idlist = new ArrayList<Integer>();
                    idlist.add(id);
                }
                linearprobing.put(nounsArray[i], idlist);
            }
            //System.out.println("idlist");
        }
        return vertices;
        // readHypernym(hypernym, digraph);
    }
    /**
     * Reads a hypernym.
     *
     * @param      hypernyms1  The hypernyms1
     */
    public void readHypernym(final String hypernyms1) {
        In in = new In("./Files/" + hypernyms1);
        while (!in.isEmpty()) {
            String[] tokens = in.readString().split(",");
            for (int i = 1; i < tokens.length; i++) {
                graph.addEdge(Integer.parseInt(tokens[0]),
                              Integer.parseInt(tokens[i]));
            }
        }


    }

    /**
     * gets the nouns.
     *
     * @return  nouns.
     */
    public Iterable<String> nouns() {
        return linearprobing.keys();
    }

    /**
     * Determines if noun.
     *
     * @param      word  The word
     *
     * @return     True if noun, False otherwise.
     */
    public boolean isNoun(final String word) {
        if (word.equals("null")) {
            throw new IllegalArgumentException();
        }
        return linearprobing.contains(word);
    }


// distance between nounA and nounB (defined below)
    public int distance(final String nounA, final String nounB) {
        ArrayList<Integer> noun1 = linearprobing.get(nounA);
        ArrayList<Integer> noun2 = linearprobing.get(nounB);
        if (!isNoun(nounA) || !isNoun(nounB)) {
            throw new IllegalArgumentException();
        }
        return sap.length(noun1, noun2);

    }
    public String sap(final String nounA, final String nounB) {
        ArrayList<Integer> noun1 = linearprobing.get(nounA);
        ArrayList<Integer> noun2 = linearprobing.get(nounB);
        if (!isNoun(nounA) || !isNoun(nounB)) {
            throw new IllegalArgumentException();
        }
        int id = sap.ancestor(noun1, noun2);
        return synId.get(id);
    }
    public void display() {

        DirectedCycle directedCycle = new DirectedCycle(graph);
        if (directedCycle.hasCycle()) {
            throw new IllegalArgumentException("Cycle detected");
        } else {
            int degree = 0;

            for (int i = 0; i < graph.vertices(); i++) {
                if (graph.outdegree(i) == 0) {
                    degree++;
                }
            }
            if (degree > 1) {
                throw new IllegalArgumentException("Multiple roots");
            }
            System.out.println(graph.toString());
        }
    }
}