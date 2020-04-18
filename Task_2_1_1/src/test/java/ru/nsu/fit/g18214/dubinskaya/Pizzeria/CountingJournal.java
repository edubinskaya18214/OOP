package ru.nsu.fit.g18214.dubinskaya.Pizzeria;

public class CountingJournal implements Journal {
  private int takenOrders = 0;
  private int cookedOrders = 0;
  private int startDeliveringOrders = 0;
  private int deliveredOrders = 0;
  private int droppedOrders = 0;
  @Override
  public synchronized void cookTakeOrder(String cookID, int order) {
    takenOrders++;
  }

  @Override
  public synchronized void cookFinishOrder(String cookID, int order) {
    cookedOrders++;
  }

  @Override
  public synchronized void cookPutPizzaOnTable(String cookID, int order) {
  }

  @Override
  public synchronized void courierTakePizzas(String courierID, Object[] orders) {
    startDeliveringOrders+=orders.length;
  }

  @Override
  public synchronized void courierFinishDelivering(String courierID, int order) {
    deliveredOrders++;
  }

  @Override
  public synchronized void newOrder(int order) {
  }

  @Override
  public synchronized void courierGoHome(String ID) {
  }

  @Override
  public synchronized void cookGoHome(String ID) {
  }

  @Override
  public synchronized void orderDropped(int order) {
    droppedOrders++;
  }

  synchronized int getCookedOrders() {
    return cookedOrders;
  }

  synchronized int getTakenOrders() {
    return takenOrders;
  }

  synchronized int getStartDeliveringOrders() {
    return startDeliveringOrders;
  }
  synchronized int getDroppedOrders(){
    return droppedOrders;
  }
  synchronized int getDeliveredOrders() {
    return deliveredOrders;
  }
}
