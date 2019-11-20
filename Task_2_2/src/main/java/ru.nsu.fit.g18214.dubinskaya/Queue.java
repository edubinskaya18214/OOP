package ru.nsu.fit.g18214.dubinskaya;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Queue<K extends Comparable<K>, O> {
  private QueueElement<O, K> tail = null;
  private QueueElement<O, K> head = null;
  private int queueSize = 0;
  /*
   * @return true - if queue is empty else return false;
   */
  public boolean empty(){
    return queueSize <= 0;
  }
  //@return number of elements in queue
  public int size(){
    return queueSize;
  }
  /*
   * Method insert - add element in queue, this method increase queueSize.
   * @param O obj - obj will add to queue, position off object will be ascending order of the @param key
   * @param K key - in order to keys objects are sets in queue, key should be comparable.
   *                if key == null, obj will not be added to queue, queueSize isn't change.
   *                if obj with the same key exist, new object will push before old object.
   */
  public void insert(K key, O obj) {
    if (key == null) return;
    if (head == null) {
      head = new QueueElement<O, K>(key, obj, null, null);
      tail = head;
      ++queueSize;
      return;
    }
    if (key.compareTo(head.key) <= 0) {
      head = new QueueElement<O, K>(key, obj, null, head);
      ++queueSize;
      return;
    }
    if (key.compareTo(tail.key) > 0) {
      tail = new QueueElement<O, K>(key, obj, tail, null);
      ++queueSize;
      return;
    }
    QueueElement<O, K> nowH = head;
    for (int i = 0; i < queueSize - 1; ++i) {
      K hk = nowH.key;
      K tk = nowH.getNextElem().key;
      if (hk.compareTo(key) <= 0 && tk.compareTo(key) >= 0) {
        QueueElement<O, K> next = nowH.getNextElem();
        QueueElement<O, K> newTail = new QueueElement<O, K>(key, obj, nowH, next);
        ++queueSize;
        break;
      }
      nowH = nowH.getNextElem();
    }
  }
  /*
   * Method extractMax delete and return element with max key from queue, decrease queueSize
   * @return O obj - obj that have max key, if queue is empty throw IndexOutOfBoundsException
   * @throw NoSuchElementException
   */
  public O extractMax() throws NoSuchElementException {
    queueSize--;
    if (tail!= null && tail == head){
      QueueElement<O, K> nTail = tail;
      tail = null;
      head = null;
      return nTail.getElem();
    }
    if (tail != null) {
      QueueElement<O, K> nTail = tail;
      if (tail.getPreElem() == null) System.err.print("Out of bound!");
      tail = tail.getPreElem();
      tail.setNextElem(null);
      return nTail.getElem();
    } else {
      throw new NoSuchElementException("Queue is empty");
    }
  }
  /*
   * Method extractMin delete and return element with max key from queue, decrease queueSize
   * @return O obj - obj that have min key,  queue is empty throw NoSuchElementException
   * @throw NoSuchElementException
   */
  public O extractMin() {
    queueSize--;
    if (tail!= null && tail == head){
      QueueElement<O, K> nTail = tail;
      tail = null;
      head = null;
      return nTail.getElem();
    }
    if (head != null) {
      QueueElement<O, K> nTail = head;
      head = head.getNextElem();
      head.setPreElem(null);
      return nTail.getElem();
    } else {
      throw new NoSuchElementException("Queue is empty");
    }
  }

  public Iterator<O> iterator() {
    return new Iterator<O>() {
      private QueueElement<O, K> current = head;
      /*
       function hasNext check stack have new element
       @return true if we have next element else return false.
      */
      public boolean hasNext() {
        return current != null;
      }
      /*
       * Function next return value of next element in stack
       * @return next element if it exist else it throw NoSuchElementException
       * @throw NoSuchElementException
       */
      public O next() throws IndexOutOfBoundsException {
        if (current == null) throw new NoSuchElementException("End of list.");
        O result = current.getElem();
        current = current.getNextElem();
        return result;
      }
    };
  }
}
