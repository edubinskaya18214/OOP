package ru.nsu.fit.g18214.dubinskaya.Pizzeria;

public abstract class Staff implements Runnable {

  private Thread thisThread;

  public void work() {
    thisThread = new Thread(this);
    thisThread.start();
  }

  public void stop(){
    thisThread.interrupt();
    try {
      thisThread.join();
    } catch (InterruptedException ignored){}
    if (thisThread.isAlive())
      System.out.println("Process isn't stop");
  }
}

