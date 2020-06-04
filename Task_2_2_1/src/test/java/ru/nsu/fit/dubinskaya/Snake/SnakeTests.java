package ru.nsu.fit.dubinskaya.Snake;


import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Test;
import ru.nsu.fit.dubinskaya.Snake.SnakeModel.Cell;
import ru.nsu.fit.dubinskaya.Snake.SnakeModel.SnakeGameModel;

public class SnakeTests {

    @Test
    public void eatApples(){
        SnakeGameModel snake = new SnakeGameModel(3, 4, 1, 30);
        Cell[] tail = snake.getTail();

        int x = (int)tail[0].getCoordinates().getKey();
        int y = (int)tail[0].getCoordinates().getValue();

        snake.setDir(SnakeGameModel.right);
        snake.locateFood(0, x+1, y);

        Cell[] food = snake.getFood();
        Assert.assertEquals(x+1, (int)food[0].getCoordinates().getKey());
        Assert.assertEquals(y, (int)food[0].getCoordinates().getValue());
        snake.move();
        Assert.assertNotEquals(food[0], new Pair<Integer, Integer>(x + 1, y));
    }

    @Test
    public void growAndWin(){
        SnakeGameModel snake = new SnakeGameModel(3, 4, 1, 30);
        Cell[] tail = snake.getTail();

        int x = (int)tail[0].getCoordinates().getKey();
        int y = (int)tail[0].getCoordinates().getValue();

        snake.setDir(SnakeGameModel.right);
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
        SnakeGameModel snake = new SnakeGameModel(3, 4, 0, 30);
        snake.setDir(SnakeGameModel.up);
        while((int)(snake.getTail())[0].getCoordinates().getValue() >= 0){
            Assert.assertFalse(snake.isLost());
            snake.move();
        }
        Assert.assertTrue(snake.isLost());

        //Down
        snake = new SnakeGameModel(3, 4, 0, 30);
        snake.setDir(SnakeGameModel.down);
        while((int)(snake.getTail())[0].getCoordinates().getValue() < 30){
            Assert.assertFalse(snake.isLost());
            snake.move();
        }
        Assert.assertTrue(snake.isLost());

        //right
        snake = new SnakeGameModel(3, 4, 0, 30);
        snake.setDir(SnakeGameModel.right);
        while((int)(snake.getTail())[0].getCoordinates().getKey() < 30){
            Assert.assertFalse(snake.isLost());
            snake.move();
        }
        Assert.assertTrue(snake.isLost());

        //left
        snake = new SnakeGameModel(3, 4, 0, 0);
        snake.setDir(SnakeGameModel.down);
        snake.move();
        snake.setDir(SnakeGameModel.left);
        snake.move();
        while((int)(snake.getTail())[0].getCoordinates().getKey() > 0){
            Assert.assertFalse(snake.isLost());
            snake.move();
        }
        Assert.assertTrue(snake.isLost());
    }

    @Test
    public void eatItsTail(){
        SnakeGameModel snake = new SnakeGameModel(8, 55, 0, 30);

        Assert.assertFalse(snake.isLost());
        snake.setDir(SnakeGameModel.down);
        snake.move();
        Assert.assertFalse(snake.isLost());

        snake.setDir(SnakeGameModel.left);
        snake.move();
        Assert.assertFalse(snake.isLost());

        snake.setDir(SnakeGameModel.up);
        snake.move();
        Assert.assertTrue(snake.isLost());
    }
}
