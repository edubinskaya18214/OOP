package ru.nsu.fit.g18214.dubinskaya.Pizzeria;

import java.util.Date;

/**
 * This class is used to print messages about pizzeria's work.
 */
public class PrintableJournal implements Journal {
  private int takenOrders = 0;
  private int cookedOrders = 0;
  private int deliveredOrders = 0;

  /**
   * This method print message when cook take order.
   *
   * @param cookID - name or id of cook that take order.
   * @param order  - number of order.
   */
  @Override
  public synchronized void cookTakeOrder(String cookID, int order) {
    System.out.printf("[%s]: Order %d is being cooked by %s.\n", new Date().toString(), order, cookID);
  }

  /**
   * This method print message when cook take order.
   *
   * @param cookID - name or ID of cook that finish cooking order
   * @param order  - number of order.
   */
  @Override
  public synchronized void cookFinishOrder(String cookID, int order) {
    System.out.printf("[%s]: Order %d is finished by %s.\n", new Date().toString(), order, cookID);
    cookedOrders++;
  }


  /**
   * This method print message when cook put pizza on the table.
   *
   * @param cookID - cook's name or id.
   * @param order  - number of order.
   */
  @Override
  public synchronized void cookPutPizzaOnTable(String cookID, int order) {
    System.out.printf("[%s]: Order %d was stored by %s.\n", new Date().toString(), order, cookID);
  }

  /**
   * This method print message when courier take pizzas for delivering
   *
   * @param courierID - courier's name or id.
   * @param order     - id of delivering orders.
   */
  @Override
  public synchronized void courierTakePizzas(String courierID, Object[] order) {
    System.out.printf("[%s]: Courier %s take orders ", new Date().toString(), courierID);
    for (Object o : order) {
      System.out.print(o + " ");
    }
    System.out.println(".");
  }

  /**
   * This method print message when courier finish delivering of one of his orders.
   *
   * @param courierID - courier's name or id.
   * @param order     - id of delivering order.
   */
  @Override
  public synchronized void courierFinishDelivering(String courierID, int order) {
    System.out.printf("[%s]: Order %d was delivered by %s.\n", new Date().toString(), order, courierID);
    deliveredOrders++;
  }

  /**
   * This method print message when pizzeria have new order.
   *
   * @param order - order's id.
   */
  @Override
  public synchronized void newOrder(int order) {
    System.out.printf("[%s]: Order %d added to the list.\n", new Date().toString(), order);
    takenOrders++;
  }

  /**
   * this method print message when courier finish working.
   *
   * @param ID - courier'ss name or id.
   */
  @Override
  public synchronized void courierGoHome(String ID) {
    System.out.printf("[%s]: Courier %s finish working.\n", new Date(), ID);
  }

  /**
   * this method print message when cook finish working.
   *
   * @param ID - cook's name or id.
   */
  @Override
  public synchronized void cookGoHome(String ID) {
    System.out.printf("[%s]: Cook %s finish working.\n", new Date(), ID);
  }

  /**
   * This method is used when order was dropped.
   *
   * @param order - order's id.
   */
  @Override
  public synchronized void orderDropped(int order) {
    System.out.printf("[%s]: Order %d dropped.\n", new Date(), order);
  }

  /**
   * This method return statistic about the day.
   *
   * @return String with info about new orders, cooked and delivered orders;
   */
  @Override
  public synchronized String toString() {
    return String.format("     %d new orders today.\n     %d orders was cooked.\n     " +
            "%d orders was delivered.\n",
        takenOrders, cookedOrders, deliveredOrders);
  }
}
