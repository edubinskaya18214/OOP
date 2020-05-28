package ru.nsu.fit.dubinskaya.Snake;

import javafx.stage.Stage;

class HelperView {

    private MainMenuController menu;
    private Stage primaryStage;

    HelperView(Stage primaryStage){
        this.primaryStage = primaryStage;
    }

    void setMainMenu(MainMenuController menu){
      this.menu = menu;
    }

    void setMenuPaneOnStage(){
        primaryStage.setScene(menu.getScene());
        primaryStage.show();
    }
}
