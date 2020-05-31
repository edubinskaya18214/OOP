package ru.nsu.fit.dubinskaya.Snake.Controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ru.nsu.fit.dubinskaya.Snake.Views.MenuView;
import ru.nsu.fit.dubinskaya.Snake.Views.View;

public class MenuController extends Controller{

    private View view;

    public MenuController(Stage primaryStage) {
        view = new MenuView(primaryStage);
        setView(view);
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
        Scene scene = new Scene(root, size, size + 30);
        setScene(scene);

        buttonStartGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               view.setSnakePaneOnStage();
            }
        });

        helper.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                view.setHelpPaneOnStage();
            }
        });
    }
}
