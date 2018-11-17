import java.util.Scanner;
import java.util.TreeSet;
import java.util.HashMap;


public class Solution {

    // Don't modify this method.
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String cases = scan.nextLine();

        switch (cases) {
        case "loadDictionary":
            // input000.txt and output000.txt
            BinarySearchST<String, Integer> hash =
            loadDictionary("/Users/harinathareddy/Desktop/MSIT COURSES/ADS2_20186033/ADS2_20186033/ADS2_assignments/m18/ADS-2Exam-3/Files/t9.csv");
            while (scan.hasNextLine()) {
                String key = scan.nextLine();
                System.out.println(hash.get(key));
            }
            break;

        case "getAllPrefixes":
            // input001.txt and output001.txt
            T9 t9 = new T9(loadDictionary("/Files/t9.csv"));
            while (scan.hasNextLine()) {
                String prefix = scan.nextLine();
                for (String each : t9.getAllWords(prefix)) {
                    System.out.println(each);
                }
            }
            break;

        case "potentialWords":
            // input002.txt and output002.txt
            t9 = new T9(loadDictionary("/Files/t9.csv"));
            int count = 0;
            while (scan.hasNextLine()) {
                String t9Signature = scan.nextLine();
                for (String each : t9.potentialWords(t9Signature)) {
                    count++;
                    System.out.println(each);
                }
            }
            if (count == 0) {
                System.out.println("No valid words found.");
            }
            break;

        case "topK":
            // input003.txt and output003.txt
            t9 = new T9(loadDictionary("/Files/t9.csv"));
            Bag<String> bag = new Bag<String>();
            int k = Integer.parseInt(scan.nextLine());
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                bag.add(line);
            }
            for (String each : t9.getSuggestions(bag, k)) {
                System.out.println(each);
            }

            break;

        case "t9Signature":
            // input004.txt and output004.txt
            t9 = new T9(loadDictionary("/Files/t9.csv"));
            bag = new Bag<String>();
            k = Integer.parseInt(scan.nextLine());
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                for (String each : t9.t9(line, k)) {
                    System.out.println(each);
                }
            }
            break;

        default:
            break;

        }
    }

    // Don't modify this method.
    public static String[] toReadFile(String file) {
        In in = new In(file);
        return in.readAllStrings();
    }

    public static BinarySearchST<String, Integer> loadDictionary(String file) {
        BinarySearchST<String, Integer>  st = new BinarySearchST<String, Integer>();
        String[] words = toReadFile("/Users/harinathareddy/Desktop/MSIT COURSES/ADS2_20186033/ADS2_20186033/ADS2_assignments/m18/ADS-2Exam-3/Files/t9.csv");
        for (int i = 0; i < words.length; i++) {
            if (st.contains(words[i].toLowerCase())) {
                st.put(words[i].toLowerCase(), st.get(words[i].toLowerCase()) + 1);


            } else {
                st.put(words[i].toLowerCase(), 1);

            }
        }

        return st;
    }

}

class T9 {


    private TST tst ;
    private BinarySearchST<String, Integer> bst;

    public T9(BinarySearchST<String, Integer> st) {
        // your code goes here
        bst = st;
        tst = new TST();
        for (String s : st.keys()) {
            tst.put(s, st.get(s));

        }
    }

    // get all the prefixes that match with given prefix.
    public Iterable<String> getAllWords(String prefix) {
        // your code goes here
        return tst.keysWithPrefix(prefix);

    }

    public Iterable<String> potentialWords(String t9Signature) {
        // // your code goes here
        // // HashMap<Integer, ArrayList<String>> pwd = new HashMap();
        // String test= "";
        // for (int i =0;i<t9Signature.length(); i++) {
        //  if(t9Signature.charAt(i).equals("2")){
        //      test =test+"abc";
        //  }
        //  else if(t9Signature.charAt(i).equals("3")){
        //      test =test+"def";
        //  }else if(t9Signature.charAt(i).equals("4")){
        //      test =test+"ghi";
        //  }else if(t9Signature.charAt(i).equals("5")){
        //      test =test+"jkl";
        //  }else if(t9Signature.charAt(i).equals("6")){
        //      test =test+"mno";
        //  }
        //  else if(t9Signature.charAt(i).equals("7")){
        //      test =test+"pqrs";
        //  }
        //  else if(t9Signature.charAt(i).equals("8")){
        //      test =test+"tuv";
        //  }
        //  else if(t9Signature.charAt(i).equals("9")){
        //      test =test+"wxyz";
        //  }


        // }



        return null;
    }

    // return all possibilities(words), find top k with highest frequency.
    public Iterable<String> getSuggestions(Iterable<String> words, int k) {
        // your code goes here

        MaxPQ<Integer> freaks = new MaxPQ<Integer>();
        for (String each : words) {
            freaks.insert((Integer)tst.get(each));
        }
        TreeSet<String> treeset = new TreeSet<String>();
        for (int i = 0; i < k; i++) {
            int value = freaks.delMax();
            for (String word : words) {
                if (value == (Integer)tst.get(word)) {
                    treeset.add(word);
                }
            }
        }

        return treeset;
    }

    // final output
    // Don't modify this method.
    public Iterable<String> t9(String t9Signature, int k) {
        return getSuggestions(potentialWords(t9Signature), k);
    }
}

