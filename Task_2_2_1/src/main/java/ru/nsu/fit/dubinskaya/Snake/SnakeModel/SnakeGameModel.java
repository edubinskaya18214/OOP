package ru.nsu.fit.dubinskaya.Snake.SnakeModel;

import javafx.scene.paint.Color;
import javafx.util.Pair;

import java.util.Random;

public class SnakeGameModel {
    public static final int up = 1, right = 2, down = 3, left = 4;

    private int length;
    private int winLength;
    private int dir = right;
    private int numberOfFood;
    private Cell[] food;
    private Cell[] tail;
    private boolean lost;
    private int fieldSize;

    /**
     * This is class constructor
     * @param startLen - minimal length of the snake. Game will start with this snake's length.
     * @param winLen - maximal length of the snake. Game will finish when snake will grow till this size;
     * @param numberOfFood - number of food on the field.
     * @param fieldSize - number of sells on the field.
     */
    public SnakeGameModel(int startLen, int winLen, int numberOfFood, int fieldSize) {
        length = startLen;
        this.fieldSize = fieldSize;
        winLength = winLen;
        tail = new Cell[winLen];
        food = new Cell[numberOfFood];
        this.numberOfFood = numberOfFood;

        for (int i = 0; i < length; i++) {
            tail[i] = new Cell();
            tail[i].setColor(Color.AZURE);
            tail[i].setCoordinates(new Pair<Integer, Integer>((int)(fieldSize/2) - i, 3));
        }

        for (int i = 0; i < numberOfFood; ++i){
            food[i] = new Cell();
            food[i].generateCoordinates(0, fieldSize);
            food[i].generateColor();
            checkFoodCollision(i);
        }

        checkCollision();
    }

    /**
     * This function will change snake direction
     * @param code - code of the direction. Use SnakeGameModel.up / down / right / left.
     */
    public void setDir(int code) {
        if (code % 2 != dir % 2)
            dir = code;
    }

    /**
     * This function move snake in order to direction.
     * If on the snake's way will be an apple, it grows up.
     */
    public void move() {
        if (lost) {
            return;
        }
        Pair nextHead = tail[0].getCoordinates();

        switch (dir){
            case up:
                nextHead = new Pair<>(nextHead.getKey(), (Integer)nextHead.getValue() - 1);
                break;
            case down:
                nextHead = new Pair<>(nextHead.getKey(), (Integer) nextHead.getValue() + 1);
                break;
            case right:
                nextHead = new Pair<>((Integer)nextHead.getKey() + 1, nextHead.getValue());
                break;
            case left:
                nextHead = new Pair<>((Integer) nextHead.getKey() - 1, nextHead.getValue());
                break;
        }

        Pair currHead = tail[0].getCoordinates();

        tail[0].setCoordinates(nextHead);
        checkFood();
        tail[0].setCoordinates(currHead);

        for (int i = length-1; i > 0; --i){
            tail[i].setCoordinates(tail[i-1].getCoordinates());
        }
        tail[0].setCoordinates(nextHead);
        checkCollision();
    }

    /**
     * This method used to check snake is dead.
     * @return true if snake is dead.
     */
    public boolean isLost() {
        checkCollision();
        return lost;
    }

    /**
     * This method is used to check snake grows up till the win size.
     * @return true if user win.
     */
    public boolean isWin() {
        checkCollision();
        return length == winLength && !lost;
    }

    /**
     * this method used to get private field "tail"
     * @return snake's tail
     */
    public Cell[] getTail() {
        return tail;
    }

    /**
     * this method used to get private field "food"
     * @return food on the field
     */
    public Cell[] getFood() {
        return food;
    }

    /**
     * this method is used to get current length of the snake
     * @return snake's length
     */
    public int getLength(){
        return length;
    }

    private void checkFood() {
        for (int i = 0; i < numberOfFood; ++i)
            if (tail[0].getCoordinates().equals(food[i].getCoordinates())) {
                length++;
                tail[length-1] = new Cell();
                tail[length-1].setColor(food[i].getColor());
                locateFood(i);
                break;
            }
    }

    private void checkCollision() {
        if (lost)
            return;

        Pair<Integer, Integer> head = (Pair<Integer, Integer>)tail[0].getCoordinates();
        if (head.getKey() >= fieldSize || head.getValue() >= fieldSize) {
            lost = true;
            return;
        }

        if (head.getKey() < 0 || head.getValue() < 0) {
            lost = true;
            return;
        }

        for (int i = 1; i < length; i++)
            if (tail[0].getCoordinates().equals(tail[i].getCoordinates())) {
                lost = true;
                return;
            }
    }

    private void locateFood(int k) {
        Random rand = new Random();
        food[k].setCoordinates( new Pair<>(rand.nextInt(fieldSize - 1), rand.nextInt(fieldSize - 1)));
        checkFoodCollision(k);
    }

    private void checkFoodCollision(int k){
        for (int i = 0; i < numberOfFood; ++i) {
            if (i == k || food[i] == null)
                continue;
            if (food[i].getCoordinates().equals(food[k].getCoordinates()))
                locateFood(k);
        }
        for (int i = 0; i < length - 1; ++i) {
            if (tail[i].equals(food[k]))
                locateFood(k);
        }
    }

    public void locateFood(int k, int x, int y){
        food[k].setCoordinates(new Pair<Integer, Integer>(x, y));
        checkFoodCollision(k);
    }
}
