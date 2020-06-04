package ru.nsu.fit.dubinskaya.Snake.Views;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Pair;
import ru.nsu.fit.dubinskaya.Snake.SnakeModel.Cell;

/**
 * This class is used to show Snake Game on the Stage
 */
public class SnakeView extends View {
    private GraphicsContext gc;
    private Cell[] food;
    private Cell[] snake;

    /**
     * This is SnakeView Constructor
     * @param gc - GraphicContext of canvas where snake's game will be shown
     * @param primaryStage - Stage where view should work
     */
    public SnakeView(GraphicsContext gc, Stage primaryStage) {
        this.gc = gc;
        setPrimaryStage(primaryStage);
    }

    /**
     * Used to set food of current snake
     * @param food - food on the field
     */
    public void setFood(Cell[] food) {
        this.food = food;
    }

    /**
     * Used to set tail of current snake
     * @param snake - snake on the field
     */
    public void setSnake(Cell[] snake) {
        this.snake = snake;
    }

    /**
     * Used to draw current game on the canvas
     * @param snakeLen - length of the current snake
     * @param isWin - is user win
     * @param isLost - is user fail
     * @param fieldSize - size of the field on the canvas
     * @param color - color of current field
     */
    public void draw(int snakeLen, boolean isWin, boolean isLost, int fieldSize, Color color) {
        final int size = 500;
        gc.setFont(Font.font("Verdana", 12));
        gc.clearRect(0, 0, size, size);

        int dotSize = (size - size*2/fieldSize)/fieldSize + 1;
        //this value we will use to set field in the center
        int delta = (495 - 2 - dotSize * fieldSize)/2 + 2;

        gc.setStroke(color);
        for (int i = 0; i < fieldSize; i += 2){
            for (int j = 1; j <=  fieldSize ; j += 1){
                gc.strokeRect(i*dotSize + delta, j*dotSize, dotSize, dotSize);
            }
        }

        for (Cell f : food) {
            gc.setFill(f.getColor());
            gc.fillOval( delta + (f.getCoordinates().getKey()) * dotSize,
                (1 + f.getCoordinates().getValue()) * dotSize, dotSize, dotSize);
        }

        if (isLost) {
            gc.setFill(new Color(0.88, 0.8, 0.309, 1));
            for (int i = 1; i < snakeLen; i += 1) {
                gc.fillOval(delta + (snake[i].getCoordinates().getKey()) * dotSize,
                    (1 + snake[i].getCoordinates().getValue()) * dotSize, dotSize, dotSize);
            }

            gc.setStroke(Color.GREEN);
            gc.setFont(Font.font("Verdana", 22));
            gc.strokeText("You die", 200, 250 - 15);

            gc.setFont(Font.font("Verdana", 12));
        }
        else {
            gc.setFill(new Color(0.74, 0.74, 1, 1));
            gc.setStroke(new Color(0.79, 0.79, 0.9, 1));

            gc.fillOval(delta + (snake[0].getCoordinates().getKey()) * dotSize,
                (1 + snake[0].getCoordinates().getValue()) * dotSize, dotSize, dotSize);

            for (int i = 1; i < snakeLen; i ++) {
                gc.setFill(snake[i].getColor());
                Pair<Integer, Integer> curr = snake[i].getCoordinates();
                if (snakeLen > 2)
                    curr = snake[i].getCoordinates();
                gc.fillOval(delta + (curr.getKey()) * dotSize, (1 + curr.getValue()) * dotSize, dotSize, dotSize);
                gc.strokeOval(delta + (curr.getKey()) * dotSize, (1 + curr.getValue()) * dotSize, dotSize, dotSize);
            }
        }

        gc.setStroke(Color.CORNFLOWERBLUE);
        gc.strokeRect( delta, dotSize, dotSize * fieldSize, dotSize * fieldSize);
        gc.strokeText(String.format("%d/%d", snakeLen, snake.length), 220 + delta/2, 10);

        if (isWin) {
            gc.setStroke(Color.GREEN);
            gc.setFont(Font.font("Verdana", 20));
            gc.strokeText("You win", 250 - 50, 250 - 15);
        }

    }
}
