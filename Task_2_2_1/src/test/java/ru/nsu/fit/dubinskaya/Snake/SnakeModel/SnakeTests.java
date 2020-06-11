package ru.nsu.fit.dubinskaya.Snake.SnakeModel;

import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;

public class SnakeTests {

  @Test
  public void isMove() {
    Snake snake = new Snake(1, 5,2,2);

    Iterator<Cell> iter = snake.iterator();
    Cell head = iter.next();
    final int x = head.getX();
    final int y = head.getY();

    snake.move();

    Assert.assertEquals(x + 1, head.getX());
    Assert.assertEquals(y, head.getY());
  }

  @Test
  public void bumpIntoTheWalls() {

    //Up
    Snake snake = new Snake(3, 4,2,2);
    snake.setDir(Snake.Direction.UP);
    Iterator iter = snake.iterator();
    Cell head = (Cell) iter.next();

    while ((int) (head.getY()) >= 0) {
      Assert.assertFalse(snake.isDead());
      snake.move();
    }
    Assert.assertTrue(snake.isDead());

    //Down
    snake = new Snake(3, 4,2,2);
    snake.setDir(Snake.Direction.DOWN);
    iter = snake.iterator();
    head = (Cell) iter.next();

    while ((int) (head.getY()) < 4) {
      Assert.assertFalse(snake.isDead());
      snake.move();
    }
    Assert.assertTrue(snake.isDead());

    //right
    snake = new Snake(3, 4,2,2);
    snake.setDir(Snake.Direction.RIGHT);
    iter = snake.iterator();
    head = (Cell) iter.next();

    while ((int) head.getX() < 4) {
      Assert.assertFalse(snake.isDead());
      snake.move();
    }
    Assert.assertTrue(snake.isDead());
  }

  @Test
  public void eatItsTail() {
    Snake snake = new Snake(8, 55,2,2);

    Assert.assertFalse(snake.isDead());
    snake.setDir(Snake.Direction.DOWN);
    snake.move();
    Assert.assertFalse(snake.isDead());

    snake.setDir(Snake.Direction.LEFT);
    snake.move();
    Assert.assertFalse(snake.isDead());

    snake.setDir(Snake.Direction.UP);
    snake.move();
    Assert.assertTrue(snake.isDead());
  }
}
