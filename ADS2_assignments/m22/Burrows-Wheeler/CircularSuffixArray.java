// import java.util.Scanner;
import java.util.Arrays;
public class CircularSuffixArray {
    private final  int[] myArray;
    // private final  int myIndex;
    private final  String myString;

    private class Node implements Comparable<Node> {
        private final int index;

        public Node(int index) {
            this.index = index;
        }

        public int getIndex() { return index; }

        @Override
        public int compareTo(Node arg) {
            // TODO Auto-generated method stub
            int j = arg.getIndex();
            for (int i = 0; i < length(); i++) {
                int myArray1 = (i + index + length()) % length();
                int myArray2 = (i + j + length()) % length();
                if (myString.charAt(myArray1) != myString.charAt(myArray2))
                    return myString.charAt(myArray1) - myString.charAt(myArray2);
            }
            return 0;
        }

    }
    public CircularSuffixArray(String s) {

        this.myArray = new int[s.length()];
        // this.myIndex = s.length();
        this.myString = s;
        Node[] suffix = new Node[s.length()];
        for (int i = 0; i < suffix.length; i++) {
            suffix[i] = new Node(i);
        }
        Arrays.sort(suffix);
        for (int i = 0; i < myArray.length; i++) {
            myArray[i] = suffix[i].getIndex();
        }
    }

    public int length() {
        return myString.length();
    }
    // public  void rotations() {
    //     int len = myString.length();

    //     // Generate all rotations one by one and print
    //     StringBuffer sb;

    //     for (int i = 0; i < len; i++) {
    //         sb = new StringBuffer();

    //         int j = i;  // Current index in myString
    //         int k = 0;  // Current index in temp

    //         // Copying the second part from the point
    //         // of rotation.
    //         for (int k2 = j; k2 < myString.length(); k2++) {
    //             sb.insert(k, myString.charAt(j));
    //             k++;
    //             j++;
    //         }

    //         // Copying the first part from the point
    //         // of rotation.
    //         j = 0;
    //         while (j < i) {
    //             sb.insert(k, myString.charAt(j));
    //             j++;
    //             k++;
    //         }

    //         // System.out.println(sb);
    //         myArray[i] = sb.toString();
    //     }
    //     System.out.println(Arrays.toString(myArray));

    //     Arrays.sort(myArray);
    //     System.out.println(Arrays.toString(myArray));

    // }



    /**
     * returns index of ith sorted suffix
     *
     * @param i
     *            the index of the ith sorted suffix
     * @return
     */
    public int index(int i) {
        return myArray[i];
    }

}



