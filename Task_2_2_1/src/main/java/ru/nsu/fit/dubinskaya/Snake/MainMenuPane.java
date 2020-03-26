package ru.nsu.fit.dubinskaya.Snake;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainMenuPane {

  Scene scene;

  MainMenuPane(int size, Stage primaryStage) {
    Pane root = new Pane();
    Canvas canvas = new Canvas(size, size+30);
    canvas.setFocusTraversable(true);

    Button buttonStartGame = new Button("Start");
    Button helper = new Button("?");
    helper.setLayoutY(30);
    helper.setLayoutX(30);

    buttonStartGame.setPrefWidth(100);
    buttonStartGame.setPrefHeight(20);
    buttonStartGame.setLayoutX(250 - 40);
    buttonStartGame.setLayoutY((int)(size / 3) + 15);

    helper.setLayoutY(5);
    helper.setLayoutX(size - 5);
    helper.setLayoutX(size - 30);


    HelperButtonPane helpPane = new HelperButtonPane(size);

    helper.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        helpPane.setHelperScene(primaryStage, primaryStage.getScene());
      }
    });

    root.getChildren().add(canvas);
    root.getChildren().add(buttonStartGame);
    root.getChildren().add(helper);
    scene = new Scene(root, size, size + 30);

    buttonStartGame.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        SnakeGame snake = new SnakeGame(primaryStage,80);
        snake.startGame(scene);
      }
    });
  }

  void setMenuScene(Stage primaryStage) {

    primaryStage.setScene(scene);
    primaryStage.show();
  }
}
