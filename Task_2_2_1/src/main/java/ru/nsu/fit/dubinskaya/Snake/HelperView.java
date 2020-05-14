package ru.nsu.fit.dubinskaya.Snake;

import javafx.stage.Stage;

class HelperView {
    void setMainMenuPane(Stage primaryStage, MainMenuController menu){
        primaryStage.setScene(menu.getScene());
        primaryStage.show();
    }
}
