package ru.nsu.fit.g18214.dubinskaya.Pizzeria;

public interface Journal {
  void cookTakeOrder(String cookID, int order);

  void cookFinishOrder(String cookID, int order);

  void cookPutPizzaOnTable(String cookID, int order);

  void courierTakePizzas(String courierID, Object[] orders);

  void courierFinishDelivering(String courierID, int order);

  void newOrder(int order);

  void courierGoHome(String ID);

  void cookGoHome(String ID);

  void orderDropped(int order);
}
