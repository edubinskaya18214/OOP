package ru.nsu.fit.g18214.dubinskaya.Pizzeria;

import java.util.Map;

class PizzeriaConfig {

  private int minWaitingClient = -1;
  private int maxWaitingClient = -1;
  private Map<String, Integer> cookingTime = null;
  private Map<String, Integer> deliveryTime = null;
  private int bagSize = -1;
  private int workTime = -1;
  private int queueSize = -1;
  private int wareHouseSize = -1;

  boolean isCorrect() {
    return !(minWaitingClient < 0 || maxWaitingClient < minWaitingClient
        || cookingTime == null || deliveryTime == null || bagSize <= 0 || workTime <= 0
        || queueSize <= 0 || wareHouseSize <= 0);
  }

  int getMinWaitingClient() {
    return minWaitingClient;
  }

  int getMaxWaitingClient() {
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
