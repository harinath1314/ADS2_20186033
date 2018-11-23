import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

/**
 * Class for move to front.
 */
public class MoveToFront {
    private  static final  int RAD = 256;
    /**
     * encode method.
     */
    public static void encode() {
        //      initialization
         char[] pos = new char[RAD];
        for (int i = 0; i < pos.length; i++) pos[i] = (char) i;
        while (!BinaryStdIn.isEmpty()) {
            char c = BinaryStdIn.readChar();
            for (int i = 0; i < pos.length; i++) {
                if (pos[i] == c) {
                    BinaryStdOut.write((char) i);

                    for (int j = i; j > 0; j--) pos[j] = pos[j - 1];
                    pos[0] = c;
                    break;
                }
            } 
        }
        // BinaryStdOut.flush();
        BinaryStdOut.close();
        return;
    }
    /**
     * decode method.
     */
    public static void decode() {

         char[] pos = new char[RAD];
        for (int i = 0; i < pos.length; i++) pos[i] = (char) i;
        while (!BinaryStdIn.isEmpty()) {
            char c = BinaryStdIn.readChar();

            BinaryStdOut.write(pos[c]);

            char tmp = pos[c];

            for (int i = c; i > 0; i--) pos[i] = pos[i - 1];
            pos[0] = tmp;
        }
        // BinaryStdOut.flush();
        BinaryStdOut.close();
        return;
    }

    public static void main(String[] args) {


        // MoveToFront move = new MoveToFront();
        if (args[0].equals("-"))
            encode();
        if (args[0].equals("+"))
            decode();
        return;
    }
}
