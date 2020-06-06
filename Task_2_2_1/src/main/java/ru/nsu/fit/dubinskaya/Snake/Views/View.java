package ru.nsu.fit.dubinskaya.Snake.Views;

import javafx.stage.Stage;
import ru.nsu.fit.dubinskaya.Snake.Controllers.HelpController;
import ru.nsu.fit.dubinskaya.Snake.Controllers.MenuController;
import ru.nsu.fit.dubinskaya.Snake.Controllers.SnakeController;

public abstract class View {
  private MenuController menu;
  private Stage primaryStage;
  private HelpController help;
  private SnakeController snake;

  /**
   * This method used to switch current scene to Menu Scene.
   */
  public void setMenuSceneOnStage() {
    primaryStage.setScene(menu.getScene());
    primaryStage.show();
  }

  /**
   * This method used to switch current scene to Snake Game.
   *
   * @param level - level of snake's game
   */
  public void setSnakeSceneOnStage(int level) {
    primaryStage.setScene(snake.getScene());
    snake.createNewGame(level);
    primaryStage.show();
  }

  /**
   * This method used to switch current scene to Help Scene.
   */
  public void setHelpSceneOnStage() {
    primaryStage.setScene(help.getScene());
    primaryStage.show();
  }

  /**
   * This method used to set current help controller.
   *
   * @param help - current help controller. From this controller
   *             view takes scene to switch on in the method setHelpSceneOnTheStage()
   */
  public void setHelpController(HelpController help) {
    this.help = help;
  }

  /**
   * This method used to set current menu controller.
   *
   * @param menu - current menu controller. From this controller
   *             view takes scene to switch on in the method setMenuSceneOnTheStage()
   */
  public void setMenuController(MenuController menu) {
    this.menu = menu;
  }

  /**
   * This method used to set current snake controller.
   *
   * @param snake - current snake controller. From this controller
   *              view takes scene to switch on in the method setSnakeSceneOnTheStage()
   */
  public void setSnakeController(SnakeController snake) {
    this.snake = snake;
  }

  void setPrimaryStage(Stage primaryStage) {
    this.primaryStage = primaryStage;
  }

}
