package ru.nsu.fit.g18214.dubinskaya;

import java.util.Iterator;

public class Queue<K extends Comparable<K>, O> {
  private Elem<O, K> tail = null;
  private Elem<O, K> head = null;
  private int queueSize = 0;
  /*
   * @return true - if queue is empty else return false;
   */
  public boolean empty(){
    return queueSize <= 0;
  }
  /*
   @return number of elements in queue
   */
  public int size(){
    return queueSize;
  }
  /*
   * Method insert - add element in queue, this method increase queueSize
   * @param O obj - obj will add to queue, position off object will be ascending order of the @param key
   * @param K key - in order to keys objects are sets in queue, key should be comparable
   */
  public void insert(K key, O obj) {
    if (head == null) {
      head = new Elem<O, K>(key, obj, null);
      tail = head;
      ++queueSize;
      return;
    }
    if (key.compareTo(head.key) <= 0) {
      Elem<O, K> newHead = new Elem<O, K>(key, obj, null);
      newHead.addNextElem(head);
      head = newHead;
      ++queueSize;
      return;
    }
    if (key.compareTo(tail.key) >= 0) {
      Elem<O, K> newTail = new Elem<O, K>(key, obj, tail);
      tail.addNextElem(newTail);
      tail = newTail;
      ++queueSize;
      return;
    }
    Elem<O, K> nowH = head;
    for (int i = 0; i < queueSize - 1; ++i) {
      K hk = nowH.key;
      K tk = nowH.getNextElem().key;
      if (hk.compareTo(key) <= 0 && tk.compareTo(key) >= 0) {
        Elem<O, K> next = nowH.getNextElem();
        Elem<O, K> newTail = new Elem<O, K>(key, obj, nowH);
        nowH.addNextElem(newTail);
        newTail.addNextElem(next);
        ++queueSize;
        break;
      }
      nowH = nowH.getNextElem();
    }
  }
  /*
   * Method extractMax delete and return element with max key from queue, decrease queueSize
   * @return O obj - obj that have max key, it queue is empty return nullptr and print err
   */
  public O extractMax() {
    queueSize--;
    if (tail!= null && tail == head){
      Elem<O, K> nTail = tail;
      tail = null;
      head = null;
      return nTail.getElem();
    }
    if (tail != null) {
      Elem<O, K> nTail = tail;
      if (tail.getPreElem() == null)
        System.err.print("Out of bound!");
      tail = tail.getPreElem();
      tail.addNextElem(null);
      return nTail.getElem();
    } else {
      System.err.println("Query is empty!");
      return null;
    }
  }
  /*
   * Method extractMin delete and return element with max key from queue, decrease queueSize
   * @return O obj - obj that have min key, it queue is empty return nullptr and print err
   */
  public O extractMin() {
    queueSize--;
    if (tail!= null && tail == head){
      Elem<O, K> nTail = tail;
      tail = null;
      head = null;
      return nTail.getElem();
    }
    if (head != null) {
      Elem<O, K> nTail = head;
      head = head.getNextElem();
      return nTail.getElem();
    } else {
      System.err.println("Query is empty!");
      return null;
    }
  }

  public Iterator<O> iterator() {
    return new Iterator<O>() {
      private Elem<O, K> current = head;
      /*
       function hasNext check stack have new element
       @return true if we have next element else return false.
      */
      public boolean hasNext() {
        return current != null;
      }
      /*
       * Function next return value of next element in stack
       * @return next element if it exist else it throw OutOfBoundsExeption
       */
      public O next() throws IndexOutOfBoundsException {
        if (current == null) throw new IndexOutOfBoundsException("End of list.");
        O result = current.getElem();
        current = current.getNextElem();
        return result;
      }
    };
  }
}
