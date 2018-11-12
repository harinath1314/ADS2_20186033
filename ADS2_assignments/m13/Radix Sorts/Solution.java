/**
 * module 13- ads2.
 * author harinatha reddy
 * date 11-11-18.
 */

import java.util.Scanner;
/**
 * class for Solution.
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
        Scanner sc = new Scanner(System.in);
        int number = Integer.parseInt(sc.nextLine());
        String[] arr = new String[number];
        for (int i = 0; i < number; i++) {
            arr[i] = sc.nextLine();

        }
        Quick3string qs = new Quick3string();
        qs.sort(arr);
        for (int j = 0; j < number; j++) {
            System.out.println(arr[j]);

        }

    }
}
