package ru.nsu.fit.dubinskaya.Snake.SnakeModel;

import java.util.ArrayList;
import java.util.Iterator;

public class GameField {
  private final Boolean foodLock = true;
  private Snake snake;
  private int fieldSize;
  private int numberOfFood;
  private ArrayList<Cell> food;

  /**
   * GameField constructor.
   *
   * @param numberOfFood  - number of food on the field. When snakes eat a food
   *                      number of food doesn't decrease - new food generates on the field.
   * @param snakeStartLen - length of snake's tail in the beginning of the game.
   * @param fieldSize     - width and height of generated field.
   */
  public GameField(int numberOfFood, int snakeStartLen, int fieldSize, int startSnakeX, int startSnakeY) {
    snake = new Snake(snakeStartLen, fieldSize, startSnakeX, startSnakeY);

    food = new ArrayList<>();
    this.numberOfFood = numberOfFood;
    this.fieldSize = fieldSize;

    synchronized (foodLock) {
      for (int i = 0; i < numberOfFood; ++i) {
        food.add(i, new Cell());
        locateFood(i);
      }
    }
  }

  /**
   * This method used to move snake on the field.
   */
  public void move() {
    /* if we will set synchronized block only in snake itself, it will be useless, because UI thread can take
    tail iterator before snake grows.*/
    synchronized (snake.getTailLock()) {
      snake.move();
      int k = checkSnakeEatFood();
      if (k >= 0) {
        snake.grow();
        locateFood(k);
      }
    }
  }

  /**
   * this method used to get snake from the current field.
   *
   * @return generated snake.
   */
  public Snake getSnake() {
    return snake;
  }

  private void locateFood(int k) {
    synchronized (foodLock) {
      food.get(k).generateCoordinates(0, fieldSize);
      while (!isFoodCorrect(k)) {
        food.get(k).generateCoordinates(0, fieldSize);
      }
    }
  }

  /**
   * This method used to get food.
   *
   * @return Iterable with current food.
   */
  public Iterable<Cell> getFood() {
    synchronized (foodLock) {
      return (ArrayList<Cell>) food.clone();
    }
  }

  /**
   * This method used to get snake's tail.
   *
   * @return Iterable Cell with current snake's tail.
   */
  public Iterable<Cell> getTail() {
    return () -> snake.iterator();
  }

  private int checkSnakeEatFood() {
    Iterator iter = snake.iterator();
    Cell head = (Cell) iter.next();
    for (int i = 0; i < numberOfFood; ++i) {
      if (head.equals(food.get(i))) {
        return i;
      }
    }
    return -1;
  }

  private boolean isFoodCorrect(int k) {

    for (int i = 0; i < food.size(); ++i) {
      if (i == k) {
        continue;
      }
      if (food.get(i).equals(food.get(k))) {
        return false;
      }
    }
    for (Object o : snake) {
      if (o.equals(food.get(k))) {
        return false;
      }
    }
    return true;
  }
}
