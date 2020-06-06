package ru.nsu.fit.dubinskaya.Snake;


import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Test;
import ru.nsu.fit.dubinskaya.Snake.SnakeModel.Cell;
import ru.nsu.fit.dubinskaya.Snake.SnakeModel.Snake;

public class SnakeTests {

    @Test
    public void eatApples(){
        Snake snake = new Snake(3, 4, 1, 30);
        Cell[] tail = snake.getTail();

        int x = (int)tail[0].getCoordinates().getKey();
        int y = (int)tail[0].getCoordinates().getValue();

        snake.setDir(Snake.right);
        snake.locateFood(0, x+1, y);

        Cell[] food = snake.getFood();
        Assert.assertEquals(x+1, (int)food[0].getCoordinates().getKey());
        Assert.assertEquals(y, (int)food[0].getCoordinates().getValue());
        snake.move();
        Assert.assertNotEquals(food[0], new Pair<Integer, Integer>(x + 1, y));
    }

    @Test
    public void growAndWin(){
        Snake snake = new Snake(3, 4, 1, 30);
        Cell[] tail = snake.getTail();

        int x = (int)tail[0].getCoordinates().getKey();
        int y = (int)tail[0].getCoordinates().getValue();

        snake.setDir(Snake.right);
        snake.locateFood(0, x+1, y);

        Cell[] food = snake.getFood();

        Assert.assertEquals(3, snake.getLength());
        snake.move();
        Assert.assertEquals(4, snake.getLength());
        Assert.assertTrue(snake.isWin());
    }

    @Test
    public void bumpIntoTheWalls(){

        //Up
        Snake snake = new Snake(3, 4, 0, 30);
        snake.setDir(Snake.up);
        while((int)(snake.getTail())[0].getCoordinates().getValue() >= 0){
            Assert.assertFalse(snake.isDead());
            snake.move();
        }
        Assert.assertTrue(snake.isDead());

        //Down
        snake = new Snake(3, 4, 0, 30);
        snake.setDir(Snake.down);
        while((int)(snake.getTail())[0].getCoordinates().getValue() < 30){
            Assert.assertFalse(snake.isDead());
            snake.move();
        }
        Assert.assertTrue(snake.isDead());

        //right
        snake = new Snake(3, 4, 0, 30);
        snake.setDir(Snake.right);
        while((int)(snake.getTail())[0].getCoordinates().getKey() < 30){
            Assert.assertFalse(snake.isDead());
            snake.move();
        }
        Assert.assertTrue(snake.isDead());

        //left
        snake = new Snake(3, 4, 0, 0);
        snake.setDir(Snake.down);
        snake.move();
        snake.setDir(Snake.left);
        snake.move();
        while((int)(snake.getTail())[0].getCoordinates().getKey() > 0){
            Assert.assertFalse(snake.isDead());
            snake.move();
        }
        Assert.assertTrue(snake.isDead());
    }

    @Test
    public void eatItsTail(){
        Snake snake = new Snake(8, 55, 0, 30);

        Assert.assertFalse(snake.isDead());
        snake.setDir(Snake.down);
        snake.move();
        Assert.assertFalse(snake.isDead());

        snake.setDir(Snake.left);
        snake.move();
        Assert.assertFalse(snake.isDead());

        snake.setDir(Snake.up);
        snake.move();
        Assert.assertTrue(snake.isDead());
    }
}
