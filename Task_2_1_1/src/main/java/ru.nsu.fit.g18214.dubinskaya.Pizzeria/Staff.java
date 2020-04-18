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
    } catch (InterruptedException e){
      if (thisThread.isAlive())
        System.out.printf("Can't stop process %d\n", thisThread.getId());
    }
  }
}

