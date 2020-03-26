package ru.nsu.fit.g18214.dubinskaya.Pizzeria;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class CourierTests {
  @Test
  public void takeAndDeliveringOrder()throws InterruptedException {
    CountingJournal journal = new CountingJournal();
    BlockingQueue<Integer> wareHouse = new ArrayBlockingQueue<Integer>(5);
    Courier testedCourier = new Courier(200, 15,journal, "Oleg", wareHouse);
    wareHouse.add(15);
    testedCourier.work();
    Thread.sleep(300);
    testedCourier.stop();
    Assert.assertFalse(wareHouse.contains(15));
    Assert.assertEquals(1, journal.getDeliveredOrders());
  }

  @Test
  public void finishDeliveringOrdersAfterWorkDayEnd() throws InterruptedException {
    CountingJournal journal = new CountingJournal();
    BlockingQueue<Integer> wareHouse = new ArrayBlockingQueue<Integer>(5);
    Courier testedCourier = new Courier(200, 15,journal, "Oleg", wareHouse);
    wareHouse.add(15);
    wareHouse.add(25);
    wareHouse.add(41);
    wareHouse.add(500);
    testedCourier.work();
    Thread.sleep(50);
    testedCourier.stop();
    Assert.assertFalse(wareHouse.contains(15));
    Assert.assertFalse(wareHouse.contains(25));
    Assert.assertFalse(wareHouse.contains(41));
    Assert.assertFalse(wareHouse.contains(500));
    Assert.assertEquals(0, journal.getDeliveredOrders());
    Assert.assertEquals(4, journal.startDeliveringOrders);
  }
}
