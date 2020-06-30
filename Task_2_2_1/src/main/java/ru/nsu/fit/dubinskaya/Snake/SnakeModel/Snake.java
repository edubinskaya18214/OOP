package ru.nsu.fit.dubinskaya.Snake.SnakeModel;


import java.util.ArrayList;
import java.util.Iterator;

public class Snake implements Iterable {

  public enum Direction {
    LEFT,
    RIGHT,
    UP,
    DOWN
  }

  private int length;
  private Direction dir = Direction.RIGHT;
  private ArrayList<Cell> tail;
  private boolean lost;
  private int fieldSize;
  private Cell savedTail;
  private final Boolean tailLock = true;

  /**
   * This is class constructor.
   *
   * @param startLen  - minimal length of the snake. Game will start with this snake's length.
   * @param fieldSize - size of the game's field
   */
  public Snake(int startLen, int fieldSize, int startX, int startY) {
    length = startLen;
    this.fieldSize = fieldSize;
    tail = new ArrayList<>();

    synchronized (tailLock) {
      for (int i = 0; i < length; i++) {
        tail.add(i, new Cell());
        tail.get(i).setCoordinates(startX - i, startY);
      }
    }
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
      case UP:
        if (dir == Direction.DOWN) {
          return true;
        }
        break;
      case DOWN:
        if (dir == Direction.UP) {
          return true;
        }
        break;
      case RIGHT:
        if (dir == Direction.LEFT) {
          return true;
        }
        break;
      case LEFT:
        if (dir == Direction.RIGHT) {
          return true;
        }
        break;
      default:
        return false;
    }
    return false;
  }

  void move() {

    int nextHeadX = tail.get(0).getX();
    int nextHeadY = tail.get(0).getY();
    switch (dir) {
      case UP:
        nextHeadY--;
        break;
      case DOWN:
        nextHeadY++;
        break;
      case RIGHT:
        nextHeadX++;
        break;
      case LEFT:
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
  public Iterator<Cell> iterator() {
    synchronized (tailLock) {
      ArrayList<Cell> currTail = (ArrayList<Cell>) tail.clone();
      return currTail.iterator();
    }
  }

  Boolean getTailLock() {
    return tailLock;
  }

  void grow() {
    if (savedTail != null) {
      tail.add(savedTail);
      savedTail = null;
      length++;
    }
  }
}
