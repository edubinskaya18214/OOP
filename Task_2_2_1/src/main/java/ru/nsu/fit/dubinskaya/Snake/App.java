package ru.nsu.fit.dubinskaya.Snake;


import javafx.application.Application;

import javafx.stage.Stage;

public class App extends Application {

  int size = 500;

  @Override
  public void start(Stage primaryStage) {
    MainMenuPane menu = new MainMenuPane(size, primaryStage);
    menu.setMenuScene(primaryStage);
    primaryStage.show();
  }


  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    launch(args);
  }

}