package ru.nsu.fit.g18214.dubinskaya.Operations;

public class Cos implements Operation {
  public int getNumArg() {
    return 1;
  }
  public double calculateOperation(double ... args) {
    if (args.length != 1) throw new IllegalArgumentException();
    return (double)Math.cos(args[0]);
  }
}
