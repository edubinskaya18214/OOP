package ru.nsu.fit.dubinskaya.Snake.SnakeModel;

import java.util.Random;

/**
 * This class is used in SnakeGameModel and SnakeView
 * for containing coordinates and colors of food and snake's tail
 */
public class Cell {
    private int x;
    private int y;

    /**
     * generate random coordinates for this sell.
     * @param from - minimal generated coordinate
     * @param to - maximal generated coordinate.
     */
    public void generateCoordinates(int from, int to){
        Random rand = new Random();
        x = rand.nextInt(to-from) + from;
        y = rand.nextInt(to-from) + from;
    }

    /**
     * set new coordinates as coordinates of this sell
     * @param newX - new value for x coordinate.
     * @param newY - new value for y coordinate.
     */
    public void setCoordinates(int newX, int newY) {
        x = newX;
        y = newY;
    }

    /**
     * this method used to get x coordinate of this cell.
     * @return field coordinate;
     */
    public int getX(){
        return x;
    }

    /**
     * this method used to get y coordinate of this cell.
     * @return field coordinate;
     */
    public int getY(){
        return y;
    }

    public boolean equals(Cell cell){
        return (x == cell.getX() && y == cell.getY());
    }
}
