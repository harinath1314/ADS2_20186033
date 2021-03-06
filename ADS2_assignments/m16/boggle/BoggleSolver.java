import java.util.Set;
import java.util.TreeSet;
/**
 * Class for boggle solver.
 */
public class BoggleSolver {
    /**
     * trieobject variable.
     */
    private TrieST<Integer> trieobj;
    /**
     * set of valid words.
     */
    private Set<String> vwords;
    /**
     * visited character.
     */
    private boolean[][] markedarr;
    /**
     * Constructs the object.
     *
     * @param      dictionary  The dictionary
     */
    public BoggleSolver(final String[] dictionary) {
        trieobj = new TrieST<Integer>();
        vwords = new TreeSet<String>();
        final int th = 3;
        final int f = 5;
        final int e = 8;
        final int ele = 11;
        int[] points = {0, 0, 0, 1, 1, 2, th, f, ele};
        for (String word : dictionary) {
            if (word.length() >= e) {
                trieobj.put(word, ele);
            } else {
                trieobj.put(word, points[word.length()]);
            }
        }
    }
    /**
     * Gets all valid words.
     * Returns the set of all valid words
     * in the given Boggle board, as an Iterable.
     *
     * @param      board  The board
     *
     * @return     All valid words.
     */
    public Iterable<String> getAllValidWords(final BoggleBoard board) {
        if (board == null) {
            throw new IllegalArgumentException("board is null");
        }
        markedarr = new boolean[board.rows()][board.cols()];
        for (int i = 0; i < board.rows(); i++) {
            for (int j = 0; j < board.cols(); j++) {
                String str = appendCharacter("", board.getLetter(i, j));
                dfs(board, markedarr, i, j, str);
            }
        }
        return vwords;
    }
    /**
     * dfs implementation to find the words.
     *
     * @param      board   The board
     * @param      markedarr1  The markedarr
     * @param      rows    The rows
     * @param      cols    The cols
     * @param      word    The word
     */
    public void dfs(final BoggleBoard board, final boolean[][] markedarr1,
                    final int rows, final int cols, final String word) {
        if (!trieobj.hasPrefix(word)) {
            return;
        }

        if (isValidWord(word)) {
            vwords.add(word);
        }
        markedarr1[rows][cols] = true;
        for (int i = rows - 1; i <= rows + 1; i++) {
            for (int j = cols - 1; j <= cols + 1; j++) {
                if (isValidRowColumn(i, j, board) && !markedarr1[i][j]) {
                    String sequence = appendCharacter(word,
                                                      board.getLetter(i, j));
                    dfs(board, markedarr1, i, j, sequence);
                }
            }
        }
        markedarr[rows][cols] = false;
    }
    /**
     * Determines if valid word.
     *
     * @param      word  The word
     *
     * @return     True if valid word, False otherwise.
     */
    private boolean isValidWord(final String word) {
        final int th1 = 3;
        if (word.length() < th1) {
            return false;
        }
        return trieobj.contains(word);
    }
    /**
     * Appends a character.
     *
     * @param      str String
     * @param      ch  character that to be added for the string.
     *
     * @return  appended String.
     */
    private String appendCharacter(final String str, final char ch) {
        String str1 = str;
        if (ch == 'Q') {
            str1 += "QU";
            return str1;
        } else {
            str1 += ch;
            return str1;
        }
    }
    /**
     * Determines if valid row column.
     *
     * @param      row    The row
     * @param      col    The col
     * @param      board  The board
     *
     * @return     True if valid row column, False otherwise.
     */
    private boolean isValidRowColumn(final int row,
                                     final int col, final BoggleBoard board) {
        return (row >= 0 && col >= 0
                && row < board.rows() && col < board.cols());
    }
    /**
     * Score.
     *
     * @param      word  The word
     *
     * @return     { description_of_the_return_value }
     */
    public int scoreOf(final String word) {
        if (word == null) {
            return 0;
        }
        if (trieobj.contains(word)) {
            return trieobj.get(word);
        }
        return 0;
    }
}

