package ru.nsu.fit.g18214.dubinskaya;

class StackElem<T> {

  private T a;
  private StackElem<T> nextElem;
  private StackElem<T> preElem;

  StackElem(T newElem, StackElem<T> pre) {
    preElem = pre;
    a = newElem;
  }
  void addNextElem(StackElem<T> next){
    nextElem = next;
  }
  T getElem(){
    return a;
  }
  StackElem<T> getPreElem(){
    return preElem;
  }
  StackElem<T> getNextElem(){
    return nextElem;
  }
  StackElem<T> CreateNextElem(T next){
    nextElem = new StackElem<T>(next, this);
    return nextElem;
  }
}
