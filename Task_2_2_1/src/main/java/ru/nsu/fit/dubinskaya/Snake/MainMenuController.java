package ru.nsu.fit.dubinskaya.Snake;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

class MainMenuController {

    private Scene scene;
    private SnakeController snake;
    private HelperController help;
    private MainMenuView view;

    MainMenuController(Stage primaryStage) {
        view = new MainMenuView();
        Pane root = new Pane();
        int size = 500;

        Canvas canvas = new Canvas(size, size + 30);
        canvas.setFocusTraversable(true);

        Button buttonStartGame = new Button("Start");
        Button helper = new Button("?");
        helper.setLayoutY(30);
        helper.setLayoutX(30);
        helper.setPrefSize(30,30);

        buttonStartGame.setPrefWidth(100);
        buttonStartGame.setPrefHeight(20);
        buttonStartGame.setLayoutX(250 - 40);
        buttonStartGame.setLayoutY((int) (size / 3) + 15);

        helper.setLayoutY(5);
        helper.setLayoutX(size - 5);
        helper.setLayoutX(size - 30);

        root.getChildren().add(canvas);
        root.getChildren().add(buttonStartGame);
        root.getChildren().add(helper);
        scene = new Scene(root, size, size + 30);

        buttonStartGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               view.startSnakeGame(primaryStage, snake);
            }
        });

        helper.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                view.setHelpPane(primaryStage, help);
            }
        });
    }

    Scene getScene(){
        return scene;
    }

    void setSnake(SnakeController snake){
        this.snake = snake;
    }

    void setHelper(HelperController help){
        this.help = help;
    }
/*
    void setMenuScene(Stage primaryStage) {
        primaryStage.setScene(scene);
        primaryStage.show();
    }

 */
}
