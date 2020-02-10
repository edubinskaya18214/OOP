package ru.nsu.fit.g18214.dubinskaya;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Stack<T> implements Iterable<T> {
  private StackElement<T> posStackElem = null;
  private StackElement<T> head = null;
  private int pos = 0;
  /*
   * Function add take an object and store it in stack. Increase size of stack.
   * @param n - object stored in stack.
   */
  public void add(T n) {
    pos++;
    if (posStackElem == null) {
      posStackElem = new StackElement<T>(n, null);
      head = posStackElem;
    } else {
      posStackElem = new StackElement<>(n, posStackElem);
    }
  }
  /*
   * Function pop return and delete last object in stack.
   * @return last object in stack. If stack is empty throw IndexOutOfBoundException (recommended use method empty()
   * before pop()).
   */
  public T pop() throws IndexOutOfBoundsException {
    if (pos == 0) {
      throw new IndexOutOfBoundsException();
    } else {
      T ret = posStackElem.getElem();
      posStackElem = posStackElem.getPreElem();
      if (posStackElem != null) posStackElem.setNextElem(null);
      pos--;
      if (pos == 0) head = null;
      return ret;
    }
  }
  /*
   * Function count return number of elements in stack. It doesn't change stack size.
   * @return number of objects in stack.
   */
  public int count() {
    return pos;
  }
  /*
   * @return true if stack is empty else return false.
   */
  public boolean empty() {
    return pos == 0;
  }

  public Iterator<T> iterator() {
    return new Iterator<T>() {
      private StackElement<T> current = head;
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
       */
      public T next() throws NoSuchElementException {
        T result = current.getElem();
        if (!hasNext()) throw new NoSuchElementException("End of list.");
        current = current.getNextElem();
        return result;
      }
    };
  }
}
