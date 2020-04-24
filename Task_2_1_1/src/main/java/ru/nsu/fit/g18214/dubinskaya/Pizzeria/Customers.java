package ru.nsu.fit.g18214.dubinskaya.Pizzeria;

import java.util.Random;
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
  private Random random = new Random();

  Customers(int minWaitingTime, int maxWaitingTime, BlockingQueue<Integer> order, Journal journal) {
    if (minWaitingTime <= 0 || maxWaitingTime < minWaitingTime) {
      throw new IllegalArgumentException();
    }
    if (order == null || journal == null) {
      throw new NullPointerException();
    }
    this.orders = order;
    this.journal = journal;
    this.from = minWaitingTime;
    this.to = maxWaitingTime;
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

      try {
        sleep(random.nextInt(to - from) + from);
      } catch (InterruptedException ignored) {
        return;
      }
    }
  }
}
