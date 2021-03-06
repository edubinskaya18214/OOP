package ru.nsu.fit.g18214.dubinskaya.Pizzeria;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * This class imitate work of pizzeria.
 */
public class Pizzeria implements Runnable {
  private int minWaitingClient;
  private int maxWaitingClient;
  private Map<String, Integer> cookingTime;
  private Map<String, Integer> deliveryTime;
  private int bagSize;
  private int workTime;
  private int queueSize;
  private int wareHouseSize;
  private BlockingQueue<Integer> orders;
  private BlockingQueue<Integer> wareHouse;
  private Journal journal;

  /**
   * this method create new pizzeria
   *
   * @param config - Pizzeria config that reading from Json File
   * @throws IllegalArgumentException - if config incorrect
   */
  public Pizzeria(PizzeriaConfig config) throws IllegalArgumentException {
    if (!config.isCorrect()) {
      throw new IllegalArgumentException();
    }
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
    journal = new PrintableJournal();
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
      customers = new Customers(minWaitingClient, maxWaitingClient, orders, journal);
      customers.work();
      cookers.forEach(Staff::work);
      couriers.forEach(Staff::work);
      if (workDayEnd.getTime() > (new Date()).getTime()) {
        Thread.sleep(workDayEnd.getTime() - (new Date()).getTime());
      }
      System.out.println("----------- Work day end -----------");

      customers.stop();
      cookers.forEach(Staff::stop);
      couriers.forEach(Staff::stop);

      System.out.println("----------- All staff go home ----------- \n");
      System.out.printf(" \n%s\n", journal.toString());


    } catch (InterruptedException e) {
      System.out.println("process interrupted");
      stopChildProcesses(customers, cookers, couriers);
      Thread.currentThread().interrupt();
    } catch (NullPointerException e) {
      System.out.println("Something went wrong. Check your config file doesn't"
          + " have empty strings or try to use another config file");
      stopChildProcesses(customers, cookers, couriers);
    }
  }

  private void stopChildProcesses(Staff customers, List<Staff> cookers, List<Staff> couriers) {
    System.out.println("stop child processes...\n");
    if (customers != null) {
      customers.stop();
    }
    cookers.forEach(Staff::stop);
    couriers.forEach(Staff::stop);
    System.out.println("all process stopped\n");
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
}
