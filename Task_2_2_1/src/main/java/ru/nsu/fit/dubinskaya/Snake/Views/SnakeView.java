package ru.nsu.fit.dubinskaya.Snake.Views;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Pair;
import ru.nsu.fit.dubinskaya.Snake.SnakeModel.Cell;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/**
 * This class is used to show Snake Game on the Stage
 */
public class SnakeView extends View {
    private GraphicsContext gc;
    private int winSize;

    /**
     * This is SnakeView Constructor
     * @param gc - GraphicContext of canvas where snake's game will be shown
     * @param primaryStage - Stage where view should work
     */
    public SnakeView(GraphicsContext gc, Stage primaryStage) {
        this.gc = gc;
        setPrimaryStage(primaryStage);
    }

    public void setWinSize(int winSize){
        this.winSize = winSize;
    }

    /**
     * Used to draw current game on the canvas
     * @param isWin - is user win
     * @param isLost - is user fail
     * @param fieldSize - size of the field on the canvas
     */
    public void draw(boolean isWin, boolean isLost, int fieldSize, Iterator<Cell> tail, Iterator<Cell> food) {
        final int size = 500;
        int snakeSize = 1;
        gc.setFont(Font.font("Verdana", 12));
        gc.clearRect(0, 0, size, size);

        int dotSize = (size - size*2/fieldSize)/fieldSize + 1;
        //this value we will use to set field in the center
        int delta = (495 - 2 - dotSize * fieldSize)/2 + 2;

        for (int i = 0; i < fieldSize; i += 1){
            for (int j = 1; j <=  fieldSize ; j += 1){
                if ((j+i)%2 == 1) gc.setFill(new Color(0.968, 0.968, 0.8, 0.7));
                else gc.setFill(new Color(0.8, 0.9, 0.75, 0.7));
                gc.fillRect(i*dotSize + delta, j*dotSize, dotSize, dotSize);
            }
        }

        gc.setFill(Color.FORESTGREEN);
        while(food.hasNext()) {
            Cell curr = food.next();
            gc.fillOval( delta + (curr.getX()) * dotSize,
                (1 + curr.getY()) * dotSize, dotSize, dotSize);
        }

        if (isLost) {
            gc.setFill(new Color(0.88, 0.8, 0.309, 1));
            //skip head
            tail.next();
            while(tail.hasNext()){
                snakeSize++;
                Cell curr = (Cell) tail.next();
                gc.fillOval(delta + (curr.getX()) * dotSize,
                    (1 + curr.getY()) * dotSize, dotSize, dotSize);
            }

            gc.setStroke(Color.ALICEBLUE);
            gc.setFont(Font.font("Verdana", 30));
            gc.setFill(Color.DARKSLATEBLUE);
            gc.fillText("You die", 200, 250 - 15);
            gc.strokeText("You die", 200, 250 - 15);

            gc.setFont(Font.font("Verdana", 12));
        }
        else {
            gc.setFill(Color.GREENYELLOW);
            gc.setStroke(new Color(0.79, 0.79, 0.9, 1));
            Cell curr = (Cell) tail.next();
            gc.fillOval(delta + (curr.getX()) * dotSize,
                (1 + curr.getY()) * dotSize, dotSize, dotSize);

            gc.setFill(Color.DARKOLIVEGREEN);
            gc.fillOval(delta + (0.25 +curr.getX()) * dotSize,
                (1.25 + curr.getY()) * dotSize, dotSize/2, dotSize/2);

            while(tail.hasNext()) {
                snakeSize++;
                curr = (Cell) tail.next();
                gc.setFill(Color.DARKOLIVEGREEN);
                int x = curr.getX();
                int y = curr.getY();
                gc.fillOval(delta + x * dotSize, (1 + y) * dotSize, dotSize, dotSize);
                gc.setFill(Color.GREENYELLOW);
                gc.fillOval(delta + (x + 0.25) * dotSize, (1.25 + y) * dotSize, dotSize/2, dotSize/2);
            }
        }

        gc.setStroke(Color.CORNFLOWERBLUE);
        gc.strokeRect( delta, dotSize, dotSize * fieldSize, dotSize * fieldSize);
        gc.strokeText(String.format("%d/%d", snakeSize, winSize), 220 + delta/2, 10);

        if (isWin) {
            gc.setFill(Color.DARKSLATEBLUE);
            gc.setStroke(Color.ALICEBLUE);
            gc.setFont(Font.font("Verdana", 30));
            gc.fillText("You win", 250 - 50, 250 - 15);
            gc.strokeText("You win", 250 - 50, 250 - 15);
        }

    }
}
