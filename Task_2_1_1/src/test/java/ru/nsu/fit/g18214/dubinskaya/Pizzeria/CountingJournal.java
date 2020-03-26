package ru.nsu.fit.g18214.dubinskaya.Pizzeria;

public class CountingJournal implements Journal {
  int takenOrders = 0;
  int cookedOrders = 0;
  int startDeliveringOrders = 0;
  int deliveredOrders = 0;
  int droppedOrders = 0;
  @Override
  public void cookTakeOrder(String cookID, int order) {
    takenOrders++;
  }

  @Override
  public void cookFinishOrder(String cookID, int order) {
    cookedOrders++;
  }

  @Override
  public void cookPutPizzaOnTable(String cookID, int order) {
  }

  @Override
  public void courierTakePizzas(String courierID, Object[] orders) {
    startDeliveringOrders+=orders.length;
  }

  @Override
  public void courierFinishDelivering(String courierID, int order) {
    deliveredOrders++;
  }

  @Override
  public void newOrder(int order) {
  }

  @Override
  public void courierGoHome(String ID) {
  }

  @Override
  public void cookGoHome(String ID) {
  }

  @Override
  public void orderDropped(int order) {
    droppedOrders++;
  }

  int getCookedOrders() {
    return cookedOrders;
  }

  int getTakenOrders() {
    return takenOrders;
  }

  int getStartDeliveringOrders() {
    return startDeliveringOrders;
  }
  int getDroppedOrders(){
    return droppedOrders;
  }
  int getDeliveredOrders() {
    return deliveredOrders;
  }
}
