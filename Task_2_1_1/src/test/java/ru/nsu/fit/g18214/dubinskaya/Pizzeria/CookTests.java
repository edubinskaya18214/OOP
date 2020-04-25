package ru.nsu.fit.g18214.dubinskaya.Pizzeria;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class CookTests {
  //Test check that cook take order from orderBoard and then after cooking put it to the warehouse;
  @Test
  public void makeOrdersTests() throws InterruptedException {
    Journal journal = new CountingJournal();
    BlockingQueue<Integer> orders = new ArrayBlockingQueue<>(5);
    BlockingQueue<Integer> wareHouse = new ArrayBlockingQueue<>(5);
    Cook testedCook = new Cook(200, journal, "Oleg", orders, wareHouse);
    orders.add(15);
    testedCook.work();
    Thread.sleep(220);
    testedCook.stop();
    Assert.assertFalse(orders.contains(15));
    Assert.assertTrue(wareHouse.contains(15));
  }

  @Test
  public void finishCooking() throws InterruptedException {
    Journal journal = new CountingJournal();
    BlockingQueue<Integer> orders = new ArrayBlockingQueue<>(5);
    BlockingQueue<Integer> wareHouse = new ArrayBlockingQueue<>(5);
    Cook testedCook = new Cook(1000, journal, "Oleg", orders, wareHouse);
    orders.add(15);
    long timeBeforeCook = System.currentTimeMillis();
    testedCook.work();
    Thread.sleep(220);
    testedCook.stop();
    long timeAfterWorkDayEnd = System.currentTimeMillis();
    Assert.assertFalse(orders.contains(15));
    Assert.assertFalse(wareHouse.contains(15));
    long delta = timeAfterWorkDayEnd - timeBeforeCook;
    Assert.assertTrue(delta < 1000);
  }

  @Test
  public void stopWaitingAfterWorkDayEnd() throws InterruptedException {
    CountingJournal journal = new CountingJournal();
    BlockingQueue<Integer> orders = new ArrayBlockingQueue<>(5);
    BlockingQueue<Integer> wareHouse = new ArrayBlockingQueue<>(5);
    Cook testedCook = new Cook(1000, journal, "Oleg", orders, wareHouse);
    long timeBeforeCook = System.currentTimeMillis();
    testedCook.work();
    Thread.sleep(220);
    testedCook.stop();
    long timeAfterWorkDayEnd = System.currentTimeMillis();
    Assert.assertEquals(0, orders.size());
    Assert.assertEquals(0, wareHouse.size());
    Assert.assertEquals(0, journal.getTakenOrders());
    Assert.assertEquals(0, journal.getCookedOrders());
    Assert.assertTrue(timeAfterWorkDayEnd - timeBeforeCook < 300);
  }

  @Test
  public void stopWaitingToPutPizzaOnTheTable() throws InterruptedException {
    CountingJournal journal = new CountingJournal();
    BlockingQueue<Integer> orders = new ArrayBlockingQueue<>(5);
    BlockingQueue<Integer> wareHouse = new ArrayBlockingQueue<>(1);
    Cook testedCook = new Cook(1000, journal, "Oleg", orders, wareHouse);
    wareHouse.put(1);
    orders.put(2);
    testedCook.work();
    Thread.sleep(220);
    testedCook.stop();
    Assert.assertEquals(0, orders.size());
    Assert.assertEquals(1, wareHouse.size());
    Assert.assertEquals(1, journal.getTakenOrders());
    Assert.assertEquals(1, journal.getDroppedOrders());
  }

}
