package ru.nsu.fit.dubinskaya.Snake;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class SnakeGame {

  final int up = 1, right = 2, down = 3, left = 4;
  int length = 3, dir = 2, food_x, food_y;

  final int size = 500, dot_size = 15, winSize = size/dot_size - 2;
  int[] x = new int[size * size];
  int[] y = new int[size * size];


  boolean lost;
  Pane root;
  Stage primaryStage;
  int delay;
  /*
    TODO:
      add more food
      die when he bite himself

   */

  SnakeGame(Stage primaryStage, int delay) {
    this.primaryStage = primaryStage;
    this.delay = delay;
  }

  void startGame(Scene menu) {
    length = 3;
    root = new Pane();
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

    primaryStage.setScene(new Scene(root, size, size+40));
    primaryStage.show();

    for (int i = 0; i < length; i++) {
      x[i] = size/dot_size*7 - i * dot_size;
      y[i] = 50;
    }

    canvas.setOnKeyPressed(new EventHandler<KeyEvent>() {
      @Override
      public void handle(KeyEvent e) {
        setDir(e.getCode());
      }
    });

    locateFood();
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


  void setDir(KeyCode key) {
    if (key.equals(KeyCode.UP) && dir != down) dir = up;
    if (key.equals(KeyCode.DOWN) && dir != up) dir = down;
    if (key.equals(KeyCode.LEFT) && dir != right) dir = left;
    if (key.equals(KeyCode.RIGHT) && dir != left) dir = right;
  }

  private void draw(GraphicsContext gc){
    gc.clearRect(0, 0, size, size);

    if (length == winSize){
      gc.setFill(Paint.valueOf("black"));
      gc.fillText("You win", size / 2 - 50, size / 2 - 15);
      lost = true;
      try {
        Thread.sleep(delay * 3);
      } catch (InterruptedException ignored){};
    }
    if (!lost) {

      gc.setFill(Color.FORESTGREEN);
      gc.fillOval(food_x, food_y, dot_size, dot_size);

      gc.setFill(Color.BLUE);
      gc.fillOval(x[0], y[0], dot_size, dot_size);

      gc.setFill(Color.DEEPSKYBLUE);
      gc.setStroke(Color.CORNFLOWERBLUE);

      for (int i = 1; i < length; i += 2) {
        gc.fillOval(x[i], y[i], dot_size, dot_size);
      }
      for (int i = 2; i < length; i+= 2){
        gc.strokeOval(x[i], y[i], dot_size, dot_size);
      }

      gc.strokeRect(dot_size,dot_size, size - dot_size*2, size-dot_size*2 );
      gc.strokeText(String.format("%d/%d", length, winSize), size/2 - 30, 10);
    } else {
      gc.setFill(Paint.valueOf("black"));
      gc.fillText("You die", size / 2 - 30, size / 2 - 15);
      try {
        Thread.sleep(delay * 3);
      } catch (InterruptedException ignored){};
    }
  }

  private void locateFood() {
    food_x = (int) (Math.random() * (size - dot_size)/dot_size) * dot_size - 9;
    while (food_x < dot_size) food_x += dot_size;
    food_y = (int) (Math.random() * (size - dot_size)/dot_size) * dot_size - 10;
    while (food_y < dot_size) food_y+= dot_size;
  }

  private void checkFood() {
    if (x[0] == food_x && y[0] == food_y) {
      length++;
      locateFood();
    }
  }

  private void checkCollision() {
    if (x[0] >= size - dot_size - 9|| y[0] >= size - dot_size - 10 || x[0] < dot_size || y[0] < dot_size) {
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
    if (dir == up) y[0] -= dot_size;
    if (dir == down) y[0] += dot_size;
    if (dir == right) x[0] += dot_size;
    if (dir == left) x[0] -= dot_size;
  }
}
