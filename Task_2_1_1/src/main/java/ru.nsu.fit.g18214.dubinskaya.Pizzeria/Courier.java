package ru.nsu.fit.g18214.dubinskaya.Pizzeria;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

/**
 * this method imitate work of courier.
 */
public class Courier extends Staff {
  private int capacity;
  private long time;
  private Journal journal;
  private String courierID;
  private BlockingQueue<Integer> wareHouse;
  private int timeout;

  /**
   * this method create new courier.
   *
   * @param deliveryTime - time of delivering one pizza.
   * @param capacity     - max number of pizza courier can take.
   * @param courierID    - courier's name or id.
   * @param workPlace    - Pizzeria where courier work.
   */
  public Courier(long deliveryTime, int capacity, String courierID, Pizzeria workPlace) {
    this.journal = workPlace.getJournal();
    this.courierID = courierID;
    this.wareHouse = workPlace.getWareHouse();
    this.capacity = capacity;
    this.time = deliveryTime;
  }
  Courier(long deliveryTime, int capacity, Journal journal, String courierID, BlockingQueue<Integer> wareHouse) {
    this.journal = journal;
    this.courierID = courierID;
    this.wareHouse = wareHouse;
    this.capacity = capacity;
    this.time = deliveryTime;
  }
  @Override
  public void run() {
    List<Integer> orders = new ArrayList<>();
    int i = 0;
    try {
      while (!Thread.interrupted()) {
        orders = new ArrayList<>();
        i = 0;
        Integer current;

        orders.add(wareHouse.take());

        while (orders.size() < capacity) {
          current = wareHouse.poll((long) (time * 0.15), TimeUnit.MILLISECONDS);
          if (current == null) break;
          orders.add(current);
        }

        journal.courierTakePizzas(courierID, orders.toArray());
        for (; i < orders.size(); ++i) {
          sleep(time);
          journal.courierFinishDelivering(courierID, orders.get(i));
        }
      }
    } catch (InterruptedException e) {
      for (; i < orders.size(); ++i)
        journal.orderDropped(orders.get(i));
      journal.courierGoHome(courierID);
      return;
    }
    journal.courierGoHome(courierID);
  }
}
