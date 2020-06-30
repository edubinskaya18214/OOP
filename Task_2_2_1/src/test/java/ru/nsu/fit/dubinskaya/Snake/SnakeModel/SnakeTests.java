package ru.nsu.fit.dubinskaya.Snake.SnakeModel;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class SnakeTests {

  @Test
  public void isMove() {
    Snake snake = new Snake(1, 5,2,2);

    List<Cell> tail = (List<Cell>)snake.getTail();
    Cell head = tail.get(0);
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
    List<Cell> tail = (List<Cell>)snake.getTail();
    Cell head = tail.get(0);

    while ((int) (head.getY()) >= 0) {
      Assert.assertFalse(snake.isDead());
      snake.move();
    }
    Assert.assertTrue(snake.isDead());

    //Down
    snake = new Snake(3, 4,2,2);
    snake.setDir(Snake.Direction.DOWN);
    tail = (List<Cell>)snake.getTail();
    head = tail.get(0);

    while ((int) (head.getY()) < 4) {
      Assert.assertFalse(snake.isDead());
      snake.move();
    }
    Assert.assertTrue(snake.isDead());

    //right
    snake = new Snake(3, 4,2,2);
    snake.setDir(Snake.Direction.RIGHT);
    tail = (List<Cell>)snake.getTail();
    head = tail.get(0);

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
