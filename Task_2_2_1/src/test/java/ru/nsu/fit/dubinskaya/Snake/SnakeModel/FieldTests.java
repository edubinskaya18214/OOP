package ru.nsu.fit.dubinskaya.Snake.SnakeModel;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class FieldTests {

  @Test
  public void eatApples() {
    GameField field = new GameField(3 * 3 - 2, 1, 3, 1,1);
    Snake snake = field.getSnake();
    Iterable<Cell> food = field.getFood();
    List<Cell> tail = (List<Cell>)snake.getTail();
    Cell head = tail.get(0);
    int x = (int) head.getX();
    int y = (int) head.getY();
    boolean hasFoodOnTheWay = false;
    Cell onWay = new Cell();
    onWay.setCoordinates(x + 1, y);

    for (Cell f : food) {
      if (f.equals(onWay)) {
        hasFoodOnTheWay = true;
        break;
      }
    }

    Assert.assertTrue(hasFoodOnTheWay);
    field.move();
    hasFoodOnTheWay = false;
    food = field.getFood();

    for (Cell f : food) {
      if (f.equals(onWay)) {
        hasFoodOnTheWay = true;
        break;
      }
    }
    Assert.assertFalse(hasFoodOnTheWay);
  }

  @Test
  public void isGrow() {
    GameField field = new GameField(5 * 5 - 2, 1, 5, 2,2);
    Snake snake = field.getSnake();
    List<Cell> tail = (List<Cell>)snake.getTail();
    Cell head = tail.get(0);

    int x = (int) head.getX();
    int y = (int) head.getY();

    Assert.assertEquals(1, snake.getLength());
    field.move();
    Assert.assertEquals(2, snake.getLength());
  }
}
