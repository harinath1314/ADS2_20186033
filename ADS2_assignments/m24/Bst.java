/**
 * BST using arrays.
 */
import java.util.Scanner;
public final class Bst {
    private static String[] keys;
    private static int [] values;
    private static int size;
    /**
     * Constructs the object.
     */
    Bst(int n) {
        this.keys = new String[n];
        this.values = new int[n];
        this.size = 0;



    }
    public static int get(String key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        int i = rank(key);
        if (i < size && keys[i].equals(key)) return values[i];
        return 0;
    }
    public static int rank(String key) {
        if (key == null) throw new IllegalArgumentException("argument to rank() is null");

        int lo = 0, hi = size - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if      (cmp < 0) hi = mid - 1;
            else if (cmp > 0) lo = mid + 1;
            else return mid;
        }
        return lo;
    }
    public static void put(String key, int val)  {
        if (key == null) throw new IllegalArgumentException("first argument to put() is null");

        if (val == 0) {
            delete(key);
            return;
        }

        int i = rank(key);

        if (i < size && keys[i].equals(key)) {
            values[i] = val;
            return;
        }

        for (int j = size; j > i; j--)  {
            keys[j] = keys[j - 1];
            values[j] = values[j - 1];
        }
        keys[i] = key;
        values[i] = val;
        size++;

    }
    public static void delete(String key) {
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");
        int i = rank(key);

        if (i == size || !keys[i].equals(key)) {
            return;
        }

        for (int j = i; j < size - 1; j++)  {
            keys[j] = keys[j + 1];
            values[j] = values[j + 1];
        }

        size--;
        keys[size] = null;
        values[size] = 0;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String cas = sc.nextLine();
        switch (cas) {
        case "put":
            put(sc.next(), sc.nextInt());
            break;
        case "get":
            get(sc.nextLine());
            break;
        case"delete":
            delete(sc.nextLine());
            break;
        default:
            break;


        }
    }

}