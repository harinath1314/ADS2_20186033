import java.util.Scanner;


public class Solution {

	// Don't modify this method.
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String cases = scan.nextLine();

		switch (cases) {
		case "loadDictionary":
			// input000.txt and output000.txt
			BinarySearchST<String, Integer> hash = loadDictionary("/Users/harinathareddy/Desktop/MSIT COURSES/ADS2_20186033/ADS2_20186033/ADS2_assignments/m18/ADS-2Exam-3/Files/t9.csv");
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
		// your code goes here

		return null;
	}

	// return all possibilities(words), find top k with highest frequency.
	public Iterable<String> getSuggestions(Iterable<String> words, int k) {
		// your code goes here
		String[] list = new String[k];
			Bag<String> bag = new Bag<String>();
			BinarySearchST<String, Integer> gst = new BinarySearchST<String, Integer>();
		for (String word : words) {
			int count = 0;
			for (String s: getAllWords(word)) {
				count += bst.get(s);	
			}
			gst.put(word, count);
		}
		for (int i =0; i < k; i++) {
			bag.add(gst.max());
			gst.deleteMax();	
		}
		return bag;
	}

	// final output
	// Don't modify this method.
	public Iterable<String> t9(String t9Signature, int k) {
		return getSuggestions(potentialWords(t9Signature), k);
	}
}
