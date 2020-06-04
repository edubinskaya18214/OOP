package ru.nsu.fit.dubinskaya.Snake.SnakeModel;

import javafx.scene.paint.Color;
import javafx.util.Pair;

import java.util.Random;

import static java.lang.StrictMath.abs;

/**
 * This class is used in SnakeGameModel and SnakeView
 * for containing coordinates and colors of food and snake's tail
 */
public class Cell {
    private Pair<Integer, Integer> coordinates;
    private Color color;

    /**
     * generate random coordinates for this sell.
     * @param from - minimal generated coordinate
     * @param to - maximal generated coordinate.
     */
    public void generateCoordinates(int from, int to){
        Random rand = new Random();
        coordinates = new Pair<Integer, Integer>(rand.nextInt(to-from) + from, rand.nextInt(to-from) + from);
    }

    /**
     * set new coordinates as coordinates of this sell
     * @param newCoordinates - pair which will be setted as coordinates, not copy of this Pair.
     */
    public void setCoordinates(Pair<Integer, Integer> newCoordinates){
        coordinates = newCoordinates;
    }

    /**
     * this method used to get private field "coordinates"
     * @return field coordinates;
     */
    public Pair<Integer, Integer> getCoordinates(){
        return coordinates;
    }

    /**
     * generate random Color for this cell
     */
    public void generateColor(){
        Random rand = new Random();
        float r = rand.nextInt(255);
        float g = rand.nextInt(255);
        float b = rand.nextInt(255);
        color = new Color(r/255, g/255, b/255, 1);
    }

    /**
     * this method used to set private field "color"
     * @param color - new value for field "color"
     */
    public void setColor(Color color){
        this.color = color;
    }

    /**
     * this method used to get private field "Color"
     * @return Color
     */
    public Color getColor(){
        return color;
    }
}
