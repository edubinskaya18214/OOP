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
   * @throw NullPointerException
   */
  public void insert(K key, O value) throws NullPointerException{
    if (key == null) throw new NullPointerException();
    if (head == null) {
      head = new QueueElement<>(key, value, null, null);
      tail = head;
      ++queueSize;
      return;
    }
    if (key.compareTo(head.getKey()) <= 0) {
      head = new QueueElement<>(key, value, null, head);
      ++queueSize;
      return;
    }
    if (key.compareTo(tail.getKey()) > 0) {
      tail = new QueueElement<>(key, value, tail, null);
      ++queueSize;
      return;
    }
    QueueElement<O, K> current = head;
    for (int i = 0; i < queueSize - 1; ++i) {
      K currentKey = current.getKey();
      K nextKey = current.getNextElem().getKey();
      if (currentKey.compareTo(key) <= 0 && nextKey.compareTo(key) >= 0) {
        QueueElement<O, K> nextQueueElement = current.getNextElem();
        // QueueElement constructor save value with it's key between current and next elements
        new QueueElement<>(key, value, current, nextQueueElement);
        ++queueSize;
        break;
      }
      current = current.getNextElem();
    }
  }
  /*
   * Method extractMax delete and return element with max key from queue, decrease queueSize
   * @return O obj - obj that have max key, if queue is empty:
   * @throw NoSuchElementException
   */
  public O extractMax() throws NoSuchElementException {
    queueSize--;
    if (tail!= null && tail == head){
      O returnedValue = tail.getElem();
      tail = null;
      head = null;
      return returnedValue;
    }
    if (tail != null) {
      O returnedValue = tail.getElem();
      assert(tail.getPreElem() != null);
      tail = tail.getPreElem();
      tail.setNextElem(null);
      return returnedValue;
    } else {
      throw new NoSuchElementException("Queue is empty");
    }
  }
  /*
   * Method extractMin delete and return element with max key from queue, decrease queueSize
   * @return O obj - obj that have min key, if queue is empty:
   * @throw NoSuchElementException
   */
  public O extractMin() {
    queueSize--;
    if (tail!= null && tail == head){
      O returnedValue = tail.getElem();
      tail = null;
      head = null;
      return returnedValue;
    }
    if (head != null) {
      O returnedValue = head.getElem();
      assert(head.getNextElem() != null);
      head = head.getNextElem();
      head.setPreElem(null);
      return returnedValue;
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
