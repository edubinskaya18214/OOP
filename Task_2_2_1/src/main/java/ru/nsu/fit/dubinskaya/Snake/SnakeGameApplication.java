package ru.nsu.fit.dubinskaya.Snake;

/**
 * this class is used to start playing snake
 */

import javafx.scene.image.Image;
import javafx.stage.Stage;

public class SnakeGameApplication extends javafx.application.Application {

    @Override
    public void start(Stage primaryStage) {
        MainMenuController main = new MainMenuController(primaryStage);
        HelperController help = new HelperController(primaryStage);
        SnakeController snake = new SnakeController(primaryStage);

        main.setHelper(help);
        main.setSnake(snake);

        help.setMainPane(main);

        snake.setHelpPane(help);
        snake.setMainPane(main);

        primaryStage.setScene(main.getScene());
        primaryStage.getIcons().add(new Image("snake2.png"));

        primaryStage.show();
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}