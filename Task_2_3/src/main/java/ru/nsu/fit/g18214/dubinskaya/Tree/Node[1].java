package ru.nsu.fit.g18214.dubinskaya.Tree;

import java.util.*;

class Node<T extends Comparable<T>> {
  private boolean isSaveState = false;
  private T value;
  private TreeMap<T, Node<T>> children;

  Node(T root) throws NullPointerException {
    if (root == null) throw new NullPointerException();
    isSaveState = false;
    value = root;
    children = new TreeMap<>();
  }

  void addChild(T elem) throws NullPointerException {
    if (elem == null) throw new NullPointerException();
    if (children.containsKey(elem))
      throw new IllegalArgumentException("One node can't contain two child with same name");
    children.put(elem, new Node<>(elem));
    isSaveState = false;
  }

  void setRoot(T root) {
    value = root;
  }

  boolean isContain(T value){
    return children.containsKey(value);
  }
  void addChild(Node<T> child) {
    if (child == null) throw new NullPointerException();
    if (children.containsKey(child.value)) throw new IllegalArgumentException("One node can't contain two child with same name");
    children.put(child.value,child);
    isSaveState = false;
  }

  TreeMap<T, Node<T>> getChildren() {
    return children;
  }

  Node<T> getChild(T value) {
    if (value == null) throw new NullPointerException();
    return children.get(value);
  }

  Node<T> copy(){
    Node<T> ret = new Node<>(value);
    TreeMap<T, Node<T>> copySubNodes = new TreeMap<>();
    for (Map.Entry<T,Node<T>> node : children.entrySet()) {
      Node<T> currCopy = node.getValue().copy();
      copySubNodes.put(currCopy.value, currCopy);
    }
    ret.children = copySubNodes;
    return ret;
  }

  void deleteChild(T value) throws NullPointerException {
    if (value == null) throw new NullPointerException();
    children.remove(value);
  }

  T getValue() {
    return value;
  }


  void saveState() {
    isSaveState = true;
    for (Map.Entry<T,Node<T>> child : children.entrySet())
      child.getValue().saveState();
  }

  boolean isNotChanged() {
    if (isSaveState) {
      for (Map.Entry<T,Node<T>> child : children.entrySet()) {
        boolean res = child.getValue().isNotChanged();
        if (!res) return false;
      }
    } else return false;
    return true;
  }
}
