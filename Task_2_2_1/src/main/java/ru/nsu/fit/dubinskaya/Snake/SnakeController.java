package ru.nsu.fit.dubinskaya.Snake;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

class SnakeController {

    private ModelSnake currSnake;
    private Thread game;
    private Stage primaryStage;
    private final int delay = 60;
    private Scene scene;
    private Canvas canvas;
    private SnakeView view;

    SnakeController(Stage primaryStage) {
        this.primaryStage = primaryStage;

        Pane root = new Pane();
        int size = 500;
        canvas = new Canvas(size, size);
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

        scene = (new Scene(root, size, size + 40));

        view = new SnakeView(canvas.getGraphicsContext2D(), primaryStage);

        stop.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                game.interrupt();
                view.setMenuPaneOnStage();
            }
        });

        helper.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                game.interrupt();
                view.setHelperPaneOnStage();
            }
        });

        restart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                game.interrupt();
                createNewGame();
            }
        });
    }

    void createNewGame() {
        primaryStage.setScene(scene);
        primaryStage.show();

        int foodNumber = 7;
        currSnake = new ModelSnake(3, 25, foodNumber);
        view.setFood(currSnake.getFood());
        view.setSnake(currSnake.getTail());

        game = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!currSnake.isWin() && !currSnake.isLost() && !Thread.interrupted()) {
                    currSnake.move();
                    view.draw(currSnake.getLength(), currSnake.isWin(), currSnake.isLost());
                    try {
                        Thread.sleep(delay);
                    } catch (InterruptedException e) {
                        break;
                    };
                }
            }
        });
        game.start();

        canvas.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                setDir(e.getCode());
            }
        });
    }

    void setHelpPane(HelperController helpPane){
        view.setHelper(helpPane);
    }

    void setMainPane(MainMenuController mainPane){
        view.setMenu(mainPane);
    }

    Scene getScene(){
        return scene;
    }

    private void setDir(KeyCode key) {
        switch (key) {
            case UP:
                currSnake.setDir(ModelSnake.up);
                break;
            case DOWN:
                currSnake.setDir(ModelSnake.down);
                break;
            case RIGHT:
                currSnake.setDir(ModelSnake.right);
                break;
            case LEFT:
                currSnake.setDir(ModelSnake.left);
                break;
        }
    }
}
