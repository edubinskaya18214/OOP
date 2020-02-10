package ru.nsu.fit.g18214.dubinskaya.pizza;
import java.util.Date;

import static java.lang.Thread.sleep;

public class Customers implements Runnable {
  private Table orders;
  private Date isWork;
  private Journal journal;
  private int from;
  private int to;
  private int counter = 1;

  public Customers(int from, int to, Table orders, Date isWork, Journal journal) {
    this.orders = orders;
    this.isWork = isWork;
    this.journal = journal;
    this.from = from;
    this.to = to;
  }

  @Override
  public void run() {
    while (isWork.after(new Date())) {
      while (!orders.addOneObject(counter))
        Thread.yield();
      journal.newOrder(counter++);
      int a = from + (int) (Math.random() * (to - from));
      try {
        sleep(a);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
