/*************************************************************************
 *  Compilation:  javac Bag.java
 *  Execution:    java Bag < input.txt
 *
 *  A generic bag or multiset, implemented using a linked list.
 *
 *************************************************************************/

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class for bag.
 *
 * @param      <Item>  The item
 */
public class Bag<Item> implements Iterable<Item> {
    /**
     * number of elements in bag.
     */
    private int num;
    /**
     * beginning of bag.
     */
    private Node first;

    /**
     * Class for node.
     */
    private class Node {
        /**
         * item varible.
         */
        private Item item;
        /**
         * next variable.
         */
        private Node next;
    }

    /**
      * Create an empty stack.
      */
    public Bag() {
        first = null;
        num = 0;
    }

    /**
     * Determines if empty.
     *
     * @return     True if empty, False otherwise.
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * ..
     *
     * @return     { description_of_the_return_value }
     */
    public int size() {
        return num;
    }

    /**
     * add fuctiom.
     *
     * @param      item  The item
     */
    public void add(final Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        num++;
    }


    /**
     * iterator.
     *
     * @return     { description_of_the_return_value }
     */
    public Iterator<Item> iterator()  {
        return new ListIterator();
    }


    /**
     * Class for list iterator.
     * an iterator, doesn't implement remove() since it's optional
     */
    private class ListIterator implements Iterator<Item> {
        /**
         * current variable of linked list.
         */
        private Node current = first;
        /**
         * Determines if it has next.
         *
         * @return     True if has next, False otherwise.
         */
        public boolean hasNext() {
            return current != null;
        }
        /**
         * remove function to remove edges.
         */
        public void remove() {
            throw new UnsupportedOperationException();
        }
        /**
         * next funxtion to iterate over next edge.
         *
         * @return     { description_of_the_return_value }
         */
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}
