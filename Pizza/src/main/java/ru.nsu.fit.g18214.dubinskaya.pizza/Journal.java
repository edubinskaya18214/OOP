package ru.nsu.fit.g18214.dubinskaya.pizza;
public interface Journal {
  void cookTakeOrder(String cookID, int order);
  void cookFinishOrder(String cookID, int order);
  void cookPutPizzaOnTable(String cookID, int order);
  void courierTakePizzas(String courierID, Object[] orders);
  void courierFinishDelivery(String courierID, int order);
  void newOrder(int order);
}
