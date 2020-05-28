package ru.nsu.fit.dubinskaya.Snake;

import javafx.util.Pair;

import java.util.Random;

class ModelSnake {
    static final int size = 500;
    static final int dotSize = 15;

    static final int up = 1, right = 2, down = 3, left = 4;
    private int length;
    private int winLength;
    private int dir = right;
    private int numberOfFood;
    private Pair[] food;
    private Pair[] tail;
    private boolean lost;

    ModelSnake(int startLen, int winSize, int numberOfFood) {
        length = startLen;
        winLength = winSize;
        tail = new Pair[winSize];
        food = new Pair[numberOfFood];
        this.numberOfFood = numberOfFood;
        for (int i = 0; i < length; i++) {
            tail[i] = (new Pair<>((size / dotSize * 7 - i * dotSize), 50));
        }

        for (int i = 0; i < numberOfFood; ++i) {
            locateFood(i);
        }
        checkCollision();
    }

    void setDir(int code) {
        if (code % 2 != dir % 2)
            dir = code;
    }

    void move() {
        if (lost) {
            return;
        }
        Pair currHead = tail[0];
        Pair nextHead = tail[0];
        if (dir == up) nextHead = new Pair<>(tail[0].getKey(), (Integer) tail[0].getValue() - dotSize);
        if (dir == down) nextHead = new Pair<>(tail[0].getKey(), (Integer) tail[0].getValue() + dotSize);
        if (dir == right) nextHead = new Pair<>((Integer) tail[0].getKey() + dotSize, tail[0].getValue());
        if (dir == left) nextHead = new Pair<>((Integer) tail[0].getKey() - dotSize, tail[0].getValue());

        tail[0] = nextHead;
        checkFood();
        tail[0] = currHead;
        if (length - 1 >= 0) System.arraycopy(tail, 0, tail, 1, length - 1);
        tail[0] = nextHead;
        checkCollision();
    }

    boolean isLost() {
        checkCollision();
        return lost;
    }

    boolean isWin() {
        checkCollision();
        return length == winLength && !lost;
    }

    Pair[] getTail() {
        return tail;
    }

    Pair[] getFood() {
        return food;
    }

    int getLength(){
        return length;
    }

    private void checkFood() {
        for (int i = 0; i < numberOfFood; ++i)
            if (tail[0].equals(food[i])) {
                length++;
                locateFood(i);
                break;
            }
    }

    private void checkCollision() {
        if (lost)
            return;
        if ((Integer) tail[0].getKey() >= size - 24 || (Integer) tail[0].getValue() >= size - 25) {
            lost = true;
            return;
        }
        if ((Integer) tail[0].getKey() < dotSize || (Integer) tail[0].getValue() < dotSize) {
            lost = true;
            return;
        }
        for (int i = 1; i < length; i++)
            if (tail[0].equals(tail[i])) {
                lost = true;
                return;
            }
    }

    private void locateFood(int k) {
        Random rand = new Random();
        int numberOfDots = (size - dotSize) / dotSize;
        food[k] = new Pair<>(rand.nextInt(numberOfDots - 3)*dotSize + 21, rand.nextInt(numberOfDots - 3)*dotSize + 20);
        checkFoodCollision(k);
    }

    private void checkFoodCollision(int k){
        for (int i = 0; i < numberOfFood; ++i) {
            if (i == k || food[i] == null)
                continue;
            if (food[i].equals(food[k]))
                locateFood(k);
        }
        for (int i = 0; i < length - 1; ++i) {
            if (tail[i].equals(food[k]))
                locateFood(k);
        }
    }

    void locateFood(int k, int x, int y){
        food[k] = new Pair<>(x, y);
        checkFoodCollision(k);
    }
}
