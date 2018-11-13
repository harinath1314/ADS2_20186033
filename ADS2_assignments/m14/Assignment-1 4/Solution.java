import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        String[] words = loadWords();
        //Your code goes here...
        TST tst = new TST();
        for (int i = 0; i < words.length; i++) {
            tst.put(words[i], 0);

        }
        Scanner sc = new Scanner(System.in);
        System.out.println(tst.keysWithPrefix(sc.nextLine()));


    }

    public static String[] loadWords() {
        In in = new In("/Files/dictionary-algs4.txt");
        String[] words = in.readAllStrings();
        return words;
    }
}
