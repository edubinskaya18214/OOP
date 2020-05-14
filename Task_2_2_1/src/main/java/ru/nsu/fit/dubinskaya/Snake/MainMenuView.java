package ru.nsu.fit.dubinskaya.Snake;

import javafx.stage.Stage;

class MainMenuView {

    void startSnakeGame(Stage primaryStage, SnakeController snake){
        primaryStage.setScene(snake.getScene());
        snake.createNewGame();
    }

    void setHelpPane(Stage primaryStage, HelperController help){
        primaryStage.setScene(help.getScene());
        primaryStage.show();
    }
}
