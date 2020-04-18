package ru.nsu.fit.g18214.dubinskaya.Pizzeria;

import java.util.Map;

public class PizzeriaConfig {

  private double minWaitingClient = -1;
  private double maxWaitingClient = -1;
  private Map<String, Integer> cookingTime = null;
  private Map<String, Integer> deliveryTime = null;
  private int bagSize = -1;
  private int workTime = -1;
  private int queueSize = -1;
  private int wareHouseSize = -1;

  public boolean isCorrect() {
    return !(minWaitingClient < 0 || maxWaitingClient < minWaitingClient ||
        cookingTime == null || deliveryTime == null || bagSize <= 0 || workTime <= 0 ||
        queueSize <= 0 || wareHouseSize <= 0);
  }

  double getMinWaitingClient() {
    return minWaitingClient;
  }

  double getMaxWaitingClient() {
    return maxWaitingClient;
  }

  Map<String, Integer> getCookingTime() {
    return cookingTime;
  }

  Map<String, Integer> getDeliveryTime() {
    return deliveryTime;
  }

  int getBagSize() {
    return bagSize;
  }

  int getWorkTime() {
    return workTime;
  }

  int getQueueSize() {
    return queueSize;
  }

  int getWareHouseSize() {
    return wareHouseSize;
  }
}
