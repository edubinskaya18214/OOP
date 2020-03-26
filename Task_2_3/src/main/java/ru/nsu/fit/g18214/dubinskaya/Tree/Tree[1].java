package ru.nsu.fit.g18214.dubinskaya.Tree;

import java.util.*;

public class Tree<T extends Comparable<T>> implements Iterable<T> {
  public static final boolean DFS = true;
  public static final boolean BFS = false;

  private boolean iterationType = DFS;
  Node<T> root;
  boolean isSaveState = false;

  /**
   * Tree constructor.
   *
   * @param root - value of root of tree.
   * @throws NullPointerException if root == null.
   */
  public Tree(T root) {
    if (root == null) throw new NullPointerException();
    this.root = new Node<>(root);
  }
  /**
   * add child to Node "node" with value "value".
   *
   * @param way   - is way to added child/
   * @param value - value of added node.
   * @return true if Node with name node exist and value was added, else return false;
   * @throws NullPointerException - if one of param equals null.
   */
  public boolean addChild(T value, T... way) throws NullPointerException {
    if (value == null) throw new NullPointerException();
    Node<T> curr = root;
    for (T pos : way) {
      if (pos == null) throw new NullPointerException();
      curr = curr.getChild(pos);
      if (curr == null) return false;
    }
    curr.addChild(value);
    isSaveState = false;
    return true;
  }

  /**
   * Copy Tree as new child of current tree, this tree only copied to a current tree, all action on old tree doesn't effect
   * on current tree.
   * @param subTree - child to add.
   * @throws IllegalArgumentException if child with subtree.value already exist.
   */
  public void addSubTree(Tree<T> subTree) throws IllegalArgumentException{
    if (root.isContain(subTree.root.getValue()))
      throw new IllegalArgumentException("Tree already have node with same name");
    root.addChild(subTree.root.copy());
  }

  /**
   * This method used to take root's value.
   *
   * @return value of root of current tree.
   */
  public T getRoot() {
    return root.getValue();
  }

  /**
   * This method change root
   *
   * @param root - value of new root;
   */
  public void setRoot(T root) {
    this.root.setRoot(root);
    isSaveState = false;
  }

  /**
   * This method find, delete and returns a subTree.
   *
   * @param value - root of finding subtree.
   * @throws NullPointerException value == null.
   */
  public void deleteChild(T value, T... way) throws NullPointerException {
    if (value == null) throw new NullPointerException();
    Node<T> curr = root;
    isSaveState = false;
    for (int i = 1; i < way.length - 1; ++i) {
      curr = curr.getChild(way[i]);
    }
  }

  /**
   * Change iteration type.
   *
   * @param iterationType flag that choose iteration type.
   *                      can use DFS or BFS.
   */
  public void setIterationType(boolean iterationType) {
    this.iterationType = iterationType;
    isSaveState = false;
  }

  private void saveState() {
    isSaveState = true;
    root.saveState();
  }

  private boolean isChanged() {
    if (isSaveState) {
      return !root.isNotChanged();
    }
    return true;
  }

  private Iterator<T> dfs(List<Node<T>> queue) throws ConcurrentModificationException, NoSuchElementException {
    return new Iterator<T>() {
      public boolean hasNext() {
        return queue.size() > 0;
      }

      public T next() {
        if (isChanged()) throw new ConcurrentModificationException();
        if (queue.size() < 1) throw new NoSuchElementException();
        Node<T> curr = queue.remove(0);
        int currSize = curr.getChildren().size();
        int i = 0;
        for (Map.Entry<T,Node<T>> child : curr.getChildren().entrySet()) {
          queue.add(i, child.getValue());
          i++;
        }
        return curr.getValue();
      }
    };
  }

  private Iterator<T> bfs(List<Node<T>> queue) throws ConcurrentModificationException, NoSuchElementException {
    return new Iterator<T>() {
      public boolean hasNext() {
        return queue.size() > 0;
      }
      public T next() {
        if (isChanged()) throw new ConcurrentModificationException();
        if (queue.size() < 1) throw new NoSuchElementException();
        Node<T> next = queue.get(0);
        queue.addAll(next.getChildren().values());
        queue.remove(0);
        return next.getValue();
      }
    };
  }

  /**
   * This method returns Iterator.
   *
   * @return If iterationType == DFS returns iterator, working with DFS, else return BFS-iterator.
   * Iterator:
   * @throws NoSuchElementException          if you'll try take elements from empty iterator.
   * @throws ConcurrentModificationException if you'll try take element from iterator after tree's modification.
   */
  public Iterator<T> iterator() {
    saveState();
    List<Node<T>> queue = new LinkedList<>();
    queue.add(root);
    if (iterationType == DFS) {
      return dfs(queue);
    } else {
      return bfs(queue);
    }
  }
}