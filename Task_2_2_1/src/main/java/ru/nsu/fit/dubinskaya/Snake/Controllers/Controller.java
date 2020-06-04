package ru.nsu.fit.dubinskaya.Snake.Controllers;

import javafx.scene.Scene;
import ru.nsu.fit.dubinskaya.Snake.Views.View;

/**
 * This class is used to create controllers for the snake game
 */
public abstract class Controller {

    private Scene scene;
    private View view;

    /**
     * Used to get private field "scene"
     * @return current scene which was created in controller
     */
    public Scene getScene(){
        return scene;
    };

    /**
     * This method set Snake Controller to controller's view
     * @param snake - current snake controller. Pane which will be used to play.
     */
    public void setSnake(SnakeController snake){
        view.setSnakeController(snake);
    }

    /**
     * This method take Help controller and set to the current view.
     * @param help - current Help Controller.
     */
    public void setHelp(HelpController help){
        view.setHelpController(help);
    }

    /**
     * This method take Menu controller and set to the current view.
     * @param menu - current Menu Controller.
     */
    public void setMenu(MenuController menu){
        view.setMenuController(menu);
    }

    /**
     * This method used to take current view
     * @return - current view
     */
    public View getView(){
        return view;
    }

    void setView(View view){
        this.view = view;
    }

    void setScene(Scene scene){
        this.scene = scene;
    }
}
