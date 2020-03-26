package ru.nsu.fit.g18214.dubinskaya.Pizzeria;

import java.util.concurrent.BlockingQueue;

import static java.lang.Thread.sleep;

/**
 * this class imitate clients in pizzeria. Clients are generate orders.
 */
public class Customers extends Staff {
  private BlockingQueue<Integer> orders;
  private Journal journal;
  private int from;
  private int to;
  private int counter = 1;
  private Thread thisThread;

  public Customers(int from, int to, BlockingQueue<Integer> orders, Journal journal) {
    this.orders = orders;
    this.journal = journal;
    this.from = from;
    this.to = to;
  }

  @Override
  public void run() {
    while (!Thread.interrupted()) {
      try {
        orders.put(counter);
      } catch (InterruptedException e) {
        return;
      }
      journal.newOrder(counter++);
      int a;
      if (to != -1) a = from + (int) (Math.random() * (to - from));
      else a = from;
      try {
        sleep(a);
      } catch (InterruptedException ignored) {
        return;
      }
    }
  }
}
