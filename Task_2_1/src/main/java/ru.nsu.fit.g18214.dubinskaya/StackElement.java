package ru.nsu.fit.g18214.dubinskaya;

class StackElement<T> {

  private T a;
  private StackElement<T> nextElem;
  private StackElement<T> preElem;

  StackElement(T newElem, StackElement<T> pre) {
    preElem = pre;
    a = newElem;
    if (pre != null) {
      pre.nextElem = this;
    }
  }

  void setNextElem(StackElement<T> next) {
    nextElem = next;
    if (next != null) next.preElem = this;
  }

  void setPreviousElem(StackElement<T> pred) {
    preElem = pred;
    pred.nextElem = this;
  }

  T getElem() {
    return a;
  }

  StackElement<T> getPreElem() {
    return preElem;
  }

  StackElement<T> getNextElem() {
    return nextElem;
  }
}
