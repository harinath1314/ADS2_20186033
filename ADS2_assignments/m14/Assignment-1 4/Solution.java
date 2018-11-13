import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        String[] word = loadWords();
        //Your code goes here...
        TST tst = new TST();
        for (int i = 0; i < word.length; i++) {
            int n = word[i].length();
            String[] suffixes = new String[n];
            for (int j = 0; j < n; j++) {
                suffixes[j] = word[i].substring(j, n);
                tst.put(suffixes[j], 0);

            }

        }
        Scanner sc = new Scanner(System.in);
        Iterable<String> st = tst.keysWithPrefix(sc.nextLine());
        // Iterable<String> st = tst.keys();



        for (String s : st) {
            System.out.println(s.toString());
        }


    }

    public static String[] loadWords() {
        In in = new In("/Files/dictionary-algs4.txt");
        String[] words = in.readAllStrings();
        return words;
    }

}
