package ru.nsu.fit.dubinskaya.Snake;


import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Test;

public class SnakeTests {

    @Test
    public void eatApples(){
        ModelSnake snake = new ModelSnake(3, 4, 1);
        Pair[] tail = snake.getTail();

        int x = (int)tail[0].getKey();
        int y = (int)tail[0].getValue();

        snake.setDir(ModelSnake.right);
        snake.locateFood(0, x+ModelSnake.dotSize, y);

        Pair[] food = snake.getFood();
        Assert.assertEquals(x+ModelSnake.dotSize, food[0].getKey());
        Assert.assertEquals(y, food[0].getValue());
        snake.move();
        Assert.assertNotEquals(food[0], new Pair<Integer, Integer>(x + ModelSnake.dotSize, y));
    }

    @Test
    public void growAndWin(){
        ModelSnake snake = new ModelSnake(3, 4, 1);
        Pair[] tail = snake.getTail();

        int x = (int)tail[0].getKey();
        int y = (int)tail[0].getValue();

        snake.setDir(ModelSnake.right);
        snake.locateFood(0, x+ModelSnake.dotSize, y);

        Pair[] food = snake.getFood();

        Assert.assertEquals(3, snake.getLength());
        snake.move();
        Assert.assertEquals(4, snake.getLength());
        Assert.assertTrue(snake.isWin());
    }

    @Test
    public void bumpIntoTheWalls(){

        //Up
        ModelSnake snake = new ModelSnake(3, 4, 0);
        snake.setDir(ModelSnake.up);
        Assert.assertFalse(snake.isLost());
        snake.move();
        Assert.assertFalse(snake.isLost());
        snake.move();
        Assert.assertFalse(snake.isLost());
        snake.move();
        Assert.assertTrue(snake.isLost());

        //Down
        snake = new ModelSnake(3, 4, 0);
        snake.setDir(ModelSnake.down);
        while((int)(snake.getTail())[0].getValue() < ModelSnake.size - 25){
            Assert.assertFalse(snake.isLost());
            snake.move();
        }
        Assert.assertTrue(snake.isLost());

        //right
        snake = new ModelSnake(3, 4, 0);
        snake.setDir(ModelSnake.right);
        while((int)(snake.getTail())[0].getKey() < ModelSnake.size - 24){
            Assert.assertFalse(snake.isLost());
            snake.move();
        }
        Assert.assertTrue(snake.isLost());

        //left
        snake = new ModelSnake(3, 4, 0);
        snake.setDir(ModelSnake.down);
        snake.move();
        snake.setDir(ModelSnake.left);
        snake.move();
        while((int)(snake.getTail())[0].getKey() > ModelSnake.dotSize){
            Assert.assertFalse(snake.isLost());
            snake.move();
        }
        Assert.assertTrue(snake.isLost());
    }

    @Test
    public void eatItsTail(){
        ModelSnake snake = new ModelSnake(8, 55, 0);

        Assert.assertFalse(snake.isLost());
        snake.setDir(ModelSnake.down);
        snake.move();
        Assert.assertFalse(snake.isLost());

        snake.setDir(ModelSnake.left);
        snake.move();
        Assert.assertFalse(snake.isLost());

        snake.setDir(ModelSnake.up);
        snake.move();
        Assert.assertTrue(snake.isLost());
    }
}
