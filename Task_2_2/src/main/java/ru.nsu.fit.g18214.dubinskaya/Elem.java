package ru.nsu.fit.g18214.dubinskaya;

class Elem<O, K> {

  private O a;
  Elem<O, K> nextElem;
  Elem<O, K> preElem;
  K key;

  Elem(K k, O newElem, Elem<O, K> pre) {
    preElem = pre;
    a = newElem;
    key = k;
  }

  void addNextElem(Elem<O, K> next) {
    nextElem = next;
    if (next != null) next.preElem = this;
  }

  O getElem() {
    return a;
  }

  Elem<O, K> getPreElem() {
    return preElem;
  }

  Elem<O, K> getNextElem() {
    return nextElem;
  }

  Elem<O, K> createNextElem(K k, O next) {
    if (nextElem == null) {
      nextElem = new Elem<O, K>(k, next, this);
    } else {
      Elem<O, K> savedNext = nextElem;
      nextElem = new Elem<O, K>(k, next, this);
      nextElem.nextElem = savedNext;
    }
    return nextElem;
  }
}
