package ru.nsu.fit.dubinskaya.Snake.Views;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Pair;
import ru.nsu.fit.dubinskaya.Snake.Controllers.HelperController;
import ru.nsu.fit.dubinskaya.Snake.Controllers.MenuController;
import ru.nsu.fit.dubinskaya.Snake.ModelSnake;

public class SnakeView extends View {
    private GraphicsContext gc;
    private Pair<Integer, Integer>[] food;
    private Pair<Integer, Integer>[] snake;

    public SnakeView(GraphicsContext gc, Stage primaryStage) {
        this.gc = gc;
        setPrimaryStage(primaryStage);
    }

    public void setFood(Pair[] food) {
        this.food = food;
    }

    public void setSnake(Pair[] snake) {
        this.snake = snake;
    }


    public void draw(int snakeLen, boolean isWin, boolean isLost) {
        gc.setFont(Font.font("Verdana", 12));
        final int size = 500;
        gc.clearRect(0, 0, size, size);
        gc.setFill(Color.FORESTGREEN);
        int dotSize = 15;
        int delta = 5;
        for (Pair<Integer, Integer> pair : food) {
            gc.fillOval((2 + pair.getKey()) * dotSize - delta, (2 + pair.getValue()) * dotSize - delta, dotSize, dotSize);
        }

        gc.setFill(Color.BLUE);
        gc.fillOval((2 + snake[0].getKey()) * dotSize - delta, (2 + snake[0].getValue()) * dotSize - delta, dotSize, dotSize);
        gc.setFill(Color.DEEPSKYBLUE);
        gc.setStroke(Color.CORNFLOWERBLUE);

        for (int i = 1; i < snakeLen; i += 2) {
            gc.fillOval((2 + snake[i].getKey()) * dotSize - delta, (2 + snake[i].getValue()) * dotSize - delta, dotSize, dotSize);
        }
        for (int i = 2; i < snakeLen; i += 2) {
            gc.strokeOval((2 + snake[i].getKey()) * dotSize - delta, (2 + snake[i].getValue()) * dotSize - delta, dotSize, dotSize);
        }

        gc.strokeRect(dotSize, dotSize, size - dotSize * 2, size - dotSize * 2);
        gc.strokeText(String.format("%d/%d", snakeLen, 25), 220, 10);

        if (isWin) {
            gc.setStroke(Color.GREEN);
            gc.setFont(Font.font("Verdana", 20));
            gc.strokeText("You win", 250 - 50, 250 - 15);
            return;
        }

        if (isLost) {
            gc.setStroke(Color.GREEN);
            gc.setFont(Font.font("Verdana", 22));
            gc.strokeText("You die", 200, 250 - 15);
        }
    }
}
