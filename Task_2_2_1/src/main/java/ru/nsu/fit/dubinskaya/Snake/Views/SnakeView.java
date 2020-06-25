package ru.nsu.fit.dubinskaya.Snake.Views;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import ru.nsu.fit.dubinskaya.Snake.SnakeModel.Cell;

/**
 * This class is used to show Snake Game on the Stage.
 */
public class SnakeView extends View {
  private GraphicsContext gc;
  private int winSize;

  /**
   * This is SnakeView Constructor.
   *
   * @param gc           - GraphicContext of canvas where snake's game will be shown
   * @param primaryStage - Stage where view should work
   */
  public SnakeView(GraphicsContext gc, Stage primaryStage) {
    this.gc = gc;
    setPrimaryStage(primaryStage);
  }

  /**
   * This method used to set win size of snake.
   *
   * @param winSize - new winSize. This parameter effects only on showing the score
   *                - in the top of the window.
   */
  public void setWinSize(int winSize) {
    this.winSize = winSize;
  }

  /**
   * Used to draw current game on the canvas.
   *
   * @param isWin  - is user win
   * @param isDead - is user fail
   * @param size   - size of the field on the canvas
   * @param tail   - Cell iterator with snake's tail
   * @param food   - Cell iterator with food on the game's field
   */
  public void draw(boolean isWin, boolean isDead, int size, Iterable<Cell> tail, Iterable<Cell> food) {
    int snakeSize = 0;
    gc.setFont(Font.font("Verdana", 12));
    gc.clearRect(0, 0, 500, 500);

    final int dotSize = (500 - 2 * 500 / size) / size + 1;
    final int halfDotSize = dotSize / 2;
    //this value we will use to set field in the center of window
    int delta = (495 - 2 - dotSize * size) / 2 + 2;

    for (int i = 0; i < size; i += 1) {
      for (int j = 1; j <= size; j += 1) {
        if ((j + i) % 2 == 1) {
          gc.setFill(new Color(0.968, 0.968, 0.8, 0.7));
        } else {
          gc.setFill(new Color(0.8, 0.9, 0.75, 0.7));
        }
        gc.fillRect(i * dotSize + delta, j * dotSize, dotSize, dotSize);
      }
    }

    gc.setFill(Color.FORESTGREEN);
    for (Object f: food) {
      gc.fillOval(delta + (((Cell) f).getX()) * dotSize,
          (1 + ((Cell) f).getY()) * dotSize, dotSize, dotSize);
    }

    if (isDead) {
      gc.setFill(new Color(0.88, 0.8, 0.309, 1));

      for (Cell t : tail) {
        snakeSize++;
        //Skip head
        if (snakeSize == 1) {
          continue;
        }
        gc.fillOval(delta + (t.getX()) * dotSize,
            (1 + t.getY()) * dotSize, dotSize, dotSize);
      }

      gc.setStroke(Color.ALICEBLUE);
      gc.setFont(Font.font("Verdana", 30));
      gc.setFill(Color.DARKSLATEBLUE);
      gc.fillText("You die", 200, 250 - 15);
      gc.strokeText("You die", 200, 250 - 15);

      gc.setFont(Font.font("Verdana", 12));
    } else {
      for (Cell curr: tail) {
        if (snakeSize == 0) {
          //Drawing head
          gc.setFill(Color.GREENYELLOW);
          gc.setStroke(new Color(0.79, 0.79, 0.9, 1));

          gc.fillOval(delta + (curr.getX()) * dotSize,
              (1 + curr.getY()) * dotSize, dotSize, dotSize);

          gc.setFill(Color.DARKOLIVEGREEN);
          gc.fillOval(delta + (0.25 + curr.getX()) * dotSize,
              (1.25 + curr.getY()) * dotSize, halfDotSize, halfDotSize);
        } else {
          //Drawing tail using different colors
          gc.setFill(Color.DARKOLIVEGREEN);
          int x = curr.getX();
          int y = curr.getY();
          gc.fillOval(delta + x * dotSize, (1 + y) * dotSize, dotSize, dotSize);
          gc.setFill(Color.GREENYELLOW);
          gc.fillOval(delta + (x + 0.25) * dotSize, (1.25 + y) * dotSize, halfDotSize, halfDotSize);
        }
        snakeSize++;
      }
    }

    gc.setStroke(Color.CORNFLOWERBLUE);
    gc.strokeRect(delta, dotSize, dotSize * size, dotSize * size);
    gc.strokeText(String.format("%d/%d", snakeSize, winSize), 220 + (delta / 2), 10);

    if (isWin) {
      gc.setFill(Color.DARKSLATEBLUE);
      gc.setStroke(Color.ALICEBLUE);
      gc.setFont(Font.font("Verdana", 30));
      gc.fillText("You win", 250 - 50, 250 - 15);
      gc.strokeText("You win", 250 - 50, 250 - 15);
    }
  }
}
