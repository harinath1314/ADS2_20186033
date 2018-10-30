// public class Percolation {
//    public Percolation(int n)
// create n-by-n grid, with all sites blocked
//    public    void open(int row, int col)
// open site (row, col) if it is not open already
//    public boolean isOpen(int row, int col)  // is site (row, col) open?
//    public boolean isFull(int row, int col)  // is site (row, col) full?
//    public     int numberOfOpenSites()       // number of open sites
//    public boolean percolates()              // does the system percolate?
// }

/**
 *author harinatha reddy
 *date:25/9/28
 *percolation.
 */
// You can implement the above API to solve the problem
import java.util.Scanner;
/**
 * Class for percolation.
 */
class Percolation {
    /**
     * n-by-n matrix, with all sites blocked
     *
     */
    /**
     * final row.
     */
    private int[] finalrow;
    /**
     * matrix.
     */
    private boolean[] matrix;
    /**
     * countopen.
     */
    private int countofopen;
    /**
     * dimension.
     */
    private int dimension;
    /**
     * weight union.
     */
    private WeightedQuickUnionUF uf;
    /**
     * Constructs the object.
     *
     * @param      n    dimension of matrix.
     */
    Percolation(final int n) {
        matrix = new boolean[n * n];
        countofopen = 0;
        finalrow = new int[n];
        dimension = n;
        uf = new WeightedQuickUnionUF(n * n + 2);


    }
    /**
     * open function to creat open sites in matrix.
     *
     * @param      row   The row
     * @param      col   The col
     */
    public void open(final int row, final int col) {
        if (!isopen(row, col)) {
            matrix[(row * dimension) + col] = true;
            countofopen += 1;
            if (row - 1 >= 0 && isopen(row - 1, col)) {
                uf.union((row * dimension) + col, ((row - 1)
                                                   * dimension) + col);


            }
            if ((row + 1 < dimension) && isopen(row + 1, col)) {
                uf.union((row * dimension) + col, ((row + 1)
                                                   * dimension) + col);

            }
            if (col - 1 >= 0 && isopen(row, col - 1)) {
                uf.union((row * dimension) + col, ((row) * dimension)
                         + (col - 1));


            }
            if (col + 1 < dimension && isopen(row, col + 1)) {
                uf.union((row * dimension)
                         + col, ((row) * dimension) + (col + 1));


            }
            if (row == 0) {
                uf.union(row * dimension + col, dimension * dimension);

            }
            if (row == dimension - 1) {
                uf.union(row * dimension + col, dimension * dimension + 1);

            }



        }

    }
    /**
     * isopen function.
     *
     * @param      row   The row
     * @param      col   The col
     *
     * @return     boolean type.
     */
    public boolean isopen(final int row, final int col) {
        if (matrix[row * dimension + col]) {
            return true;
        }
        return false;

    }


    /**
     * percolates function.
     *
     * @return     { description_of_the_return_value }
     */
    public boolean percolates() {
        return uf.connected(dimension * dimension, dimension * dimension + 1);
    }



}




// h
// a
// r
// i
/**
 * class for the solution to find pecolation.
 */
public final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {


    }
    /**
     * { function_description }.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner input = new Scanner(System.in);
        int dim = input.nextInt();

        Percolation p = new Percolation(dim);

        while (input.hasNextInt()) {
            int row = input.nextInt();
            int col = input.nextInt();
            p.open(row - 1, col - 1);
        }
        System.out.println(p.percolates());
    }

}

/******************************************************************************
 *  Compilation:  javac WeightedQuickUnionUF.java
 *  Execution:  java WeightedQuickUnionUF < input.txt
 *  Dependencies: StdIn.java StdOut.java
 *  Data files:   https://algs4.cs.princeton.edu/15uf/tinyUF.txt
 *                https://algs4.cs.princeton.edu/15uf/mediumUF.txt
 *                https://algs4.cs.princeton.edu/15uf/largeUF.txt
 *
 *  Weighted quick-union (without path compression).
 *
 ******************************************************************************/

// package edu.princeton.cs.algs4;

