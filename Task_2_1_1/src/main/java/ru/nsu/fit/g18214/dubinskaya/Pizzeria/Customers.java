package ru.nsu.fit.g18214.dubinskaya.Pizzeria;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

import static java.lang.Thread.sleep;

/**
 * this class imitate clients in pizzeria. Clients are generate orders.
 */
public class Customers extends Staff {
    private BlockingQueue<Integer> orders;
    private Journal journal;
    private int from;
    private int to;
    private int counter = 1;
    private Thread thisThread;
    private Random random = new Random();

    Customers(int minTimeWaitingClient, int maxTimeWaitingClient, BlockingQueue<Integer> orders, Journal journal) {
      if (minTimeWaitingClient <= 0 || maxTimeWaitingClient < minTimeWaitingClient ) throw new IllegalArgumentException();
      if (orders == null || journal == null) throw new NullPointerException();
        this.orders = orders;
        this.journal = journal;
        this.from = minTimeWaitingClient;
        this.to = maxTimeWaitingClient;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                orders.put(counter);
            } catch (InterruptedException e) {
                return;
            }
            journal.newOrder(counter++);

            int sleepTime;
            sleepTime = random.nextInt(to - from) + from;
            try {
                sleep(sleepTime);
            } catch (InterruptedException ignored) {
                return;
            }
        }
    }
}
