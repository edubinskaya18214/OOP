package ru.nsu.fit.g18214.dubinskaya;

import java.util.Iterator;

/*
 * This class can contain elements. Stack can contain objects with any type, but all objects
 * in stack should have the same type.
 * It work like simple stack: has methods push, pop and count.
 */
public class Stack<T> implements Iterable<T>{
  private StackElem<T> posStackElem = null;
  private int pos = -1;
/*
 * Function push take an object and store it in stack. Increase size of stack.
 * @param n - object that stored in stack. If n == NULL stack size will not increase.
 */
  public void push (T n) {
    if (n == null) return;
    pos++;
    if (posStackElem == null)
      posStackElem = new StackElem<T> (n, null);
    else{
      StackElem<T> next = posStackElem.CreateNextElem(n);
      posStackElem.addNextElem(next);
      posStackElem = next;
    }
  }
  /*
   * Function pop return and delete last object in stack.
   * @return last object in stack.
   */
  public T pop() {
    if (pos == -1){
      //System.out.println("Stack is empty");
      return null;
    }
    T ret = posStackElem.getElem();
    posStackElem = posStackElem.getPreElem();
    pos--;
    return ret;
  }
  /*
   * Function count return number of elements in stack. It doesn't change stack size.
   * @return number of objects in stack.
   */
  public int count(){
    return pos+1;
  }

  private StackElem<T> FindFirstElem(){
    if (posStackElem == null) return null;
    StackElem<T> elem = posStackElem;
    while (elem.getPreElem() != null)
      elem = elem.getPreElem();
    return elem;
  }
  public Iterator<T> iterator() {
    return new Iterator<T>() {
      private StackElem<T> current = FindFirstElem();

      public boolean hasNext() {
        return current.getNextElem() != null;
      }

      public T next() throws IndexOutOfBoundsException {
        T result = current.getElem();
        if (current.getNextElem() == null ) throw new IndexOutOfBoundsException("End of list.");
        current = current.getNextElem();
        return result;
      }
    };
  }
}

