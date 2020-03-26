package ru.nsu.fit.dubinskaya.Snake;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

class SnakeGame {

    private final int up = 1, right = 2, down = 3, left = 4;
    private int length = 3;
    private int dir = 2;
    private int[] foodX = new int[7];
    private int[] foodY = new int[7];

    private final int size = 500;
    private final int dotSize = 15;
    private int[] x = new int[size * size];
    private int[] y = new int[size * size];

    private boolean lost;
    private Stage primaryStage;
    private int delay;

    SnakeGame(Stage primaryStage, int delay) {
        this.primaryStage = primaryStage;
        this.delay = delay;
    }

    void startGame(Scene menu) {
        length = 3;
        Pane root = new Pane();
        Canvas canvas = new Canvas(size, size);
        canvas.setLayoutX(0);
        canvas.setLayoutY(40);

        Button helper = new Button("?");
        helper.setLayoutY(5);
        helper.setLayoutX(size - 30);
        helper.setPrefHeight(30);
        helper.setPrefWidth(30);

        helper.setFocusTraversable(false);

        Button stop = new Button();
        stop.setPrefWidth(30);
        stop.setPrefHeight(30);
        stop.setLayoutY(5);
        stop.setLayoutX(size - 61);
        stop.getStylesheets().add("./Buttons/Stop_button_config.css");
        stop.setFocusTraversable(false);

        Button restart = new Button();
        restart.setPrefWidth(30);
        restart.setPrefHeight(30);
        restart.setLayoutY(5);
        restart.setLayoutX(size - 92);
        restart.getStylesheets().add("./Buttons/restart.css");
        restart.setFocusTraversable(false);

        root.getChildren().add(canvas);
        root.getChildren().add(helper);
        root.getChildren().add(stop);
        root.getChildren().add(restart);

        canvas.setFocusTraversable(true);

        primaryStage.setScene(new Scene(root, size, size + 40));
        primaryStage.show();

        for (int i = 0; i < length; i++) {
            x[i] = size / dotSize * 7 - i * dotSize;
            y[i] = 50;
        }

        canvas.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                setDir(e.getCode());
            }
        });
        for (int i = 0; i < 7; ++i) {
            locateFood(i);
        }
        checkCollision();
        Thread game = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!lost && !Thread.interrupted()) {
                    checkFood();
                    checkCollision();
                    move();
                    draw(canvas.getGraphicsContext2D());
                    try {
                        Thread.sleep(delay);
                    } catch (InterruptedException e) {
                        break;
                    };
                }
            }
        });
        game.start();

        stop.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                game.interrupt();
                primaryStage.setScene(menu);
            }
        });

        helper.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                game.interrupt();
                HelperButtonPane helpPane = new HelperButtonPane(size);
                helpPane.setHelperScene(primaryStage, menu);
            }
        });

        restart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                game.interrupt();
                startGame(menu);
            }
        });
    }


    private void setDir(KeyCode key) {
        if (key.equals(KeyCode.UP) && dir != down) dir = up;
        if (key.equals(KeyCode.DOWN) && dir != up) dir = down;
        if (key.equals(KeyCode.LEFT) && dir != right) dir = left;
        if (key.equals(KeyCode.RIGHT) && dir != left) dir = right;
    }

    private void draw(GraphicsContext gc) {
        gc.clearRect(0, 0, size, size);

        int winSize = size / dotSize - 2;
        if (length == winSize) {
            gc.setFill(Paint.valueOf("black"));
            gc.fillText("You win", 250 - 50, 250 - 15);
            lost = true;
            try {
                Thread.sleep(delay * 3);
            } catch (InterruptedException ignored) {
            }
            ;
        }
        if (!lost) {

            gc.setFill(Color.FORESTGREEN);
            for (int i = 0; i < 7; ++i) {
                gc.fillOval(foodX[i], foodY[i], dotSize, dotSize);
            }

            gc.setFill(Color.BLUE);
            gc.fillOval(x[0], y[0], dotSize, dotSize);

            gc.setFill(Color.DEEPSKYBLUE);
            gc.setStroke(Color.CORNFLOWERBLUE);

            for (int i = 1; i < length; i += 2) {
                gc.fillOval(x[i], y[i], dotSize, dotSize);
            }
            for (int i = 2; i < length; i += 2) {
                gc.strokeOval(x[i], y[i], dotSize, dotSize);
            }

            gc.strokeRect(dotSize, dotSize, size - dotSize * 2, size - dotSize * 2);
            gc.strokeText(String.format("%d/%d", length, winSize), size / 2 - 30, 10);
        } else {
            gc.setFill(Paint.valueOf("black"));
            gc.fillText("You die", size / 2 - 30, size / 2 - 15);
            try {
                Thread.sleep(delay * 3);
            } catch (InterruptedException ignored) {
            }
            ;
        }
    }

    private void locateFood(int k) {
        foodX[k] = (int) (Math.random() * (size - dotSize) / dotSize) * dotSize - 9;
        while (foodX[k] < dotSize) foodX[k] += dotSize;
        foodY[k] = (int) (Math.random() * (size - dotSize) / dotSize) * dotSize - 10;
        while (foodY[k] < dotSize) foodY[k] += dotSize;
        for (int i = 0; i < 7; ++i) {
            if (i == k) continue;
            if (foodX[i] == foodX[k] && foodY[i] == foodY[k])
                locateFood(k);
        }
        for (int i = 0; i < length; ++i) {
            if (x[i] == foodX[k] && y[i] == foodY[k])
                locateFood(k);
        }
    }

    private void checkFood() {
        for (int i = 0; i < 7; ++i)
            if (x[0] == foodX[i] && y[0] == foodY[i]) {
                length++;
                locateFood(i);
                break;
            }
    }

    private void checkCollision() {
        if (x[0] >= size - dotSize - 9 || y[0] >= size - dotSize - 10 || x[0] < dotSize || y[0] < dotSize) {
            lost = true;
            return;
        }
        for (int i = 3; i < length; i++)
            if (x[0] == x[i] && y[0] == y[i]) {
                lost = true;
                return;
            }
        lost = false;
    }

    private void move() {
        for (int i = length - 1; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }
        if (dir == up) y[0] -= dotSize;
        if (dir == down) y[0] += dotSize;
        if (dir == right) x[0] += dotSize;
        if (dir == left) x[0] -= dotSize;
    }
}
