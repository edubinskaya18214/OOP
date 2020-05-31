package ru.nsu.fit.dubinskaya.Snake.Views;

import javafx.stage.Stage;
import ru.nsu.fit.dubinskaya.Snake.Controllers.HelperController;
import ru.nsu.fit.dubinskaya.Snake.Controllers.MenuController;
import ru.nsu.fit.dubinskaya.Snake.Controllers.SnakeController;

public abstract class View {
    private MenuController menu;
    private Stage primaryStage;
    private HelperController help;
    private SnakeController snake;

    void setPrimaryStage(Stage primaryStage){
        this.primaryStage = primaryStage;
    }

    public void setMenuPaneOnStage(){
        primaryStage.setScene(menu.getScene());
        primaryStage.show();
    }

    public void setSnakePaneOnStage(int level){
        primaryStage.setScene(snake.getScene());
        snake.createNewGame(level);
        primaryStage.show();
    }

    public void setHelpPaneOnStage(){
        primaryStage.setScene(help.getScene());
        primaryStage.show();
    }

    public void setHelpController(HelperController help){
        this.help = help;
    }

    public void setMenuController(MenuController menu){
        this.menu = menu;
    }

    public void setSnakeController(SnakeController snake){
        this.snake = snake;
    }
}
