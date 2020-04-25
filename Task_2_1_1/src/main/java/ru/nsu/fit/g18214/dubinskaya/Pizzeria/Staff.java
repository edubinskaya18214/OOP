package ru.nsu.fit.g18214.dubinskaya.Pizzeria;


import com.sun.tools.javac.util.Assert;

/**
 * this class imitates customers visiting pizzeria.
 */
public abstract class Staff implements Runnable {

  private Thread thisThread;

  /**
   * This method start staff's work.
   */
  public void work() {
    thisThread = new Thread(this);
    thisThread.start();
  }

  /**
   * This method stop staff's Thread
   */
  public void stop() {
    thisThread.interrupt();
    try {
      thisThread.join();
    } catch (InterruptedException e) {
      assert false: "Something interrupt pizzeria";
    }
  }
}

