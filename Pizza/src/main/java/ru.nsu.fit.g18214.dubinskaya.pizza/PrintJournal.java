package ru.nsu.fit.g18214.dubinskaya.pizza;
import java.util.Date;

public class PrintJournal implements Journal {
  @Override
  public synchronized void cookTakeOrder(String cookID, int order) {
    System.out.println("[" + new Date().toString() + "]: Cook " + cookID + " take order " + order + ".");
  }

  @Override
  public synchronized void cookFinishOrder(String cookID, int order) {
    System.out.println("[" + new Date().toString() + "]: Cook " + cookID + " finish making order " + order + ".");
  }

  @Override
  public synchronized void cookPutPizzaOnTable(String cookID, int order) {
    System.out.println("[" + new Date().toString() + "]: Cook " + cookID + " put " + order + " on the table.");
  }

  @Override
  public synchronized void courierTakePizzas(String courierID, Object[] order) {
    System.out.print("[" + new Date().toString() + "]: Courier " + courierID + " take ");
    for (Object o : order){
      System.out.print(o + " ");
    }
    System.out.println();
  }

  @Override
  public synchronized void courierFinishDelivery(String courierID, int order) {
    System.out.println("[" + new Date().toString() + "]: Courier " + courierID + " finish delivering order " + order + ".");
  }

  @Override
  public synchronized void newOrder(int order) {
    System.out.println("[" + new Date().toString() + "]: Order " + order + " was added to the list ");
  }
}
