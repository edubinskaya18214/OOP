package ru.nsu.fit.dubinskaya.Snake.Controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ru.nsu.fit.dubinskaya.Snake.SnakeModel.GameField;
import ru.nsu.fit.dubinskaya.Snake.SnakeModel.Snake;
import ru.nsu.fit.dubinskaya.Snake.Views.SnakeView;


public class SnakeController extends Controller{

    private GameField field;
    private Snake currSnake;
    private Thread game;
    private Stage primaryStage;
    private int delay = 60;
    private Canvas canvas;
    private Scene scene;
    private SnakeView view;
    private int currLevel;
    /**
     * This class create and show Snake Pane
     * @param primaryStage stage where pane will be shown
     */
    public SnakeController(Stage primaryStage) {
        this.primaryStage = primaryStage;

        Pane root = new Pane();
        int size = 500;
        canvas = new Canvas(size, size);
        canvas.setLayoutX(5);
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
        stop.getStylesheets().add("/Buttons/Stop_button_config.css");
        stop.setFocusTraversable(false);

        Button restart = new Button();
        restart.setPrefWidth(30);
        restart.setPrefHeight(30);
        restart.setLayoutY(5);
        restart.setLayoutX(size - 92);
        restart.getStylesheets().add("/Buttons/restart.css");
        restart.setFocusTraversable(false);

        root.getChildren().add(canvas);
        root.getChildren().add(helper);
        root.getChildren().add(stop);
        root.getChildren().add(restart);

        canvas.setFocusTraversable(true);

        scene = (new Scene(root, size, size + 40));
        setScene(scene);

        view = new SnakeView(canvas.getGraphicsContext2D(), primaryStage);
        setView(view);

        stop.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                game.interrupt();
                view.setMenuSceneOnStage();
            }
        });

        helper.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                game.interrupt();
                view.setHelpSceneOnStage();
            }
        });

        restart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                game.interrupt();
                createNewGame(currLevel);
            }
        });
    }

    public void createNewGame(int level) {
        primaryStage.setScene(scene);
        primaryStage.show();
        currLevel = level;
        int fieldSize;
        int winLen;

        switch (level) {
            case 2:
                fieldSize = 20;
                winLen = 25;
                field = new GameField(5, 2,  fieldSize);
                delay = 60;
                break;
            case 3:
                fieldSize = 20;
                winLen = 50;
                field = new GameField(2, 2,  fieldSize);
                delay = 55;
                break;
            default:
                fieldSize = 11;
                winLen = 5;
                field = new GameField(2, 2,  fieldSize);
                delay = 85;
                break;
        }

        currSnake = field.getSnake();

        game = new Thread(new Runnable() {
            @Override
            public void run() {
                while (currSnake.getLength() !=  winLen && !currSnake.isDead() && !Thread.interrupted()) {
                    field.move();
                    view.setWinSize(winLen);

                    boolean isWin = currSnake.getLength() == winLen;
                    view.draw(isWin , currSnake.isDead(), fieldSize, field.getSnakeIterator(), field.getFoodIterator());
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

    private void setDir(KeyCode key) {
        switch (key) {
            case UP:
                currSnake.setDir(Snake.direction.up);
                break;
            case DOWN:
                currSnake.setDir(Snake.direction.down);
                break;
            case RIGHT:
                currSnake.setDir(Snake.direction.right);
                break;
            case LEFT:
                currSnake.setDir(Snake.direction.left);
                break;
        }
    }
}
