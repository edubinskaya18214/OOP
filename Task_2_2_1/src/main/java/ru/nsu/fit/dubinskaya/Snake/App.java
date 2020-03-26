package ru.nsu.fit.dubinskaya.Snake;

/**
 * this class is used to start playing snake
 */

import javafx.application.Application;

import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {

  @Override
  public void start(Stage primaryStage) {
    MainMenuPane menu = new MainMenuPane(500, primaryStage);
    menu.setMenuScene(primaryStage);
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