package ru.nsu.fit.g18214.dubinskaya.pizza;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Class table is used in pizzeria. You can puts orders for cooks on table or for putting ready pizza.
 */
public class Table {

  private int capacity;
  private Queue<Integer> orders = new PriorityQueue<>();
  /**
   * create new class Table.
   * @param tableSize - maximum number of object on table.
   */
  public Table(int tableSize){
    this.capacity = tableSize;
  }

  /**
   * @return true if objects number on table less than table size.
   */
  public synchronized boolean isNotFull(){
    return orders.size() < capacity;
  }

  /**
   * @return true if objects number equals zero.
   */
  public synchronized boolean isFree(){
    return orders.size() == 0;
  }
  /**
   * @return number of objects on table.
   */
  public synchronized int getUsedSpace(){
    return orders.size();
  }

  /**
   * method increase objects.
   * @throws IndexOutOfBoundsException if you try to add pizza on full table.
   */
  public synchronized boolean addOneObject(int orderNumber) throws IndexOutOfBoundsException{
    if (!isNotFull()) return false;
    orders.add(orderNumber);
    return true;
  }

  /**
   * method decrease number of orders by one.
   * @return - number of taken orders.
   */
  public synchronized int takeObjects(){
    if (orders.size() == 0){
      return Integer.MIN_VALUE;
    }
    return orders.remove();
  }
}
