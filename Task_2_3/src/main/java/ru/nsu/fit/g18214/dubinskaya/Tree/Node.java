package ru.nsu.fit.g18214.dubinskaya.Tree;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

class Node<T extends Comparable<T>> {
  private boolean isSaveState = false;
  private T value;
  private List<Node<T>> children;
  private Set<T> savedNodes = new HashSet<>();

  Node(T root) throws NullPointerException {
    if (root == null) throw new NullPointerException();
    isSaveState = false;
    value = root;
    children = new LinkedList<>();
  }

  void addChild(T elem) throws NullPointerException {
    if (elem == null) throw new NullPointerException();
    if (savedNodes.contains(elem)) throw new IllegalArgumentException("One node can't contain two child with same name");
    children.add(new Node<T>(elem));
    isSaveState = false;
    savedNodes.add(elem);
  }

  void setRoot(T root) {
    value = root;
  }

  boolean isContain(T value){
    return savedNodes.contains(value);
  }
  void addChild(Node<T> child) {
    if (child == null) throw new NullPointerException();
    if (savedNodes.contains(child.value)) throw new IllegalArgumentException("One node can't contain two child with same name");
    children.add(child);
    isSaveState = false;
    savedNodes.add(child.value);
  }

  List<Node<T>> getChildren() {
    return children;
  }

  Node<T> getChild(T value) {
    if (value == null) throw new NullPointerException();
    for (Node<T> child : children)
      if (child.value.equals(value))
        return child;
    return null;
  }

  Node<T> copy(){
    Node<T> ret = new Node<>(value);
    List<Node<T>> copySubNodes = new LinkedList<>();
    Set<T> saved = new HashSet<>(savedNodes);
    for (Node<T> curr : children) {
      Node<T> currCopy = curr.copy();
      copySubNodes.add(currCopy);
    }
    ret.children = copySubNodes;
    ret.savedNodes = saved;
    return ret;
  }

  boolean deleteChild(T value) throws NullPointerException {
    if (value == null) throw new NullPointerException();
    Node<T> child;
    for (int i = 0; i < children.size(); ++i) {
      child = children.get(i);
      if (child.value.compareTo(value) == 0) {
        isSaveState = false;
        children.remove(i);
        savedNodes.remove(value);
        return true;
      }
    }
    return false;
  }

  T getValue() {
    return value;
  }


  void saveState() {
    isSaveState = true;
    for (Node<T> child : children)
      child.saveState();
  }

  boolean isNotChanged() {
    if (isSaveState) {
      for (Node<T> child : children) {
        boolean res = child.isNotChanged();
        if (!res) return false;
      }
    } else return false;
    return true;
  }
}