/**
 *  The {@code WeightedQuickUnionUF} class represents a <em>union–find data type</em>
 *  (also known as the <em>disjoint-sets data type</em>).
 *  It supports the <em>union</em> and <em>find</em> operations,
 *  along with a <em>connected</em> operation for determining whether
 *  two sites are in the same component and a <em>count</em> operation that
 *  returns the total number of components.
 *  <p>
 *  The union–find data type models connectivity among a set of <em>n</em>
 *  sites, named 0 through <em>n</em>–1.
 *  The <em>is-connected-to</em> relation must be an 
 *  <em>equivalence relation</em>:
 *  <ul>
 *  <li> <em>Reflexive</em>: <em>p</em> is connected to <em>p</em>.
 *  <li> <em>Symmetric</em>: If <em>p</em> is connected to <em>q</em>,
 *       then <em>q</em> is connected to <em>p</em>.
 *  <li> <em>Transitive</em>: If <em>p</em> is connected to <em>q</em>
 *       and <em>q</em> is connected to <em>r</em>, then
 *       <em>p</em> is connected to <em>r</em>.
 *  </ul>
 *  <p>
 *  An equivalence relation partitions the sites into
 *  <em>equivalence classes</em> (or <em>components</em>). In this case,
 *  two sites are in the same component if and only if they are connected.
 *  Both sites and components are identified with integers between 0 and
 *  <em>n</em>–1. 
 *  Initially, there are <em>n</em> components, with each site in its
 *  own component.  The <em>component identifier</em> of a component
 *  (also known as the <em>root</em>, <em>canonical element</em>, <em>leader</em>,
 *  or <em>set representative</em>) is one of the sites in the component:
 *  two sites have the same component identifier if and only if they are
 *  in the same component.
 *  <ul>
 *  <li><em>union</em>(<em>p</em>, <em>q</em>) adds a
 *      connection between the two sites <em>p</em> and <em>q</em>.
 *      If <em>p</em> and <em>q</em> are in different components,
 *      then it replaces
 *      these two components with a new component that is the union of
 *      the two.
 *  <li><em>find</em>(<em>p</em>) returns the component
 *      identifier of the component containing <em>p</em>.
 *  <li><em>connected</em>(<em>p</em>, <em>q</em>)
 *      returns true if both <em>p</em> and <em>q</em>
 *      are in the same component, and false otherwise.
 *  <li><em>count</em>() returns the number of components.
 *  </ul>
 *  <p>
 *  The component identifier of a component can change
 *  only when the component itself changes during a call to
 *  <em>union</em>—it cannot change during a call
 *  to <em>find</em>, <em>connected</em>, or <em>count</em>.
 *  <p>
 *  This implementation uses weighted quick union by size (without path compression).
 *  Initializing a data structure with <em>n</em> sites takes linear time.
 *  Afterwards, the <em>union</em>, <em>find</em>, and <em>connected</em>
 *  operations  take logarithmic time (in the worst case) and the
 *  <em>count</em> operation takes constant time.
 *  For alternate implementations of the same API, see
 *  {@link UF}, {@link QuickFindUF}, and {@link QuickUnionUF}.
 *
 *  <p>
 *  For additional documentation, see <a href="https://algs4.cs.princeton.edu/15uf">Section 1.5</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
class WeightedQuickUnionUF {
    private int[] parent;   // parent[i] = parent of i
    private int[] size;     // size[i] = number of sites in subtree rooted at i
    private int count;      // number of components

    /**
     * Initializes an empty union–find data structure with {@code n} sites
     * {@code 0} through {@code n-1}. Each site is initially in its own 
     * component.
     *
     * @param  n the number of sites
     * @throws IllegalArgumentException if {@code n < 0}
     */
    public WeightedQuickUnionUF(int n) {
        count = n;
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    /**
     * Returns the number of components.
     *
     * @return the number of components (between {@code 1} and {@code n})
     */
    public int count() {
        return count;
    }
  
    /**
     * Returns the component identifier for the component containing site {@code p}.
     *
     * @param  p the integer representing one object
     * @return the component identifier for the component containing site {@code p}
     * @throws IllegalArgumentException unless {@code 0 <= p < n}
     */
    public int find(int p) {
        validate(p);
        while (p != parent[p])
            p = parent[p];
        return p;
    }

    // validate that p is a valid index
    private void validate(int p) {
        int n = parent.length;
        if (p < 0 || p >= n) {
            throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n-1));  
        }
    }

    /**
     * Returns true if the the two sites are in the same component.
     *
     * @param  p the integer representing one site
     * @param  q the integer representing the other site
     * @return {@code true} if the two sites {@code p} and {@code q} are in the same component;
     *         {@code false} otherwise
     * @throws IllegalArgumentException unless
     *         both {@code 0 <= p < n} and {@code 0 <= q < n}
     */
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * Merges the component containing site {@code p} with the 
     * the component containing site {@code q}.
     *
     * @param  p the integer representing one site
     * @param  q the integer representing the other site
     * @throws IllegalArgumentException unless
     *         both {@code 0 <= p < n} and {@code 0 <= q < n}
     */
    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;

        // make smaller root point to larger one
        if (size[rootP] < size[rootQ]) {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        }
        else {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        }
        count--;
    }

}