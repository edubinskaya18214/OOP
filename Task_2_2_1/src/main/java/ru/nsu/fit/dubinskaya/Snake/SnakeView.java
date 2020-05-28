package ru.nsu.fit.dubinskaya.Snake;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Pair;

class SnakeView {
    private GraphicsContext gc;
    private Stage primaryStage;
    private HelperController help;
    private MainMenuController menu;
    private Pair[] food;
    private Pair[] snake;

    SnakeView(GraphicsContext gc, Stage primaryStage){
        this.gc = gc;
        this.primaryStage = primaryStage;
    }

    void setFood(Pair[] food){
        this.food = food;
    }

    void setSnake(Pair[] snake){
        this.snake = snake;
    }

    void draw(int snakeLen, boolean isWin, boolean isLost) {
        gc.setFont(Font.font("Verdana", 12));
        int size = 500;
        gc.clearRect(0, 0, size, size);
        gc.setFill(Color.FORESTGREEN);
        int dotSize = 15;
        int foodNumber = food.length;

        for (Pair pair : food) {
            gc.fillOval((Integer) pair.getKey(), (Integer) pair.getValue(), dotSize, dotSize);
        }

        gc.setFill(Color.BLUE);
        gc.fillOval((Integer) snake[0].getKey(), (Integer) snake[0].getValue(), dotSize, dotSize);
        gc.setFill(Color.DEEPSKYBLUE);
        gc.setStroke(Color.CORNFLOWERBLUE);

        for (int i = 1; i < snakeLen; i += 2) {
            gc.fillOval((Integer) snake[i].getKey(), (Integer) snake[i].getValue(), dotSize, dotSize);
        }
        for (int i = 2; i < snakeLen; i += 2) {
            gc.strokeOval((Integer) snake[i].getKey(), (Integer) snake[i].getValue(), dotSize, dotSize);
        }

        gc.strokeRect(dotSize, dotSize, size - dotSize * 2, size - dotSize * 2);
        gc.strokeText(String.format("%d/%d", snakeLen, 25), size / 2 - 30, 10);

        if (isWin) {
            gc.setStroke(Color.GREEN);
            gc.setFont(Font.font("Verdana", 20));
            gc.strokeText("You win", 250 - 50, 250 - 15);
            return;
        }

        if (isLost){
            gc.setStroke(Color.GREEN);
            gc.setFont(Font.font("Verdana", 22));
            gc.strokeText("You die", size / 2 - 50, size / 2 - 15);
        }
    }

    void setMenuPaneOnStage(){
        primaryStage.setScene(menu.getScene());
        primaryStage.show();
    }

    void setHelperPaneOnStage(){
        primaryStage.setScene(help.getScene());
        primaryStage.show();
    }

    void setHelper(HelperController help){
        this.help = help;
    }

    void setMenu(MainMenuController menu){
        this.menu = menu;
    }
}
