package ru.nsu.fit.g18214.dubinskaya;

class Multiplication implements Operation {
  public int getNumArg() {
    return 2;
  }
  public double calculateOperation(double ... args) {
    if (args.length != 2) throw new IllegalArgumentException();
    return args[1]*args[0];
  }
}
