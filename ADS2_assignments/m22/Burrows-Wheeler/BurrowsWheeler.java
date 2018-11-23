// import java.util.Arrays;
// import java.util.HashMap;
import edu.princeton.cs.algs4.BinaryStdOut;
import edu.princeton.cs.algs4.BinaryStdIn;


/**
 * Class for burrows wheeler.
 */
public class BurrowsWheeler {
    private static final int R = 256; /**
     * transfrom function.
     */
    public static void transform() {
        String s = BinaryStdIn.readString();
        CircularSuffixArray csa = new CircularSuffixArray(s);
        int first = 0;
        while (first < csa.length() && csa.index(first) != 0) {
            first++;
        }
        BinaryStdOut.write(first);
        for (int i = 0; i < csa.length(); i++) {
            BinaryStdOut.write(s.charAt((csa.index(i) + s.length() - 1) % s.length()));
        }
        BinaryStdOut.close();
    }
    /**
     * invertransform function.
     */
    public static void inverseTransform() {
        int first = BinaryStdIn.readInt();
        String t = BinaryStdIn.readString();
        int n = t.length();
        int[] count = new int[R + 1], next = new int[n];
        for (int i = 0; i < n; i++)
            count[t.charAt(i) + 1]++;
        for (int i = 1; i < R + 1; i++)
            count[i] += count[i - 1];
        for (int i = 0; i < n; i++)
            next[count[t.charAt(i)]++] = i;
        for (int i = next[first], c = 0; c < n; i = next[i], c++)
            BinaryStdOut.write(t.charAt(i));
        BinaryStdOut.close();
    }
    /**
     * main method starts here.
     *
     * @param      args  The arguments
     */
    public static void main(String[] args) {

        if (args[0].equals("-"))
            BurrowsWheeler.transform();
        if (args[0].equals("+"))
            BurrowsWheeler.inverseTransform();
        return;
    }
}
