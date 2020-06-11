package ru.nsu.fit.dubinskaya.Snake.SnakeModel;

import java.util.Random;

/**
 * This class is used in SnakeGameModel and SnakeView.
 * Contains coordinates and colors of food and snake's tail.
 */
public class Cell {
  private int coordinateX;
  private int coordinateY;

  /**
   * this method used to get x coordinate of this cell.
   *
   * @return field coordinate;
   */
  public int getX() {
    return coordinateX;
  }

  /**
   * this method used to get y coordinate of this cell.
   *
   * @return field coordinate;
   */
  public int getY() {
    return coordinateY;
  }

  @Override
  public boolean equals(Object cell) {
    if (cell == null || cell.getClass() != this.getClass()) {
      return false;
    }
    return (coordinateX == ((Cell)(cell)).getX() && coordinateY == ((Cell)(cell)).getY());
  }

  void setCoordinates(int newX, int newY) {
    coordinateX = newX;
    coordinateY = newY;
  }

  void generateCoordinates(int from, int to) {
    Random rand = new Random();
    coordinateX = rand.nextInt(to - from) + from;
    coordinateY = rand.nextInt(to - from) + from;
  }
}
