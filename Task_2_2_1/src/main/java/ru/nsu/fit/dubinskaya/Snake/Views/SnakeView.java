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


    public void draw(int snakeLen, boolean isWin, boolean isLost, int fieldSize, Color color) {
        gc.setFont(Font.font("Verdana", 12));
        final int size = 500;
        gc.clearRect(0, 0, size, size);
        int dotSize = (size - size*2/fieldSize)/fieldSize + 1;

        gc.setStroke(color);
        for (int i = 0; i < fieldSize; i += 2){
            for (int j = 1; j <=  fieldSize ; j += 1){
                gc.strokeRect(i*dotSize+2, j*dotSize, dotSize, dotSize);
            }
        }

        gc.setFill(new Color(1, 0.3, 0.3, 1));
        for (Pair<Integer, Integer> pair : food) {
            gc.fillOval( 2 + (pair.getKey()) * dotSize, (1 + pair.getValue()) * dotSize, dotSize, dotSize);
        }

        if (isLost) {
            gc.setFill(new Color(0.88, 0.8, 0.309, 1));
            for (int i = 1; i < snakeLen; i += 1) {
                gc.fillOval(2 + (snake[i].getKey()) * dotSize, (1 + snake[i].getValue()) * dotSize, dotSize, dotSize);
            }

            gc.setStroke(Color.GREEN);
            gc.setFont(Font.font("Verdana", 22));
            gc.strokeText("You die", 200, 250 - 15);

            gc.setFont(Font.font("Verdana", 12));
        }
        else {
            gc.setFill(new Color(0.74, 0.74, 1, 1));
            gc.setStroke(new Color(0.79, 0.79, 0.9, 1));
            gc.fillOval(2 + (snake[0].getKey()) * dotSize, (1 + snake[0].getValue()) * dotSize, dotSize, dotSize);
            gc.setFill(new Color(0.85, 0.75, 0.9, 0.9));
            for (int i = 1; i < snakeLen; i += 1) {
                gc.fillOval(2 + (snake[i].getKey()) * dotSize, (1 + snake[i].getValue()) * dotSize, dotSize, dotSize);
                gc.strokeOval(2 + (snake[i].getKey()) * dotSize, (1 + snake[i].getValue()) * dotSize, dotSize, dotSize);
            }
        }

        gc.setStroke(Color.CORNFLOWERBLUE);
        gc.strokeRect(2, dotSize, dotSize * fieldSize, dotSize * fieldSize);
        gc.strokeText(String.format("%d/%d", snakeLen, snake.length), 220, 10);

        if (isWin) {
            gc.setStroke(Color.GREEN);
            gc.setFont(Font.font("Verdana", 20));
            gc.strokeText("You win", 250 - 50, 250 - 15);
        }

    }
}
