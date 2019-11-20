package ru.nsu.fit.g18214.dubinskaya;

class QueueElement<O, K> {

  private O a;
  private QueueElement<O, K> nextElem;
  private QueueElement<O, K> preElem;
  K key;

  QueueElement(K k, O newElem, QueueElement<O, K> pre, QueueElement<O, K> next) {
    preElem = pre;
    if (pre != null) pre.setNextElem(this);
    if (next != null) next.setPreElem(this);
    a = newElem;
    key = k;
  }

  void setNextElem(QueueElement<O, K> next) {
    nextElem = next;
    if (next != null) next.preElem = this;
  }

  void setPreElem(QueueElement<O, K> pre) {
    preElem = pre;
    if (pre != null) pre.nextElem = this;
  }

  O getElem() {
    return a;
  }

  QueueElement<O, K> getPreElem() {
    return preElem;
  }

  QueueElement<O, K> getNextElem() {
    return nextElem;
  }
}
