package ru.nsu.fit.dubinskaya.Snake.Controllers;

import javafx.scene.Scene;
import ru.nsu.fit.dubinskaya.Snake.Views.View;

public abstract class Controller {

    private Scene scene;
    private View view;

    void setScene(Scene scene){
        this.scene = scene;
    }

    public Scene getScene(){
        return scene;
    };

    public void setSnake(SnakeController snake){
        view.setSnakeController(snake);
    }

    public void setHelp(HelperController help){
        view.setHelpController(help);
    }

    public void setMenu(MenuController menu){
        view.setMenuController(menu);
    }

    public View getView(){
        return view;
    }

    void setView(View view){
        this.view = view;
    }
}
