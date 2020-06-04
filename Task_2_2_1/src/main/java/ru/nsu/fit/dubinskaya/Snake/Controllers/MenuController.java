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
    /**
     * This class create and show Menu Pane
     * @param primaryStage stage where pane will be shown
     */
    public MenuController(Stage primaryStage) {
        view = new MenuView(primaryStage);
        setView(view);
        Pane root = new Pane();
        int size = 500;

        Canvas canvas = new Canvas(size, size + 30);
        canvas.setFocusTraversable(true);

        Button level1 = new Button("Level 1");
        Button level2 = new Button("Level 2");
        Button level3 = new Button("Level 3");
        Button helper = new Button("?");
        helper.setLayoutY(30);
        helper.setLayoutX(30);
        helper.setPrefSize(30,30);

        level1.setPrefWidth(120);
        level1.setPrefHeight(30);
        level1.setLayoutX(250 - 60);
        level1.setLayoutY((int) (size / 3) + 15);

        level2.setPrefWidth(120);
        level2.setPrefHeight(30);
        level2.setLayoutX(250 - 60);
        level2.setLayoutY((int) (size / 3) + 15 + 30 + 7);

        level3.setPrefWidth(120);
        level3.setPrefHeight(30);
        level3.setLayoutX(250 - 60);
        level3.setLayoutY((int) (size / 3) + 15 + 30*2 + 7*2);

        helper.setLayoutY(5);
        helper.setLayoutX(size - 5);
        helper.setLayoutX(size - 30);

        root.getChildren().add(canvas);
        root.getChildren().add(level1);
        root.getChildren().add(level2);
        root.getChildren().add(level3);
        root.getChildren().add(helper);
        Scene scene = new Scene(root, size, size + 30);
        setScene(scene);

        level1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               view.setSnakeSceneOnStage(1);
            }
        });

        level2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                view.setSnakeSceneOnStage(2);
            }
        });

        level3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                view.setSnakeSceneOnStage(3);
            }
        });

        helper.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                view.setHelpSceneOnStage();
            }
        });
    }
}
