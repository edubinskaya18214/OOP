package ru.nsu.fit.dubinskaya.Snake;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

class HelperButtonPane {

  Scene helpScene;
  Button helpButton;


  HelperButtonPane(int size){
    Pane root = new Pane();
    Canvas canvas = new Canvas(size, size);
    canvas.setFocusTraversable(true);

    Button buttonStartGame = new Button("Start");
    buttonStartGame.setPrefWidth(80);

    Button helper = new Button("?");
    helper.setLayoutX(size - 30);
    helper.setPrefWidth(30);
    helper.setPrefHeight(30);


    Label instruction = new Label("Click on                        to start new game\n\n" +
        "Click on          to stop the game and return to menu\n\n" +
        "Click on          to reset the game\n" +
        "\nUse to move your snake\n\n\n\n\n\nIf you eat an apple you'll increase your size\n" +
        "If you eat your tail or hit the wall you will die");

    instruction.setLayoutX((int)(size/4));
    instruction.setLayoutY((int)(size/3));
    instruction.getStylesheets().add("/help/white_label.css");

    buttonStartGame.setLayoutX((int)(size/3 )+ 20);
    buttonStartGame.setLayoutY((int)(size/3) - 5);

    Image buttons = new Image("/help/buttons.png");
    ImageView image = new ImageView(buttons);
    image.setLayoutX(150);
    image.setLayoutY(250);
    image.setScaleX(0.5);
    image.setScaleY(0.5);

    Button stop = new Button();
    stop.setPrefWidth(30);
    stop.setPrefHeight(30);
    stop.setLayoutX((int)(size/3) + 20);
    stop.setLayoutY((int)(size/3) + 25);
    stop.getStylesheets().add("/Buttons/Stop_button_config.css");

    Button restart = new Button();
    restart.setPrefWidth(30);
    restart.setPrefHeight(30);
    restart.setLayoutY((int)(size/3) + 25 + 31);
    restart.setLayoutX((int)(size/3) + 20);
    restart.getStylesheets().add("./Buttons/restart.css");
    restart.setFocusTraversable(false);

    root.getChildren().add(instruction);
    root.getChildren().add(image);
    root.getChildren().add(canvas);
    root.getChildren().add(buttonStartGame);
    root.getChildren().add(helper);
    root.getChildren().add(stop);
    root.getChildren().add(restart);


    helpButton = helper;
    helpScene  = new Scene(root, size, size+30);
    helpScene.getStylesheets().add("/help/helperStyle.css");
  }

  void setHelperScene(Stage primaryStage, Scene curr){

    primaryStage.setScene(helpScene);
    primaryStage.show();
    helpButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        primaryStage.setScene(curr);
        primaryStage.show();
      }
    });

  }
}
