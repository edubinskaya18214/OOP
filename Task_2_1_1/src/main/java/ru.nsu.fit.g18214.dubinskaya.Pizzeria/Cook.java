package ru.nsu.fit.g18214.dubinskaya.Pizzeria;


import java.util.concurrent.BlockingQueue;

import static java.lang.Thread.sleep;

/**
 * this class imitate work of cook.
 */
public class Cook extends Staff {
  private long cookingTime;
  private Journal journal;
  private String ID;
  private BlockingQueue<Integer> orders;
  private BlockingQueue<Integer> wareHouse;

  Cook(long cookingTime, String ID, Pizzeria workPlace) {
    this(cookingTime, workPlace.getJournal(), ID, workPlace.getOrders(), workPlace.getWareHouse());
  }

  Cook(long cookingTime, Journal journal, String ID, BlockingQueue<Integer> orders, BlockingQueue<Integer> wareHouse) {
    if(journal == null || orders == null || wareHouse == null) throw new NullPointerException();
    if (ID == null || ID.equals("")) throw new NullPointerException("json file has empty strings");
    this.cookingTime = cookingTime;
    this.journal = journal;
    this.ID = ID;
    this.orders = orders;
    this.wareHouse = wareHouse;
  }
  @Override
  public void run() {
    int currentOrder = -1;
    try {
      while (!Thread.interrupted()) {
        currentOrder = -1;
        currentOrder = orders.take();
        journal.cookTakeOrder(ID, currentOrder);
        sleep(cookingTime);
        journal.cookFinishOrder(ID, currentOrder);
        wareHouse.put(currentOrder);
        journal.cookPutPizzaOnTable(ID, currentOrder);
      }
    } catch (InterruptedException e) {
      if (currentOrder != -1)
        journal.orderDropped(currentOrder);
      journal.cookGoHome(ID);
      return;
    }

    journal.cookGoHome(ID);
  }
}
