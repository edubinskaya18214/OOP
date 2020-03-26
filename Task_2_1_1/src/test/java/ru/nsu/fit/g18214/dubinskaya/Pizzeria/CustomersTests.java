package ru.nsu.fit.g18214.dubinskaya.Pizzeria;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.*;

public class CustomersTests {

  @Test
  public void ordersGenerationTest() throws InterruptedException {
    CountingJournal journal = new CountingJournal();
    BlockingQueue<Integer> orders = new ArrayBlockingQueue<Integer>(5);
    Customers gen = new Customers(20, 50, orders, journal);
    gen.work();
    Thread.sleep(100);
    gen.stop();
    Assert.assertFalse(orders.size() < 2);
  }

  @Test
  public void ordersNumerationTest() throws InterruptedException {
    CountingJournal journal = new CountingJournal();
    BlockingQueue<Integer> orders = new ArrayBlockingQueue<>(5);
    Customers gen = new Customers(20, 50, orders, journal);
    gen.work();
    Thread.sleep(200);
    gen.stop();
    Assert.assertFalse(orders.size() < 200 / 50);
    for (int i = 1; orders.size() > 0; i++) {
      Assert.assertEquals((int) orders.take(), i);
    }
  }
}
