import java.util.Scanner;
/**
 * Class for solution.
 */
public final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {

    }
    /**
     * main method starts here.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        String[] word = loadWords();
        //Your code goes here...
        //tst has a complexity of L + lg N for search and has lg N for miss.
        TST tst = new TST();
        for (int i = 0; i < word.length; i++) {
            int n = word[i].length();
            // string array of all the suffixes of the given word.
            String[] suffixes = new String[n];
            for (int j = 0; j < n; j++) {
                suffixes[j] = word[i].substring(j, n);
                tst.put(suffixes[j], 0);

            }

        }
        Scanner sc = new Scanner(System.in);
        // iterablr String of all the keys with prefix as given word.
        Iterable<String> st = tst.keysWithPrefix(sc.nextLine());

        for (String s : st) {
            System.out.println(s.toString());
        }


    }
    /**
     * Loads words.
     *
     * @return     a String array of words.
     */
    public static String[] loadWords() {
        In in = new In("/Files/dictionary-algs4.txt");
        String[] words = in.readAllStrings();
        return words;
    }

}
