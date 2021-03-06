/**
 * Class for trie st.
 *
 * @param      <Value>  The value
 */
public class TrieST<Value> {
    /**
     * extended ASCII.
     */
    private static final int R = 256;
    /**
     * root of trie.
     */
    private Node root;
    /**
     * number of keys in trie.
     */
    private int n;
    /**
     * Class for node.
     * R-way trie node.
     */
    private static class Node {
        /**
         * Object variable.
         */
        private Object val;
        /**
         * Node array.
         */
        private Node[] next = new Node[R];
    }
    /**
     * Initializes an empty string symbol table.
     */
    public TrieST() {
        // default constructor is not used.
    }
    /**
     * Returns the value associated with the given key.
     * @param key the key
     * @return the value associated with the given key
     *  if the key is in the symbol table
     *     and {@code null} if the key is not in the symbol table
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public Value get(final String key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to get() is null");
        }
        Node x = get(root, key, 0);
        if (x == null) {
            return null;
        }
        return (Value) x.val;
    }
    /**
     * Does this symbol table contain the given key?
     * @param key the key
     * @return {@code true} if this symbol table contains {@code key} and
     *     {@code false} otherwise
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public boolean contains(final String key) {
        if (key == null) {
            throw new IllegalArgumentException(
                "argument to contains() is null");
        }
        return get(key) != null;
    }
    /**
     * get.
     *
     * @param      x     Node object.
     * @param      key   The key
     * @param      d     Integer variable.
     *
     * @return     { description_of_the_return_value }
     */
    private Node get(final Node x, final String key, final int d) {
        final int a = 65;
        if (x == null) {
            return null;
        }
        if (d == key.length()) {
            return x;
        }
        char c = key.charAt(d);
        return get(x.next[c - a], key, d + 1);
    }
    /**
     * Determines if it has prefix.
     *
     * @param      str   The string
     *
     * @return     True if has prefix, False otherwise.
     */
    public boolean hasPrefix(final String str) {
        Node x = get(root, str, 0);
        return x != null;
    }
    /**
     * Inserts the key-value pair into the symbol table,
     *  overwriting the old value.
     * with the new value if the key is already in the symbol table.
     * If the value is {@code null}, this effectively deletes the key
     *  from the symbol table.
     * @param key the key
     * @param val the value
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public void put(final String key, final Value val) {
        if (key == null) {
            throw new IllegalArgumentException(
                "first argument to put() is null");
        }
        if (val == null) {
            delete(key);
        } else {
            root = put(root, key, val, 0);
        }
    }
    /**
     * put.
     *
     * @param      x     Node object.
     * @param      key   The key
     * @param      val   The value
     * @param      d     Integer variable.
     *
     * @return     { description_of_the_return_value }
     */
    private Node put(final Node x, final String key,
     final Value val, final int d) {
        final int b = 65;
        Node x1 = x;
        if (x1 == null) {
            x1 = new Node();
        }
        if (d == key.length()) {
            if (x1.val == null) {
                n++;
            }
            x1.val = val;
            return x1;
        }
        char c = key.charAt(d);
        x1.next[c - b] = put(x1.next[c - b], key, val, d + 1);
        return x1;
    }
    /**
     * Returns the number of key-value pairs in this symbol table.
     * @return the number of key-value pairs in this symbol table
     */
    public int size() {
        return n;
    }
    /**
     * Is this symbol table empty?
     * @return {@code true} if this symbol table is empty
     *  and {@code false} otherwise
     */
    public boolean isEmpty() {
        return size() == 0;
    }
    /**
     * Returns all keys in the symbol table as an {@code Iterable}.
     * To iterate over all of the keys in the symbol table named {@code st},
     * use the foreach notation: {@code for (Key key : st.keys())}.
     * @return all keys in the symbol table as an {@code Iterable}
     */
    public Iterable<String> keys() {
        return keysWithPrefix("");
    }
    /**
     * Returns all of the keys in the set that start with {@code prefix}.
     * @param prefix the prefix
     * @return all of the keys in the set that start with {@code prefix},
     *     as an iterable
     */
    public Iterable<String> keysWithPrefix(final String prefix) {
        Queue<String> results = new Queue<String>();
        Node x = get(root, prefix, 0);
        collect(x, new StringBuilder(prefix), results);
        return results;
    }
    /**
     * Collect.
     *
     * @param      x        { parameter_description }
     * @param      prefix   The prefix
     * @param      results  The results
     */
    private void collect(final Node x, final StringBuilder prefix,
     final Queue<String> results) {
        if (x == null) {
            return;
        }
        if (x.val != null) {
            results.enqueue(prefix.toString());
        }
        for (char c = 0; c < R; c++) {
            prefix.append(c);
            collect(x.next[c], prefix, results);
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }
    /**
     * Returns all of the keys in the symbol table that match {@code pattern},
     * where . symbol is treated as a wildcard character.
     * @param pattern the pattern
     * @return all of the keys in the symbol table that match {@code pattern},
     *     as an iterable, where . is treated as a wildcard character.
     */
    public Iterable<String> keysThatMatch(final String pattern) {
        Queue<String> results = new Queue<String>();
        collect(root, new StringBuilder(), pattern, results);
        return results;
    }
    /**
     * Collect.
     *
     * @param      x        { parameter_description }
     * @param      prefix   The prefix
     * @param      pattern  The pattern
     * @param      results  The results
     */
    private void collect(final Node x, final StringBuilder prefix,
     final String pattern, final Queue<String> results) {
        if (x == null) {
            return;
        }
        int d = prefix.length();
        if (d == pattern.length() && x.val != null) {
            results.enqueue(prefix.toString());
        }
        if (d == pattern.length()) {
            return;
        }
        char c = pattern.charAt(d);
        if (c == '.') {
            for (char ch = 0; ch < R; ch++) {
                prefix.append(ch);
                collect(x.next[ch], prefix, pattern, results);
                prefix.deleteCharAt(prefix.length() - 1);
            }
        } else {
            prefix.append(c);
            collect(x.next[c], prefix, pattern, results);
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }
    /**
     * Returns the string in the symbol table that is the
     *  longest prefix of {@code query},
     * or {@code null}, if no such string.
     * @param query the query string
     * @return the string in the symbol table that is the
     *  longest prefix of {@code query},
     *     or {@code null} if no such string
     * @throws IllegalArgumentException if {@code query} is {@code null}
     */
    public String longestPrefixOf(final String query) {
        if (query == null) {
            throw new IllegalArgumentException(
                "argument to longestPrefixOf() is null");
        }
        int length = longestPrefixOf(root, query, 0, -1);
        if (length == -1) {
            return null;
        } else {
            return query.substring(0, length);
        }
    }
    // returns the length of the longest string key in the subtrie
    // rooted at x that is a prefix of the query string,
    // assuming the first d character match and we have already
    // found a prefix match of given length (-1 if no such match)
    /**
     * LongestPrefixOf.
     *
     * @param      x       Node object
     * @param      query   The query
     * @param      d       Integer variable.
     * @param      length  The length
     *
     * @return     { description_of_the_return_value }
     */
    private int longestPrefixOf(final Node x, final String query,
     final int d, final int length) {
        int length1 = length;
        if (x == null) {
            return length1;
        }
        if (x.val != null) {
            length1 = d;
        }
        if (d == query.length()) {
            return length1;
        }
        char c = query.charAt(d);
        return longestPrefixOf(x.next[c], query, d + 1, length1);
    }
    /**
     * Removes the key from the set if the key is present.
     * @param key the key
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public void delete(final String key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to delete() is null");
        }
        root = delete(root, key, 0);
    }
    /**
     * delete.
     *
     * @param      x     { parameter_description }
     * @param      key   The key
     * @param      d     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    private Node delete(final Node x, final String key, final int d) {
        if (x == null) {
            return null;
        }
        if (d == key.length()) {
            if (x.val != null) {
                n--;
            }
            x.val = null;
        } else {
            char c = key.charAt(d);
            x.next[c] = delete(x.next[c], key, d + 1);
        }
        // remove subtrie rooted at x if it is completely empty
        if (x.val != null) {
            return x;
        }
        for (int c = 0; c < R; c++) {
            if (x.next[c] != null) {
                return x;
            }
        }
        return null;
    }
}

