package ru.nsu.fit.g18214.dubinskaya.Pizzeria;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import static java.lang.Thread.sleep;

/**
 * this method imitates work of courier.
 */
public class Courier extends Staff {
  private int capacity;
  private long time;
  private Journal journal;
  private String courierID;
  private BlockingQueue<Integer> wareHouse;


  Courier(long deliveryTime, int capacity, String courierID, Pizzeria workPlace) {
    this(deliveryTime, capacity, workPlace.getJournal(), courierID, workPlace.getWareHouse());
  }

  Courier(long deliveryTime, int capacity, Journal journal, String id, BlockingQueue<Integer> wareHouse) {
    if (journal == null || wareHouse == null) {
      throw new NullPointerException();
    }
    if (id == null || id.equals("")) {
      throw new NullPointerException("json file has empty strings");
    }
    this.journal = journal;
    this.courierID = id;
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
          if (current == null) {
            break;
          }
          orders.add(current);
        }

        journal.courierTakePizzas(courierID, orders.toArray());
        for (; i < orders.size(); ++i) {
          sleep(time);
          journal.courierFinishDelivering(courierID, orders.get(i));
        }
      }

    } catch (InterruptedException e) {
      for (; i < orders.size(); ++i) {
        journal.orderDropped(orders.get(i));
      }
      journal.courierGoHome(courierID);
      return;
    }
    journal.courierGoHome(courierID);
  }
}
