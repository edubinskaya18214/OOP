package ru.nsu.fit.g18214.dubinskaya.pizza;
import com.google.gson.Gson;
import java.util.Date;

public class Test {

  static void creationTreads(){
    Gson gson = new Gson();

    // 1. JSON file to Java object
    Object object = gson.fromJson(new FileReader("C:\\fileName.json"), Object.class);

    // 2. JSON string to Java object
    String json = "{'name' : 'mkyong'}";
    Object object = gson.fromJson(json, Staff.class);
  }


  public static void main(String[] args) throws InterruptedException {
    Table orders = new Table(8);
    Table ware = new Table(8);
    Journal j = new PrintJournal();
    Date endTime = new Date();
    endTime.setTime(endTime.getTime() + 15000);
    Thread cust = new Thread(new Customers(1000, 3000, orders, endTime, j));
    Thread c1 = new Thread(new Cook(1500, j, "Misha", orders, ware, endTime));
    Thread c2 = new Thread(new Cook(7000, j, "Leonid", orders, ware, endTime));
    Thread cour1 =  new Thread(new Courier(2500, 4, j, "Pavel",orders, ware, endTime));
    c1.start();
    c2.start();
    cust.start();
    cour1.start();
    while (endTime.after(new Date()))
      Thread.yield();
    System.out.println(" --------------- End of the day --------------- ");
  }

}
