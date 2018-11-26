/**
 * class for BST using Arrays.
 * ADS2 assignnet 24.
 * author harinath reddy.
 */
import java.util.Scanner;
class BSTArray<Key extends Comparable<Key>, Value> {
    private Key[] keys;
    private Value[] values;
    private int[] size;
    private int[] left;
    private int[] right;

    BSTArray(int size) {
        keys = (Key[]) new Comparable[size];
        values = (Value[]) new Object[size];
        this.size = new int[size];
        left = new int[size];
        right = new int[size];
        for (int i = 0; i < size; i++) {
            left[i] = -1;
            right[i] = -1;
        }
    }

    public int size() {
        return size(0);
    }

    private int size(int index) {
        if (index == -1) {
            return 0;
        }

        return size[index];
    }

    private int min(int index) {
        if (left[index] == -1) {
            return index;
        }
        return min(left[index]);
    }

    public Value get(Key key) {
        return get(0, key);
    }

    private Value get(int index, Key key) {
        if (index == -1 || keys[index] == null) {
            return null;
        }
        int compare = key.compareTo(keys[index]);
        if (compare < 0) {
            return get(left[index], key);
        } else if (compare > 0) {
            return get(right[index], key);
        } else {
            return values[index];
        }
    }

    public void put(Key key, Value value) {
        if (size() == keys.length) {
            System.out.println("BST is full");
            return;
        }
        put(0, key, value);
    }

    private int put(int index, Key key, Value value) {
        if (index == -1 || keys[index] == null) {
            int nextIndex = size();
            keys[nextIndex] = key;
            values[nextIndex] = value;
            size[nextIndex] = 1;
            // size += 1;
            return nextIndex;
        }

        int compare = key.compareTo(keys[index]);

        if (compare < 0) {
            left[index] = put(left[index], key, value);
        } else if (compare > 0) {
            right[index] = put(right[index], key, value);
        } else {
            values[index] = value;
        }

        size[index] = size(left[index]) + 1 + size(right[index]);
        return index;
    }

    public void delete(Key key) {
        int rootIndex = delete(0, key);
    }

    private int delete(int index, Key key) {
        if (index == -1 || keys[index] == null) {
            return -1;
        }

        int compare = key.compareTo(keys[index]);
        if (compare < 0) {
            int leftIndex = delete(left[index], key);
            left[index] = leftIndex;
        } else if (compare > 0) {
            int rightIndex = delete(right[index], key);
            right[index] = rightIndex;
        } else {
            keys[index] = null;
            values[index] = null;
            size[index] = 0;

            if (left[index] == -1) {
                int rightLinkIndex = right[index];
                right[index] = -1;
                return rightLinkIndex;
            } else if (right[index] == -1) {
                int leftLinkIndex = left[index];
                left[index] = -1;
                return leftLinkIndex;
            } else {
                int temp = min(right[index]);
                right[temp] = deleteMin(right[index], false);
                left[temp] = left[index];
                right[index] = -1;
                left[index] = -1;
                index = temp;
            }
        }
        size[index] = size(left[index]) + 1 + size(right[index]);
        return index;
    }

    public void deleteMin() {
        int rootIndex = deleteMin(0, true);
    }
    private int deleteMin(int index, boolean setKeyNull) {
        if (index == -1 || keys[index] == null) {
            return -1;
        }

        int leftIndex = deleteMin(left[index], setKeyNull);
        left[index] = leftIndex;

        size[index] = size(left[index]) + 1 + size(right[index]);
        return index;
    }
}

class Bst {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        BSTArray<Integer, String> bstobj = new BSTArray<>(n);
        while (n > 0) {
            String s = sc.nextLine();
            String[] inputs = s.split(" ");
            switch (inputs[0]) {
            case"put":
                bstobj.put(Integer.parseInt(inputs[1]), inputs[2]);
                break;
            case"get":
                System.out.println(bstobj.get(Integer.parseInt(inputs[1])));
                break;
            case"delete":
                bstobj.delete(Integer.parseInt(inputs[1]));
            }
            n--;
        }
    }
}