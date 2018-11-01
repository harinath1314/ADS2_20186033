/*************************************************************************
 *  Compilation:  javac Queue.java
 *  Execution:    java Queue < input.txt
 *  Data files:   http://algs4.cs.princeton.edu/13stacks/tobe.txt
 *
 *  A generic queue, implemented using a linked list.
 *
 *  % java Queue < tobe.txt
 *  to be or not to be (2 left on queue)
 *
 *************************************************************************/

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * List of .
 *
 * @param      <Item>  The item
 */
public class Queue<Item> implements Iterable<Item> {
    private int N;         // number of elements on queue
    private Node first;    // beginning of queue
    private Node last;     // end of queue

    /**
     * Class for node.
     */
    private class Node {
        /**
         * item.
         */
        private Item item;
        /**
         * next.
         */
        private Node next;
    }

    /**
      * Create an empty queue.
      */
    public Queue() {
        first = null;
        last  = null;
    }

    /**
      * Is the queue empty?
      */
    public boolean isEmpty() {
        return first == null;
    }

    /**
      * Return the number of items in the queue.
      */
    public int size() {
        return N;
    }

    /**
      * Return the item least recently added to the queue.
      * Throw an exception if the queue is empty.
      */
    public Item peek() {
        if (isEmpty()) {
            throw new RuntimeException("Queue underflow");
        }
        return first.item;
    }

    /**
     * enqueue method.
     *
     * @param      item  The item
     */
    public void enqueue(Item item) {
        Node x = new Node();
        x.item = item;
        if (isEmpty()) { first = x;     last = x; }
        else           { last.next = x; last = x; }
        N++;
    }

    /**
      * Remove and return the item on the queue least recently added.
      * Throw an exception if the queue is empty.
      */
    public Item dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue underflow");
        }
        Item item = first.item;
        first = first.next;
        N--;
        if (isEmpty()) last = null;   // to avoid loitering
        return item;
    }

    /**
      * Return string representation.
      */
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this)
            s.append(item + " ");
        return s.toString();
    }


    /**
      * Return an iterator that iterates over items on queue in FIFO order.
      */
    public Iterator<Item> iterator()  {
        return new ListIterator();
    }

    // an iterator, doesn't implement remove() since it's optional
    private class ListIterator implements Iterator<Item> {
        /**
         * current node.
         */
        private Node current = first;
        /**
         * Determines if it has next.
         *
         * @return     True if has next, False otherwise.
         */
        public boolean hasNext()  {
            return current != null;
        }
        /**
         * remove method.
         */
        public void remove()      {
            throw new UnsupportedOperationException();
        }
        /**
         * next method.
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
