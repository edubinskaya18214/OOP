package ru.nsu.fit.g18214.dubinskaya.Pizzeria;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * This class imitate work of pizzeria.
 */
public class Pizzeria implements Runnable {
  private double minWaitingClient;
  private double maxWaitingClient;
  private Map<String, Integer> cookingTime;
  private Map<String, Integer> deliveryTime;
  private int bagSize;
  private int workTime;
  private int queueSize;
  private int wareHouseSize;
  private BlockingQueue<Integer> orders;
  private BlockingQueue<Integer> wareHouse;
  private Journal journal;
  private boolean isWork = true;

  /**
   * this method create new pizzeria
   *
   * @param config - Pizzeria config that reading from Json File
   * @throws IllegalArgumentException - if config incorrect
   */
  public Pizzeria(PizzeriaConfig config) throws IllegalArgumentException {
    if (!config.isCorrect()) throw new IllegalArgumentException();
    this.workTime = config.getWorkTime();
    this.queueSize = config.getQueueSize();
    this.wareHouseSize = config.getWareHouseSize();
    this.minWaitingClient = config.getMinWaitingClient();
    this.maxWaitingClient = config.getMaxWaitingClient();
    this.cookingTime = config.getCookingTime();
    this.deliveryTime = config.getDeliveryTime();
    this.bagSize = config.getBagSize();
  }

  /**
   * this method start pizzeria's work.
   */
  public void run() {
    orders = new ArrayBlockingQueue<>(queueSize);
    wareHouse = new ArrayBlockingQueue<>(wareHouseSize);
    journal = new PrintJournal();
    ArrayList<Staff> cookers = new ArrayList<>();
    ArrayList<Staff> couriers = new ArrayList<>();
    Staff customers = null;
    try {
      Date workDayEnd = new Date();
      workDayEnd.setTime(workDayEnd.getTime() + workTime);

      for (Map.Entry<String, Integer> item : cookingTime.entrySet()) {
        Staff cook = new Cook((long) item.getValue(), item.getKey(), this);
        cookers.add(cook);
      }
      for (Map.Entry<String, Integer> item : deliveryTime.entrySet()) {
        Staff courier = new Courier((long) (item.getValue()), bagSize, item.getKey(), this);
        couriers.add(courier);
      }
      customers = new Customers((int) (minWaitingClient), (int) (maxWaitingClient), orders, journal);
      customers.work();
      cookers.forEach(Staff::work);
      couriers.forEach(Staff::work);
      if (workDayEnd.getTime() > (new Date()).getTime())
        Thread.sleep(workDayEnd.getTime() - (new Date()).getTime());
      System.out.println("----------- Work day end -----------");

      isWork = false;
      customers.stop();
      cookers.forEach(Staff::stop);
      couriers.forEach(Staff::stop);

      System.out.println("----------- All staff go home ----------- \n");
      System.out.printf(" \n%s\n", journal.toString());


    } catch (InterruptedException e) {
      System.out.println("stop child processes...\n");
      if (customers != null)
        customers.stop();
      cookers.forEach(Staff::stop);
      couriers.forEach(Staff::stop);
      System.out.println("all process stopped\n");
    }
  }

  BlockingQueue<Integer> getOrders() {
    return orders;
  }

  BlockingQueue<Integer> getWareHouse() {
    return wareHouse;
  }

  Journal getJournal() {
    return journal;
  }

  boolean isWork(){
    return isWork;
  }
}
