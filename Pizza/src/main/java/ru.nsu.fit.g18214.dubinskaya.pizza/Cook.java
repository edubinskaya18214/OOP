package ru.nsu.fit.g18214.dubinskaya.pizza;
import java.util.Date;

import static java.lang.Thread.sleep;

/**
 * this class imitate work of cook.
 */
public class Cook implements Runnable{
  private long cookingTime;
  private Journal journal;
  private String cookID;
  private Table orders;
  private Table wareHouse;
  private Date isWork;
  /**
   * this method create new cook.
   * @param cookingTime - time that cook need to make pizza
   * @param journal - journal where cooker mark all his actions
   * @param cookID - cooker ID which need for journal
   * @param orders - table where cook will take orders for making pizza
   * @param wareHouse - table where cook will put pizza
   */
  public Cook (long cookingTime, Journal journal, String cookID, Table orders, Table wareHouse, Date isWork ){
    this.cookingTime = cookingTime;
    this.journal = journal;
    this.cookID = cookID;
    this.orders = orders;
    this.wareHouse = wareHouse;
    this.isWork = isWork;
  }
  @Override
  public void run() {
    int currentOrder;
    while(!orders.isFree() || isWork.after(new Date())) {
      // wait while cook can take order
      if ((currentOrder = orders.takeObjects()) != Integer.MIN_VALUE) {
        journal.cookTakeOrder(cookID, currentOrder);
        // start make pizza
        try {
          sleep(cookingTime);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }

        journal.cookFinishOrder(cookID, currentOrder);
        // wait while cook can put pizza on table
        while (!wareHouse.addOneObject(currentOrder))
          Thread.yield();
        journal.cookPutPizzaOnTable(cookID, currentOrder);
      }
      Thread.yield();
    }
    System.out.println("END " + cookID + orders.getUsedSpace());
  }
}
