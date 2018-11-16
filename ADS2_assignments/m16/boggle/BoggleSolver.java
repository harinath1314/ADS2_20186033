import java.util.TreeSet;
import java.util.Arrays;




public class BoggleSolver {
	private TreeSet<String> dictionaryofwords;
	// Initializes the data structure using the given array of strings as the dictionary.
	// (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
	public BoggleSolver(String[] dictionary) {
		dictionaryofwords = new TreeSet<String>(Arrays.asList(dictionary));


	}

	// Returns the set of all valid words in the given Boggle board, as an Iterable.
	public Iterable<String> getAllValidWords(BoggleBoard board) {

		if(board.rows() == 0 || board.cols() == 0){
            throw new NullPointerException("The matrix cannot be null");

		}
		// final List<String> validWords = new ArrayList<String>();
		Bag<String> validWords = new Bag<String>();
        for (int i = 0; i < board.rows(); i++) {
            for (int j = 0; j < board.cols(); j++) {
                solve(board.board, i, j, board.getLetter(i,j) + "", validWords);
            }
        }
        return validWords;
		// return new Bag<String>();
	}



	public  void solve(char[][] m, int i, int j, String prefix, Bag<String> validWords) {
        assert m != null;
        assert validWords != null;

        for (int i1 = Math.max(0, i - 1); i1 < Math.min(m.length, i + 2); i1++) {
            for (int j1 = Math.max(0, j - 1); j1 < Math.min(m[0].length, j + 2); j1++) {
                if (i1 != i || j1 != j) {
                    String word = prefix+ m[i1][j1];

                    if (dictionaryofwords.contains(word)) {
                        validWords.add(word);
                    }

                    if (dictionaryofwords.subSet(word, word + Character.MAX_VALUE).size() > 0) {
                        solve(m, i1, j1, word, validWords);
                    }
                }
            }
        } 
    }

	// // Returns the score of the given word if it is in the dictionary, zero otherwise.
	// // (You can assume the word contains only the uppercase letters A through Z.)
	// public int scoreOf(String word) {
	// 	return 0;
	// }


	/** 
     * Score a word based off typical Boggle scoring.
     * @param s Word to score
     * @return Score of the word passed in 
     */
    public  int scoreOf(String s) {
        int pointValue;
        int length = s.length();

        if (length >=0 && length <= 2) pointValue = 0;
        else if      (length >= 3 && length <= 4)  pointValue = 1;
        else if (length == 5) pointValue = 2;
        else if (length == 6) pointValue = 3;
        else if (length == 7) pointValue = 5;
        else                  pointValue = 11;
        return pointValue;
    }
}









// public static List<String> boggleSolver(char[][] m) {
//         if (m == null) {
//             throw new NullPointerException("The matrix cannot be null");
//         }
//         final List<String> validWords = new ArrayList<String>();
//         for (int i = 0; i < m.length; i++) {
//             for (int j = 0; j < m[0].length; j++) {
//                 solve(m, i, j, m[i][j] + "", validWords);
//             }
//         }
//         return validWords;
//     }
//     
//     
// private static void solve(char[][] m, int i, int j, String prefix, List<String> validWords) {
//         assert m != null;
//         assert validWords != null;

//         for (int i1 = Math.max(0, i - 1); i1 < Math.min(m.length, i + 2); i1++) {
//             for (int j1 = Math.max(0, j - 1); j1 < Math.min(m[0].length, j + 2); j1++) {
//                 if (i1 != i || j1 != j) {
//                     String word = prefix+ m[i1][j1];

//                     if (dictionary.contains(word)) {
//                         validWords.add(word);
//                     }

//                     if (dictionary.subSet(word, word + Character.MAX_VALUE).size() > 0) {
//                         solve(m, i1, j1, word, validWords);
//                     }
//                 }
//             }
//         } 
//     }