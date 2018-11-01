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
  /**
   * number of elelmenst on queue.
   */
  private int n;
  /**
   * beginning of queue.
   */
  private Node first;
  /**
   * end of queue.
   */
  private Node last;

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
   * Determines if empty.
   *
   * @return     True if empty, False otherwise.
   */
  public boolean isEmpty() {
    return first == null;
  }

  /**
   * size function.
   *
   * @return     { int type. }
   */
  public int size() {
    return n;
  }

  /**
   * peek method.
   *
   * @return     { item. }
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
  public void enqueue(final Item item) {
    Node x = new Node();
    x.item = item;
    if (isEmpty()) {
      first = x;     last = x;
    } else {
      last.next = x; last = x;
    }
    n++;
  }

  /**
   * dequeue method.
   *
   * @return      item.
   */
  public Item dequeue() {
    if (isEmpty()) {
      throw new RuntimeException("Queue underflow");
    }
    Item item = first.item;
    first = first.next;
    n--;
    if (isEmpty()) {
      last = null;
    }
    return item;
  }

  /**
   * Returns a string representation of the object.
   *
   * @return     String representation of the object.
   */
  public String toString() {
    StringBuilder s = new StringBuilder();
    for (Item item : this) {
      s.append(item + " ");
    }

    return s.toString();
  }


  /**
   * iterator.
   *
   * @return     { item. }
   */
  public Iterator<Item> iterator()  {
    return new ListIterator();
  }

  /**
   * Class for list iterator.
   */
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
