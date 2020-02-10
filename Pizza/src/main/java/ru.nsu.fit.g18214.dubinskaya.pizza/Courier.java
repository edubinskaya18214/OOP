package ru.nsu.fit.g18214.dubinskaya.pizza;
import java.util.Date;
import java.util.PriorityQueue;
import java.util.Queue;

import static java.lang.Thread.sleep;

public class Courier implements Runnable {
  private int capacity;
  private int time;
  private Date isWork;
  private Journal journal;
  private String courierID;
  private Table wareHouse;
  private Table orders;

  public Courier(int deliveryTime, int capacity, Journal journal, String courierID, Table orders, Table wareHouse, Date isWork) {
    this.journal = journal;
    this.courierID = courierID;
    this.wareHouse = wareHouse;
    this.capacity = capacity;
    this.time = deliveryTime;
    this.isWork = isWork;
    this.orders = orders;
  }

  @Override
  public synchronized void run() {
    Queue<Integer> orders = new PriorityQueue<>();
    while (!wareHouse.isFree() || isWork.after(new Date())) {
      if (! wareHouse.isFree()) {
        while (!wareHouse.isFree() && orders.size() < capacity) {
          orders.add(wareHouse.takeObjects());
        }
        // deliver orders
        journal.courierTakePizzas(courierID, orders.toArray());
        for (int i = 0; i < orders.size(); ) {
          try {
            sleep(time);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          journal.courierFinishDelivery(courierID, orders.remove());
        }
      }
      Thread.yield();
    }
    System.out.println(" END COURIER " + wareHouse.getUsedSpace());
    Thread.yield();
  }
}
