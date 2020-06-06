package ru.nsu.fit.dubinskaya.Snake.SnakeModel;


import java.util.*;

public class Snake implements Iterable {

  public enum Direction {
    left, right, up, down
  }

  private int length;
  private Direction dir = Direction.right;
  private ArrayList<Cell> tail;
  private boolean lost;
  private int fieldSize;
  private Cell savedTail;

  /**
   * This is class constructor.
   *
   * @param startLen  - minimal length of the snake. Game will start with this snake's length.
   * @param fieldSize - size of the game's field
   */
  public Snake(int startLen, int fieldSize) {
    length = startLen;
    this.fieldSize = fieldSize;
    tail = new ArrayList<>();

    for (int i = 0; i < length; i++) {
      tail.add(i, new Cell());
      tail.get(i).setCoordinates((int) (fieldSize / 2) - i, 3);
    }

    isDead();
  }

  /**
   * This function will change snake direction.
   *
   * @param newDirection - direction which will be setted as new snake's direction.
   */
  public void setDir(Direction newDirection) {
    if (!isOpposite(newDirection)) {
      dir = newDirection;
    }
  }

  private boolean isOpposite(Direction dir1) {
    switch (dir1) {
      case up:
        if (dir == Direction.down) {
          return true;
        }
        break;
      case down:
        if (dir == Direction.up) {
          return true;
        }
        break;
      case right:
        if (dir == Direction.left) {
          return true;
        }
        break;
      case left:
        if (dir == Direction.right) {
          return true;
        }
        break;
      default:
        return false;
    }
    return false;
  }

  /**
   * This function move snake in order to direction.
   * If on the snake's way will be an apple, it grows up.
   */
  public void move() {
    if (lost) {
      return;
    }
    int nextHeadX = tail.get(0).getX();
    int nextHeadY = tail.get(0).getY();
    switch (dir) {
      case up:
        nextHeadY--;
        break;
      case down:
        nextHeadY++;
        break;
      case right:
        nextHeadX++;
        break;
      case left:
        nextHeadX--;
        break;
      default:
        break;
    }

    savedTail = new Cell();
    savedTail.setCoordinates(tail.get(tail.size() - 1).getX(), tail.get(tail.size() - 1).getY());

    for (int i = length - 1; i > 0; --i) {
      tail.get(i).setCoordinates(tail.get(i - 1).getX(), tail.get(i - 1).getY());
    }
    tail.get(0).setCoordinates(nextHeadX, nextHeadY);
    isDead();
  }

  /**
   * This method used to check snake is dead.
   *
   * @return true if snake is dead.
   */
  public boolean isDead() {
    if (lost) {
      return true;
    }

    int headX = tail.get(0).getX();
    int headY = tail.get(0).getY();
    if (headX >= fieldSize || headY >= fieldSize) {
      lost = true;
      return true;
    }

    if (headX < 0 || headY < 0) {
      lost = true;
      return true;
    }

    for (int i = 1; i < length; i++) {
      if (tail.get(0).equals(tail.get(i))) {
        lost = true;
        return true;
      }
    }
    return lost;
  }

  /**
   * this method is used to get current length of the snake.
   *
   * @return snake's length
   */
  public int getLength() {
    return length;
  }

  @Override
  public Iterator iterator() {
    return new Iterator() {
      int pos = 0;

      @Override
      public boolean hasNext() {
        return pos < tail.size();
      }

      @Override
      public Cell next() {
        return tail.get(pos++);
      }
    };
  }

  void grow() {
    if (savedTail != null) {
      tail.add(savedTail);
      savedTail = null;
      length++;
    }
  }
}
