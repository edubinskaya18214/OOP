package ru.nsu.fit.dubinskaya.Snake.SnakeModel;

import java.util.ArrayList;
import java.util.Iterator;

public class GameField {
    private Snake snake;
    private int fieldSize;
    private int numberOfFood;
    private ArrayList<Cell> food;

    public GameField(int numberOfFood, int snakeStartLen, int fieldSize){
        snake = new Snake(snakeStartLen, fieldSize);

        food = new ArrayList<>();
        this.numberOfFood = numberOfFood;
        this.fieldSize = fieldSize;

        for (int i = 0; i < numberOfFood; ++i) {
            food.add(i, new Cell());
            food.get(i).generateCoordinates(0, fieldSize);
            checkFoodCorrectness(i);
        }
    }

    public void move(){
        snake.move();
        int k = checkSnakeEatFood();
        if (k >= 0){
            snake.grow();
            locateFood(k);
        }
    }

    public Snake getSnake(){
        return snake;
    }

    private int checkSnakeEatFood() {
        Iterator iter = snake.iterator();
        Cell head = (Cell) iter.next();
        for (int i = 0; i < numberOfFood; ++i)
            if (head.equals(food.get(i))) {
                return i;
            }
        return -1;
    }

    private void locateFood(int k) {
        food.get(k).generateCoordinates(0, fieldSize);
        checkFoodCorrectness(k);
    }

    private void checkFoodCorrectness(int k) {

        for (int i = 0; i < food.size(); ++i) {
            if (i == k)
                continue;
            if (food.get(i).equals(food.get(k)))
                locateFood(k);
        }
        Iterator iter = snake.iterator();
        while(iter.hasNext()){
            if (iter.next().equals(food.get(k)))
                locateFood(k);
        }
    }

    public void locateFood(int k, int x, int y) {
        food.get(k).setCoordinates(x, y);
        checkFoodCorrectness(k);
    }

    public Iterator<Cell> getFoodIterator(){
        return new Iterator<Cell>(){
            int pos = 0;
            @Override
            public boolean hasNext() {
                return pos < food.size();
            }

            @Override
            public Cell next() {
                return food.get(pos++);
            }
        };
    }

    public Iterator getSnakeIterator(){
        return snake.iterator();
    }
}
