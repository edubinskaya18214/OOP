package ru.nsu.fit.dubinskaya.Snake;

import javafx.scene.image.Image;
import javafx.stage.Stage;
import ru.nsu.fit.dubinskaya.Snake.Controllers.HelpController;
import ru.nsu.fit.dubinskaya.Snake.Controllers.MenuController;
import ru.nsu.fit.dubinskaya.Snake.Controllers.SnakeController;

/**
 * this class is used to start playing snake
 */
public class SnakeGame extends javafx.application.Application {

    @Override
    public void start(Stage primaryStage) {
        MenuController main = new MenuController(primaryStage);
        HelpController help = new HelpController(primaryStage);
        SnakeController snake = new SnakeController(primaryStage);

        main.setHelp(help);
        main.setSnake(snake);

        help.setMenu(main);

        snake.setHelp(help);
        snake.setMenu(main);

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