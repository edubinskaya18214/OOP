package ru.nsu.fit.g18214.dubinskaya.Pizzeria;


import java.util.concurrent.BlockingQueue;

import static java.lang.Thread.sleep;

/**
 * this class imitates work of cook.
 */
public class Cook extends Staff {
  private long cookingTime;
  private Journal journal;
  private String id;
  private BlockingQueue<Integer> orders;
  private BlockingQueue<Integer> wareHouse;

  Cook(long cookingTime, String id, Pizzeria workPlace) {
    this(cookingTime, workPlace.getJournal(), id, workPlace.getOrders(), workPlace.getWareHouse());
  }

  Cook(long cookTime, Journal journal, String id, BlockingQueue<Integer> orders, BlockingQueue<Integer> store) {
    if (journal == null || orders == null || store == null) {
      throw new NullPointerException();
    }
    if (id == null || id.equals("")) {
      throw new NullPointerException("json file has empty strings");
    }
    this.cookingTime = cookTime;
    this.journal = journal;
    this.id = id;
    this.orders = orders;
    this.wareHouse = store;
  }

  @Override
  public void run() {
    int currentOrder = -1;
    try {
      while (!Thread.interrupted()) {
        currentOrder = -1;
        currentOrder = orders.take();
        journal.cookTakeOrder(id, currentOrder);
        sleep(cookingTime);
        journal.cookFinishOrder(id, currentOrder);
        wareHouse.put(currentOrder);
        journal.cookPutPizzaOnTable(id, currentOrder);
      }
    } catch (InterruptedException e) {
      if (currentOrder != -1) {
        journal.orderDropped(currentOrder);
      }
      journal.cookGoHome(id);
      return;
    }

    journal.cookGoHome(id);
  }
}
