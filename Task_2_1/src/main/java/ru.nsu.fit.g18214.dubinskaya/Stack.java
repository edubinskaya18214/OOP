package ru.nsu.fit.g18214.dubinskaya;

public class Stack<T> {

  private StackElem<T> posStackElem = null;
  private int pos = -1;

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

  public T pop() {
    if (pos == -1){
      System.out.println("Stack is empty");
      return null;
    }
    T ret = posStackElem.getElem();
    posStackElem = posStackElem.getPreElem();
    pos--;
    return ret;
  }
  public int count(){
    return pos+1;
  }
}

