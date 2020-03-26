package ru.nsu.fit.g18214.dubinskaya;

class Abs implements Operation {
  public int getNumArg() {
    return 1;
  }
  public double calculateOperation(double ... args) {
    if (args.length != 1) throw new IllegalArgumentException();
    return (double)Math.abs(args[0]);
  }
}
