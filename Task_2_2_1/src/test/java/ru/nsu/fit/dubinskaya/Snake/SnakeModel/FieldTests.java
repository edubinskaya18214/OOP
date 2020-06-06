package ru.nsu.fit.dubinskaya.Snake.SnakeModel;

import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;

public class FieldTests {

  @Test
  public void eatApples() {
    GameField field = new GameField(3 * 3 - 2, 1, 3);
    Snake snake = field.getSnake();
    Iterator food = field.getFoodIterator();

    Iterator iter = snake.iterator();
    Cell head = (Cell) iter.next();
    int x = (int) head.getX();
    int y = (int) head.getY();
    boolean hasFoodOnTheWay = false;
    Cell onWay = new Cell();
    onWay.setCoordinates(x + 1, y);

    while (food.hasNext()) {
      if (food.next().equals(onWay)) {
        hasFoodOnTheWay = true;
        break;
      }
    }

    Assert.assertTrue(hasFoodOnTheWay);

    field.move();
    hasFoodOnTheWay = false;
    while (food.hasNext()) {
      if (food.next().equals(onWay)) {
        hasFoodOnTheWay = true;
        break;
      }
    }
    Assert.assertFalse(hasFoodOnTheWay);
  }

  @Test
  public void isGrow() {
    GameField field = new GameField(5 * 5 - 2, 1, 5);
    Snake snake = field.getSnake();

    Iterator iter = snake.iterator();
    Cell head = (Cell) iter.next();

    int x = (int) head.getX();
    int y = (int) head.getY();

    Assert.assertEquals(1, snake.getLength());
    field.move();
    Assert.assertEquals(2, snake.getLength());
  }
}
