package ru.nsu.fit.g18214.dubinskaya.Operations;

public class Exp implements Operation {
  public int getNumArg() {
    return 2;
  }

  public double calculateOperation(double... args) {
    if (args.length != 2) throw new IllegalArgumentException();
    return (double)Math.pow(args[0], args[1]);
  }
}
